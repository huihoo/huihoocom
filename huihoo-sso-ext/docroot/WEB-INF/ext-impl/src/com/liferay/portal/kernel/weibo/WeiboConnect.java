package com.liferay.portal.kernel.weibo;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.portlet.PortletRequest;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;

public interface WeiboConnect {
	public JSONObject getAccessToken(long companyId, String redirect, String code)
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
