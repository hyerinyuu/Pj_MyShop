package com.biz.ajax.domain;

import java.io.Serializable;

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
// vo를 map에 담으려면 serializable을 해줘야함.
public class UserVO implements Serializable{
	
	private String userId;
	private String password;
	private String userName;
	private String userRole;
	
	// method명을 getSmapleVO ==> sampleVO로 변경
	// ajax로 데이터를 보낼때 200오류가 뜸
	// ==> get Method는 필드변수가 있다라고 인식을 하기 때문에
	// jackson이 json으로 변경하는 동안에 착각을 하고
	// 오류를 일으킬 수 있다.
	public UserVO sampleVO() {
		UserVO userVO = UserVO.builder()
				.userId("admin")
				.password("12345")
				.userName("홍길동")
				.userRole("admin").build();
		
		return userVO;
		
	}

}
