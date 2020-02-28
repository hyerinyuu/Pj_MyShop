package com.biz.bbs.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.bbs.domain.BBsVO;
import com.biz.bbs.domain.CommentVO;
import com.biz.bbs.service.BBsService;
import com.biz.bbs.service.CommentService;

import lombok.RequiredArgsConstructor;

/*
 * class에 부착하는 RequestMapping
 * 		==> type 수준의 RequestMapping 또는 top level RequestMapping이라고도 한다.
 * method에 /list라고 RequestMapping을 붙이면
 * 호출 URL에 context/comment/list라고 path를 지정한다.
 */
@RequiredArgsConstructor
@RequestMapping(value="/comment")
@Controller
public class CommentController {
	
	private final CommentService cmtService;
	private final BBsService bbsService;
	
	// 게시판의 id값을 매개변수로 받아서
	// 코멘트 리스트를 보여주는 method
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String cmtList(String b_id, Model model){
		long c_b_id = Long.valueOf(b_id);
		List<CommentVO> commentList = cmtService.findByBId(c_b_id);
		model.addAttribute("CMT_LIST", commentList);
	
		return "comment-list";
		
	}

	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(CommentVO cmtVO, Model model) {
		
		cmtService.insert(cmtVO);
		/*
		 * redirect로 방향 전환을 할 때
		 * 변수에 값을 전달하고 싶으면 일반적인 방법은 ?변수=값&변수1값 형식으로 문자열 연결 연산을 수행해하는데,
		 * model에 attribute로 해당 값을 add 수행한 후 redirect를 수행하면
		 * query문자열을 자동으로 만들어서 전달한다.
		 */
		model.addAttribute("b_id", cmtVO.getC_b_id());
		return "redirect:/comment/list";

		/*
		 * <전통적인 방법>
		 * long c_b_id = cmtVO.getC_b_id();
		 * return "redirect:/detail?b_id" + c_b_id;
		 */
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(String c_id, String b_id, Model model) {
		
		cmtService.delete(Long.valueOf(c_id));
		model.addAttribute("b_id", b_id);
		return "redirect:/comment/list"; 
	}

	
	
	
	
}
