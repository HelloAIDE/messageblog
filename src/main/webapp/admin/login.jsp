<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../include/header.jsp"%>
</head>
<body>
<form action="checkuser.do">
		<input type="text" name="name" placeholder="用户名"/><br/>
				<input type="password" name="pass" placeholder="密码"/><br/>
		<input type="text"
			name="code"  placeholder="验证码" /><img id="verify" onclick="javascript:recode();" src="<%=Config.BASE_URL %>verify.html" alt="验证码"/><br/>
		<input type="submit" onclick="javascript:recode();" value="提交"/>
	</form>
</body>
</html>