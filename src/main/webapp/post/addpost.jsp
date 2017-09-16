<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<%@ include file="../include/header.jsp"%>
</head>
<body>
		<input type="text" name="title" placeholder="标题" /><br /> <input
			type="text" name="content" placeholder="内容" /><br /> <input
			type="text" name="city" placeholder="城市" /><br /> 
			<select name="type">
			<option>请选择</option>
			<c:forEach items="${types }" var="type">
				<option value="${type.id }">${type.name }</option>
			</c:forEach>
		</select><br /> <input type="text" name="resources" placeholder="附件" /><br />
		<input type="text" name="code" placeholder="验证码" /><img id="verify"
			onclick="javascript:recode();" src="<%=Config.BASE_URL%>verify.html"
			alt="验证码" /><br /> <button name="send_post_submit" >提交</button>
</body>
</html>