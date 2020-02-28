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
.cmt-item-del {
	cursor: pointer;
}
</style>
<script>
$(function(){
	
	// ajax로 댓글을 입력하면 ajax success에서 동적으로 댓글을 작성하기 때문에
	// event를 적용할 수 없다.(event다음에 ajax가 수행되기 때문에)
	// 따라서 document 자체에 click event를 걸어서 event를 동적으로 생성해줘야함
	//$(".cmt-item").click(function(){
	$(document).on("click", ".cmt-item", function(){
		let id = $(this).data("id")
		alert("cmt-item : " + id)
	})
	
	$(document).on("click",".cmt-item-del", function(event){

		// 나를 감싸고 있는 곳으로 이벤트가 전파되는 것을 그만 두어라
		event.stopPropagation()	
		if(!confirm("댓글을 삭제할까요?")){
			return false
		}
		
		
		// closest : 현재 나를 감싸고 있는 가장 가까운 div tag를 가져와서 그 아이디 값을 추출하라
		/*
			event bubbling
		이벤트를 중복해서 걸어주면 이벤트가 2개 다 실행되는데 안쪽 tag에 걸어둔 이벤트가 먼저 발동되고
		그 다음 바깥쪽 tag에 걸어준 이벤트가 발동됨.
		*/
		let c_id = $(this).closest("div").data("id")
		// alert("cmt-item-del : " + c_id)
		$.ajax({
			url : "${rootPath}/comment/delete/",
			data : {
				c_id : c_id,
				b_id : "${BBS.b_id}"
			},
			type : "POST",
			success : function(result) {
				$("div.cmt-list").html(result)
			},
			error: function(){
				alert("서버 통신 오류")
			}
			
		})
	})
	
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
			
			/* ajax를 사용해서 form에 담긴 데이터를 controller로 전송해보기 */
			// form에 입력한 inputbox에 있는 데이터를 한줄로 쭉 나열하라
			var formData = $("form").serialize()
			
			$.ajax({
				url : "${rootPath}/comment/insert",
				data : formData,
				type : "POST",
				success : function(result){
					$("div.cmt-list").html(result)
					$(".form-control").val("")
				},
				error : function(){
					alert("서버 통신 오류")
				}
			})
			
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

<div class="cmt-list mt-5">
	<h3>Comment List</h3>
	<hr />

	<div class="p-4">
		<%@ include file="/WEB-INF/views/comment-list.jsp" %>
	</div>
</div>



<section class="container-fluid">
	<h2>Comment</h2>
	<hr/>
	<form action="${rootPath}/comment/insert" method="POST">
		<div class="row d-flex justify-content-center">
			<div class="form-group col-2">
				<input class="form-control" placeholder="작성자" name="c_writer">
			</div>
				<input type="hidden" value="${BBS.b_id}" name="c_b_id">
			<div class="form-group col-8">
				<input class="form-control" placeholder="댓글" name="c_subject">
			</div>
			<div class="form-group col-2">
				<button type="button" class="btn btn-primary">저장</button>
			</div>
		</div>
	</form>
</section>
</body>