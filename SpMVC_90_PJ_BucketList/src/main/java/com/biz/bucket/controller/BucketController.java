package com.biz.bucket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.bucket.domain.BucketVO;
import com.biz.bucket.service.BucketService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BucketController {
	
	@Autowired
	@Qualifier("bucketServiceV1")
	private BucketService bkService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
		
		List<BucketVO> bkList = bkService.selectAll();
		model.addAttribute("BKLIST", bkList);
		return "home";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute BucketVO bkVO) {
		
		log.debug("insert값 : " + bkVO.toString());
		int ret = bkService.insert(bkVO);
		
		return "redirect:/list";
		
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@ModelAttribute BucketVO bkVO) {
		
		int ret = bkService.update(bkVO);
		return "redirect:/list";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(String id) {
		
		log.debug("####삭제 id값 : " + id);
		
		int ret = bkService.delete(id);
		return "redirect:/list";
		
	}
	
	@RequestMapping(value="/compStateUpdate", method=RequestMethod.POST)
	public String compStateUpdate(String id) {
		
		bkService.compStateUpdate(id);
		return "redirect:/list";
	}
	
	

}
