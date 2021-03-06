package com.biz.shop.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.shop.domain.CustomUserDetails;
import com.biz.shop.domain.Users;
import com.biz.shop.persistance.AuthRepository;
import com.biz.shop.persistance.UserRepository;
import com.biz.shop.service.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(value="/auth")
@Controller
public class AuthController {
	
	private final AuthService aService;

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		
		return "login";
	}

	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		
		return "join";
	}
	
//	@ResponseBody
	@RequestMapping(value="/login_ok", method=RequestMethod.GET)
	public String login_ok() {
		CustomUserDetails cUserDetails = (CustomUserDetails)SecurityContextHolder
				.getContext().getAuthentication().getDetails();
		
		return "redirect:/user/product/list";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(Users userVO) {
		
		aService.userSave(userVO);
		return "redirect:/";
	}

	
	@RequestMapping(value="/auther", method=RequestMethod.GET)
	public String test() {
		
		return "auther/test";
	}
	
}
