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


package com.liferay.portal.servlet.filters.sso;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.servlet.filters.BasePortalFilter;
import com.liferay.portal.util.GoogleConnectUtil;
import com.liferay.portal.util.GoogleWebKeys;
import com.liferay.portal.util.PortalUtil;

/**
 * @author Rajesh
 * 
 * TODO - to be plugged in linkedin logout filter
 * 
 */
public class LinkedInLogoutFilter extends BasePortalFilter {

	public static String LOGIN = LinkedInLogoutFilter.class.getName() + "LOGIN";

	protected Log getLog() {
		return log;
	}

	protected void processFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws SystemException, IOException {

//		long companyId = PortalUtil.getCompanyId(request);
//
//		if (GoogleConnectUtil.isEnabled(companyId)) {
//
//			HttpSession session = request.getSession();
//
//			String pathInfo = request.getPathInfo();
//
//			// log.debug("Path: " + pathInfo);
//
//			if (pathInfo.indexOf("/portal/logout") != -1
//					|| pathInfo.indexOf("/portal/expire_session") != -1) {
//
//				String accessToken = (String) request.getSession()
//						.getAttribute(GoogleWebKeys.GOOGLE_ACCESS_TOKEN);
//
//				if (Validator.isNotNull(accessToken)) {
//					GoogleConnectUtil.getRevokeURL(companyId);
//
//					GoogleConnectUtil.revokeAccessToken(companyId, accessToken);
//
//					session.invalidate();
//				}
//
//			}
//		}
//		try {
//			processFilter(LinkedInLogoutFilter.class, request, response,
//					filterChain);
//		} catch (Exception e) {
//			e.getMessage();
//		}
	}

	private static Log log = LogFactoryUtil.getLog(LinkedInLogoutFilter.class);

}