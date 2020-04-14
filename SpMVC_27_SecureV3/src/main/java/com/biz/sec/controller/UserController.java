package com.biz.sec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.sec.domain.UserDetailsVO;
import com.biz.sec.domain.UserVO;
import com.biz.sec.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping(value="/user")
public class UserController {

	private final UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "auth/login";
	}
	
	@ResponseBody
	@RequestMapping(value="/idcheck", method=RequestMethod.GET)
	public String idCheck(String username) {
		
		boolean ret = userService.isExistsUserName(username);
		if(ret) {
			return "USED";
		}
		return "YES";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		
		return "auth/join";
	}
	
	@ResponseBody
	@RequestMapping(value="/join", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String join(String username, String password) {
		
		log.debug("아이디 {}, 비번 {}", username, password);
		
		userService.insert(username, password);
		
		return String.format("<p>아이디 : </p> <b>%s</b> <br/> <p>비밀번호 : </p> <b>%s</b>", username, password);
		// return "redirect:/";
	}
	
	
	@ResponseBody
	@RequestMapping(value="", method=RequestMethod.GET)
	public String user() {
		
		
		return "user HOME";
	}
	
	
//	@ResponseBody
	@RequestMapping(value="/mypage", method=RequestMethod.GET)
	public String mypage(long id, Model model) {
		
		UserDetailsVO userVO = userService.findById(id);
		model.addAttribute("userVO", userVO);
		// return "user/mypage";
		return "/user/mypage";
	}
	
	
	
}
