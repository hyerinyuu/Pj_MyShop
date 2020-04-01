package com.biz.ajax.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.biz.ajax.domain.UserVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/*-context.xml"})
public class UserControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private UserController userController;
	
	@Before
	public void setUp() throws Exception {
		
		// @InjectMocks로 설정한 클래스의 의존성 주입
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	public void testSaveUser() throws Exception {
		
		UserVO userVO = new UserVO();
		 
		/*
		 * saveUser를 POST방식으로 호출하면서
		 * 4개의 requestParam(parameter) 데이터를 주입하고
		 * 결과가 200인지 검사하고
		 * 최종적으로 model에 담겨서 return되는 값이 userVO인지 테스트
		 */
		mockMvc.perform(post("/saveUser")
		// parameter로 userVO를 주입해라
		.param("userId","userVO", userVO.sampleVO().getUserId())
		.param("password","userVO", userVO.sampleVO().getPassword())
		.param("userName","userVO", userVO.sampleVO().getUserName())
		.param("userRole","userVO", userVO.sampleVO().getUserRole())
		).andExpect(status().isOk())
		.andExpect(model().attributeExists("userVO")).andReturn();
		
	}
	
	@Test
	public void sendUserIdTest() throws Exception {
		mockMvc.perform(post("/sendUserId")
				.param("userId", "admin")
				.param("password", "1234")
				.param("userName", "John Doe")
				.param("userRole", "admin")
				).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				// data의 type이 json이냐? json이라면 userId가 존재하는가?
				.andExpect(jsonPath("$.userVO.userId").exists())
				// 존재한다면 userId의 값이 admin인가?
				.andExpect(jsonPath("$.userVO.userId", is("admin")))
				.andExpect(jsonPath("$.userVO.password").exists())
				.andExpect(jsonPath("$.userVO.password", is("1234")))
				.andExpect(jsonPath("$.userVO.userName").exists())
				.andExpect(jsonPath("$.userVO.userName", is("John Doe")))
				.andExpect(jsonPath("$.userVO.userRole").exists())
				.andExpect(jsonPath("$.userVO.userRole", is("admin")));
				
				
				
	
	}

}
