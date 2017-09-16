<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../include/header.jsp"%>
</head>
<body>
		<input type="text" name="name" placeholder="用户名"/><br/>
		<input type="password" name="pass" placeholder="密码"/><br/>
		<input type="text" name="phone" placeholder="手机号"/><br/>
		<input type="text" name="qq" placeholder="qq"/><br/>
		<input type="text" name="email" placeholder="email"/><br/>
		<input type="text" name="address" placeholder="地址"/><br/>
		<input type="text"
			name="code" placeholder="验证码" /><img id="verify" onclick="javascript:recode();" src="<%=Config.BASE_URL %>verify.html" alt="验证码"/><br/>
		<button name="register_submit">提交</button>
</body>

</html>