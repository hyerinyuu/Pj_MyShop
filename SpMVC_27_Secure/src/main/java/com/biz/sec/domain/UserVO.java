package com.biz.sec.domain;

import java.util.Collection;

import org.apache.ibatis.type.Alias;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * Spring Security와 연동하여 회원정보를 관리하기 위한 
 * UserVO는 단독으로 작성하지 않고
 * 
 * UserDetail이라는 Interface를 implements하여 작성한다.
 * 
 * Spring security에서 제공하는 User라는 클래스를 상솧가여 사용할 것이다.
 * 
 * UserVO는 인스턴스를 생성할 때
 * 생성자를 사용하여 초기 값을 설정하도록 디자인되어있다.
 */
@Alias("userVO")
public class UserVO extends User{
	
	/**
	 * vo객체를 map에 담아서 req, res에 실어서 보낼 대
	 * 객체를 문자열형으로 변환하는 과정이 있다.
	 * 이 과정을 serialize라고 하는데, 각 변환된 문자열이 서로 흐트러지지 않게 하도록 설정하는 Id
	 */
	private static final long serialVersionUID = 1L;  // add default serialId

	// 이미 기존에 상속받은 username등은 getter와 setter가 존재하기 때문에
	// 여기에 있는 추가적인 필드변수에만 getter와 setter를 추가하라는 소리
	// (클래스 선언부에 추가하면 기존변수 + 추가변수 모두 getter와 setter가 추가됨)
	@Getter @Setter
	private String email;
	
	@Getter @Setter
	private String phone;
	
	@Getter @Setter
	private String addr;
	
	
// 입력 안하면 오류나서 잠시 주석처리	
//	@Getter @Setter
//	private int age;
	
	public UserVO(String username, String password, boolean enabled) {
		super(username, password, enabled, true, true, true, null);
	}
	
	public UserVO(String username, String password, boolean enabled, 

			String email, String phone, String addr, 
			// int age,
			boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
				this.email = email;
				this.phone = phone;
				this.addr = addr;
				// this.age = age;
	}
	
	
	

	

}
