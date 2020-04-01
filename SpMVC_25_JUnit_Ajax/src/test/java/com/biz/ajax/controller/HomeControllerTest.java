package com.biz.ajax.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/*
 *  [.class] 형식의 코드
 *  매개변수로 실제 *.class파일을 직접 주입하고
 *  그 파일을 실행하여 결과를 스스로 알아서 가져가라
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/*-context.xml"})
public class HomeControllerTest {

	/*
	 * 가상의 Mock class를 생성하고, 의존성 주입을 하기 위한 도구
	 * @Before method 내부에서 초기화를 해주기 때문에 따로 초기화를 수행하지 않아도 된다.
	 */
	MockMvc mockMvc;
	
	// Home Controller를 단독으로 테스트 하겠다.
	@InjectMocks
	private HomeController hController;
	
	@Before
	public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(hController).build();
		
	}

	@Test
	public void testHome() throws Exception {

		// http://localhost:8080/으로 request를 요청하라
		// controller가 상태코드 200(OK)를 response 했느냐?
		mockMvc.perform(get("/"))
		// controller의 응답코드가 200이냐?
		.andExpect(status().isOk())
		// controller가 마지막에 return한 view가 home.jsp이냐?
		.andExpect(view().name("home"));
	}

}
