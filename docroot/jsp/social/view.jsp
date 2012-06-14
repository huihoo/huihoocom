<%@page import="org.jsoup.nodes.Document"%>
<%@page import="org.jsoup.Jsoup"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.horrabin.horrorss.RssItemBean"%>
<%@page import="org.horrabin.horrorss.RssImageBean"%>
<%@page import="org.horrabin.horrorss.RssChannelBean"%>
<%@page import="org.horrabin.horrorss.RssFeed"%>
<%@page import="org.horrabin.horrorss.RssParser"%>
<%@page import="com.liferay.util.RSSUtil"%>
<%@page import="com.huihoo.social.model.Socialcontent"%>
<%@page import="java.util.List"%>
<%@page import="com.huihoo.social.service.SocialcontentLocalServiceUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@include file="/init.jsp" %>
<%
	int pageSize=20;
	int total=SocialcontentLocalServiceUtil.getService().getSocialCount(themeDisplay.getCompanyId());
	int pageTotal=(int)Math.ceil((double)total/pageSize) ;
	int currentPage = request.getAttribute("page") == null? 1: Integer.valueOf(request.getAttribute("page").toString()) ;
	int start=(currentPage-1)*pageSize;
	int end=start+pageSize;
	List<Socialcontent> contents = SocialcontentLocalServiceUtil.getService().getSocialContent(themeDisplay.getCompanyId(),start, end);	
	pageContext.setAttribute("total", total);	
	pageContext.setAttribute("currentPage", currentPage);	
	pageContext.setAttribute("contents", contents);	
	pageContext.setAttribute("pageTotal",pageTotal);
	
	try{
		List<RssItemBean> topItems = (List<RssItemBean>) application.getAttribute("topItems");		
		if(topItems==null){
			 topItems = new ArrayList<RssItemBean>();
			RssParser rssParser = new RssParser();
	        RssFeed feed = rssParser.load("http://blog.huihoo.com/?feed=rss2");        
	        // Gets the channel information of the feed and 
	        // display its title
	        RssChannelBean channel = feed.getChannel();         
	        // Gets the image of the feed and display the image URL
	        RssImageBean image = feed.getImage();           
	        // Gets and iterate the items of the feed 
	        
	        List<RssItemBean> items = feed.getItems();
	        
	        for (int i=0; i<items.size(); i++){
	             RssItemBean item = items.get(i); 
	             if(i<4){
	            	 Document d = Jsoup.parse(item.getDescription()); 
	            	 String text=d.text();
	            	 if(text!=null&&text.length()>255)
	            	 	item.setDescription(text.substring(0,255));
	            	 else
	            		 item.setDescription(text);
	            	 topItems.add(item);                   
	             }
	        }     
	        application.setAttribute("topItems", topItems);
		}
	}catch(Exception e){
	    // Something to do if an exception occurs
	}
%>
<liferay-ui:success key="contentSaved" message="content-saved-successfully" />
<liferay-ui:error key="content-must-required" message="content-must-required"/>

<div class="c_left">
	<c:if test="${applicationScope.topItems!=null}">
		<div class="cleft_top">
	     <c:forEach items="${applicationScope.topItems}" var="t" varStatus="i">
		     <div class="tags1" id="tags_${i.index+1}" style="<c:if test='${i.index>0}'>display: none;</c:if>">
		      <img src="<%= themeDisplay.getPathThemeImages()%>/temp/01.png">
		      <div class="tj_title b g"><a href="${t.link}" title="${t.title}" target="_blank">${t.title}</a></div>
		      <p class="lh21 g"><a href="${t.link}" title="${t.description}" target="_blank">${t.description}</a></p>
		     </div>
	     </c:forEach>     
	     <div class="slide">
	      <span class="s_on"  id="s1"></span>
	      <span id="s2"><a href="javascript:;"></a></span>
	      <span id="s3"><a href="javascript:;"></a></span>
	      <span id="s4"><a href="javascript:;"></a></span>
	     </div>
	    </div>
	    <script>
		    function simpleSlide(obj){
		    	$("#s1,#s2,#s3,#s4").each(function(){
					$(this).removeClass("s_on");
					$(this).html("<a href='javascript:;'></a>");
				});
				$(obj).addClass("s_on");
				$(obj).find("a").first().remove();
				var idDex=$(obj).attr("id").substring(1);
				var divTags=$("#tags_"+idDex);
				$(".tags1").each(function(){
					$(this).hide();
				});
				$(divTags).show();
		    }
	    	$(function(){
	    		$("#s1,#s2,#s3,#s4").hover(function(){
	    			simpleSlide(this);	    			
	    			intervalTimes=$(this).attr("id").substring(1)-1;
	    		});
	    	});
	    	var intervalTimes = 1;	    	
	    	function intervalSlide () {	    	
	    	    intervalTimes++;	
	    	    simpleSlide($("#s"+intervalTimes));
	    	    if (intervalTimes >= 4) {	    	
	    	    	intervalTimes=0;	    	
	    	    }	    	
	    	}	    	
	    	var interv = setInterval(intervalSlide, 3000);
	    </script>
	</c:if>	    
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
  	  <c:if test="${pageScope.pageTotal>1}">
	  	  <c:if test="${currentPage>1}">
	  	  	<li><a href="<%= list.toString() %>&page=${currentPage-1}">上一页</a></li>
	  	  </c:if>	  	  
	  	  <c:forEach begin="1" end="${pageScope.pageTotal}" var="c">
	  	  	<c:if test="${pageScope.currentPage==c}">
	  	  		<li><span class="number_on"><a href="<%= list.toString() %>&page=${c}">${c}</a></span></li>
	  	  	</c:if>
	  	  	
	  	  	<c:if test="${pageScope.currentPage!=c}">
	  	  		<li><span class=""><a href="<%= list.toString() %>&page=${c}">${c}</a></span></li>
	  	  	</c:if>
	  	  	
	  	  </c:forEach>
	  	   <c:if test="${currentPage<pageScope.pageTotal}">
	       	<li><a href="<%= list.toString() %>&page=${currentPage+1}">下一页</a></li>
	       </c:if>
	       <c:if test="${currentPage!=pageScope.pageTotal}">
	       	<li><a href="<%= list.toString() %>&page=${pageScope.pageTotal}">最后</a></li>
	       </c:if>
  	  </c:if>       
      </ul>
     </div>
     <div class="clear"></div>
    </div>
   </div>
