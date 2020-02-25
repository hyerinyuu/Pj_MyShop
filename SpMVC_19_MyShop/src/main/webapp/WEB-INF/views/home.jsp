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
<nav class="navbar navbar-expand-sm bg-dark d-flex fixed-top position-sticky">
	<ul class="navbar-nav">
		<li class="nav-item"><a class="nav-link" href="${rootPath}/">HOME</a></li>
	</ul>	
	
	<ul class="navbar-nav ml-auto">	
		<!--  로그인이 되지 않았을때만 로그인 메뉴 보이기(로그인 된 상태면 안보임) -->
		<sec:authorize access="isAnonymous()">
			<li class="nav-item ml-auto"><a class="nav-link" href="${rootPath}/auth/login">SignIn</a></li>
			<li class="nav-item"><a class="nav-link" href="${rootPath}/auth/join">SignUp</a></li>
		</sec:authorize>	
		
		<sec:authorize access="isAuthenticated()" >
			<form id="logout" method="POST" action="${rootPath}/logout">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				<li class="nav-item"><a href="#" class="nav-link logout">Sign Out</a></li>
			</form>
		</sec:authorize>
		
		<li class="nav-item"><a class="nav-link" href="${rootPath}/user/product/cart_view">CART</a></li>

		<sec:authorize access="hasRole('ADMIN')">
			<li class="nav-item"><a class="nav-link" href="${rootPath}/admin/">ADMIN</a></li>
		</sec:authorize>
		
		<form class="form-inline" action="#">
	    	<input class="form-control mr-sm-2" placeholder="Search">
	  	</form>
		
	</ul>
</nav>

<section class="container">
	<c:choose>
		<c:when test="${BODY == 'PRO-LIST'}" >
			<%@ include file="/WEB-INF/views/users/user_product_list.jsp"  %>
		</c:when>
		<c:when test="${BODY == 'PRO-DETAIL'}" >
			<%@ include file="/WEB-INF/views/users/user_product_detail.jsp"  %>
		</c:when>
		<c:when test="${BODY == 'CART_VIEW'}" >
			<%@ include file="/WEB-INF/views/users/cart_view.jsp"  %>
		</c:when>
	</c:choose>	
</section>

</body>
</html>