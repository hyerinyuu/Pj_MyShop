package com.biz.ajax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.ajax.domain.BookVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/book")
@Controller
public class BookController {
	
	
	@RequestMapping(value="/input", method=RequestMethod.GET)
	public String input(BookVO bookVO, Model model) {
		
		/*
		 * @ResponseBody가 없는 controller의 method의 return값은
		 * 문자열.jsp파일을 찾으라는 요청이다.
		 * 
		 * 하지만 return값이 null이면 
		 * 현재 method를 호출할 때 사용할 url의 path('ajax/book/input')를
		 * 문자열로 인식하고 (== return "book/input";)
		 * book/input.jsp를 찾는다.(그냥 view에 만들면 얘가 못찾고 book폴더를 만들어서 집어넣어야함 
		 * path가 book/input이니까)
		 * 
		 */
		return null;
	}
	// 405오류
	// 주소가 있긴 있는데 post method가 없다.
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(BookVO bookVO, Model model) {
		
	
		
		return "book_insert";
	}
	
	// 400오류 
	// form에서 보낸 데이터를 controller에서 받을 때
	// 모든 데이터는 문자열형으로 전송된다.
	// 하지만 b_price는 int형이라서 valueOf() method를 사용해서 변경해줘야하는데
	// 그러지 않으면 그냥 ""값으로 넘어온다.
	// 400오류 == 니가 보낸 데이터를 못 받겠다, 요청을 잘 못 했다. == numberformatException
	// 내가 처리할 수 없는 코드이므로 다시 확인하고 보내시오
	// 404 rendering할 jsp가 없다.
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(BookVO bookVO) {
		
		log.debug("########### price값 : " + bookVO.getB_price());
		
		return "home";
	}
	
	

}