<style>
.main_c{ width:1003px; margin:0 auto; overflow:hidden; font-size:14px;padding:12px 0 20px 0;}
.clear{ clear:both; line-height:0; font-size:0; height:10px; margin:0; _height:0px; }
.lh21{ line-height:21px;}
.c_left{ float:left; width:702px; margin-right:0px;background:url(/huihoo-2012-theme/images/bg_cleft.png) repeat-y; padding-right:3px;  border-bottom:1px solid #d8dbdc;_margin-right:8px;}
.cleft_top{ height:153px; background-color:#f8f8f8; border-top:1px solid #d8dbdc;  border-left:1px solid #d8dbdc;padding:30px; _padding-bottom:10px;}
.tags1 img{ float:left; margin-right:20px; width:107px; height:124px; overflow:hidden; display:block;}
.tj_title{ font-size:26px; font-family:"微软雅黑"; color:#5d5d5d; }
.cleft_top p{ height:90px; overflow:hidden;}
.slide{ padding-left:295px;}
.slide span a:link,.slide span a:visited,.s_on{ display:block; width:13px; height:13px; float:left; margin-right:5px;background:url(/huihoo-2012-theme/images/slide.png); overflow:hidden;}
.slide span a:hover{background:url(/huihoo-2012-theme/images/slide_h.png);}
.s_on{background:url(/huihoo-2012-theme/images/slide_h.png);}
.cleft_b{padding:5px 30px 15px 30px;}
.cleft_mid{ padding:20px 30px 10px 30px; border-bottom:1px dashed #c3c3c3; }
.status{ background-color:#e8e7e7; text-align:center; height:30px; line-height:30px; font-size:12px;}
.user,.user_my{ float:left; width:50px; height:50px; margin-right:15px;}
.user_my{margin-right:4px;}
.wbcontent{ float:lef; line-height:21px; padding-left:65px;}
.wbdate{ font-size:12px; color:#999;}
.wblist { border-bottom:1px dashed #c3c3c3; padding:20px 0 15px 0;}
.page{ margin:20px;}
.page li{ float:left; margin-right:5px;}
.number{ display:block;  background-color:#FFF;padding:0px 7px;}
.number_on,.number a:hover{ border-bottom:1px solid #3367cd;display:block; padding:0px 7px; }
.wb_input{ float:left; width:588px;}
.top_input{ background:url(/huihoo-2012-theme/images/bg_input.jpg) no-repeat; width:545px; height:104px; padding:15px 13px 15px 30px;}
.top_input li{ float:left;}
.in_min{ margin:0px 245px 0px 50px; }
.ckbox,.ts_text,.wbimg{ display:block; float:left; margin-right:5px;}
.wbimg{ width:24px; height:24px; overflow:hidden; vertical-align:middle;}
.ts_text{ margin-top:4px;}
.inputstyle{ height:64px; padding-top:5px; border:1px solid #FFF; width:540px; margin-bottom:10px;}
.wb_lasttime{ font-size:12px; color:#7d7d7d; line-height:18px; padding-left:15px;}
.send,.send a:link,.send a:visited{ display:block; width:56px; height:26px; background:url(/huihoo-2012-theme/images/send.jpg);}
.send a:hover{background:url(/huihoo-2012-theme/images/send_h.jpg);}
</style>