/**
 * Copyright (C) Rotterdam Community Solutions B.V.
 * http://www.rotterdam-cs.com
 *
 ***********************************************************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */


package com.liferay.portlet.login.action;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroupRole;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.struts.ActionConstants;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.GoogleConnectUtil;
import com.liferay.portal.util.GoogleWebKeys;
import com.liferay.portal.util.WebKeys;

/**
 * @author Rajesh
 */
public class GoogleConnectAction extends PortletAction {

	private static Log _log = LogFactoryUtil.getLog(GoogleConnectAction.class);

	@Override
	public ActionForward render(ActionMapping mapping, ActionForm form,
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest
				.getAttribute(WebKeys.THEME_DISPLAY);

		if (!GoogleConnectUtil.isEnabled(themeDisplay.getCompanyId())) {
			return null;
		}
		ActionForward af = mapping.findForward("portlet.login.google_login");		
		return af;
	}

	@Override
	public ActionForward strutsExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

		if (!GoogleConnectUtil.isEnabled(themeDisplay.getCompanyId())) {
			return null;
		}

		HttpSession session = request.getSession();

		String redirect = ParamUtil.getString(request, "redirect",
				(String) session.getAttribute(WebKeys.REDIRECT));
		String code = ParamUtil.getString(request, "code");

		if (_log.isDebugEnabled())
			_log.debug("strutsExecute code=" + code + ", redirect=" + redirect);

		String token = GoogleConnectUtil.getAccessToken(
				themeDisplay.getCompanyId(), redirect, code);

		if (_log.isDebugEnabled())
			_log.debug("strutsExecute token =" + token);

		if (Validator.isNotNull(token)) {

			session.setAttribute(GoogleWebKeys.GOOGLE_ACCESS_TOKEN, token);

			setGoogleCredentials(session, themeDisplay.getCompanyId(), token);

		} else {
			return mapping.findForward(ActionConstants.COMMON_REFERER);
		}

		if (Validator.isNull(redirect)) {
			redirect = themeDisplay.getURLSignIn();
		}
		System.out.println("user google login redirect url is : "+redirect);
		response.sendRedirect(redirect);

