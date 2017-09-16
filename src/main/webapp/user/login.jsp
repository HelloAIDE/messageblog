 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../include/header.jsp"%>
</head>
<body>
		<input class="name" type="text" name="name"  placeholder="用户名"/><br/>
		<input class="pass" type="password" name="pass" placeholder="密码"/><br/>
		<input class="code" type="text" name="code"  placeholder="验证码" />
		<img id="verify" onclick="javascript:recode();" src="<%=Config.BASE_URL %>verify.html" alt="验证码"/><br/>
		<button name="login_submit">提交</button>
</body>

<script type="text/javascript"></script>
</html>