<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@page import="com.huihoo.social.service.SocialcontentLocalServiceUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.huihoo.social.model.Socialcontent"%>
<%@page import="java.util.List"%>
<%@include file="/init.jsp" %>
<liferay-ui:success key="socialContentDeleted" message="socialContentDeleted" />
<liferay-ui:error  key="error-deleting" message="error-deleting" />
<liferay-ui:search-container emptyResultsMessage="there-are-no-registrations" delta="5">
  <liferay-ui:search-container-results>
  <%
  List<Socialcontent> tempResults = SocialcontentLocalServiceUtil.getService().getSocialcontents(0, 30);
  results=ListUtil.subList(tempResults, searchContainer.getStart(), searchContainer.getEnd());;
  pageContext.setAttribute("results", results);
  pageContext.setAttribute("total", tempResults.size());
  %>
  </liferay-ui:search-container-results>
<portlet:actionURL name="clearCache" var="clearCache"/> 
<liferay-ui:success key="cache-refresh-successfully" message="cache-refresh-successfully" /> 
<a href="<%=clearCache%>">刷新内容缓存</a>
  <liferay-ui:search-container-row
      className="com.huihoo.social.model.Socialcontent"
      keyProperty="id"
      modelVar="socialContent">
 <liferay-ui:search-container-column-text
        name="id"
        property="id" />
    <liferay-ui:search-container-column-text
        name="user-name"
        property="screenName" />
    <liferay-ui:search-container-column-text
        name="content"
        property="content" />
    <liferay-ui:search-container-column-text
        name="created-at"
        property="createdAt" />
    <liferay-ui:search-container-column-jsp
    	path="/jsp/social/admin_actions.jsp"
    	align="right" />    
  </liferay-ui:search-container-row>

  <liferay-ui:search-iterator />

</liferay-ui:search-container>