		return null;
	}

	protected void setGoogleCredentials(HttpSession session, long companyId,
			String token) throws Exception {		
		JSONObject jsonObject = GoogleConnectUtil.getGraphResources(companyId,
				"", token, "");

		if (_log.isDebugEnabled())
			_log.debug("jsonObject=" + jsonObject.toString());

		if ((jsonObject == null) || (jsonObject.getJSONObject("error") != null)) {
			return;
		}

		if (GoogleConnectUtil.isVerifiedAccountRequired(companyId)
				&& !jsonObject.getBoolean("verified_email")) {
			return;
		}

		User user = null;

		long googleId = jsonObject.getLong("id");

		if (googleId > 0) {
			session.setAttribute(GoogleWebKeys.GOOGLE_USER_ID,
					String.valueOf(googleId));
		}

		String emailAddress = jsonObject.getString("email");
		if ((user == null) && Validator.isNotNull(emailAddress)) {
			session.setAttribute(GoogleWebKeys.GOOGLE_USER_EMAIL_ADDRESS,emailAddress);
			try {
				user = UserLocalServiceUtil.getUserByEmailAddress(companyId,
						emailAddress);
			} catch (NoSuchUserException nsue) {
			}
		}

		if (user != null) {
			updateUser(user, jsonObject);
		} else {
			addUser(session, companyId, jsonObject);
		}
	}

	protected void addUser(HttpSession session, long companyId,
			JSONObject jsonObject) throws Exception {		
		// long googleId = jsonObject.getLong("id");
		String emailAddress = jsonObject.getString("email");
		String firstName = jsonObject.getString("given_name");
		String lastName = jsonObject.getString("family_name");
		String pictureUrl = jsonObject.getString("picture");
		boolean male = Validator.equals(jsonObject.getString("gender"), "male");

		long creatorUserId = 0;
		boolean autoPassword = true;
		String password1 = StringPool.BLANK;
		String password2 = StringPool.BLANK;
		boolean autoScreenName = true;
		String screenName = StringPool.BLANK;
		long facebookId = 0;
		String openId = StringPool.BLANK;
		Locale locale = LocaleUtil.getDefault();
		String middleName = StringPool.BLANK;
		int prefixId = 0;
		int suffixId = 0;
		int birthdayMonth = Calendar.JANUARY;
		int birthdayDay = 1;
		int birthdayYear = 1970;
		String jobTitle = StringPool.BLANK;
		long[] groupIds = null;
		long[] organizationIds = null;
		long[] roleIds = null;
		long[] userGroupIds = null;
		boolean sendEmail = true;

		ServiceContext serviceContext = new ServiceContext();

		User user = UserLocalServiceUtil.addUser(creatorUserId, companyId,
				autoPassword, password1, password2, autoScreenName, screenName,
				emailAddress, facebookId, openId, locale, firstName,
				middleName, lastName, prefixId, suffixId, male, birthdayMonth,
				birthdayDay, birthdayYear, jobTitle, groupIds, organizationIds,
				roleIds, userGroupIds, sendEmail, serviceContext);

		UserLocalServiceUtil.updateLastLogin(user.getUserId(),user.getLoginIP());

		UserLocalServiceUtil.updatePasswordReset(user.getUserId(), true);

		UserLocalServiceUtil.updateEmailAddressVerified(user.getUserId(), true);
		
		try {
			byte[] image = GoogleConnectUtil.getProfileImage(pictureUrl);
			UserLocalServiceUtil.updatePortrait(user.getUserId(), image);
		} catch (Exception e) {
			_log.error(e.getMessage());
		}

		session.setAttribute(GoogleWebKeys.GOOGLE_USER_EMAIL_ADDRESS,emailAddress);
	}

	protected void updateUser(User user, JSONObject jsonObject)
			throws Exception {
		System.out.println("Executed....updateUser");
		String emailAddress = jsonObject.getString("email");
		String firstName = jsonObject.getString("given_name");
		String lastName = jsonObject.getString("family_name");
		String pictureUrl = jsonObject.getString("picture");
		boolean male = Validator.equals(jsonObject.getString("gender"), "male");

		if (emailAddress.equals(user.getEmailAddress())
				&& firstName.equals(user.getFirstName())
				&& lastName.equals(user.getLastName())
				&& (male == user.isMale())) {

			return;
		}

		Contact contact = user.getContact();

		Calendar birthdayCal = CalendarFactoryUtil.getCalendar();

		birthdayCal.setTime(contact.getBirthday());

		int birthdayMonth = birthdayCal.get(Calendar.MONTH);
		int birthdayDay = birthdayCal.get(Calendar.DAY_OF_MONTH);
		int birthdayYear = birthdayCal.get(Calendar.YEAR);

		long[] groupIds = null;
		long[] organizationIds = null;
		long[] roleIds = null;
		List<UserGroupRole> userGroupRoles = null;
		long[] userGroupIds = null;

		ServiceContext serviceContext = new ServiceContext();

		if (!emailAddress.equalsIgnoreCase(user.getEmailAddress())) {
			UserLocalServiceUtil.updateEmailAddress(user.getUserId(),
					StringPool.BLANK, emailAddress, emailAddress);
		}

		UserLocalServiceUtil.updateEmailAddressVerified(user.getUserId(), true);

		UserLocalServiceUtil.updateUser(user.getUserId(), StringPool.BLANK,
				StringPool.BLANK, StringPool.BLANK, false,
				user.getReminderQueryQuestion(), user.getReminderQueryAnswer(),
				user.getScreenName(), emailAddress, 0, user.getOpenId(),
				user.getLanguageId(), user.getTimeZoneId(), user.getGreeting(),
				user.getComments(), firstName, user.getMiddleName(), lastName,
				contact.getPrefixId(), contact.getSuffixId(), male,
				birthdayMonth, birthdayDay, birthdayYear, contact.getSmsSn(),
				contact.getAimSn(), contact.getFacebookSn(),
				contact.getIcqSn(), contact.getJabberSn(), contact.getMsnSn(),
				contact.getMySpaceSn(), contact.getSkypeSn(),
				contact.getTwitterSn(), contact.getYmSn(),
				contact.getJobTitle(), groupIds, organizationIds, roleIds,
				userGroupRoles, userGroupIds, serviceContext);
		
		try {
			byte[] image = GoogleConnectUtil.getProfileImage(pictureUrl);
			UserLocalServiceUtil.updatePortrait(user.getUserId(), image);
		} catch (Exception e) {
			_log.error(e.getMessage());
		}
	}

}