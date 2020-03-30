<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<%@include file="/WEB-INF/views/include/include-head.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BucketList</title>
<meta name='viewport' content='width=device-width, initial-scale=1'>
</head>
<style>
	* {
		margin:0;
		padding: 0;
	}
</style>
<body>
<%@ include file="/WEB-INF/views/include/include-nav.jsp" %>
<header>
	<%@include file="/WEB-INF/views/include/include-header.jsp" %>
</header>
<%@ include file="/WEB-INF/views/bucket/bk_list.jsp" %>	
</body>
</html>