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

import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WeiboConnectUtil;
import com.liferay.portal.util.WeiboWebKeys;

public class WeiboAutoLogin implements AutoLogin {
	private static Log _log = LogFactoryUtil.getLog(WeiboAutoLogin.class);

	@Override
	public String[] login(HttpServletRequest request, HttpServletResponse response) throws AutoLoginException {
		
		String[] credentials = null;

		try {
			long companyId = PortalUtil.getCompanyId(request);

			if (!WeiboConnectUtil.isEnabled(companyId)) {
				return credentials;
			}

			HttpSession session = request.getSession();

			String screenName = (String) session.getAttribute(WeiboWebKeys.WEIBO_SCREEN_NAME);

			User user = null;

			if (_log.isDebugEnabled())
				_log.debug("login - screenName=" + screenName);

			if (Validator.isNotNull(screenName)) {
				session.removeAttribute(WeiboWebKeys.WEIBO_SCREEN_NAME);
				try {
					user = UserLocalServiceUtil.getUserByScreenName(companyId, screenName);
				} catch (NoSuchUserException nsue) {
					_log.error("error : " + nsue.getMessage());
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

}
