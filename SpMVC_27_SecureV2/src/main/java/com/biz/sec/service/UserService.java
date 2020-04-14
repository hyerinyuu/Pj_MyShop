package com.biz.sec.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.biz.sec.domain.UserVO;
import com.biz.sec.persistance.UserDao;

import lombok.RequiredArgsConstructor;

// classdp @AutoWired Annotaion을 붙인것과 같은 역할
@RequiredArgsConstructor
@Service
public class UserService {

	
	// 변수를 private final로 설정하고 RequiredArgsConstructor Annotation을 설정해주면 된다.
	// security-context에 설정해놓은 passwordEncode를 가져와서 초기화해주는 생성자.(id값과 객체명을 똑같이 지정해줘야함.)
	private final PasswordEncoder passwordEncoder;
	
	private final UserDao userDao;
	
	/**
	 * @since 2020-04-09
	 * @author hyerin.you@gmail.com
	 * 
	 * @param username
	 * @param password
	 * @return
	 * 
	 * 회원가입을 신청하면 비밀번호는 암호화하고,
	 * 아이디와 비밀번호를 DB Insert 수행
	 * 
	 * @update 2020-04-10
	 * Map<String,String> 구조의 VO 데이터를 UserVO로 변경
	 * 
	 */
	public int insert(String username, String password) {
		
		// 회원가입 form에서 전달받은 password값을 암호화시키는 과정
		String encPassword = passwordEncoder.encode(password);

		UserVO userVO = UserVO.builder().username(username).password(encPassword).build();
		
		int ret = userDao.insert(userVO);
		return ret;
	}

	public boolean isExistsUserName(String username) {
		
		UserVO userVO = userDao.findByUserName(username);

		// 이미 DB에 회원정보(username)이 저장되어 있다.
		// userVO가 null이 아닌조건을 붙이지 않으면 nullpointException이 발생함
		if(userVO != null && userVO.getUsername().equalsIgnoreCase(username)) {
			return true;
		}
		return false;
	}
	
}
