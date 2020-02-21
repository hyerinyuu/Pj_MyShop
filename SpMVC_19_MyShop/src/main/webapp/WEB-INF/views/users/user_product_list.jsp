<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

</style>

<!-- Page Content -->
	<div class="container list-top">
		<!-- Page Features -->
		<div class="row text-center">
			<c:forEach var="PVO" items="${PLIST}" varStatus="i">
				<div class="col-lg-3 col-md-6 mb-4">
					<div class="card h-100">
						<img class="card-img-top" src="http://placehold.it/500x325" alt="">
						<div class="card-body">
							<h4 class="card-title">${PVO.p_name}</h4>
							<div>
								<c:choose>
									<c:when test="${empty PLIST}">
										<tr>
											<td colspan="6">상품 정보가 없습니다.</td>
										</tr>
									</c:when>
									<c:otherwise>

										<div class="B2C_LIST" >
											<div>${PVO.p_oprice}원</div>
										</div>

									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="card-footer">
							<button data-id="${PVO.id}" class="btn basket btn-primary btn-detail">보러만 가기</button>
						</div>
					</div>
				</div>
			</c:forEach>

		</div>
		<!-- /.row -->

	</div>
	<!-- /.container -->