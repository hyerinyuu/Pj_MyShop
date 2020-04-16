<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jsp" %>
<%@ include file="/WEB-INF/views/include/include-nav.jspf" %>
<script>
	$(function(){
		$("input").prop("readonly", true)
		$("#btn-save").prop("disabled", true)
		
		$(document).on("click", "p#auth_append", function(){
			let auth_input = $("<input/>", {class:"form-control auth", name:"auth"})
			
			// auth_input.append($("<p/>", {text:'제거',class:'auth_delete'}))
			
			$("div#auth_box").append(auth_input)
			
		})
		
		$(document).on("click", "#btn-update", function(){
			
			let pass = $("#password").val()
			if(pass == ""){
				alert(" 비밀번호를 입력하신 후\n 다시 수정버튼을 클릭하세요")
				$("div.password").css("display", "block")
				$("#password").prop("readonly", false)
				$("#password").focus()
				return false;
		}
			
			if(pass != ""){
				
				$.ajax({
					url : '${rootPath}/user/password',
					method: 'POST',  // post로 데이터를 보낼때는 반드시 csrf token도 같이 보내줘야함(get은 상관x)
					data : {
							password:pass,
							"${_csrf.parameterName}" : "${_csrf.token}"
						   },
					success: function(result){
						if(result == 'PASS_OK'){
							$("input").prop("readonly", false)
							$("input").css("color", "blue")
							$("button#btn-save").prop("disabled", false)
							$("button#btn-update").prop("disabled", true)
						}else{
							alert("비밀번호를 다시 확인하시고 입력하세요")
							$("#password").focus()
						}
							
					},
					error : function(result){
						alert('서버 통신 오류')
					}
				})
			}
		})
		
	})

</script>
</head>
<style> 
.mypage-form{
	margin-top : 250px;
}

form div.password {
	display: none;
}
</style>
<body>
<div class="container mypage-form">
	<form:form modelAttribute="userVO">
		<div class="form-group">
			<form:input path="username" class="form-control"/>
		</div>
		
		<div class="form-group password">
			<input class="form-control" id="password" type="password" placeholder="비밀번호를 입력하세요">
		</div>
		
		<div class="form-group">
			<form:input path="email" class="form-control" />
		</div>
		
		<div class="form-group">
			<form:input path="phone" class="form-control"/>
		</div>
		
		<div class="form-group">
			<form:input path="address" class="form-control"/>
		</div>
		
		<div class="form-group" id="auth_box">
		<p id="auth_append">추가</p>
			<c:if test="${not empty userVO.authorities}">
				<c:forEach items="${userVO.authorities}" var="auth">
					<input name="auth" class="form-control auth" value="${auth.authority}">
				</c:forEach>
			</c:if>		
		</div>
		
		<div>
			<button type="button" id="btn-update" class="btn btn-primary">수정</button>
			<button type="submit" id="btn-save" class="btn btn-danger">저장</button>
		</div>
	</form:form>
</div>

</body>
</html>