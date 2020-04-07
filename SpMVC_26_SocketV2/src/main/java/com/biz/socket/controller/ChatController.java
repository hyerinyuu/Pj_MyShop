package com.biz.socket.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.biz.socket.domain.MessageVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

/*
 * LifeCycle Method
 * 어떤 동작이 수행되는 과정에서 자동으로 호출되는 method들
 * 
 * LifeCycle Method를 잘 구현함으로써
 * 별도로 어떤 동작에 해당하는 복잡한 코드를 만들지 않아도 
 * 충분히 효과를 발휘할 수 있다.
 * 
 * afterConnectionEstablished
 * 서버와 클라이언트가 서로 Socket으로 연결되었을 때 호출되는 method
 * session을 별도로 저장하거나 하는 일을 수행
 * 
 * HandleTextMessage
 * 클라이언트에서 메시지를 보내면 메시지를 수신하고
 * 연산코드를 수행한 후
 * 그 결과를 다시 클라이언트에게 보내는 일을 수행
 * (nodejs등 다른 서버에서는 메시지별로 별도로 method를 독립적으로 작성하기도 함)
 * 
 * 
 * afterConnectionClosed
 * 클라이언트와 연결이 정상, 비정상적으로 종료되었을 때 수행할 일을 담당
 */
@Slf4j
@Component
public class ChatController extends TextWebSocketHandler {

	List<WebSocketSession> sessionList;
	Map<String, MessageVO> messageMap;

	public ChatController() {
		sessionList = new ArrayList<WebSocketSession>();
		messageMap = new HashMap<String, MessageVO>();
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionEstablished(session);
		
		/*
		 * 최초 어떤 사용자가 접속하면
		 * 사용자에 대한 메시지 정보를 담을 변수 초기화
		 * 
		 * session ID를 key 값으로 하는 비어있는 사용자 정보를 map에 저장
		 */
		sessionList.add(session);

		MessageVO mVO = new MessageVO();
		messageMap.put(session.getId(), mVO);

	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);

		// 공통으로 사용하는 ObjectMapper와 map
		// vo클래스를 Json형 문자열로 바로 변환시키기
		ObjectMapper objMapper = new ObjectMapper();
		Map<String, String> map = new HashMap<String, String>();

		// 여기서부터는 임의로 만든 command protocol 선언부

		// 전달받은 메시지에 command가 포함되어 있는가를 구분
		String user[] = message.getPayload().split(":");

		
		// userName: 또는 getUserList: 이런식의 메시지가 전달되었나?
		if (user.length > 1) {

			
			// command에 USERNAMEdl 포함되어 있다면
			// 채팅어플에 접속했을 때 최초 사용자 이름을 입력하면
			// 사용자 이름과 session을 정보에 저장한 후
			// 다시 클라이언트에게 알려주는 부분
			if (user[0].equalsIgnoreCase("USERNAME")) {
				MessageVO mVO = messageMap.get(session.getId());
				mVO.setUserName(user[1]);

				// home.jsp에 userName을 setting할 때 JSON type으로 데이터를 보냈더니
				// 못받아서 Map으로 JSON형식의 데이터 만듦

				map.put("msg", "userName");
				map.put("userName", mVO.getUserName());
				
				String userName = objMapper.writeValueAsString(map);
				this.sendMeMessage(session, userName);
				
				// 메시지 보내는 method 따로 빼려고 주석처리(update)
				// session.sendMessage(new TextMessage(objMapper.writeValueAsString(map)));
				return;

			// command가 GETUSERLIST이면
			// 현재 접속자정보를 모두 클라이언트로 보내기
			} else if (user[0].equalsIgnoreCase("GETUSERLIST")) {
				
				log.debug("GETUSERLIST");
			
				// userList 가져오는 부분
				String userList = objMapper.writeValueAsString(messageMap);
				map.put("msg", "userList");
				map.put("userList", userList);

				String userListMap = objMapper.writeValueAsString(map);
				
				/*
				 * {"msg":"userList",
				 * 		"userList":"{
				 * 					\"tr2iw0ji\":
				 * 							{
				 * 								\"userName\":\"1번\",\"message\":null,\"sendUser\":null
				 * 							},
				 * 					\"i3aeldxw\":
				 * 							{
				 * 								\"userName\":\"2번\",\"message\":null,\"sendUser\":null
				 * 							}
				 * 					}"
				 * 	}
				 * tr2iw0ji\ : 세션id
				 * {userList:{session:{userName:'홍길동'}}}식의 json 구조임
				 */
				log.debug(userListMap);
				
				this.sendAllMessage(session, userListMap);
				// 더이상 진행되지 못하도록 return
				return;
			}
		}

		// 채팅이 진행되는 과정에서 메시지 전파
		Gson gson = new Gson();
		
		// gson을 사용하여 문자열형 JSON데이터를 VO로 변환
		MessageVO messageVO = gson.fromJson(message.getPayload(), MessageVO.class);
		String sendMessage = String.format("%s (으)로부터 : %s", messageVO.getUserName(), messageVO.getMessage());

		// TextMessage sendTextMessage = new TextMessage(sendMessage);

		// jackson Bind의 클래스인 ObjectMapper를 사용하여
		// VO클래스를 JSON형 문자열로 바로 변환시키기

		String jSonTextMessage = objMapper.writeValueAsString(messageVO);
		this.sendNotMeMessage(session, jSonTextMessage);
	}

	
	
	// 요청한 접속자에게만 메시지 보내기
	private void sendMeMessage(WebSocketSession session, String textMessage) throws IOException {
		
		TextMessage sendMessage = new TextMessage(textMessage);
		session.sendMessage(sendMessage);
		
	}
	
	
	
	// 무조건 전체 접속자에게 메시지 보내기
	private void sendAllMessage(WebSocketSession session, String textMessage) throws IOException {

		TextMessage sendMessage = new TextMessage(textMessage);

		for (WebSocketSession ws : sessionList) {
			// 자신이 보낸 메시지는 다시 자신에게 보낼 필요가 없기 때문에 제외하고 전송해야함
				ws.sendMessage(sendMessage);
		}
	}

	// 현재 접속자(나)를 제외한 나머지 접속자에게 메시지 보내기
	private void sendNotMeMessage(WebSocketSession session, String textMessage) throws IOException {

		TextMessage sendMessage = new TextMessage(textMessage);

		for (WebSocketSession ws : sessionList) {
			// 자신이 보낸 메시지는 다시 자신에게 보낼 필요가 없기 때문에 제외하고 전송해야함
			if (!ws.getId().equals(session.getId())) {
				// ws.sendMessage(sendTextMessage);
				ws.sendMessage(sendMessage);
			}

		}
	}

	// 새로고침하면 socket이 close되는 현상 발생
	// session에 리스트가 들어있는데 새로고침을 해서
	// session에 남아있는 정보 삭제
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionClosed(session, status);

		sessionList.remove(session);

	}

}
