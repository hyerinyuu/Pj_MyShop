<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 홈페이지</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<style>
	section {
		border : 1px solid blue;
		padding : 1rem;
		
	}
</style>
<script>
	$(function(){
		// jquery의 event handler를 작성할 때
		// $("#call_ajax").click()이라고 작성을 하는데,
		// ajax를 사용하여 server로부터 나중에 가져온 page는
		// 해당 event handler로 작동이 안된다.
		// ajax를 고려할 때 아래와 같은 handler를 작성하는 것을 
		// 자주 연습하자.
		$(document).on("click", "#call_ajax", function(){
			$.ajax({
				url : "${rootPath}/ajax",
				method : "GET",
				success : function(result){
					if(result == "OK"){
						alert(result)	
					}
				},
				error : function(){
					alert("서버통신오류")
				}
			})			
		})
	})

</script>
<script>
	// jsp외부에 script를 작성할 때는
	// el tag로 작성한 
	var rootPath = "${rootPath}"
</script>
<script src="${rootPath}/resources/js/ajax.js"></script>
</head>
<body>

<section id="main">
	<button id="call_ajax">Ajax호출</button>
	<input type="text"  id="msg"/>
	<button id="call_msg">msg 호출</button>
	<button id="call_addr">주소 호출</button>
	<button id="call_addr_view">주소 viewe 호출</button>
</section>

<section id="sub">

</section>

</body>
</html>