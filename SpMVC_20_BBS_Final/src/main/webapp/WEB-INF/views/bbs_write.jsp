<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>
</head>
<script>
$(function(){
	$(".btn-list").click(function(){
		document.location.href = "${rootPath}/list"
	})
	
	var toolbar = [
		['style',['bold','italic','underline'] ],
		['fontsize',['fontsize']],
		['font Style',['fontname']],
		['color',['color']],
		['para',['ul','ol','paragraph']],
		['height',['height']],
		['table',['table']],
		['insert',['link','hr','picture']],
		['view',['fullscreen','codeview']]
		
	]
	
	$("#b_content").summernote({
		lang:'ko-KR',
		placeholder:'본문을 입력하세요',
		width:'100%',
		toolbar:toolbar,
		height:'200px',
		disableDragAndDrop : false,
		/*
			DRAG AND DROP을 그대로 두면 SUMMERNOTE가 자기 맘대로 파일을 요리해버리기 때문에
			중간에 가로채기 위해서 callbacks 옵션 사용
		*/
		callbacks : {
			/*
			files : drag한 파일 정보 
			editor : summernote
			*/
			onImageUpload : function(files,editor,isEdite){
				
				for(let i = files.length -1 ; i >= 0; i--){
					// 파일을 1개씩 업로드할 함수
					upFile(files[i], this) // this == editor == summernote
				}
				
			}
		}
	})// end summer
	
	// ajax를 사용해서 파일을 서버로 업로드를 수행하고
	// 실제 저장된 파일 이름을 받아서
	// summernote에 기록된 내용중 img src = ""을 변경
	function upFile(file,editor){
		
		var formData = new FormData()
		
		// upFile변수에 file정보를 담아서 보내기 위한 준비
		
		// img src="data:base64~!~~~" 형태를
		//img src ="UUID파일.jsp" 형태로 변경
		formData.append('upFile', file)
		$.ajax({
			url : "${rootPath}/image_up",
			type : "POST",
			data : formData,
			// 파일업로드를 위해 반드시 있어야하는 변수들
			contentType : false,
			processData : false,
			enctype : "multipart/form-data",
			success : function(result){
				result = "${rootPath}/files/" + result
				// summernote에 내장되어 있는 기능 ==> 이미지 태그를 변경하라는 일종의 class tag
				$(editor).summernote('editor.insertImage', result)
				alert(result)
			},
			error: function(){
				alert("서버통신오류")
			}
		})
		
	}
	
})

</script>
<body>
<%@ include file="/WEB-INF/views/include/include-header.jspf" %>
	<section class="container-fluid">
		
		<fieldset>
			<form method=POST>
				<div class="form-group">
					<input name="b_writer" class="form-control" placeholder="작성자" value="${BBS.b_writer}">
				</div>
				<div class="form-group">
					<input name="b_subject" class="form-control" placeholder="제목" value="${BBS.b_subject}">
				</div>
				<div class="form-group">
					<textarea id="b_content" rows="20" cols="50" name="b_content" class="form-control" placeholder="내용">${BBS.b_content}</textarea>
				</div>
				
				<div class="form-group d-flex justify-content-end">
					<button class="btn btn-primary mr-2">저장</button>
					<button type="button" class="btn btn-success btn-list">목록으로</button>
				</div>
			</form>			
		</fieldset>
		
	</section>
</body>