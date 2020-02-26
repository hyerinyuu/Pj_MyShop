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
	
})

</script>
<body>
<%@ include file="/WEB-INF/views/include/include-header.jspf" %>
	<section class="container-fluid">
		
		<fieldset>
			<form action="${rootPath}/insert" method=POST>
				<div class="form-group">
					<input name="b_writer" class="form-control" placeholder="작성자">
				</div>
				<div class="form-group">
					<input name="b_date_time" class="form-control" placeholder="작성일자">
				</div>
				<div class="form-group">
					<input name="b_subject" class="form-control" placeholder="제목">
				</div>
				<div class="form-group">
					<textarea rows="20" cols="50" name="b_content" class="form-control" placeholder="내용"></textarea>
				</div>
				
				<div class="form-group d-flex justify-content-end">
					<button class="btn btn-primary mr-2">저장</button>
					<button type="button" class="btn btn-success">목록으로</button>
				</div>
			</form>			
		</fieldset>
		
	</section>
</body>