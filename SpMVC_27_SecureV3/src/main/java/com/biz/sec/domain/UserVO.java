package com.biz.sec.domain;

import java.util.Collection;

import org.apache.ibatis.type.Alias;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// DB와 연동해서 순수 CRUD를 수행할 클래스로 변경
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVO{
	
	// 아래 필드변수들은 spring security에서 사용할 기본 칼럼
	private long id;
	private String username;
	private String password;
	private boolean enabled;
	
	private String email;
	private String phone;
	private String address;
	
}
