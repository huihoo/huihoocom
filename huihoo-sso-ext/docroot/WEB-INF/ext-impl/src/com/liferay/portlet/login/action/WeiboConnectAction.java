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
import com.liferay.portal.util.GoogleWebKeys;
import com.liferay.portal.util.WebKeys;
import com.liferay.portal.util.WeiboConnectUtil;
import com.liferay.portal.util.WeiboWebKeys;

public class WeiboConnectAction extends PortletAction{
	private static Log _log = LogFactoryUtil.getLog(WeiboConnectAction.class);
	
	
	@Override
	public ActionForward render(ActionMapping mapping, ActionForm form,
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest
				.getAttribute(WebKeys.THEME_DISPLAY);

		if (!WeiboConnectUtil.isEnabled(themeDisplay.getCompanyId())) {
			return null;
		}
		ActionForward af = mapping.findForward("portlet.login.weibo_login");
		System.out.println("WeiboConnectAction worked:"+af);
		return af ;
	}
	
	
	@Override
	public ActionForward strutsExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);

		if (!WeiboConnectUtil.isEnabled(themeDisplay.getCompanyId())) {
			return null;
		}

		HttpSession session = request.getSession();

		String redirect = ParamUtil.getString(request, "redirect",(String) session.getAttribute(WebKeys.REDIRECT));
		String code = ParamUtil.getString(request, "code");

		if (_log.isDebugEnabled())
			_log.debug("strutsExecute code=" + code + ", redirect=" + redirect);

		JSONObject tokenJson = WeiboConnectUtil.getAccessToken(themeDisplay.getCompanyId(), redirect, code) ;
		if(tokenJson==null ) 
			return null ;
		String token = tokenJson.getString("access_token");
		//String remindIn   = tokenJson.getString("remind_in");
		//String expiresIn   = tokenJson.getString("expires_in");
		String uid   = tokenJson.getString("uid");

		if (_log.isDebugEnabled())
			_log.debug("strutsExecute token =" + token);

		if (Validator.isNotNull(token)) {

			session.setAttribute(WeiboWebKeys.WEIBO_ACCESS_TOKEN, token);

			setWeiboCredentials(session, themeDisplay.getCompanyId(), token,uid);

		} else {
			return mapping.findForward(ActionConstants.COMMON_REFERER);
		}

		if (Validator.isNull(redirect)) {
			redirect = themeDisplay.getURLSignIn();
		}
		System.out.println("user weibo login redirect url is : "+redirect);
		response.sendRedirect(redirect);
		
		return null;
	}
	
	protected void setWeiboCredentials(HttpSession session, long companyId,
			String token,String uid) throws Exception {
		//获取用户信息   参考   http://open.weibo.com/wiki/2/users/show
		JSONObject jsonObject = WeiboConnectUtil.getGraphResources(companyId,"", token, uid);

		if (_log.isDebugEnabled())
			_log.debug("jsonObject=" + jsonObject.toString());

		if ((jsonObject == null) || (jsonObject.getJSONObject("error") != null)) {
			return;
		}

		if (WeiboConnectUtil.isVerifiedAccountRequired(companyId)
				&& !jsonObject.getBoolean("verified_email")) {
			return;
		}

		User user = null;

		long weiboId = jsonObject.getLong("id");

		if (weiboId > 0) {
			session.setAttribute(WeiboWebKeys.WEIBO_USER_ID,String.valueOf(weiboId));
		}
		//微博用户只能获取  用户的昵称(screenName)-且是唯一的
		String screenName = jsonObject.getString("screen_name");
		if ((user == null) && Validator.isNotNull(screenName)) {
			session.setAttribute(WeiboWebKeys.WEIBO_SCREEN_NAME,screenName);
			try {
				user = UserLocalServiceUtil.getUserByScreenName(companyId, screenName);
			} catch (NoSuchUserException nsue) {
			}
		}
		
		if(user!=null){
			updateUser(user, jsonObject);
		}else{			
			addUser(session, companyId, jsonObject);
		}
		
	}
	
	protected void addUser(HttpSession session, long companyId,
			JSONObject jsonObject) throws Exception {	
		
		String emailAddress = StringPool.BLANK ;
		String firstName = jsonObject.getString("name"); //the firstName must be required .  so let the name to be it.
		String lastName = StringPool.BLANK ;
		String pictureUrl = jsonObject.getString("profile_image_url");
		boolean male = Validator.equals(jsonObject.getString("gender"), "m");

		long creatorUserId = 0;
		boolean autoPassword = true;
		String password1 = StringPool.BLANK;
		String password2 = StringPool.BLANK;
		boolean autoScreenName = true;
		String screenName = jsonObject.getString("screen_name");
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
			byte[] image = WeiboConnectUtil.getProfileImage(pictureUrl);
			UserLocalServiceUtil.updatePortrait(user.getUserId(), image);
		} catch (Exception e) {
			_log.error(e.getMessage());
		}

		session.setAttribute(GoogleWebKeys.GOOGLE_USER_EMAIL_ADDRESS,emailAddress);
	}

	protected void updateUser(User user, JSONObject jsonObject)
			throws Exception {
		String screenName = jsonObject.getString("screen_name");
		String firstName = jsonObject.getString("name");
		String lastName =  StringPool.BLANK;
		String pictureUrl = jsonObject.getString("profile_image_url");
		boolean male = Validator.equals(jsonObject.getString("gender"), "m");

		if (screenName.equals(user.getScreenName())
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

		if (!screenName.equalsIgnoreCase(user.getScreenName())) {
			UserLocalServiceUtil.updateScreenName(user.getUserId(), screenName);
		}

		UserLocalServiceUtil.updateEmailAddressVerified(user.getUserId(), true);

		UserLocalServiceUtil.updateUser(user.getUserId(), StringPool.BLANK,
				StringPool.BLANK, StringPool.BLANK, false,
				user.getReminderQueryQuestion(), user.getReminderQueryAnswer(),
				screenName, user.getEmailAddress(), 0, user.getOpenId(),
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
			byte[] image = WeiboConnectUtil.getProfileImage(pictureUrl);
			UserLocalServiceUtil.updatePortrait(user.getUserId(), image);
		} catch (Exception e) {
			_log.error(e.getMessage());
		}
	}

}
