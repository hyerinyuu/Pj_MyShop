<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
$(function(){
	
	$(".dept_tr").click(function(){
		let id = $(this).data("id")  // attr("data-id") id는 자주 써서 함수로 만들어버림
		let c = $(this).attr("class") // class값을 가져오는 함수
		
		document.location.href="${rootPath}/admin/dept/update/" + id
		
	})
	
})

</script>
<style>
tr td{
	cursor: pointer;
}

</style>
<table class="col-md-4 col-12">
	<tr>
		<th>거래처코드</th>
		<th>거래처명</th>
		<th>대표자명</th>
		<th>사업자번호</th>
		<th>대표전화</th>
		<th>주소</th>
		<th>담당자</th>
		<th>담당자연락처</th>
		<th>비고</th>
	</tr>
	<c:choose>
		<c:when test="${empty DEPT_LIST}">
			<tr>
				<td colspan="9">상품정보가 없습니다.</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach var="DEPT" items="${DEPT_LIST}" varStatus="i">		
				<tr class="dept_tr" data-id="${DEPT.id}">		
					<td>${DEPT.d_code}</td>
					<td><span class="d_name">${PRO.d_name}</span></td>
					<td>${DEPT.d_ceo}</td>
					<td>${DEPT.d_sid}</td>
					<td>${DEPT.d_tel}</td>
					<td>${DEPT.d_addr}</td>
					<td>${DEPT.d_manager}</td>
					<td>${DEPT.d_mtel}</td>
					<td>${DEPT.d_rem}</td>
				</tr>	
			</c:forEach>
		</c:otherwise>
	</c:choose>	
</table>
