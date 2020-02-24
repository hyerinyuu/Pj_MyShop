package com.biz.shop.controller.user;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.shop.domain.CartVO;
import com.biz.shop.domain.ProductVO;
import com.biz.shop.service.CartService;
import com.biz.shop.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(value="/user/product")
@Controller
public class B2C_Controller {

	private final ProductService pService;
	private final CartService cartService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
		
		ProductVO pVO = new ProductVO();
		List<ProductVO> pList = pService.selectAll();
		
		model.addAttribute("PVO", pVO);
		model.addAttribute("PLIST", pList);
		model.addAttribute("BODY", "PRO-LIST");
		
		return "home";
	}
	
	@RequestMapping(value="/detail/{id}", method=RequestMethod.GET)
	public String detail(@PathVariable("id") String id,Model model) {
		
		ProductVO pVO = new ProductVO();
		Long longId = Long.valueOf(id);
		pVO = pService.findById(longId);
		
		model.addAttribute("pVO", pVO);
		model.addAttribute("BODY", "PRO-DETAIL");
		return "home";
	}
	
	/*
	 * Authentication : spring sequrity 로그인 된 사용자 정보 추출을 위한 인터페이스
	 */
	@ResponseBody
	@RequestMapping(value="/cart", method=RequestMethod.POST)
	public String cart(CartVO cartVO , Authentication authen) {

		try {
			// 스프링 시큐리티로 로그인한 사용자 username 추출
			cartVO.setUsername(authen.getPrincipal().toString());
		} catch (Exception e) {
			return "LOGIN_FAIL";
		}
		cartService.save(cartVO);
		return "OK";
		// return "LOGIN USER : " + authen.getPrincipal();
	}
	
	@RequestMapping(value="/cart_view", method=RequestMethod.GET)
	public String cart_view(Model model, Authentication authen, CartVO cartVO) {
		
		model.addAttribute("BODY", "CART_VIEW");
		try {
			String username = authen.getPrincipal().toString();
			List<CartVO> cList = cartService.selectCart(username);
			model.addAttribute("CART_LIST", cList);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "home";
	}
	
	
}
