package com.liferay.portal.weibo;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.portlet.PortletRequest;

import org.apache.commons.io.IOUtils;

import com.liferay.portal.kernel.exception.SystemException;
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
import com.liferay.portal.kernel.weibo.WeiboConnect;

import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.WeiboConnectUtil;
import com.liferay.portal.util.WeiboPropsKeys;

public class WeiboConnectImpl implements WeiboConnect{
	private static Log _log = LogFactoryUtil.getLog(WeiboConnectImpl.class);

	@Override
	public JSONObject getAccessToken(long companyId, String redirect, String code) throws SystemException {
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
		queryBuilder.append(HttpUtil.encodeURL(WeiboConnectUtil.getRedirectURL(companyId)));

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

			return jsonObject ;
		} catch (Exception e) {
			throw new SystemException("Unable to retrieve Google access token",
					e);
		}
	}
	
	
	
	
	

	@Override
	public String getAccessTokenURL(long companyId) throws SystemException {
		return PrefsPropsUtil.getString(companyId,
				WeiboPropsKeys.WEIBO_CONNECT_OAUTH_TOKEN_URL);
	}

	@Override
	public String getAppId(long companyId) throws SystemException {
		return PrefsPropsUtil.getString(companyId,
				WeiboPropsKeys.WEIBO_CONNECT_APP_ID);
	}

	@Override
	public String getAppSecret(long companyId) throws SystemException {
		return PrefsPropsUtil.getString(companyId,
				WeiboPropsKeys.WEIBO_CONNECT_APP_SECRET);
	}

	@Override
	public String getAuthURL(long companyId) throws SystemException {
		return PrefsPropsUtil.getString(companyId,
				WeiboPropsKeys.WEIBO_CONNECT_OAUTH_AUTH_URL);
	}

	@Override
	public JSONObject getGraphResources(long companyId, String path, String accessToken, String fields) {
		try {
			String url = HttpUtil.addParameter(
					getGraphURL(companyId).concat(path), "access_token",
					accessToken);

			Http.Options options = new Http.Options();

			options.setLocation(url+"&uid="+fields);
			

			String json = HttpUtil.URLtoString(options);

			return JSONFactoryUtil.createJSONObject(json);

		} catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}
		return null;
	}

	@Override
	public String getGraphURL(long companyId) throws SystemException {
		return PrefsPropsUtil.getString(companyId,
				WeiboPropsKeys.WEIBO_CONNECT_GRAPH_URL);
	}

	@Override
	public String getProfileImageURL(PortletRequest portletRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRedirectURL(long companyId) throws SystemException {
		String redirectURL=PrefsPropsUtil.getString(companyId,
				WeiboPropsKeys.WEIBO_CONNECT_OAUTH_REDIRECT_URL);		
		return redirectURL;
	}

	@Override
	public boolean isEnabled(long companyId) throws SystemException {
		return PrefsPropsUtil.getBoolean(companyId,
				WeiboPropsKeys.WEIBO_CONNECT_AUTH_ENABLED);
	}

	@Override
	public boolean isVerifiedAccountRequired(long companyId) throws SystemException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getProfileScope(long companyId) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRevokeURL(long companyId) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void revokeAccessToken(long companyId, String accessToken) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] getProfileImage(String pictureUrl) throws SystemException, MalformedURLException, IOException {
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
	
}
