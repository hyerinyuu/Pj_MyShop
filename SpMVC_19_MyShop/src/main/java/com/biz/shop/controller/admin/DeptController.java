package com.biz.shop.controller.admin;


import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.shop.domain.DeptVO;
import com.biz.shop.service.DeptService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@SessionAttributes("deptVO")
@RequestMapping(value="/admin/dept")
@Controller
public class DeptController {
	
	private final DeptService dService;
	
	@ModelAttribute("deptVO")
	public DeptVO newDeptVO() {
		return new DeptVO();
	}
	
	// /admin/dept로 매핑
	@RequestMapping(value= {"/", ""}, method=RequestMethod.GET)
	public String input( Model model, SessionStatus status) {
		
		DeptVO deptVO = new DeptVO();
		
		this.modelMapping(model);
		model.addAttribute("deptVO", deptVO);
		// status.setComplete();
		return "admin/main";
	}
	
	@RequestMapping(value="/input", method=RequestMethod.POST)
	public String input(@Valid @ModelAttribute("deptVO") DeptVO deptVO, BindingResult result, Model model, SessionStatus status) {
		
		log.debug("DEPT:" + deptVO.toString());
		if(result.hasErrors()) {
		
			log.debug("DEPT-ERRORE:" + deptVO.toString());
			this.modelMapping(model);
			model.addAttribute("deptVO",deptVO);
			return "admin/main";
			
		}
		
		log.debug("DEPT-SAVE:" + deptVO.toString());
		DeptVO ret = dService.save(deptVO);
		status.setComplete();
		return "redirect:/admin/dept";
		
	}
	
	@RequestMapping(value= {"/search/{search}", "/search/", "/search"}, method=RequestMethod.GET)
	public String search(@PathVariable(name = "search", required = false) String search, Model model) {
		this.modelMapping(model,search);
		return "admin/deptList";
	}

	private void modelMapping(Model model, String search) {
		
		List<DeptVO> deptList = null;
		if(search == null) {
			deptList = dService.selectAll();
		}else {
			deptList = dService.findByDName(search);
		}
		model.addAttribute("DEPT_LIST", deptList);
		model.addAttribute("BODY", "DEPT");
		
	}
	
	private void modelMapping(Model model) {
		this.modelMapping(model, null);
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String update(@PathVariable("id") String strId, @ModelAttribute("deptVO")DeptVO deptVO, Model model) {
			
		this.modelMapping(model);
		long id = Long.valueOf(strId);
		deptVO = dService.findById(id);
		model.addAttribute("deptVO", deptVO);
		
		return "admin/main";
		
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
		
		this.modelMapping(model);
		return "admin/deptList";
	}
	
	
	
	
	
	
}
