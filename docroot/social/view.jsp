<%@page import="com.huihoo.social.model.Socialcontent"%>
<%@page import="java.util.List"%>
<%@page import="com.huihoo.social.service.SocialcontentLocalServiceUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@include file="/init.jsp" %>
<%
	int pageSize=30;
	int total=SocialcontentLocalServiceUtil.getService().getSocialCount(themeDisplay.getCompanyId());
	int currentPage = request.getAttribute("page") == null? 1: Integer.valueOf(request.getAttribute("page").toString()) ;
	int start=(currentPage-1)*pageSize;
	int end=start+pageSize;
	List<Socialcontent> contents = SocialcontentLocalServiceUtil.getService().getSocialContent(themeDisplay.getCompanyId(),start, end);	
	pageContext.setAttribute("total", total);	
	pageContext.setAttribute("contents", contents);	
%>
<liferay-ui:success key="contentSaved" message="content-saved-successfully" />
<div class="c_left">	
    <div class="cleft_top">
     <div class="tags1">
      <img src="<%= themeDisplay.getPathThemeImages()%>/temp/01.png">
      <div class="tj_title b g"><a href="#">创业公司是如何被不断新idea所摧毁？</a></div>
      <p class="lh21 g"><a href="#">在Chris Dixon谈何时放弃你的idea一文中，Chris Dixon曾强调务必留意不要被各种新鲜好玩的idea 给分了心：会伴随所有新东西出现的通常都是些"无知的乐观"  的虚假信号。那么为何我们说过多的新idea对创业公司不好呢？.行为的角度来说，有些在陌生人前囿于出口不怎么活跃的用户，在网络上倒是巧舌如簧游刃有余。</a></p>
     </div>
     <div class="slide">
      <span><a href="#"></a></span>
      <span><a href="#"></a></span>
      <span class="s_on"></span>
      <span><a href="#"></a></span>
     </div>
    </div>
    <c:if test="<%= ( themeDisplay.isSignedIn() ) %>">
    	
    	<%
    		List<Socialcontent> lastSocial=SocialcontentLocalServiceUtil.getService().getUserLastSocialContent(user.getUserId());
    		if(lastSocial!=null&&lastSocial.size()>0){
    			pageContext.setAttribute("lastSocial", lastSocial.get(0));
    		}
    	%>
    
    	<portlet:actionURL name="addContent" var="addContent"/>   
    	<aui:form action="<%= addContent.toString() %>" method="post" name="contentForm" id="contentForm">   
	    	<!--- 登录后微博输入框  开始 -->
		    <div class="cleft_mid">
		     <div class=" user_my"><img src="<%= HtmlUtil.escape(user.getPortraitURL(themeDisplay)) %>"  width="50px" height="50px"></div>
		     <div class="wb_input">
		      <div class="top_input">
		        <textarea class="inputstyle" name="content"></textarea>
		        <input class="" name="type" value="1" type="hidden"></textarea>
		        <ul>
		         <li class="bule"><a href="#">添加链接</a></li>
		         <li class="in_min">
		          <span class="ckbox"><input name="is_weibo" type="checkbox" value="1" <c:if test="${!empty(requestScope.isWeibo)}">checked='checked'</c:if> ></span>
		          <span class="ts_text">同步至新浪微博</span>
		          <span class="wbimg"><img src="<%= themeDisplay.getPathThemeImages() + "/sina_min.jpg" %>"></span>
		          </li>
		          <li><input type="submit" value="发布"/></li>
		        </ul>
		      </div>
		     <c:if test="${pageScope.lastSocial!=null}">
		      <div class="wb_lasttime">
		      	最近发布：${sessionScope.USER_ID}&nbsp;&nbsp;${pageScope.lastSocial.content}	      	
		      </div>
		     </c:if>
		     </div>
		     <div class="clear"></div>
		    </div>
		    <!--- 登录后微博输入框  结束 -->
	    </aui:form>
	    
    </c:if>
    
    <div class="cleft_b">
     <p class="status">有${total}条微博更新</p>
     <portlet:actionURL name="list" var="list"/>   
     <c:forEach items="${pageScope.contents}" var="c">
	     <div class="wblist">
	      <div class="user"><img src="${c.portraitUrl}" width="50px" height="50px"></div>
	      <div class="wbcontent"><span class="bule"><a href="#">${c.screenName}</a></span>&nbsp;&nbsp;&nbsp;&nbsp;${c.content}<span class="bule"></span>
	       <div class="wbdate"><fmt:formatDate value="${c.createdAt}" pattern="MM月dd日 HH:mm"/> 来自新浪微博 </div>
	      </div>
	     </div>
     </c:forEach>  
     
     <div class="page g">
      <ul>
  
       <li><a href="<%= list.toString() %>&page=1">上一页</a></li>
       <li><span class="number_on"><a href="<%= list.toString() %>&page=1">1</a></span></li>
       <li><span class="number"><a href="<%= list.toString() %>">2</a></span></li>
       <li><span class="number"><a href="<%= list.toString() %>">3</a></span></li>
       <li><span class="number"><a href="<%= list.toString() %>">4</a></span></li>
       <li><a href="#">下一页</a></li>
       <li><a href="#">最后</a></li>
      </ul>
     </div>
     <div class="clear"></div>
    </div>
   </div>