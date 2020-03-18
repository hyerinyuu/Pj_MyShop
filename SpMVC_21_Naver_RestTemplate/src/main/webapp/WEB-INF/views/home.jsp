<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 홈페이지</title>
</head>
<body>

<form action="${rootPath}/search" method="POST">
	<select name="cat">
		<option value="NEWS">뉴스</option>
		<option value="MOVIE">영화</option>
		<option value="BOOK">도서</option>
	</select>
	<input name="search" placeholder="검색어를 입력하세요" />
</form>

</body>
</html>