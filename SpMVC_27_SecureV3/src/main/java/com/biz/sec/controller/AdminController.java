package com.biz.sec.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.sec.domain.UserDetailsVO;

@Controller
@RequestMapping(value="/admin")
public class AdminController {

	@ResponseBody
	@RequestMapping(value="",method=RequestMethod.GET)
	public Principal admin(Principal principal) {

		return principal;
	}
	
}
