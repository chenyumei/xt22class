<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'UserList3.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  <script type="text/javascript" src="<%=basePath%>statics/js/jquery-1.8.3.min.js"></script></head>
  
  <body> <a href="<%=basePath%>user/toaddUser">添加用户</a>
   <table>
    <tr><Td colspan="2">
    <form action="user/getUserListPage" method="post">
    <input type="text" name="queryUserName" value="${queryUserName}"/>
    <input type="text" name="queryUserRole" value="${queryUserRole}"/>
    <input type="submit" value="提交"/></form></td></tr>
   <tr><td>用户名</td><td>code</td></tr>
   <c:forEach items="${userList}" var="user">
	   <tr>
	      <td>${user.userName }</td><td>${user.userCode}&nbsp;&nbsp;<a href="<%=basePath%>user/getUserById/${user.id }">修改</a> &nbsp;&nbsp;&nbsp;<a href="javascript:look('<%=basePath%>',${user.id })">查看</a></td>
	   </tr>
	   
   </c:forEach>
   <tr>
   <td colspan="2">
   总${page.currentPageNo}/${total }页&nbsp;&nbsp;
   <c:if test="${page.currentPageNo>1}">
   <a href="user/getUserListPage?currentPageNo=${page.currentPageNo-1 }">上一页 </a></c:if>
    &nbsp;&nbsp;<c:if test="${page.currentPageNo<total}">
    <a href="user/getUserListPage?currentPageNo=${page.currentPageNo+1} ">下一页</a></c:if>
    &nbsp;&nbsp; 去<input type="text" name="currentPageNo" id="currentPageNo" />页
    <input type="button" name="btn" id="btn" value="go" onclick="toPage()"/></td></tr>
   
   </table>
   --------------------------
   <div id="showDetail" style="display:none">
     用户名:<input type="text" id="userName" value=""/>
     用户code：<input type="text" id="userCode" value=""/>
     生日：<input type="text" id="birthday" value=""/>
     创建日期：<input type="text" id="creatDate" value=""/>
   </div>
   
  </body>
</html>
<script type="text/javascript">
  function toPage(){
      window.location.href="user/getUserListPage?currentPageNo="+document.getElementById("currentPageNo").value;
    }
    
    function look(path,id){
        $.ajax({
         type:"GET",
          url:path+"user/userbean",
          data:{id:id},
          dataType:"json",
          success:function(data){
               $("#showDetail").show();
               $("#userName").val(data.userName);
               $("#userCode").val(data.userCode);
                $("#birthday").val(data.birthday);
                 $("#creatDate").val(data.creationDate);
          }
        })
    }
</script>
