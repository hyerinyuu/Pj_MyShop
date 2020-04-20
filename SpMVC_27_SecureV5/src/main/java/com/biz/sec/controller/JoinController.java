package com.biz.sec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import com.biz.sec.domain.UserDetailsVO;
import com.biz.sec.service.UserService;
import com.biz.sec.utils.PbeEncryptor;

import lombok.RequiredArgsConstructor;

/**
 * SessionAttribute를 사용한 회원가입 기능 구현
 * 
 * @author hyerinyuu
 *
 */
@SessionAttributes("userVO")
@RequiredArgsConstructor
@Controller
@RequestMapping(value="/join")
public class JoinController {

	private final UserService userService;
	
	@ModelAttribute("userVO")
	public UserDetailsVO newUser() {
		
		return new UserDetailsVO();
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String join(@ModelAttribute("userVO") UserDetailsVO userVO, Model model) {
		
		return  "join/join";
	}
	
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public String user(@ModelAttribute("userVO") UserDetailsVO userVO, Model model) {
		
		return  "join/join-email";
	}
	
	@ResponseBody
	@RequestMapping(value="/joinok", method=RequestMethod.POST)
	public String joinok(@ModelAttribute("userVO") UserDetailsVO userVO, Model model) {
		
		int ret = userService.insert(userVO);
		
		// 테스트용 코드
		// String ret = userService.insert(userVO);
		
		model.addAttribute("JOIN", "EMAIL_OK");
		
		// sessionAttribute에 저장된 session값을 clear 시키기
		// session.setComplete();
		return "join/join-email";
		// return ret;
	}
	
	@ResponseBody
	@RequestMapping(value="/emailok", method=RequestMethod.GET)
	public String emailOk(@ModelAttribute("userVO") UserDetailsVO userVO, Model model, SessionStatus session) {
		
		// emailOk에서 다시 이메일을 재 인증
		boolean ret = userService.emailok(userVO.getUsername(), userVO.getEmail());
		
		session.setComplete();
		if(ret) {
			return "redirect:/user/login";
		}else {
			return "join/join_email_fail";
		}
		// return PbeEncryptor.getDecrypt(email) + PbeEncryptor.getDecrypt(username);
		
	}
	
}
