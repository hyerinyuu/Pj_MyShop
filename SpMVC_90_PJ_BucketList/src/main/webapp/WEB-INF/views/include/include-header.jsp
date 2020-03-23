<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<%@include file="/WEB-INF/views/include/include-head.jsp"%>
<link href="https://fonts.googleapis.com/css?family=Playfair+Display:400,800i&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Lobster&display=swap" rel="stylesheet">
<style>
#main-header {
	padding: 200px;
	background-image: url('${rootPath}/resources/images/main-background.jpg');
	background-attachment: fixed;
 	background-position: center;
 	background-repeat: no-repeat;
 	background-size: cover;
	color: white;
}
.main-h1{
	font-weight: bold;
	font-family: 'Lobster', cursive;
	font-size: 70px;
}
.main-p{
	color: black;
	font-weight: bold;
	font-family: 'Playfair Display', serif;
	
}
</style>
<div id="main-header" class="jumbotron text-center" style="margin-bottom: 2rem">
	<h1 class="main-h1">BucketList</h1>
	<p class="main-p">Things to do BEFORE YOU DIE!<p>
</div>
