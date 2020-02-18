<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<script>
$(function(){
	
	/*
		const
	: javascript에서 상수를 선언하는 keyword
	*/
	const KEY_F10 = 121
	const KEY_F9 = 120
	const KEY_F8 = 119
	const KEY_F7 = 118
	
	$("#p_d_code").keyup(function(e){
		/*
				e.keyCode
			f10 : 121
			f9  : 120
			f8  : 119
			f7  : 118
		*/
		if(e.keyCode == KEY_F9){
			let search = $(this).val()
			let dept_url = "${rootPath}/admin/dept/list"
			
			let win_style = "toolbar=no,"
				win_style += "scrollbar=yes,"
				win_style += "resizable=no,"
				win_style += "top=500,"
				win_style += "left=500,"
				win_style += "width=400,"
				win_style += "height=400"
			
			window.open(dept_url, "_blank", win_style)
		}
	})
	
})


</script>
	<form:form action="${rootPath}/admin/product/detail" modelAttribute="productVO">
		<div class="form-group">
			<div class="container-fluid row">
				<form:select path="p_b_code" class="custom-select-sm col-6">
					<form:option value="0">품목을 선택하세요</form:option>
					<form:option value="B0001">공산품</form:option>
					<form:option value="B0002">농산물</form:option>
					<form:option value="B0003">수산물</form:option>
				</form:select>
				<form:input path="p_d_code" class="custom-select-sm col-6" />
				<span id="d_name"></span>
			</div>
			
			<div class="container-fluid row">
				<form:errors path="p_b_code" class="in-errors col-6"/>
				<form:errors path="p_d_code" class="in-errors col-6" />
			</div>
			
		</div>
		
			<div class="form-group">
				<form:input path="p_code" class="form-control" placeholder="상품코드" />
				<form:errors path="p_code" class="in-errors" />
			</div>		
			
			<div class="form-group">
				<form:input path="p_name" class="form-control" placeholder="상품이름" />
				<form:errors path="p_name" class="in-errors" />
			</div>		
		
			<div class="container-fluid form-group row">
				<form:input path="p_iprice" class="form-control col-6" placeholder="매입단가" />
				<form:errors path="p_iprice" class="in-errors" />
				<form:input path="p_oprice" class="form-control col-6" placeholder="판매단가" />
				<form:errors path="p_oprice" class="in-errors" />
			</div>
			
			<div class="form-group">
				<button>상세정보 입력 &gt;&gt;</button>
			</div>
		</form:form>