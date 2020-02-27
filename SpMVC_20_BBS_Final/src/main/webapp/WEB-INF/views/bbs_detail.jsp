<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>
</head>
<style>
.writer{
	font-weight: bold;
}
</style>
<script>
$(function(){
	
	$("button").click(function(){
		let txt = $(this).text()
		let id = $(this).data("id")
		
		if(txt == '수정'){
			if(confirm("게시글을 수정하시겠습니까?")){
				document.location.href = "${rootPath}/update?b_id=" +id
						
			}
		}else if(txt == '삭제'){
			if(confirm("게시글을 삭제하시겠습니까?")){
				document.location.replace("${rootPath}/delete/${BBS.b_id}")
						
			}	
		}else if(txt == '저장'){
			return false
		}else{
			document.location.href = "${rootPath}/list"
		}
	})
	
})
</script>
<body>
<%@ include file="/WEB-INF/views/include/include-header.jspf" %>
<br/>

<section>
	
	<h2>${BBS.b_subject}</h2>
	<hr/>
	<small class="writer">${BBS.b_writer}</small>
	<small>${BBS.b_date_time}</small>
	<hr/>
	<div>
		${BBS.b_content}
	</div>	
	<hr/>			
	
	<div class="form-group d-flex justify-content-end">
		<button data-id="${BBS.b_id}" class="btn btn-warning mr-2 btn-update">수정</button>
		<button data-id="${BBS.b_id}" class="btn btn-danger mr-2 btn-delete">삭제</button>
		<button data-id="${BBS.b_id}" class="btn btn-primary btn-list">목록으로</button>
	</div>		

</section>

<br/>

<section class="container-fluid">
	<h2>Comment</h2>
	<hr/>
	<div class="row d-flex justify-content-center">
		<div class="form-group col-2">
			<input class="form-control" placeholder="작성자">
		</div>
		<div class="form-group col-8">
			<input class="form-control" placeholder="댓글">
		</div>
		<div class="form-group col-2">
			<button class="btn btn-primary">저장</button>
		</div>
	</div>
	
</section>
</body>