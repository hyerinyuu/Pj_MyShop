<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/include/include-head.jsp" %>
<%@ include file="/WEB-INF/views/auth/join-modal.jsp" %>
<!DOCTYPE html>
<html>
<head>
</head>
<script>
	$(function(){
		$(document).on("click", "button.join", function(){
			document.location.href = "${rootPath}/user/join"
			
		})
	})

</script>
<style>
.join-form{
	font-family: 'Open Sans', sans-serif;
}

</style>
<body>
<div class="container join-form">
  <h2>LOGIN</h2>
  <form action="user/login" method="POST">
    <div class="form-group">
      <label for="username">User Id:</label>
      <input class="form-control" id="username"  name="username" placeholder="Enter user ID">
    </div>
    
    <div class="form-group">
      <label for="password">Password:</label>
      <input class="form-control" type="password" id="password" name="password" placeholder="Enter password">
    </div>
    
    <button type="submit" class="btn btn-primary">LOGIN</button>
    <button type="button" class="btn btn-danger join">JOIN</button>
  </form>
</div>

</body>
</html>