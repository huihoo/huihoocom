<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@page import="com.liferay.portal.kernel.util.StringPool"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.liferay.portal.kernel.util.HttpUtil"%>
<%@page import="com.liferay.portal.util.GoogleConnectUtil"%>
<%@page import="com.liferay.portal.util.WeiboConnectUtil"%>

<%@ include file="/html/portlet/login/init.jsp" %>

<%
String strutsAction = ParamUtil.getString(request, "struts_action");

boolean showAnonymousIcon = false;

if (!strutsAction.startsWith("/login/create_anonymous_account") && portletName.equals(PortletKeys.FAST_LOGIN)) {
	showAnonymousIcon = true;
}

boolean showCreateAccountIcon = false;

if (!strutsAction.equals("/login/create_account") && company.isStrangers() && !portletName.equals(PortletKeys.FAST_LOGIN)) {
	showCreateAccountIcon = true;
}

boolean showFacebookConnectIcon = false;

if (!strutsAction.startsWith("/login/facebook_connect") && FacebookConnectUtil.isEnabled(company.getCompanyId())) {
	showFacebookConnectIcon = true;
}

boolean showGoogleConnectIcon = false;

if (!strutsAction.startsWith("/login/facebook_connect") && GoogleConnectUtil.isEnabled(company.getCompanyId())) {
	showGoogleConnectIcon = true;
}


boolean showWeiboConnectIcon = true;

if (!strutsAction.startsWith("/login/facebook_connect") && WeiboConnectUtil.isEnabled(company.getCompanyId())) {
	showWeiboConnectIcon = true;
}


boolean showForgotPasswordIcon = false;

if (!strutsAction.equals("/login/forgot_password") && (company.isSendPassword() || company.isSendPasswordResetLink())) {
	showForgotPasswordIcon = true;
}

boolean showOpenIdIcon = false;

if (!strutsAction.equals("/login/open_id") && OpenIdUtil.isEnabled(company.getCompanyId())) {
	showOpenIdIcon = true;
}

boolean showSignInIcon = false;

if (Validator.isNotNull(strutsAction) && !strutsAction.equals("/login/login")) {
	showSignInIcon = true;
}
%>

