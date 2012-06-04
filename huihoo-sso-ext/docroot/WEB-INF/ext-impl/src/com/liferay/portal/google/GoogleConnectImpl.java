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

package com.liferay.portal.google;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.google.GoogleConnect;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.Http.Body;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.URLCodec;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.GoogleConnectUtil;
import com.liferay.portal.util.GooglePropsKeys;
import com.liferay.portal.util.GoogleWebKeys;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PrefsPropsUtil;

/**
 * @author Rajesh
 */
public class GoogleConnectImpl implements GoogleConnect {

	public String getAccessToken(long companyId, String redirect, String code)
			throws SystemException {

		String url = getAccessTokenURL(companyId);

		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("client_id=");
		queryBuilder.append(getAppId(companyId));
		queryBuilder.append("&client_secret=");
		queryBuilder.append(getAppSecret(companyId));
		queryBuilder.append("&code=");
		queryBuilder.append(URLCodec.encodeURL(code, StringPool.UTF8, true));
		queryBuilder.append("&grant_type=authorization_code");
		queryBuilder.append("&redirect_uri=");
		queryBuilder.append(HttpUtil.encodeURL(GoogleConnectUtil
				.getRedirectURL(companyId)));

		if (_log.isDebugEnabled()) {
			_log.debug("queryBuilder=" + queryBuilder.toString());
		}

		Body body = new Body(queryBuilder.toString(),
				ContentTypes.APPLICATION_X_WWW_FORM_URLENCODED, StringPool.UTF8);

		Http.Options options = new Http.Options();
		options.setBody(body);
		options.setLocation(url);
		options.setPost(true);

		try {
			String content = HttpUtil.URLtoString(options);
			if (_log.isDebugEnabled()) {
				_log.debug("content=" + content);
			}

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(content);

			return jsonObject.getString("access_token");
		} catch (Exception e) {
			throw new SystemException("Unable to retrieve Google access token",
					e);
		}
	}

	public void revokeAccessToken(long companyId, String accessToken)
			throws SystemException {
		// TODO
	}

	public String getAccessTokenURL(long companyId) throws SystemException {
		return PrefsPropsUtil.getString(companyId,
				GooglePropsKeys.GOOGLE_CONNECT_OAUTH_TOKEN_URL);
	}

	public String getAppId(long companyId) throws SystemException {
		return PrefsPropsUtil.getString(companyId,
				GooglePropsKeys.GOOGLE_CONNECT_APP_ID);
	}

	public String getAppSecret(long companyId) throws SystemException {
		return PrefsPropsUtil.getString(companyId,
				GooglePropsKeys.GOOGLE_CONNECT_APP_SECRET);
	}

	public String getAuthURL(long companyId) throws SystemException {
		return PrefsPropsUtil.getString(companyId,
				GooglePropsKeys.GOOGLE_CONNECT_OAUTH_AUTH_URL);
	}

	public JSONObject getGraphResources(long companyId, String path,
			String accessToken, String fields) {

		try {
			String url = HttpUtil.addParameter(
					getGraphURL(companyId).concat(path), "access_token",
					accessToken);

			Http.Options options = new Http.Options();

			options.setLocation(url);

			String json = HttpUtil.URLtoString(options);

			return JSONFactoryUtil.createJSONObject(json);

		} catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}

		return null;
	}

	public String getGraphURL(long companyId) throws SystemException {
		return PrefsPropsUtil.getString(companyId,
				GooglePropsKeys.GOOGLE_CONNECT_GRAPH_URL);
	}

	public String getProfileImageURL(PortletRequest portletRequest) {
		HttpServletRequest request = PortalUtil
				.getHttpServletRequest(portletRequest);

		request = PortalUtil.getOriginalServletRequest(request);

		HttpSession session = request.getSession();

		String googleId = (String) session
				.getAttribute(GoogleWebKeys.GOOGLE_USER_ID);

		if (Validator.isNull(googleId)) {
			return null;
		}

		long companyId = PortalUtil.getCompanyId(request);

		String token = (String) session
				.getAttribute(GoogleWebKeys.GOOGLE_ACCESS_TOKEN);

		JSONObject jsonObject = getGraphResources(companyId, "/me", token,
				"id,picture");

		return jsonObject.getString("picture");
	}

	public String getProfileScope(long companyId) throws SystemException {
		String[] stringArray = PrefsPropsUtil.getStringArray(companyId,
				GooglePropsKeys.GOOGLE_CONNECT_AUTH_SCOPE, StringPool.COMMA);
		if (stringArray != null) {
			try {
				String str1 = URLCodec.encodeURL(stringArray[0],
						StringPool.UTF8, true);
				String str2 = URLCodec.encodeURL(stringArray[1],
						StringPool.UTF8, true);
				return (str1 + "+" + str2);
			} catch (Exception e) {
				_log.error(e.getMessage());
			}
		}
		return null;
	}

	public byte[] getProfileImage(String pictureUrl) throws SystemException,
			IOException {
		InputStream imageStream = null;
		byte[] byteArray = null;
		try {
			URL url = new URL(pictureUrl);
			imageStream = url.openStream();
			byteArray = IOUtils.toByteArray(imageStream);
		} finally {
			try {
				if (imageStream != null)
					imageStream.close();
			} catch (Exception e) {
			}
		}
		return byteArray;
	}

	public String getRedirectURL(long companyId) throws SystemException {
		return PrefsPropsUtil.getString(companyId,
				GooglePropsKeys.GOOGLE_CONNECT_OAUTH_REDIRECT_URL);
	}

	public boolean isEnabled(long companyId) throws SystemException {
		return PrefsPropsUtil.getBoolean(companyId,
				GooglePropsKeys.GOOGLE_CONNECT_AUTH_ENABLED);
	}

	public boolean isVerifiedAccountRequired(long companyId)
			throws SystemException {

		return PrefsPropsUtil.getBoolean(companyId,
				GooglePropsKeys.GOOGLE_CONNECT_VERIFIED_ACCOUNT_REQUIRED);
	}

	public String getRevokeURL(long companyId) throws SystemException {
		return PrefsPropsUtil.getString(companyId,
				GooglePropsKeys.GOOGLE_CONNECT_AUTH_REVOKE);
	}

	private static Log _log = LogFactoryUtil.getLog(GoogleConnectImpl.class);

}