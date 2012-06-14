package com.huihoo.portlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.huihoo.social.model.Socialcontent;
import com.huihoo.social.model.impl.SocialcontentImpl;
import com.huihoo.social.service.SocialcontentLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.Http.Body;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

public class SocialContentPortlet extends MVCPortlet {

	public void addContent(ActionRequest request, ActionResponse response)
			throws Exception {
		String content = request.getParameter("content");
		if(Validator.isNull(content)||content.trim().length()<=0){
			SessionErrors.add(request, "content-must-required");
			return ;
		}
		ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);
		User user = themeDisplay.getUser();
		PortletPreferences prefs = request.getPreferences();
		String type = request.getParameter("type");
		
		String isWeibo = request.getParameter("is_weibo");
		request.setAttribute("isWeibo", isWeibo);
		HttpSession httpSession = PortalUtil.getHttpServletRequest(request)
				.getSession();
		if ("1".equals(isWeibo)) { // 同步发送至新浪微博
			String weiboAccessToken = (String) httpSession
					.getAttribute("LIFERAY_SHARED_WEIBO_ACCESS_TOKEN");
			if (weiboAccessToken != null) {
				String url = "https://api.weibo.com/2/statuses/update.json";
				StringBuilder queryBuilder = new StringBuilder();
				queryBuilder.append("access_token=");
				queryBuilder.append(weiboAccessToken);
				queryBuilder.append("&status=");
				queryBuilder.append(content);
				queryBuilder.append("&code=");
				if (_log.isDebugEnabled()) {
					_log.debug("queryBuilder=" + queryBuilder.toString());
				}
				Body body = new Body(queryBuilder.toString(),
						ContentTypes.APPLICATION_X_WWW_FORM_URLENCODED,
						StringPool.UTF8);
				Http.Options options = new Http.Options();
				options.setBody(body);
				options.setLocation(url);
				options.setPost(true);
				try {
					String weiboResponse = HttpUtil.URLtoString(options);
					if (_log.isDebugEnabled()) {
						_log.debug("weiboResponse=" + weiboResponse);
					}
					JSONObject jsonObject = JSONFactoryUtil
							.createJSONObject(weiboResponse);
					System.out.println(jsonObject);
				} catch (Exception e) {
					throw new SystemException("Unable to post weibo content", e);
				}
			}
		}

		Socialcontent socialcontent = new SocialcontentImpl();
		socialcontent.setScreenName(user.getFirstName());
		socialcontent.setPortraitUrl(user.getPortraitURL(themeDisplay));
		socialcontent.setContent(content);
		socialcontent.setCompanyId(user.getCompanyId());
		socialcontent.setGroupId(user.getGroupId());
		socialcontent.setType(Integer.valueOf(type));
		SocialcontentLocalServiceUtil.getService().addSocialContent(
				socialcontent, user.getUserId());
		SessionMessages.add(request, "contentSaved");
	}

	public void list(ActionRequest request, ActionResponse response)
			throws Exception {
		String pageNo = request.getParameter("page");		
		request.setAttribute("page", Integer.valueOf(pageNo));
		//response.setRenderParameter("jspPage", "/jsp/social/view.jsp");
	}

	public void deleteSocialContent(ActionRequest request,
			ActionResponse response) throws Exception {
		long resourceKey = ParamUtil.getLong(request, "resourcePrimKey");

		if (Validator.isNotNull(resourceKey)) {
			SocialcontentLocalServiceUtil.deleteSocialcontent(resourceKey);
			SessionMessages.add(request, "socialContentDeleted");
		} else {
			SessionErrors.add(request, "error-deleting");
		}
	}
	
	public void clearCache(ActionRequest request,
			ActionResponse response)throws Exception{
		HttpSession httpSession = PortalUtil.getHttpServletRequest(request).getSession();
		httpSession.getServletContext().removeAttribute("topItems");
		SessionMessages.add(request, "cache-refresh-successfully");
	}

	private static Log _log = LogFactory.getLog(SocialContentPortlet.class);
}