<c:if test="<%= showAnonymousIcon || showCreateAccountIcon || showForgotPasswordIcon || showOpenIdIcon || showSignInIcon %>">
	<div class="navigation">
		<liferay-ui:icon-list>
			<c:if test="<%= showAnonymousIcon %>">
				<portlet:renderURL var="anonymousURL">
					<portlet:param name="struts_action" value="/login/create_anonymous_account" />
				</portlet:renderURL>

				<liferay-ui:icon
					message="guest"
					src='<%= themeDisplay.getPathThemeImages() + "/common/user_icon.png" %>'
					url="<%= anonymousURL %>"
				/>
			</c:if>

			<c:if test="<%= showSignInIcon %>">

				<%
				String signInURL = themeDisplay.getURLSignIn();

				if (portletName.equals(PortletKeys.FAST_LOGIN)) {
					signInURL = HttpUtil.addParameter(signInURL, "windowState", LiferayWindowState.POP_UP.toString());
				}
				%>

				<liferay-ui:icon
					image="status_online"
					message="sign-in"
					url="<%= signInURL %>"
				/>
			</c:if>

			<c:if test="<%= showFacebookConnectIcon %>">
				<portlet:renderURL var="loginRedirectURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
					<portlet:param name="struts_action" value="/login/login_redirect" />
				</portlet:renderURL>

				<%
				String facebookAuthRedirectURL = FacebookConnectUtil.getRedirectURL(themeDisplay.getCompanyId());
				facebookAuthRedirectURL = HttpUtil.addParameter(facebookAuthRedirectURL, "redirect", HttpUtil.encodeURL(loginRedirectURL.toString()));

				String facebookAuthURL = FacebookConnectUtil.getAuthURL(themeDisplay.getCompanyId());
				facebookAuthURL = HttpUtil.addParameter(facebookAuthURL, "client_id", FacebookConnectUtil.getAppId(themeDisplay.getCompanyId()));
				facebookAuthURL = HttpUtil.addParameter(facebookAuthURL, "redirect_uri", facebookAuthRedirectURL);
				facebookAuthURL = HttpUtil.addParameter(facebookAuthURL, "scope", "email");

				String taglibOpenFacebookConnectLoginWindow = "javascript:var facebookConnectLoginWindow = window.open('" + facebookAuthURL.toString() + "','facebook', 'align=center,directories=no,height=560,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=1000'); void(''); facebookConnectLoginWindow.focus();";
				%>

				<liferay-ui:icon
					image="../social_bookmarks/facebook"
					message="facebook"
					url="<%= taglibOpenFacebookConnectLoginWindow %>"
				/>
			</c:if>
			
			<c:if test="<%= showGoogleConnectIcon %>">
				<portlet:renderURL var="loginRedirectURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
					<portlet:param name="struts_action" value="/login/login_redirect" />
				</portlet:renderURL>

				<%
				String googleAuthRedirectURL = GoogleConnectUtil.getRedirectURL(themeDisplay.getCompanyId());
				//googleAuthRedirectURL = HttpUtil.addParameter(googleAuthRedirectURL, "redirect", HttpUtil.encodeURL(loginRedirectURL.toString()));

				session.setAttribute(WebKeys.REDIRECT, loginRedirectURL.toString());
				
				String googleAuthURL = GoogleConnectUtil.getAuthURL(themeDisplay.getCompanyId());
				googleAuthURL = HttpUtil.addParameter(googleAuthURL, "client_id", GoogleConnectUtil.getAppId(themeDisplay.getCompanyId()));
				googleAuthURL = HttpUtil.addParameter(googleAuthURL, "redirect_uri", HttpUtil.encodeURL(googleAuthRedirectURL));
				googleAuthURL = HttpUtil.addParameter(googleAuthURL, "response_type", "code");
				googleAuthURL = HttpUtil.addParameter(googleAuthURL, "scope", GoogleConnectUtil.getProfileScope(themeDisplay.getCompanyId()));
				
				String taglibOpenFacebookConnectLoginWindow = "javascript:var googleConnectLoginWindow = window.open('" + googleAuthURL + "','facebook', 'align=center,directories=no,height=560,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=1000'); void(''); facebookConnectLoginWindow.focus();";
				%>

				<liferay-ui:icon
					image="../social_bookmarks/google-icon"
					message="Google"
					url="<%= taglibOpenFacebookConnectLoginWindow %>"
				/>
			</c:if>
			
			<c:if test="<%= showWeiboConnectIcon %>">
				<portlet:renderURL var="loginRedirectURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
					<portlet:param name="struts_action" value="/login/login_redirect" />
				</portlet:renderURL>

				<%
				String weiboAuthRedirectURL = WeiboConnectUtil.getRedirectURL(themeDisplay.getCompanyId());
				//googleAuthRedirectURL = HttpUtil.addParameter(googleAuthRedirectURL, "redirect", HttpUtil.encodeURL(loginRedirectURL.toString()));

				session.setAttribute(WebKeys.REDIRECT, loginRedirectURL.toString());
				
				String weiboAuthURL = WeiboConnectUtil.getAuthURL(themeDisplay.getCompanyId());
				weiboAuthURL = HttpUtil.addParameter(weiboAuthURL, "client_id", WeiboConnectUtil.getAppId(themeDisplay.getCompanyId()));
				weiboAuthURL = HttpUtil.addParameter(weiboAuthURL, "redirect_uri", HttpUtil.encodeURL(weiboAuthRedirectURL));
				weiboAuthURL = HttpUtil.addParameter(weiboAuthURL, "response_type", "code");
				//weiboAuthURL = HttpUtil.addParameter(weiboAuthURL, "scope", WeiboConnectUtil.getProfileScope(themeDisplay.getCompanyId()));
				
				String taglibOpenFacebookConnectLoginWindow = "javascript:var weiboConnectLoginWindow = window.open('" + weiboAuthURL + "','facebook', 'align=center,directories=no,height=560,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=1000'); void(''); facebookConnectLoginWindow.focus();";
				%>

				<liferay-ui:icon
					src='<%= themeDisplay.getPathThemeImages() + "/common/ico_sina.gif" %>'
					message="Weibo"
					url="<%= taglibOpenFacebookConnectLoginWindow %>"
				/>
			</c:if>

			<c:if test="<%= showOpenIdIcon %>">
				<portlet:renderURL var="openIdURL">
					<portlet:param name="struts_action" value="/login/open_id" />
				</portlet:renderURL>

				<liferay-ui:icon
					message="open-id"
					src='<%= themeDisplay.getPathThemeImages() + "/common/openid.gif" %>'
					url="<%= openIdURL %>"
				/>
			</c:if>

			<c:if test="<%= showCreateAccountIcon %>">
				<liferay-ui:icon
					image="add_user"
					message="create-account"
					url="<%= PortalUtil.getCreateAccountURL(request, themeDisplay) %>"
				/>
			</c:if>

			<c:if test="<%= showForgotPasswordIcon %>">
				<portlet:renderURL var="forgotPasswordURL">
					<portlet:param name="struts_action" value="/login/forgot_password" />
				</portlet:renderURL>

				<liferay-ui:icon
					image="help"
					message="forgot-password"
					url="<%= forgotPasswordURL %>"
				/>
			</c:if>
		</liferay-ui:icon-list>
	</div>
</c:if>