<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>
</head>
<style>
.in-errors{
	color: red;
	font-size: 16px;
}
/*
	col-md-7 col-12
	해상도가 768보다 크면 7칸만 차지하고
	그 이하이면 12칸을 차지하여 풀width으로 보여라
*/
tr, td, th {
	white-space: nowrap;
}

.list-body{
	overflow: auto;
}

.p_name {
	display: inline-block;
	width: 80px;
	padding: 0 5 px;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}

</style>
<body>
	<nav class="navbar navbar-expand-sm bg-primary navbar-dark">

		<ul class="navbar-nav">
			<li class="navbar-item">
				<a class="nav-link" href="${rootPath}/">HOME</a>
			</li>
			
			<li class="navbar-item">
				<a class="nav-link" href="${rootPath}/admin/product/">상품정보</a>
			</li>
			<li class="navbar-item">
				<a class="nav-link" href="#">품목정보</a>
			</li>
			<li class="navbar-item">
				<a class="nav-link" href="${rootPath}/admin/dept">거래처정보</a>
			</li>
			<li class="navbar-item">
				<form:form action="${rootPath}/logout" name="logout_form">
					<a class="nav-link" href="javascript:void(0)" onclick="document.logout_form.submit()">로그아웃</a>
				</form:form>
			</li>
		</ul>
		
	</nav>
	<section>
		<c:choose>
			<c:when test="${BODY == 'PRODUCT'}" >
				<%@ include file="/WEB-INF/views/admin/product.jsp"  %>
			</c:when>
			<c:when test="${BODY == 'DEPT'}" >
				<%@ include file="/WEB-INF/views/admin/dept.jsp"  %>
			</c:when>
			<c:otherwise>
				<h3>카트 상품 : ${COUNT_CART}</h3>
				<h3>배송중 상품 : ${COUNT_DELIVERY}</h3>
			</c:otherwise>
		</c:choose>	
	</section>
	
</body>