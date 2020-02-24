package com.biz.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.shop.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 사용자가 접속했을때 보일 화면을 만질 controller
@Slf4j
@RequestMapping(value="/user")
@Controller
public class UserController {

	@RequestMapping(value= {"/", ""}, method=RequestMethod.GET)
	public String user() {
		
		return "home";
	}
	

}
