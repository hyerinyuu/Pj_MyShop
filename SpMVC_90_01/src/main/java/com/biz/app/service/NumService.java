package com.biz.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.app.domain.ScoreVO;

/*
 * Service Class
 * @Service Annotation을 설정한 Class
 * 
 * controller가 사용자의 요청을 받았는데
 * 단순한 연산을 수행해서 결과를 보낼 사안이 아닐 때
 * 1. 조금 복잡한 무언가를 수행해야 할 때
 * 		controller의 기능을 보조하는 역할을 수행한다.
 */
@Service
public class NumService {

	public int add(int num1, int num2) {
		
		int sum = 0;
		sum = num1 + num2;
		return sum;
	}

	public int even(int start, int end) {
		
		int sum = 0;
		
		for(int i = start ; i <= end; i++) {
			if(i%2 == 0) {
				sum += i;
			}
		}
		return sum;
		
	}

	// 총점 계산 method
	public int totalScore(int intKor, int intEng, int intMeth, int intSc, int intMu) {

		int totalScore = intKor + intEng + intMeth + intSc + intMu;
		
		return totalScore;
	}

	// 평균 계산 method
	public int avgScore(int intKor, int intEng, int intMeth, int intSc, int intMu) {

		int avgScore = (intKor + intEng + intMeth + intSc + intMu) / 5;
		
		return avgScore;
	}

	public void score(ScoreVO scoreVO) {
		 
		int sum = scoreVO.getKor();
		sum += scoreVO.getMath();
		sum += scoreVO.getEng();
		sum += scoreVO.getSc();
		sum += scoreVO.getMu();
		
		scoreVO.setSum(sum);
		scoreVO.setAvg(sum / 5);
		
	}
	
	
	
}
