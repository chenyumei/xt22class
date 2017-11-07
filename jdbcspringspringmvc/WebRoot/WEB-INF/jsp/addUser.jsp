<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addUser.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  <script type="text/javascript" src="<%=basePath%>statics/calendar/WdatePicker.js"></script><script type="text/javascript" src="<%=basePath%>statics/js/jquery-1.8.3.min.js"></script></head>
  <script type="text/javascript">
     function check(path){
        $.ajax({
          type:"POST",
          url:path+"/user/checkUser",
          data:{userCode:$("#userCode").val()},
          dataType:"json",
           success:function(data){
           if(data.userCodeExsit=="exsit"){
            alert(data.userCodeExsit);
             $("#errorValue").html("用户存在");
           }else{
             alert(data.userCodeExsit);
             $("#errorValue").html("可以添加");
           }
        }

        })
     }
  </script>
  <body>
  
   <form action="<%=basePath%>user/addUser" method="post">
   用户编码:<input type="text" name="userCode" id="userCode" onblur="check('<%=basePath%>')"/><lable id="errorValue"></lable></br>
    用户名称:<input type="text" name="userName" id="userName"/></br>
    用户密码:<input type="text" name="userPassword" id="userPassword"/></br>
   性别: 女<input type="radio" name="gender" id="gender" value="1" checked="checked"/> 男<input type="radio" name="gender" id="gender" value="2"/></br>
    出生日期:<input type="text" name="birthday" id="birthday" onclick="WdatePicker()"  Class="Wdate" /></br>
    电话:<input type="text" name="phone" id="phone"/></br>
   地址: <input type="text" name="address" id="address"/></br>
   用户角色: <select name="userRole" id="userRole">
      <option value="1">系统管理员</option>
      <option value="2">经理</option>
      <option value="3">普通用户</option>
    </select></br>
    <input type="submit" name="sbm" id="sbm" value="提交"/>
  </form>  
   
<%-- <spring:form action="<%=basePath%>user/addUser"  method="post">

     用户编码:<spring:input path="userCode"/></br>
      
     用户名称:<spring:input path="userName"/></br>
     
     用户密码:<spring:input path="userPassword"/></br>
     
    性别: 女<spring:radiobutton path="gender" value="1"/>男<spring:radiobutton path="gender" value="2"/></br>
  
    出生日期:<spring:input path="birthday" readonly="readonly" onclick="WdatePicker();" class="Wdate"/>
     电话：<spring:input path="phone"/>
     地址：<spring:input path="address"/>
    用户角色:
    <spring:select path="userRole">
     <spring:option value="1">系统管理员</spring:option>
     <spring:option value="2">经理</spring:option>
     <spring:option value="3">普通用户</spring:option>
    </spring:select>
   <input type="submit" value="保存"/>
  </spring:form>  --%>
  </body>
</html>
