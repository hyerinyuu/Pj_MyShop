package com.biz.socket.domain;

import java.io.Serializable;

import org.springframework.web.socket.WebSocketSession;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String message;
	private String sendUser;
	// private WebSocketSession wSession;
	
	// 특정사용자에게 메시지를 보내고 싶을때 사용할 sessionId값을 담을 변수
	private String toUser;
	
}
