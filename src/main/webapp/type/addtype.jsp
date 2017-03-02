<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../include/header.jsp"%>
</head>
<body>
	<form action="addtype.do">
		<input type="text" name="name" placeholder="名称"/><br/>
		<input type="text" name="image" placeholder="图片地址"/><br/>
		<input type="submit" value="提交"/>
	</form>
</body>
</html>