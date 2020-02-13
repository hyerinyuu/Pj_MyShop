<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}"></c:set>
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

.login-form {
	width: 400px;
	padding: 40px;
	
	background: white;
	text-align: center;
	z-index: 10;
	
	border-radius: 20px;
	border: 2px solid gray;
	box-shadow: 12px 12px 2px 1px rgba(0, 0, 255, 0.2);
	
	margin: 20px auto;
	
}

.login-form h2 {
		
	color: gray;
	font-weight: bold;
	font-size: 36px;
}

.login-form h3 {
		
	color: black;
	font-weight: bold;
	font-size: 20px;
	background-color: red;
	border-radius: 20px;
}


.login-form input {
	
	background: none;
	margin: 10px auto;
	padding: 14px 10px;
	text-align: center;
	width: 200px;
	outline: none;
	color: black;
	border-radius: 25px;
	border: 2px solid #3498db;
	transition: 0.2s;
}

.login-form button {
	border: 2px solid #2ECC71;
	
	padding: 14px 40px;
	margin: 20px auto;
	
	background: none;
	display: block;
	
	outline: none;
	color: gray;
	
	border-radius: 15px;
	
	
	cursor: pointer;
}

.login-form input:focus {
	
	width: 280px;
	border-color: #2ECC71;
	
}

.login-form button:hover{
	background-color: #2ECC71;
	
}

/*
	div box에 image를 2개 가져와서
	초록색 버튼만 보여준 다음 hover시에 display를 inlineblock으로 설정해
	흰색으로 바뀌는것처럼 표현
*/
.naver_login img {
	border-radius: 7px;
}

.naver_login img:last-child {
	display: none;
}

.naver_login:hover img:first-child{
	display: none;
}

.naver_login:hover img:last-child {
	display: inline-block;
}

</style>

<script>
	
	$(function(){
		$("#btn-join").click(function(){
			document.location.href="${rootPath}/auth/join"
		})
			
		$("#btn-login").click(function(){
			
			// 유효성 검사
			// id, password가 입력되지 않았을 때 경고
			let u_id = $("#u_id").val()
			if(u_id == ""){
				alert("아이디를 입력하세요")
				$("#u_id").focus
				return false
			}
			
			var params = $("form").serialize();
			$.ajax({
				url : "${rootPath}/member/login",
				type : 'POST',
				data: params,
				success:function(result){
					if(result == "LOGIN_OK"){
						document.location.href="${rootPath}/"
					}
					 
				}
			})
			
		})
	})
</script>

<form:form method="POST" action="${rootPath}/login" class="login-form">
	<h2>LOGIN</h2>
	
	<c:if test="${param.error != null}">
		<h3>아이디 혹은 비밀번호를</h3> 
		<h3>다시 확인하시고 입력해주세요</h3>
	</c:if>
	
	<c:if test="${LOGIN_MSG == 'TRY'}">
		<h3>login required</h3>
	</c:if>
	
	<c:if test="${LOGIN_MSG == '0'}">
		<h3>WELCOME</h3>
	</c:if>

	<c:if test="${LOGIN_MSG == 'NO_AUTH'}">
		<h3>작성자만이 볼 수 있음!!</h3>
	</c:if>
	
	<%
	/*
		<input type="hidden"name="${_csrf.parameterName}" value="${_csrf.token}">
	*/
	 %>
	<input type="text" id="id" name="username" placeholder="userID">
	<input type="password" id="password" name="password" placeholder="password">
	<button type="submit" id="btn-login-s">SIGN IN</button>
	<button type="button" id="btn-join">SIGN UP</button>
	
</form:form>