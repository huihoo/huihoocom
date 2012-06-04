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


package com.liferay.portal.security.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.GoogleConnectUtil;
import com.liferay.portal.util.GoogleWebKeys;
import com.liferay.portal.util.PortalUtil;

/**
 * @author Rajesh
 */
public class GoogleAutoLogin implements AutoLogin {

	public String[] login(HttpServletRequest request,
			HttpServletResponse response) {

		String[] credentials = null;

		try {
			long companyId = PortalUtil.getCompanyId(request);

			if (!GoogleConnectUtil.isEnabled(companyId)) {
				return credentials;
			}

			HttpSession session = request.getSession();

			String emailAddress = (String) session
					.getAttribute(GoogleWebKeys.GOOGLE_USER_EMAIL_ADDRESS);

			User user = null;

			if (_log.isDebugEnabled())
				_log.debug("login - emailAddress=" + emailAddress);

			if (Validator.isNotNull(emailAddress)) {
				session.removeAttribute(GoogleWebKeys.GOOGLE_USER_EMAIL_ADDRESS);
				try {
					user = UserLocalServiceUtil.getUserByEmailAddress(
							companyId, emailAddress);
				} catch (NoSuchUserException nsue) {
					_log.error("error" + nsue.getMessage());
					return credentials;
				}
			} else {
				return credentials;
			}

			credentials = new String[3];

			credentials[0] = String.valueOf(user.getUserId());
			credentials[1] = user.getPassword();
			credentials[2] = Boolean.FALSE.toString();

		} catch (Exception e) {
			_log.error(e, e);
		}

		return credentials;
	}

	private static Log _log = LogFactoryUtil.getLog(GoogleAutoLogin.class);

}