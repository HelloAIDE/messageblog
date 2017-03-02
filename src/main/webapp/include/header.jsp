<%@page import="com.blog.util.Config"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta name="Description" content="<%=Config.Description%>">
<link type="favicon" rel="shortcut icon"
	href="<%=Config.BASE_URL + Config.ICONURL%>">
<script charset="utf-8" type="text/javascript"
	src="<%=Config.BASE_URL%>js/jquery.min.js"></script>
<script charset="utf-8" type="text/javascript"
	src="<%=Config.BASE_URL%>js/main.js"></script>
<script charset="utf-8" type="text/javascript"
	src="<%=Config.BASE_URL%>js/xianbaoapi.v1.js"></script>
		<script type="text/javascript" src="<%=Config.BASE_URL%>js/notice.js" ></script>
<script type="text/javascript">
var BASE_URL = "<%=Config.BASE_URL%>";
function recode(){
	$("#verify").attr("src","<%=Config.BASE_URL%>verify.html?rand="
								+ Math.random() * 1000);

	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=request.getAttribute("title")%>-<%=Config.TITLE%></title>
