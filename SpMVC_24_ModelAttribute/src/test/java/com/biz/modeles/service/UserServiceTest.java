package com.biz.modeles.service;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.biz.modeles.domain.UsersVO;


// 이름순으로 오름차순으로 실행하라
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/*-context.xml"}
		)
public class UserServiceTest {
	
	@Autowired
	UserService userService;

	@Test
	public void c_getUserTest() {
		
		UsersVO usersVO = userService.getUser("admin");
		assertEquals(usersVO.getUserId(), "admin");
		assertEquals(usersVO.getUserName(), "홍길동");
		
		usersVO = userService.getUser("guest");
		assertEquals(usersVO.getUserId(), "guest");
		assertEquals(usersVO.getUserName(), "성춘향");
		
		usersVO = userService.getUser("dba");
		assertEquals(usersVO.getUserId(), "dba");
		assertEquals(usersVO.getUserName(), "이몽룡");
		
		
	}
	
	@Test
	public void b_insertTest() {
		
//		UsersVO usersVO = UsersVO.builder().userId("korea").password("12345").userName("대한민국").userRole("gov").build();
		
//		int ret = userService.insert(usersVO);
				
		// ret 값이 1이며 오차가 없는 결과를 받았느냐 라고 Test를 수행한다.
		// assertEquals(ret, 1, 0);
		
		UsersVO usersVO = UsersVO.builder().userId("admin").password("12345").userName("홍길동").userRole("admin").build();
		usersVO = UsersVO.builder().userId("guest").password("12345").userName("성춘향").userRole("guest").build();
		usersVO = UsersVO.builder().userId("dba").password("12345").userName("이몽룡").userRole("dba").build();
		int ret = userService.insert(usersVO);
		assertEquals(ret, 1, 0);
		
	}
	
	@Test
	public void a_deleteTest() {
		int ret = userService.delete("admin");
		ret = userService.delete("guest");
		ret = userService.delete("dba");
		
		assertEquals(ret, 1,0);
		
	}
	
	

}
