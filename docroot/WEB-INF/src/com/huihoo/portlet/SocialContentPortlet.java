package com.huihoo.portlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.huihoo.social.model.Socialcontent;
import com.huihoo.social.model.impl.SocialcontentImpl;
import com.huihoo.social.service.SocialcontentLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.mvc.MVCPortlet;

public class SocialContentPortlet extends MVCPortlet {

	public void addContent(ActionRequest request, ActionResponse response)
			throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		User user = themeDisplay.getUser();
		System.out.println(user+":"+user.getFirstName()+" : "+user.getPortraitURL(themeDisplay)+"  ====  groupId:"+user.getGroupId());
		PortletPreferences prefs = request.getPreferences();
		String type = request.getParameter("type");
		String content = request.getParameter("content");
		String isWeibo = request.getParameter("is_weibo");
		Socialcontent socialcontent = new SocialcontentImpl();
		socialcontent.setScreenName(user.getFirstName());
		socialcontent.setPortraitUrl(user.getPortraitURL(themeDisplay));
		socialcontent.setContent(content);
		socialcontent.setCompanyId(user.getCompanyId());
		socialcontent.setGroupId(user.getGroupId());
		socialcontent.setType(Integer.valueOf(type));	
		SocialcontentLocalServiceUtil.getService().addSocialContent(socialcontent, user.getUserId());
		SessionMessages.add(request, "content-saved-successfully");
	}

	
	public void list(ActionRequest request,ActionResponse response) throws Exception{
		String pageNo=request.getParameter("page");		
		request.setAttribute("page", pageNo);
		response.setRenderParameter("jspPage", "/social/view.jsp");
	}
	
	
	
	



	private static Log _log = LogFactory.getLog(SocialContentPortlet.class);
}
