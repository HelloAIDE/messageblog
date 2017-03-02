<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<%@ include file="../include/header.jsp"%>
</head>
<body>
	<h1>${title }</h1>
	<form action="update.do">
		<input type="text" name="name" value="${type.name }" placeholder="分类名称"/><br/>
		<input type="text" name="image" value="${type.image }" placeholder="图片地址"/><br/>
		<input type="text" name="managerId" value="${type.managerId }" placeholder="管理id"/><br/>
				<input type="hidden" name="id" value="${type.id }"/><br/>
		<input type="submit" value="提交"/>
	</form>
</body>
</html>