<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/include/include-head.jsp" %>
<%@ include file="/WEB-INF/views/auth/login-modal.jsp" %>
<%@ include file="/WEB-INF/views/auth/join-modal.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>나의 홈페이지</title>
</head>
<body>

<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#login-modal">LOGIN</button>
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#join-modal">JOIN</button>

</body>
</html>