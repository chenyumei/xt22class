<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'updateUser.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  <script type="text/javascript" src="<%=basePath%>statics/calendar/WdatePicker.js"></script><script type="text/javascript" src="<%=basePath%>statics/js/jquery-1.8.3.min.js"></script></head>
  
  </head>
  
  <body>
      <form action="<%=basePath%>user/updateUser" method="post">
      <input type="hidden" name="id" value="${user.id}"/> 
       <input type="hidden" name="modifyBy" value="${user.modifyBy}"/> 
   用户编码:<input type="text" name="userCode" id="userCode" value="${user.userCode}"/></br>
    用户名称:<input type="text" name="userName" id="userName"  value="${user.userName}"/></br>
    用户密码:<input type="text" name="userPassword" id="userPassword" value="${user.userPassword}"/></br>
   性别: 女
   <input type="radio" name="gender" id="gender" value="1"  checked="checked" <c:if test="${user.gender==1}"> checked</c:if>/> 
   男<input type="radio" name="gender" id="gender" value="2" <c:if test="${user.gender==2}"> checked</c:if>/></br>
    出生日期:<input type="text" name="birthday" id="birthday" onclick="WdatePicker()"  Class="Wdate" value="${user.birthday}"/></br>
    电话:<input type="text" name="phone" id="phone" value="${user.phone}"/></br>
   地址: <input type="text" name="address" id="address" value="${user.address}"/></br>
   用户角色: <select name="userRole" id="userRole">
      <option value="1" <c:if test="${user.userRole==1}">selected="select"</c:if>>系统管理员</option>
      <option value="2" <c:if test="${user.userRole==2}">selected="select"</c:if>>经理</option>
      <option value="3" <c:if test="${user.userRole==3}">selected="select"</c:if>>普通用户</option>
    </select></br>
    <input type="submit" name="sbm" id="sbm" value="提交"/>
  </form>  
  </body>
</html>
