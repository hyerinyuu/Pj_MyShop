<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/include/context-menu.jsp" %>
<script>
$(function(){
	/*
		class pro_tr은 본문의 tr tag에 설정이 되어 있고,
		본문의 tr tag를 click하면 이벤트가 발생하고 함수가 실행된다.
		
		이때 tr tag가 갖는 모든 정보는 this라는 내장 객체에 담겨
		함수 내부로 전달된다.
		
	*/
	
	/*
		tr tag가 클릭되면 id값을 추출하고
		update method로 전달하기
	*/
	$(".pro_tr_1").click(function(){
		let id = $(this).attr("data-id")  // attr("data-id") id는 자주 써서 함수로 만들어버림
		let c = $(this).attr("class") // class값을 가져오는 함수
		
		document.location.href="${rootPath}/admin/product/update/" + id
		
	})
	
	var pro_call_func = function(key){
		var id = $(this).data("id")
		if(key == "edit"){
			document.location.href="${rootPath}/admin/product/update/" + id
		}else if(key == "delete"){
			if(confirm("상품정보를 삭제하시겠습니까?")){
				document.location.href="${rootPath}/admin/product/delete/" + id
			}	
		}
	}
	
	$.contextMenu({
		selector : ".pro_tr",
		items : {
			"edit" : {name:"상품 수정", icon:"edit"},
			"delete" : {name:"상품 삭제", icon:"delete"}
		},
		callback : pro_call_func 
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
		<th>상품코드</th>
		<th>상품명</th>
		<th>거래처</th>
		<th>품목</th>
		<th>매입가격</th>
		<th>판매가격</th>
	</tr>
	<c:choose>
		<c:when test="${empty PRO_LIST}">
			<tr>
				<td colspan="6">상품정보가 없습니다.</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach var="PRO" items="${PRO_LIST}" varStatus="i">		
				<tr class="pro_tr context-menu-one btn btn-naetral" data-id="${PRO.id}">		
					<td>${PRO.p_code}</td>
					<%
						/* 상품이름이 길어질 경우에 효과주려고 class선언 */
					%>
					<td><span class="p_name">${PRO.p_name}</span></td>
					<td>${PRO.p_d_code}</td>
					<td>${PRO.p_d_code}</td>
					<td>${PRO.p_iprice}</td>
					<td>${PRO.p_oprice}</td>
				</tr>	
			</c:forEach>
		</c:otherwise>
	</c:choose>	
</table>
