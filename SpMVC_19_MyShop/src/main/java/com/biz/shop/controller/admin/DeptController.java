package com.biz.shop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.biz.shop.domain.DeptVO;
import com.biz.shop.domain.ProductVO;

@SessionAttributes("deptVO")
@RequestMapping(value="/admin/dept")
@Controller
public class DeptController {
	
	@ModelAttribute("deptVO")
	public DeptVO newDeptVO() {
		return new DeptVO();
	}
	
	@RequestMapping(value= {"","/"}, method=RequestMethod.GET)
	public String dept(@ModelAttribute("deptVO") DeptVO depVO, Model model) {

		model.addAttribute("BODY", "DEPT");
		return "admin/main";
	}
	
}
