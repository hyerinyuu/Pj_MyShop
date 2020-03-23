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
			placeholder : '내용을 입력하세요',
			width : '100%',
			toolbar : toolbar,
			height : '200px',
			disableDragAndDrop : false, /* true => 파일 d&d 안됨*/
		})
		
		$("#btn-insert-save").click(function(){
			
			if($("#bk_subject").val() == '' ){
				alert("제목은 반드시 입력하셔야합니다!")
				$("#bk_subject").focus()
				return false
			}
			if($("#bk_text").val() == ''){
				alert("내용은 반드시 입력하셔야합니다!")
				$("#bk_text").focus()
				return false
			}
			$("form").submit()
		})

	})
</script>

<!-- insert Modal -->
<div class="modal fade" id="insert-modal">
	<div class="modal-dialog" role="document">
		<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title font-weight-bold">Insert BucketList</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>

			<!-- Modal body -->
			<form action="${rootPath}/insert" method="POST">
				<div class="modal-body">
					
						<div class="form-group">
							<input name="bk_subject" id="bk_subject" class="form-control" placeholder="제목을 입력하세요" value="${BK.bk_subject}">
						</div>
						<div class="form-group">
							<textarea name="bk_text" id="bk_text" class="bk_text" rows="5" cols="30">${BK.bk_text}</textarea>
						</div>
				</div>
	
				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" id="btn-insert-save" class="btn btn-success button-insert">insert save</button>
				</div>
			</form>
		</div>
	</div>
</div>
