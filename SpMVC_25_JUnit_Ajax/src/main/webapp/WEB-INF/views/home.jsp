<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 홈페이지</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
/*
	 화면상의 DOM객체가 모두 그려지기 전에 event등을 설정하면
	 기능이 제대로 작동되지 않는다.
	 jquery에서 $(function(){코드}) 형식으로 작성하면
	 화면상의 모든 tag에 의한 DOM이 완전히 그려진 다음 코드들이 작동되어 
	 문제를 일으키지 않는다.
 */
$(function(){
	// $("button.userId").on("click", function() 
	$("button[type='button'].userId").on("click", function(){
		//alert("ajax 버튼 클릭")
		$.ajax({
			url : "sendUserId",
			method: "POST",
			data : // {userId : $("#userId").val()} 이렇게하면 데이터를 id밖에 못가져옴,
					$("form").serialize(),
			success:function(result){
				alert(result.RET_CODE)
				if(result.RET_CODE == "RECEIVE_OK"){
					let user = result.userVO.userId + "\n"
					user += result.userVO.password + "\n"
					user += result.userVO.userName + "\n"
					user += result.userVO.userRole + "\n"
					
					alert("사용자 : " + user)
					
				}
			}
		})
	})
	$("button.user").click(function(){
		$.ajax({
			url : "sendUser",
			method : "POST",
			data : $("form").serialize(),
			success : function(userVO){
				
				/*
					json 객체 형태의 데이터는 alert로 확인하면
					[Object, Object] 형식으로만 확인이 된다.
					이 객체를 toString() 하는 것 처럼 문자열로 풀어서
					alert로 보여달라는 코드
				*/
				alert(JSON.stringify(userVO))
				/*
					JSON형태로 받은 userVO으 ㅣ값을 사용해서
					html tag 코드를 작성해
				*/
				let html = "<p>" + userVO.userId + "</p>"
				html += "<p>" + userVO.password + "</p>"
				html += "<p>" + userVO.userName + "</p>"
				html += "<p>" + userVO.userRole + "</p>"
				
				/*
					html tag 코드를 #ret_html section부분에 끼워넣기
				*/
				$("#ret_html").html(html)
				
				// alert(userVO.userId)
			}
			
		})
	})
	
	$("button.user_html").click(function(){
		$.ajax({
			url : "html",
			data : $("form").serialize(),
			success : function(result){
				$("#ret_html").html(result)
			}
		})
	})
	
	
})
</script>
</head>
<body>
<section>
	<h2>사용자정보</h2>
	<h5>사용자ID : ${userVO.userId}</h5>
	<h5>비밀번호 : ${userVO.password}</h5>
	<h5>사용자이름 : ${userVO.userName}</h5>
	<h5>사용자권한 : ${userVO.userRole}</h5>
</section>
<section id="ret_html">

</section>
<section>
	<form action="saveUser" method="POST">
		<div>
			<input placeholder="사용자ID" name="userId"  id="userId">
		</div>
		
		<div>
			<input placeholder="비밀번호" name="password" id="password">
		</div>
		
		<div>
			<input placeholder="사용자이름" name="userName"  id="userName">
		</div>
		
		<div>
			<input placeholder="사용자권한" name="userRole" id="userRole">
		</div>
		
		<%
		/*
			button의 type을 지정하지 않으면 기본 type="submit"이 되고,
			이 버튼이 form안에 있으면
			버튼을 클릭햇을때 form안의 inputbox에 입력한 값들을 모두 모아서
			action으로 지정된 url로 모두 전송하는 기능을 수행
			
			button type="button"으로 지정하면 
			모양만 버튼이고 submit 기능이 무력화되는 button이 만들어짐
		*/
		%>
		<button type="submit">Ajax저장</button>
		<button type="button" class="userId">사용자ID전송</button>
		<button type="button" class="user">입력값 전송</button>
		<button type="button" class="user_html">입력값 html로 받기</button>
	</form>
</section>

</body>
</html>

