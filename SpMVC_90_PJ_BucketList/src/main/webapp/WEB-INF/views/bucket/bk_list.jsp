<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	$(function() {

		$(".btn-complete").click(function(){
			let id = $(this).data("id")
			// alert(id)
			if(confirm("완료상태를 변경하시겠어요?")){
				$.ajax({
					url : '${rootPath}/compStateUpdate',
					type : 'POST',
					data : {
						id : id
					},
					success : function(result){
						alert('변경완료!')
						location.reload()
					},
					error : function(result){
						alert('서버 통신 오류')
					}
				})
			}

			
		})
		$(".btn-delete").click(function(){
			let id = $(this).data("id")
			// alert(id)
			if(confirm("삭제하시겠습니까?")){
				$.ajax({
					url : '${rootPath}/delete',
					type : 'POST',
					data : {
						id : id
					},
					success : function(result){
						alert('삭제 완료!')
						location.reload()
					},
					error : function(result){
						alert('서버 통신 오류')
					}
				})
			}
			
		})
	})
</script>
<style>
/* 수행여부 */
.line-thourgh {
	text-decoration: line-through;
	color: lightgray;
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
						<tr class="content-body bucket-list" data-seq="${BK.bk_seq}"
							data-toggle="modal" data-target="#read-${BK.bk_seq}">
							<td
								<c:if test="${BK.bk_complete == 'Y'}"> class="line-thourgh"</c:if>>${in.index +1}</td>
							<td
								<c:if test="${BK.bk_complete == 'Y'}"> class="line-thourgh"</c:if>>${BK.bk_subject}</td>
							<td
								<c:if test="${BK.bk_complete == 'Y'}"> class="line-thourgh"</c:if>>${BK.bk_text}</td>
							<td
								<c:if test="${BK.bk_complete == 'Y'}"> class="line-thourgh"</c:if>>${BK.bk_date}
								${BK.bk_time}</td>
						</tr>
						
						
						<!-- read Modal -->
						<div class="modal fade" id="read-${BK.bk_seq}">
							<div class="modal-dialog" role="document">
								<div class="modal-content">

									<!-- Modal Header -->
									<div class="modal-header">
										<h4 class="modal-title font-weight-bold">${BK.bk_subject}</h4>
										<button type="button" class="close" data-dismiss="modal">&times;</button>
									</div>

									<!-- Modal body -->
									<div class="modal-body">
										<div>${BK.bk_text}</div>
									</div>

									<!-- Modal footer -->
									<div class="modal-footer">
										<button class="btn btn-info btn-warning btn-complete"
											data-id="${BK.bk_seq}">
											<c:if test="${BK.bk_complete == 'Y'}">달성 취소</c:if>
											<c:if test="${BK.bk_complete == 'N'}">버킷리스트 달성!</c:if>
										</button>
										
										<button class="btn btn-danger btn-delete" data-id="${BK.bk_seq}">삭제</button>
										<button class="btn btn-primary btn-update" data-toggle="modal" data-target="#update-${BK.bk_seq}">수정</button>
										
									</div>

								</div>
							</div>
						</div>


						<!-- update Modal -->
						<div class="modal fade" id="update-${BK.bk_seq}">
							<div class="modal-dialog" role="document">
								<div class="modal-content">

									<!-- Modal Header -->
									<div class="modal-header">
										<h4 class="modal-title">Update BucketList</h4>
										<button type="button" class="close" data-dismiss="modal">&times;</button>
									</div>

									<!-- Modal body -->
									<form action="${rootPath}/update" method="POST">
										<div class="modal-body">
											<input type="hidden" name="bk_seq" value="${BK.bk_seq}">
											<div class="form-group">
												<input name="bk_subject" class="form-control update-subject"
													placeholder="제목을 입력하세요" value="${BK.bk_subject}">
											</div>
											<div class="form-group">
												<textarea name="bk_text" class="bk_text update-text"
													rows="5" cols="30">${BK.bk_text}</textarea>
											</div>
										</div>

										<!-- Modal footer -->
										<div class="modal-footer">
											<button class="btn btn-success btn-save">저장</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
		<button id="btn-insert" data-toggle="modal"
			data-target="#insert-modal" class="btn btn-primary ml-auto">
			버킷리스트 추가하기
		</button>
	</div>
</section>
<%@ include file="/WEB-INF/views/bucket/bk_modal.jsp"%>
