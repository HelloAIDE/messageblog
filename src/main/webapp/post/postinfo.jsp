<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../include/header.jsp"%>
<style>
.item {
	padding-left: 10px;
	border-bottom: 1px black solid;
	padding-bottom: 10px;
}
.item1 {
	    padding: 4px 4px 6px;
    border: 1px solid #dee4e9;
    background-color: #fff;
    position: relative;
    z-index: 2;
}
.item1>.rigth {
	display : block;
	margin: 10px 10px 8px 8px;
	display: inline-block;
}
.left {
	width: 47px;
	height: 42px;
	background: red;
	margin: 10px 0px 0px 10px;
	float: left;
	display: inline-block;
}
.rigth {
	width: 650px; display : inline-block;
	margin: 10px 0px 8px 8px;
	display: inline-block;
}

.rigth p {
	display: inline-block;
	margin: 0px;
	padding: 0px;
}

.content {
	margin: 10px 0px 5px 0px;
	font-size: 18px;
}

.content_r {
	text-align: right;
}
</style>
</head>
<body style="width: 1000px; margin: 0 auto;">
		<div style="width: 800px;">
			<div style="text-align: left;">
				<h1>${id}</h1>
			</div>
			回复：
			<form action="../addreply.do">
				<textarea id="content" type="text" name="content" placeholder="回复内容"></textarea>
				<br /> <input type="hidden" name="postid" value="${info.id }" /> <br />
				<input id="parentid" type="hidden" name="parentid" value="" /> <br />
				<input type="submit" value="提交" />
			</form>
			<div class="huifu" id="replys" sid="${info.id}"></div>
		</div>
</body>
<script charset="utf-8" type="text/javascript"
	src="<%=Config.BASE_URL%>upload/huifu.js"></script>
<script type="text/javascript">
	function reply(id, name) {
		var content = document.getElementById("content");
		content.innerHTML = "@" + name + ":";
		var parentid = document.getElementById("parentid");
		parentid.value = id;
	}
</script>
</html>