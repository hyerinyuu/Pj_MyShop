<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>
<meta charset="UTF-8">
<title>나의 홈페이지</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<script>
$(function(){
	$("a.logout").click(function(){
		$("#logout").submit()
	})
	
})
</script>
<style>

#search-div{
	margin: 0 auto;
}
#search-bar{
	padding-top: 3rem;
}

</style>

</head>
<body>

<header class="jumbotron text-center">
	<h3>MY SHOP</h3>
</header>
<nav class="navbar navbar-expand-sm bg-light d-flex">
	<ul class="navbar-nav">
		<li class="nav-item"><a class="nav-link" href="${rootPath}/">HOME</a></li>
		
		<!--  로그인이 되지 않았을때만 로그인 메뉴 보이기(로그인 된 상태면 안보임) -->
		<sec:authorize access="isAnonymous()">
			<li class="nav-item"><a class="nav-link" href="${rootPath}/auth/login">SignIn</a></li>
			<li class="nav-item"><a class="nav-link" href="${rootPath}/auth/join">SignUp</a></li>
		</sec:authorize>	
		
		<sec:authorize access="isAuthenticated()" >
			<form id="logout" method="POST" action="${rootPath}/auth/logout">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				<li class="nav-item"><a href="#" class="nav-link logout">Sign Out</a></li>
			</form>
		</sec:authorize>

		<sec:authorize access="hasRole('ADMIN')">
			<li class="nav-item"><a class="nav-link" href="${rootPath}/admin/">ADMIN</a></li>
		</sec:authorize>
		
		<form class="form-inline ml-auto" action="#">
	    	<input class="form-control mr-sm-2" placeholder="Search">
	    	<button class="btn btn-primary">Search</button>
	  	</form>
		
	</ul>
</nav>

<section>
		<c:choose>
			<c:when test="${BODY == 'PRO-LIST'}" >
				<%@ include file="/WEB-INF/views/users/user_product_list.jsp"  %>
			</c:when>
			<c:when test="${BODY == 'PRO-VIEW'}" >
				<%@ include file="/WEB-INF/views/users/user_product_detail.jsp"  %>
			</c:when>
		</c:choose>	
	</section>

</body>
</html>