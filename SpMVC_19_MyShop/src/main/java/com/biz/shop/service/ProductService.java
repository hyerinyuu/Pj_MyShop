package com.biz.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.biz.shop.domain.ProductVO;
import com.biz.shop.persistance.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

	private final ProductRepository pDao;
	
	public void save(ProductVO productVO) {

		ProductVO p = pDao.save(productVO);
		log.debug("상품정보 : " + p.toString());
	}
	
	public List<ProductVO> selectAll(){
		
		List<ProductVO> proList = pDao.findAll();
		return proList;
	}

	public ProductVO findByPCode(long id) {

		// ProductVO proVO = pDao.findBy
		return null;
	}

	public ProductVO findById(long id) {
		
		/*
		 * hibernate의 기본 조회 method들은
		 * 모든 VO 클래스를 optional 클래스로 감싸서 return함.
		 * 
		 * 이것은 혹시 모를 null point exception을 방지하기 위한 조치이다.
		 * 실제 VO 객체를 추출할때는 return Obj.get()를 사용한다.
		 */
		Optional<ProductVO> proVO = pDao.findById(id);
		
		return proVO.get();
	}
	
	
}
