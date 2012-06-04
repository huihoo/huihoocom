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

package com.liferay.portal.kernel.google;

import java.io.IOException;
import java.net.MalformedURLException;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;

import javax.portlet.PortletRequest;

/**
 * @author Rajesh
 */
public interface GoogleConnect {

	public String getAccessToken(long companyId, String redirect, String code)
			throws SystemException;

	public String getAccessTokenURL(long companyId) throws SystemException;

	public String getAppId(long companyId) throws SystemException;

	public String getAppSecret(long companyId) throws SystemException;

	public String getAuthURL(long companyId) throws SystemException;

	public JSONObject getGraphResources(long companyId, String path,
			String accessToken, String fields);

	public String getGraphURL(long companyId) throws SystemException;

	public String getProfileImageURL(PortletRequest portletRequest);

	public String getRedirectURL(long companyId) throws SystemException;

	public boolean isEnabled(long companyId) throws SystemException;

	public boolean isVerifiedAccountRequired(long companyId)
			throws SystemException;

	public String getProfileScope(long companyId) throws SystemException;

	public String getRevokeURL(long companyId) throws SystemException;

	public void revokeAccessToken(long companyId, String accessToken)
			throws SystemException;

	public byte[] getProfileImage(String pictureUrl) throws SystemException,
			MalformedURLException, IOException;

}