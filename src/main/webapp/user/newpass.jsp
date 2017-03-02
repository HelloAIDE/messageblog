<%@page import="com.blog.util.Config"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../include/header.jsp"%>
</head>
<body>
	<form action="<%=Config.BASE_URL%>newpass.html">
		<input type="text" name="newpass" placeholder="新密码"/><br/>
		<input type="submit" value="提交"/>
	</form>
</body>
</html>