package com.liferay.portal.util;

import java.io.IOException;

import javax.portlet.PortletRequest;

import com.liferay.portal.kernel.exception.SystemException;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.weibo.WeiboConnect;
import com.liferay.portal.weibo.WeiboConnectImpl;

public class WeiboConnectUtil {
	
	
	public static JSONObject getAccessToken(long companyId, String redirect,
			String code) throws SystemException {

		return getWeiboConnect().getAccessToken(companyId, redirect, code);
	}
	
	

	public static String getAccessTokenURL(long companyId)
			throws SystemException {

		return getWeiboConnect().getAccessTokenURL(companyId);
	}

	public static String getAppId(long companyId) throws SystemException {
		return getWeiboConnect().getAppId(companyId);
	}

	public static String getAppSecret(long companyId) throws SystemException {
		return getWeiboConnect().getAppSecret(companyId);
	}

	public static String getProfileScope(long companyId) throws SystemException {
		return getWeiboConnect().getProfileScope(companyId);
	}

	public static String getAuthURL(long companyId) throws SystemException {
		return getWeiboConnect().getAuthURL(companyId);
	}

	public static JSONObject getGraphResources(long companyId, String path,
			String accessToken, String fields) {

		return getWeiboConnect().getGraphResources(companyId, path,
				accessToken, fields);
	}

	public static String getGraphURL(long companyId) throws SystemException {
		return getWeiboConnect().getGraphURL(companyId);
	}

	public static String getProfileImageURL(PortletRequest portletRequest) {
		return getWeiboConnect().getProfileImageURL(portletRequest);
	}

	public static String getRedirectURL(long companyId) throws SystemException {
		return getWeiboConnect().getRedirectURL(companyId);
	}

	public static String getRevokeURL(long companyId) throws SystemException {
		return getWeiboConnect().getRevokeURL(companyId);
	}

	public static boolean isEnabled(long companyId) throws SystemException {
		return getWeiboConnect().isEnabled(companyId);
	}

	public static boolean isVerifiedAccountRequired(long companyId)
			throws SystemException {

		return getWeiboConnect().isVerifiedAccountRequired(companyId);
	}

	public static void revokeAccessToken(long companyId, String accessToken)
			throws SystemException {

		getWeiboConnect().revokeAccessToken(companyId, accessToken);
	}

	public static byte[] getProfileImage(String pictureUrl)
			throws SystemException, IOException {
		return getWeiboConnect().getProfileImage(pictureUrl);
	}

	public void setFacebookConnect(WeiboConnect weiboConnect) {
		_weiboConnect = weiboConnect;
	}
	
	private static WeiboConnect _weiboConnect;

	private WeiboConnectUtil() {

	}

	public static WeiboConnect getWeiboConnect() {
		if (_weiboConnect == null)
			_weiboConnect = new WeiboConnectImpl();
		return _weiboConnect;
	}
}
