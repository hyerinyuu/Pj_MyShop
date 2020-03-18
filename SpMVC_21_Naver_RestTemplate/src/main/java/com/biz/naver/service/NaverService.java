package com.biz.naver.service;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.biz.naver.config.NaverConfig;
import com.biz.naver.domain.NaverSearchCover;
import com.biz.naver.domain.NaverVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NaverService {

	private final String naver_news = "https://openapi.naver.com/v1/search/news.json";
	private final String naver_books = "https://openapi.naver.com/v1/search/book.json";
	private final String naver_movies = "https://openapi.naver.com/v1/search/movie.json";
	
	public List<NaverVO> naverSearch(String cat, String search) throws UnsupportedEncodingException, URISyntaxException {
		
		String queryString = URLEncoder.encode(search, "UTF-8");
		
		if(cat.equalsIgnoreCase("NEWS")) {
			queryString = naver_news + "?query=" + queryString;
		}else if(cat.equalsIgnoreCase("MOVIE")){
			queryString = naver_movies + "?query=" + queryString;
		}else if(cat.equalsIgnoreCase("BOOK")) {
			queryString = naver_books + "?query=" + queryString;
		}
		
		
		// RestTemplate으로 조회하기 위해 Header값을 설정
		HttpHeaders header = new HttpHeaders();
		
		header.set("X-Naver-Client-Id", NaverConfig.NAVER_CLIENT_ID);
		header.set("X-Naver-Client-Secret", NaverConfig.NAVER_CLIENT_SECRET);
		HttpEntity<String> entity = new HttpEntity<String>(header);
		
		// 주소 변환(위에서 만든 queryString을 URI객체로 선언)
		URI restURI = new URI(queryString);
		
		// 데이터를 받아오기 위해
		RestTemplate restTemp = new RestTemplate();
		
		// 데이터를 받아서 사용할 객체 타입 지정
		ResponseEntity<String> strResult = null;
		
		ResponseEntity<NaverSearchCover> restResult = null; 
		
		
		// restTemplate에 본격적으로 데이터 보내기(문자열 형태로 받기)
		strResult = restTemp.exchange(restURI, HttpMethod.GET, entity, String.class);
		
		// naverSearchCover에 담긴 변수들에 자동으로 분배해서 데이터를 받음
		restResult = restTemp.exchange(restURI, HttpMethod.GET, entity, NaverSearchCover.class);
		
		NaverSearchCover sc = restResult.getBody();
		log.debug("가져온 데이터 개수 {} ", sc.total);
		
		// 데이터가 없으면 null값 표시
		if(Integer.valueOf(sc.total) < 1) {
			return null;
		}else {
			return sc.items;
		}
		
		
		
		// return strResult.getBody();
		
	}
}
