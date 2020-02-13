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

.join-form {
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

.join-form h2 {
		
	color: gray;
	font-weight: bold;
	font-size: 36px;
}

.join-form h3 {
		
	color: black;
	font-weight: bold;
	font-size: 20px;
	background-color: red;
	border-radius: 20px;
}


.join-form input {
	
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

.join-form button {
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

.join-form input:focus {
	
	width: 280px;
	border-color: #2ECC71;
	
}

.join-form button:hover{
	background-color: #2ECC71;
	
}

</style>

<script>
	
	$(function(){
			
		$("#btn-join").click(function(){
			
			// 유효성 검사
			// id, password가 입력되지 않았을 때 경고
			let username = $("#username")
			let password = $("#password")
			let re_password = $("#re_password")
			
			if(username.val() == ""){
				alert("아이디를 입력하세요")
				username.focus()
				return false;
			}
			if(password.val() == ""){
				alert("비밀번호를 입력하세요")
				password.focus()
				return false;
			}
			
			if(re_password.val() == ""){
				alert("비밀번호 확인을 입력하세요")
				re_password.focus()
				return false;
			}
			
			if(password.val() != re_password.val()){
				alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.")
				password.focus()
				return false;
			}
			
			$("form").submit()
		})
	})
</script>

<form:form method="POST" action="${rootPath}/auth/join" class="join-form">
	<h2>JOIN</h2>
	<input type="text" id="id" name="username" placeholder="userID">
	<input type="password" id="password" name="password" placeholder="password">
	<input type="password" id="re_password" name="re_password" placeholder="check password">
	<button type="button" id="btn-join">SIGN UP</button>
	
</form:form>