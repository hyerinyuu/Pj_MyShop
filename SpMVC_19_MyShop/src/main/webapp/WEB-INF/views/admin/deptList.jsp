<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>

<script>
$(function(){
	
	$(".dept_tr").click(function(){
		// let id = $(this).data("id")  // attr("data-id") id는 자주 써서 함수로 만들어버림
		
		let tds = $(this).children();
		let d_code = tds.eq(0).text();
		let d_name = tds.eq(1).text();
		
		// 나를 open한 화면에 있는 #p_d_code input 박스에 d_code값을 채워 넣어라
		$("#p_d_code", opener.document).val(d_code)
		$("#d_name", opener.document).text(d_name)
		// 일반 브라우저 창닫기
		window.close()
		// IE 창닫기
		window.open('about:blank', '_self').self.close() 
		
		
		
		
		// document.location.href="${rootPath}/admin/dept/update/" + id
		
	})
	
})

</script>
<style>

tr td{
	cursor: pointer;
}

 td, th{
 	white-space: nowrap;
 }

</style>
<table class="col-md-4 col-12">
	<tr>
		<th>거래처코드</th>
		<th>거래처명</th>
		<th>대표</th>
		<th>사업자번호</th>
		<th>대표전화</th>
		<th>담당자</th>
		<th>담당자연락처</th>
	</tr>
	
	<c:choose>
		<c:when test="${empty DEPT_LIST}">
			<tr>
				<td colspan="7">거래처 정보가 없습니다.</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach var="DEPT" items="${DEPT_LIST}" varStatus="i">		
				<tr class="dept_tr" data-id="${DEPT.id}">		
					<td>${DEPT.d_code}</td>
					<td>${DEPT.d_name}</td>
					<td>${DEPT.d_ceo}</td>
					<td>${DEPT.d_sid}</td>
					<td>${DEPT.d_addr}</td>
					<td>${DEPT.d_manager}</td>
					<td>${DEPT.d_mtel}</td>
				</tr>	
			</c:forEach>
		</c:otherwise>
	</c:choose>	
</table>
