<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	$(function() {

		var toolbar = [ [ 'style', [ 'bold', 'italic', 'underline' ] ],
				[ 'fontsize', [ 'fontsize' ] ],
				[ 'font Style', [ 'fontname' ] ], [ 'color', [ 'color' ] ]]

		$(".bk_text").summernote({
			lang : 'ko-KR',
			placeholder : 'details',
			width : '100%',
			toolbar : toolbar,
			height : '200px',
			disableDragAndDrop : false, /* true => 파일 d&d 안됨*/
		})
		
		$(".btn-insert").click(function(){
			
			if($("#bk_subject").val() == '' ){
				alert("버킷리스트를 입력해주세요")
				$("#bk_subject").focus()
				return false
			}
			if($("#bk_text").val() == ''){
				alert("내용을 입력해주세요")
				$("#bk_text").focus()
				return false
			}
			$("form").submit()
		})

	})
</script>
<!-- insert Modal -->
<div class="modal fade" id="insertModal">
	<div class="modal-dialog">
		<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">Insert BucketList</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>

			<!-- Modal body -->
			<div class="modal-body">
				<form action="/insert" method="POST">
					<div class="form-group">
						<input name="bk_subject" class="form-control" placeholder="subject" value="${BK.bk_subject}">
					</div>
					<div class="form-group">
						<textarea name="bk_text" class="bk_text" rows="5" cols="30">${BK.bk_text}</textarea>
					</div>

				</form>
			</div>

			<!-- Modal footer -->
			<div class="modal-footer">
				<button type="button" data-check="1" class="btn btn-success btn-insert" data-dismiss="modal">Save</button>
			</div>

		</div>
	</div>
</div>

<!-- update Modal -->
<div class="modal fade" id="updateModal">
	<div class="modal-dialog">
		<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">Update BucketList</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>

			<!-- Modal body -->
			<div class="modal-body">
				<form action="/update" method="POST">
					<div class="form-group">
						<input name="bk_subject" class="form-control" placeholder="subject" value="${BK.bk_subject}">
					</div>
					<div class="form-group">
						<textarea name="bk_text" class="bk_text" rows="5" cols="30">${BK.bk_text}</textarea>
					</div>

				</form>
			</div>

			<!-- Modal footer -->
			<div class="modal-footer">
				<button type="button" class="btn btn-success btn-warning" data-dismiss="modal">Complete</button>
				<button type="button" class="btn btn-success btn-danger" data-dismiss="modal">Delete</button>
				<button type="button" class="btn btn-success btn-insert" data-dismiss="modal">Save</button>
			</div>

		</div>
	</div>
</div>