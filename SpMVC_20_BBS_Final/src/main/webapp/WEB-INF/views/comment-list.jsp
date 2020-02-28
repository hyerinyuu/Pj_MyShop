<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<c:forEach items="${CMT_LIST}" var="CMT">
	<div class="d-flex align-items-center p-2 cmt-item" data-id="${CMT.c_id}">
		<h4>${CMT.c_writer}</h4>
		<small class="ml-2 cmt-item-del">${CMT.c_date_time} &nbsp; &nbsp; &times;</small>
	</div>
	<p class="pl-3 pr-3">${CMT.c_subject}</p>
</c:forEach>
