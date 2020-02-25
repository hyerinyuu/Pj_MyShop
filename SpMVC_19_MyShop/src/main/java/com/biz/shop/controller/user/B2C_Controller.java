package com.biz.shop.controller.user;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.shop.domain.CartListVO;
import com.biz.shop.domain.CartVO;
import com.biz.shop.domain.ProductVO;
import com.biz.shop.service.CartService;
import com.biz.shop.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	
	@ResponseBody
	@RequestMapping(value="/qty_update", method=RequestMethod.GET)
	public String qty_update(@RequestParam("seq") String seq, @RequestParam ("p_qty") String p_qty) {
		
		long longSeq = Long.valueOf(seq);
		int intP_qty = Integer.valueOf(p_qty);
		int ret = cartService.qty_update(longSeq, intP_qty);
		
		return ret + "";
	}
	
	@RequestMapping(value="/cart_one_delete/{seq}", method=RequestMethod.GET)
	public String cart_one_delete(@PathVariable("seq") String seq) {
		
		long longSeq = Long.valueOf(seq);
		cartService.deleteOne(longSeq);
		return "redirect:/user/product/cart_view";
		
	}
	
	@ResponseBody
	@RequestMapping(value="/cart_list_delete", method=RequestMethod.POST)
	public Integer cart_list_delete(@RequestParam("delList[]") List<String> strSeqList) {
		
		// List<String> seqList = new ArrayList<String>(Arrays.asList(strSeqList));
		log.debug("시퀀스 리스트 : " + strSeqList.toString());
		
		Integer ret = cartService.cart_list_delete(strSeqList);
		
		return ret;
		// ajax방식으로 넘겼기 때문에 redirect를 해주면
		// errors 상황에서도 alert가 안뜨고 redirect해버리니까
		// ajax부분의 success에 replace를 걸어서 넘어가게 처리함
		// return "redirect:/user/product/cart_view";
	}
	
	@RequestMapping(value="/cart_list_qty_update", method=RequestMethod.POST)
	public String cart_list_qty_update(CartListVO cartList) {
		
		log.debug("카트 : " + cartList);
		cartService.cart_list_qty_update(cartList);

		return "redirect:/user/product/cart_view";
	}
	
	@ResponseBody
	@RequestMapping(value="/cart_list_buy", method=RequestMethod.POST)
	public Integer cart_list_buy(@RequestParam("buyList[]") List<String> buyList) {
		
		Integer ret = cartService.cart_to_delivery(buyList);
		return ret;
	}
	
}
