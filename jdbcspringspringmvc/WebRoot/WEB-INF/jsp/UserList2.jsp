<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'UserList2.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	

  </head>
  
  <body>
  <table>
  <tr><Td colspan="2"><form action="user/listUser" method="post"><input type="text" name="queryUserName" value="${queryUserName}"/><input type="text" name="queryUserRole1" value="${queryUserRole1}"/><input type="submit" value="提交"/></form></td></tr>
  <tr>
  <td>用户名</td>
  <td>code</td>
  </tr>
   <c:forEach items="${userlist}" var="user">
    <tr><td>${user.userName}</td><td>${user.userCode}</td></tr>
   </c:forEach>
   <tr><td colspan="3" align="center">
        共${Total}页&nbsp;&nbsp;&nbsp; 
					<c:if test="${page.pageNo<1}">上一页</c:if>
					<c:if test="${page.pageNo>1}">
						<a href="user/listUser?pageNo=${page.pageNo-1 }">上一页</a>
					</c:if>
					&nbsp;&nbsp;&nbsp;
					<c:if test="${Total>1}">
					<a href="user/listUser?pageNo=${page.pageNo+1}">下一页</a>
					</c:if>
				去第<input type="text" name="pageNo" id="pageNo">页
				<input type="button" id="button1" onClick="buttonClick()" value="GO"/>
       
   </td></tr>
   </table>
  </body>
</html>
<script>
function buttonClick(){     
window.location="user/listUser?pageNo=" + document.getElementById('pageNo').value;}
</script>