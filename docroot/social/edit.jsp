<%@include file="/init.jsp" %>

<portlet:actionURL name="addName" var="addNameURL"/>    

<form id="<portlet:namespace />helloForm" action="<%=addNameURL.toString()%>"
	method="post">
	<table>
		<tr>
			<td>Name:</td>
			<td><input type="text" name="username"></td>
		</tr>
	</table>
	<input type="submit" id="nameButton" title="Add Name" value="Add Name">
</form>