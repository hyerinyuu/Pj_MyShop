<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>
<script>
$(function(){
	$(".product-list").click(function(){
		document.location.href = "${rootPath}/user/product/detail/" + $(this).data("id")
	})
})

</script>

<style>
 tr td{
 	cursor: pointer;
 }
 
 .table-view {
 	padding-top: 3rem;
 	width: 90%;
 	margin: 0 auto;
 }

</style>

<section>
	<div class="table-view">
		<table class="table table-hover" id="viewtable">
			<thead class="thead-dark">
				<tr>
					<th>NO</th>
					<th>상품명</th>
					<th>가격</th>
				</tr>
			</thead>	

			<c:choose>
					<c:when test="${empty PLIST}">
						<tr>
							<td colspan="3">데이터가 없음</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${PLIST}" var="PVO" varStatus="in">
							<tr class="content-body product-list" data-id="${PVO.id}">
								<td>${in.index}</td>
								<td>${PVO.p_name}</td>
								<td>${PVO.p_oprice}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
		</div>	
</section>