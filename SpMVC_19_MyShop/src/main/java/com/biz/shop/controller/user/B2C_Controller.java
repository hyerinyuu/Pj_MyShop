package com.biz.shop.controller.user;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.shop.domain.ProductVO;
import com.biz.shop.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(value="/user/product")
@Controller
public class B2C_Controller {

	private final ProductService pService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
		
		ProductVO pVO = new ProductVO();
		List<ProductVO> pList = pService.selectAll();
		
		model.addAttribute("PVO", pVO);
		model.addAttribute("PLIST", pList);
		model.addAttribute("BODY", "PRO-LIST");
		
		return "home";
	}
	
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public String detail(Model model) {
		
		ProductVO pVO = new ProductVO();
		List<ProductVO> pList = pService.selectAll();
		
		return null;
	}
	
	
	
	
}
