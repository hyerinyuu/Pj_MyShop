package com.biz.modeles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.biz.modeles.domain.UsersVO;
import com.biz.modeles.service.UserService;


public class UserController {
	
	@Autowired
	protected UserService userService;

	/*
	 * 클래스의 method에 
	 * @ModelAttribute를 설정하고
	 * Qualifier(객체 이름 지정하기)를 지정해주면
	 * 이 method는 controller의 다른 method가 실행되기 전에
	 * 코드를 실행하여 객체를 model에 담아주는 일을 수행한다.
	 * 
	 * view에 rendering을 위한 데이터를 내려보낼때
	 * 필요할 때마다 model을 생성하고
	 * model.addAttribute()를 실행해서 데이터를 보내는데, 
	 * 공통적으로 view에서 사용해야할 데이터가 있을 때
	 * 
	 * method에 modelAttribute()를 설정해두면
	 * view에서 해당 데이터를 가져다가 rendering용으로 사용이 가능하다.
	 * 
	 * 반드시
	 * method위에 ModelAttribute를 설정하고 반드시 객체 이름을 지정해 주어야 한다.
	 */
	@ModelAttribute("usersVO")
	public UsersVO getUser() {
		
		return userService.getUser("admin");
		// return usersVO;
	}
	
	
}
