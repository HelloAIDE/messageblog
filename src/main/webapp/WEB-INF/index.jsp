<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../include/header.jsp"%>
</head>

<body>
	<p><%=request.getAttribute("uname") == null
					? "未登陆,请先<a href='login.html'>登录</a>"
					: "欢迎登陆" + request.getAttribute("uname")%></p>
</body>

</html>
