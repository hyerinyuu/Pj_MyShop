package com.biz.sec.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.biz.sec.domain.UserDetailsVO;
import com.biz.sec.domain.UserVO;
import com.biz.sec.persistance.UserDao;

import lombok.RequiredArgsConstructor;

// classdp @AutoWired Annotaion을 붙인것과 같은 역할
// 2020-04-14 생성자를 별도로 만드려고 required~~ 주석처리
// @RequiredArgsConstructor
@Service
public class UserService {

	// 생성자부분에 테이블을 create하는 변수를 만들고 Dao의 create_table 호출
	public UserService(PasswordEncoder passwordEncoder, UserDao userDao) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userDao = userDao;
		
		String create_table_users = " CREATE TABLE IF NOT EXISTS tbl_users( " + 
				"	id bigint PRIMARY KEY AUTO_INCREMENT, " + 
				"	user_name varchar(50) UNIQUE, " + 
				"   user_pass varchar(125), " + 
				"   enabled boolean DEFAULT TRUE, " +
				"  	email varchar(80), " +
				"   phone varchar(20), " +
				"   address varchar(125) " +
				" ) ";
		
		
		String create_table_auth = " CREATE TABLE IF NOT EXISTS authorities( " + 
				"	id bigint PRIMARY KEY AUTO_INCREMENT, " + 
				"   username varchar(50), " + 
				"   authority varchar(50) " + 
				" ) ";
		
		userDao.create_table(create_table_users);
		userDao.create_table(create_table_auth);
		
	}
	
	
	

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
		
		UserDetailsVO userDetailsVO = userDao.findByUserName(username);

		// 이미 DB에 회원정보(username)이 저장되어 있다.
		// userVO가 null이 아닌조건을 붙이지 않으면 nullpointException이 발생함
		if(userDetailsVO != null && userDetailsVO.getUsername().equalsIgnoreCase(username)) {
			return true;
		}
		return false;
	}

	public UserDetailsVO findById(long id) {

		UserDetailsVO userVO = userDao.findById(id);
		
		return userVO;
	}
	
}
