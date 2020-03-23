<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
$(function(){
	$(".content-body td").click(function(){
		$("#updateModal").modal()
	})
	
	$("#btn-insert").click(function(){
		$("#insertModal").modal()
	})
})

</script>
<style>
	/* 수행여부 */
	.line-thourgh {
		text-decoration: line-through;
		color : lightgray;
	}
</style>
<section>
	<div class="table-view">
		<table class="table table-hover" id="viewtable">
			<thead class="thead-dark">
				<tr>
					<th>NO</th>
					<th>BucketList</th>
					<th>Details</th>
					<th>Date/Time</th>
				</tr>
			</thead>	
			<c:choose>
					<c:when test="${empty BKLIST}">
						<tr>
							<td colspan="3">데이터 없음</td>
						</tr>
					</c:when>
					
					<c:otherwise>
						<c:forEach items="${BKLIST}" var="BK" varStatus="in">
							<tr class="content-body bucket-list" data-seq="${BK.bk_seq}">
								<td<c:if test="${BK.bk_complete == 'Y'}"> class="line-thourgh"</c:if>>${in.index +1}</td>
								<td<c:if test="${BK.bk_complete == 'Y'}"> class="line-thourgh"</c:if>>${BK.bk_subject}</td>
								<td<c:if test="${BK.bk_complete == 'Y'}"> class="line-thourgh"</c:if>>${BK.bk_text}</td>
								<td<c:if test="${BK.bk_complete == 'Y'}"> class="line-thourgh"</c:if>>${BK.bk_date} ${BK.bk_time}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
			<button type="button" id="btn-insert" class="btn btn-primary ml-auto">insert</button>
		</div>	
</section>
<%@ include file="/WEB-INF/views/bucket/bk_modal.jsp" %>
