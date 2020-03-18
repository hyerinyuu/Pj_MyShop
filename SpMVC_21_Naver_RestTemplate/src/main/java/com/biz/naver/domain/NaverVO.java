package com.biz.naver.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NaverVO {
	
	// 뉴스
	private String title; 				// 제2의 전성기 맞은 ‘ 짜파게티’ 오스카 시상식 이후 2 월 해외매출 120% 성...",
	private String originallink; 		// http://www.lawissue.co.kr/view.php?ud=202003181328035428204ead0791_12",
	private String link; 				// http://www.lawissue.co.kr/view.php?ud=202003181328035428204ead0791_12",
	private String description; 		//  농심의 대표 제품 신라면 못지 않은 관심을 받고 있는 짜파게티는 국내는 물론 해외 소비자들이 <b>기생충</b>... 2 월 9 일( 미국시간) <b>기생충</b>의 오스카 수상소식이 전해진 후 세계 각지에서 짜파게티 구매에 나섰다고 볼 수 있다.... ",
	private String pubDate; 
	
	// 영화
	private String image;
	private String subtitle;
	private String director;
	private String actor;
	private String userRating;
	
	// 책
	private String author;
	private String price;
	private String discount;
    private String publisher;
    private String pubdate;
    private String isbn;
    
}
