<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<%@include file="/WEB-INF/views/include/include-head.jsp" %>
<script>
$(document).ready(function(){
	  $('[data-toggle="popover"]').popover();   
})
</script>
<style>


</style>

<div class="nav-box">
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	
	  <ul class="navbar-nav">
		  	<li class="nav-item">
		      <a class="nav-link" href="${rootPath}/">Home</a>
		    </li>
		  
		    <li class="nav-item">
		      <a class="nav-link" href="${rootPath}/list">BucketList</a>
		    </li>
	  </ul>	 
	  
	  <form class="form-inline ml-auto">
    	<input class="form-control mr-sm-2" id="bkSubject" name="bkSubject" placeholder="search">
    	<button class="btn btn-light">search</button>
  	  </form>   
		    
	    
	  <ul class="navbar-nav ml-auto">
	    
	    <c:if test="${empty MEMBER}">
		    <li class="nav-item">
		      <a class="nav-link" href="${rootPath}/member/login">SignUp</a>
		    </li>
		    <li class="nav-item">
		      <a class="nav-link" href="${rootPath}/member/join">SignIn</a>
		    </li>
	    </c:if>
	    
	    <c:if test="${!empty MEMBER}">
	    
	    	 <li class="nav-item"> 	
				<a class="nav-link">Hello! "${MEMBER.m_id}"</a>	      
		    </li>
		    
		    <li class="nav-item">
		      	<a class="nav-link" href="${rootPath}/member/logout" data-toggle="popover" data-trigger="hover" data-content="Really?!?">SignOut</a>
		    </li>
		   
		</c:if>    
	    
	  </ul>
	</nav>

</div>
