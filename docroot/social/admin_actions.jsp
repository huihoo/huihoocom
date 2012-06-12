<%@page import="com.huihoo.social.model.Socialcontent"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@include file="/init.jsp" %>

<%
ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
Socialcontent socialcontent = (Socialcontent) row.getObject();
long groupId = themeDisplay.getLayout().getGroupId();
String name = Socialcontent.class.getName();
String primKey = String.valueOf(socialcontent.getPrimaryKey());
%>

<liferay-ui:icon-menu>

  <!-- 
    <portlet:actionURL name="editProduct" var="editURL">
      <portlet:param name="resourcePrimKey" value="<%= primKey %>" />
    </portlet:actionURL>

    <liferay-ui:icon image="edit" url="<%= editURL.toString() %>" />
  
 -->
  
    <portlet:actionURL name="deleteSocialContent" var="deleteURL">
      <portlet:param name="resourcePrimKey" value="<%= primKey %>" />
    </portlet:actionURL>

    <liferay-ui:icon-delete url="<%= deleteURL.toString() %>" />
  

  
</liferay-ui:icon-menu>