<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 홈페이지</title>
<!-- github /sockjs의 Sockjs client의 getting started가면 있음 -->
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<script>

	var socket = new SockJS('http://localhost:8080/socket/chat');
	// socket서버에 접속 시작하라
	socket.onopen = function() {
		
		// socket이 서버에 접속 성공한 후 최초에 실행될 코드
		let userName = prompt(" 채팅방 입장!\n 이름을 입력해주세요!")
		// 창에서 esc를 누르면 null값이 입력됨
		// == userName이 null값이 아니고 ""이 아니면
		if(userName && userName != ""){
			socket.send("userName:" + userName)	
		}
	    // console.log('open');
	    
	    // 얘를 안지우니까 illigalStatementException이 발생함
	    // 우리가 보내는 데이터구조(json)와 여기서 보내는 데이터 구조(String)가 서로 달라서 GSON이 못받아서 난 오류
	    // socket.send('Hello Web-Socket! chat-server');
	};
	
	// 서버로부터 수신되는 이벤트를 처리
	socket.onmessage = function(e) {
	    console.log('message', e.data);
	    // alert(e.data);
		// 문자열형으로 수신된 json문자열을 json 객체로 변환	    
	    let mJson = JSON.parse(e.data)
	    // 서버로부터 전달받은 데이터에 msg라는 key가 있느냐
	    // key가 있으면 key가 userName이냐?
	    if(mJson.msg && mJson.msg == 'userName'){
	    	alert(mJson.userName)
	    	
	    	$('#userName').val(mJson.userName)
	    	$("#userName").prop("readonly", true)
	    	return false;
	    }
		
		if(mJson.msg && mJson.msg == 'userList'){
			let userList = JSON.parse(mJson.userList)
			let options = $("<option/>", {value:all, text:"전체"})
			
			for(let i = 0 ; i < userList.length ; i++){
				options.append($("<option/>", 
						{value:userList[i].userName, 
						 text:userList[i].userName 
						})
			)}
		}
	    
	    
	    let html = "<div class='form text-right'>" + "<span class='userName'>" + mJson.userName + " >> " + "</span>" + mJson.message + "</div>"
	    
	    $("#message_list").append(html)
	    // sock.close();
	};
	
	// socket서버와 접속 종료
	// sock.onclose = function() {
	//    console.log('close');
	//};
</script>
<script>
	$(function(){
		
		$(document).on("submit", "form", function(){
			event.preventDefault();
			// let message = $("#message").val()
			// $("#message_list").append("me >>  " + message + "<br />")
			// socket.send(message)
		})
		
		 $(document).on("click","#btn_send",function(){
			let userName = $("#userName").val()
			
			if(userName == ""){
				alert("이름을 입력하세요!")
				return false
			}
			// userName과 message를 묶어서 JSON데이터로 생성
			let message = {
					userName : userName,
					message : $("#message").val()
			}
			
			let html = "<div class='to'>" + "<span class='userName'>" +  "나 >> " + "</span>" + message.message + "</div>"
			$("#message_list").append(html)
			
			// socket을 통해 json 데이터를 보내기 위해
			// json형 문자열로 변환시킨 후 전송
			socket.send(JSON.stringify(message))
			$("#message").val("")
		 })
		 socket.send("getUserList")
		
	})

</script>
</head>
<style>

#chat-box{
	border: 1px solid black;
	height: 500px;
	width : 500px;
	overflow: auto;
}

.from, .to {
	padding : 7px;
}

.userName {
	color : blue;
	font-weight: bold;
	font-style: italic;
}

</style>
<body>
<header class="jumbotron text-center bg-success">
	<h2 class="text-white"> MY CHATTING SERVICE</h2>
</header>

<section id="chat-box" class="container-fluid">
	<p>Have Fun!</p>
	<hr/>
	<div id="user_list" class="col-3">
				
	</div>
	<div id="message_list" class="col-8">
		
	</div>
</section>

<section class="container-fluid">
	<form>
		<div class="form-group">
			<label for="userName">From</label>
			<input id="userName" class="form-control" placeholder="보내는 사람"><br/>
		</div>
		
		<div class="form-group">
			<label for="toList">받는사람</label>
			<select id="toList" class="form-controll">
				<option value="all">전체</option>
			</select>
			
		</div>
		
		<div class="form-group">
			<label for="message">메시지</label>
			<input id="message"  class="form-control" placeholder="메시지를 입력하세요">
		</div>
		<button id="btn_send" class="btn btn-primary">전송</button>
	</form>
</section>



</body>
</html>