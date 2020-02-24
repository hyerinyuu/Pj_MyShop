<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>
<script>
$(function(){
	$(".btn-detail").click(function(){
		document.location.href = "${rootPath}/user/product/detail/" + $(this).data("id")
	})
})

</script>

<style>
.btn-detail{
 	cursor: pointer;
 }

 .list-top{
 	padding-top: 3rem;
 }
 
 .p-name{
 	font-weight: bold;
 }

</style>

	<!-- Page Content -->
	<div class="container">

		<!-- Jumbotron Header -->
		<header class="jumbotron my-4">
			<h2 class="display-3">클릭하고 주문하는데까지 1분</h2>
			<p class="lead m-2">구매 하지 않으셔도 됩니다! 구경만 하고 가세요!</p>
			<a href="#" class="btn btn-primary btn-lg">추천상품 바로가기!</a>
		</header>

		<!-- Page Features -->
		<div class="row text-center">

			<c:choose>
				<c:when test="${empty PLIST}">
					<h2> 상품정보가 없습니다.</h2>
				</c:when>

				<c:otherwise>
					<c:forEach items="${PLIST}" var="PVO">
						<div class="col-lg-3 col-md-6 mb-4">
							<div class="card h-100">
								<img class="card-img-top" src="http://placehold.it/500x325" alt="상품이미지">
								<div class="card-body">
									<h4 class="card-title font-weight-bold">${PVO.p_name}</h4>
									<p><fmt:formatNumber value="${PVO.p_oprice}" type="currency" currencySymbol="￦"/></p>
								</div>
								<div class="card-footer">
									<button data-id="${PVO.id}" class="btn basket btn-primary btn-detail">자세히보기</button>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>

			</c:choose>

		</div>
		<!-- /.row -->


	</div>
	<!-- /.container -->