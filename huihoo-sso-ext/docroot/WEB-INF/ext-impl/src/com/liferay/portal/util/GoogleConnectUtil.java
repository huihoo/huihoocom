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


package com.liferay.portal.util;

import java.io.IOException;

import javax.portlet.PortletRequest;

import com.liferay.portal.google.GoogleConnectImpl;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.google.GoogleConnect;
import com.liferay.portal.kernel.json.JSONObject;

/**
 * @author Rajesh
 */
public class GoogleConnectUtil {

	public static String getAccessToken(long companyId, String redirect,
			String code) throws SystemException {

		return getGoogleConnect().getAccessToken(companyId, redirect, code);
	}

	public static String getAccessTokenURL(long companyId)
			throws SystemException {

		return getGoogleConnect().getAccessTokenURL(companyId);
	}

	public static String getAppId(long companyId) throws SystemException {
		return getGoogleConnect().getAppId(companyId);
	}

	public static String getAppSecret(long companyId) throws SystemException {
		return getGoogleConnect().getAppSecret(companyId);
	}

	public static String getProfileScope(long companyId) throws SystemException {
		return getGoogleConnect().getProfileScope(companyId);
	}

	public static String getAuthURL(long companyId) throws SystemException {
		return getGoogleConnect().getAuthURL(companyId);
	}

	public static JSONObject getGraphResources(long companyId, String path,
			String accessToken, String fields) {

		return getGoogleConnect().getGraphResources(companyId, path,
				accessToken, fields);
	}

	public static String getGraphURL(long companyId) throws SystemException {
		return getGoogleConnect().getGraphURL(companyId);
	}

	public static String getProfileImageURL(PortletRequest portletRequest) {
		return getGoogleConnect().getProfileImageURL(portletRequest);
	}

	public static String getRedirectURL(long companyId) throws SystemException {
		return getGoogleConnect().getRedirectURL(companyId);
	}

	public static String getRevokeURL(long companyId) throws SystemException {
		return getGoogleConnect().getRevokeURL(companyId);
	}

	public static boolean isEnabled(long companyId) throws SystemException {
		return getGoogleConnect().isEnabled(companyId);
	}

	public static boolean isVerifiedAccountRequired(long companyId)
			throws SystemException {

		return getGoogleConnect().isVerifiedAccountRequired(companyId);
	}

	public static void revokeAccessToken(long companyId, String accessToken)
			throws SystemException {

		getGoogleConnect().revokeAccessToken(companyId, accessToken);
	}

	public static byte[] getProfileImage(String pictureUrl)
			throws SystemException, IOException {
		return getGoogleConnect().getProfileImage(pictureUrl);
	}

	public void setFacebookConnect(GoogleConnect googleConnect) {
		_googleConnect = googleConnect;
	}

	public static GoogleConnect getGoogleConnect() {

		if (_googleConnect == null) {
			_googleConnect = new GoogleConnectImpl();
		}

		return _googleConnect;
	}

	private GoogleConnectUtil() {

	}

	private static GoogleConnect _googleConnect;

}