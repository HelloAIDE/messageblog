<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<%@ include file="../include/header.jsp"%>
</head>
<body>
	<form action="addpost.do">
		<input type="text" name="title" placeholder="标题" /><br /> <input
			type="text" name="content" placeholder="内容" /><br /> <input
			type="text" name="city" placeholder="城市" /><br /> <input type="text"
			name="type" placeholder="分类" /><br /> <input type="text"
			name="resources" placeholder="附件" /><br />
		<input type="text"
			name="code"  placeholder="验证码" /><img id="verify" onclick="javascript:recode();" src="<%=Config.BASE_URL %>verify.html" alt="验证码"/><br/>
		<input type="submit" onclick="javascript:recode();" value="提交"/>
	</form>
</body>
</html>