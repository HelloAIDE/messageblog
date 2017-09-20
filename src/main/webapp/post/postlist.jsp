<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<html>
<head>
<%@ include file="../include/header.jsp"%>
</head>
<body>
	<c:if test="${data.state==0 }">
		<c:set var="list" value="${data.data }"/>
		<c:forEach var="map" items="${list }">
		<i>
			<h1><a href="<%=Config.BASE_URL %>fourm/post-${map.id}.html"  target="<%=Config.NEWWINDOW?"_blank":"_self" %>">${map.title}</a></h1>
			<h2>作者：${map.other}</h2>
			<p>${map.content}</p>
			<p>发布时间：${map.createtime}</p>
			<p>类型：${map.type}</p>
			<%-- <h1>${map}</h1> --%>
			<hr/>
		</i>
		</c:forEach>
	</c:if>
</body>
</html>