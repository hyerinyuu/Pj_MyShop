package com.biz.sec.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.sec.domain.AuthorityVO;
import com.biz.sec.domain.UserDetailsVO;
import com.biz.sec.domain.UserVO;
import com.biz.sec.persistance.AuthoritiesDao;
import com.biz.sec.persistance.UserDao;

// classdp @AutoWired Annotaion을 붙인것과 같은 역할
// 2020-04-14 생성자를 별도로 만드려고 required~~ 주석처리
// @RequiredArgsConstructor
@Service
public class UserService {

	// 변수를 private final로 설정하고 RequiredArgsConstructor Annotation을 설정해주면 된다.
	// security-context에 설정해놓은 passwordEncode를 가져와서 초기화해주는 생성자.(id값과 객체명을 똑같이 지정해줘야함.)
	private final PasswordEncoder passwordEncoder;
	private final UserDao userDao;
	private final AuthoritiesDao authDao;
	
	@Autowired
	// 생성자부분에 테이블을 create하는 변수를 만들고 Dao의 create_table 호출
	public UserService(PasswordEncoder passwordEncoder, UserDao userDao, AuthoritiesDao authDao) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userDao = userDao;
		this.authDao = authDao;
		
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
	@Transactional
	public int insert(String username, String password) {
		
		// 회원가입 form에서 전달받은 password값을 암호화시키는 과정
		String encPassword = passwordEncoder.encode(password);
		UserVO userVO = UserVO.builder().username(username).password(encPassword).build();
		
		int ret = userDao.insert(userVO);
		
		List<AuthorityVO> authList = new ArrayList<AuthorityVO>();
		authList.add(AuthorityVO.builder().username(userVO.getUsername()).authority("ROLE_USER").build());
		authList.add(AuthorityVO.builder().username(userVO.getUsername()).authority("USER").build());
		authDao.insert(authList);
		
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

	public boolean check_password(String password) {

		// login한 사용자의 비밀번호 뽑아내기
		// 이경우에는 본인만 수정이 가능하고
		// 관리자가 수정하기는 안됨
		UserDetailsVO userVO = (UserDetailsVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		// 일치하면 true를 불일치하면 false를 return
		return passwordEncoder.matches(password, userVO.getPassword());
		
	}

	@Transactional
	public int update(UserDetailsVO userVO, String[] authList) {
		
		Authentication oldAuth = SecurityContextHolder.getContext().getAuthentication();
		
		// session에서 username과  password, enabled, accountNonExpired 등등은 지금 update를 수행하지 않아도 되는 상황임
		// 따라서 email과 phone, address등만 다시 세팅을 해주고 
		// 그 외의 값은 유지를 해줘야함
		// 아니면 session에서 삭제되서 수정 후에 다시 재수정을 위해 비밀번호를 입력하면
		// 비밀번호가 틀렸다는 오류가 계속 발생함
		UserDetailsVO oldUserVO = (UserDetailsVO) oldAuth.getPrincipal();
		oldUserVO.setEmail(userVO.getEmail());
		oldUserVO.setPhone(userVO.getPhone());
		oldUserVO.setAddress(userVO.getAddress());
		
		int ret = userDao.update(userVO);
		
		// update를 수행했는데도 view화면의 value가 변하지 않는 문제 발생
		// session에 value가 update되지 않은 탓
		// DB update가 성공하면 로그인된 session정보를 update 수행
		if(ret > 0) {
			
			// authDao.update(new ArrayList(Arrays.asList(authList)));
			List<AuthorityVO> authCollection = new ArrayList();
			for(String auth : authList) {
				
				if(!auth.isEmpty()) {
					AuthorityVO authVO = AuthorityVO.builder().username(userVO.getUsername()).authority(auth).build();
					authCollection.add(authVO);
				}
				
			}
			authDao.delete(userVO.getUsername());
			authDao.insert(authCollection);
			
			// 새로운 session정보 만들기
			Authentication newAuth = new UsernamePasswordAuthenticationToken(oldUserVO, // 변경된 사용자 정보 
																			 oldAuth.getCredentials(),
																			 this.getAuthorities(authList));  // 변경된 ROLE 정보
			
			SecurityContextHolder.getContext().setAuthentication(newAuth);
		}
		
		return ret;
	}
	
	private Collection<GrantedAuthority> getAuthorities(String[] authList){
		
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(String auth : authList) {
			
			// 빈칸이 아닐경우에만 추가
			if(!auth.isEmpty()) {
				SimpleGrantedAuthority sAuth = new SimpleGrantedAuthority(auth);
				authorities.add(sAuth);
			}
			
		}
		return authorities;
	}

	
}
