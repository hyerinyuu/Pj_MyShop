package com.biz.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.app.domain.ScoreVO;
import com.biz.app.service.NumService;

@RequestMapping(value="/number")
@Controller
public class NumController {

	@Autowired
	NumService nService;
	
	@ResponseBody
	@RequestMapping(value="/add", produces = "text/html;charset=UTF-8")
	public String add() {
		
		// NumService num = new NumService();
		int ret = nService.add(30, 40);
		return ret + "";
	}
	
	/*
	 * 사용자가 /number/even 이라고 요청ㅇ르 하면
	 * 1 ~ 100 숫자 중에서 짝수의 덧셈만 수행하여 결과를 알려주고 싶다.
	 */
	@ResponseBody
	@RequestMapping(value="/even", produces = "text/html;charset=UTF-8")
	public String even() {
		
		int start = 1;
		int end = 100;
		int even = nService.even(start, end);
		
		String res = String.format("%d부터 %d까지 숫자중 짝수의 합 : %d", start, end, even);
		return res;
		
	}
	
	/*
	 * 사용자가 요청한 변수 = 값의 형태는 무조건 값이 문자열이다.
	 * 매개변수 type을 int형으로 선언을 하면
	 * spring은 사용자의 변수를 수신한 후 Integer.valueof(변수) 코드를 실행하여
	 * 문자열을 숫자로 변환시키려 시도를 한다.
	 * 
	 * 그런데 수신한 값이 숫자로 변환이 불가능 하면
	 * 사용자에게 400오류를 전송한다.
	 */
	@ResponseBody
	@RequestMapping(value="/num2even", produces = "text/html;charset=UTF-8")
	public String num2even(String start, String end) {
		
		int intStart = 0;
		int intEnd = 0;
		try {
			intStart = Integer.valueOf(start);
			intEnd = Integer.valueOf(end);
			
		} catch (Exception e) {
			return "전송된 값을 숫자로 변환할 수 없습니다.";
		}
		int even = nService.even(intStart, intEnd);
		
		String res = String.format("%d부터 %d까지 숫자중 짝수의 합 : %d", intStart, intEnd, even);
		return res;
		
	}
	
	/*
	 * 국어 영어 수학 과학 음악 점수를 request로 받아서
	 * 총점과 평균을 계산한 후
	 * response하기
	 */
	@ResponseBody
	@RequestMapping(value="/score",produces="text/html;charset=UTF-8")
	public String score(String kor, String eng, String meth, String sc, String mu) {
		
		int intKor = 0;
		int intEng = 0;
		int intMeth = 0;
		int intSc = 0;
		int intMu = 0;
		
		try {
			intKor = Integer.valueOf(kor);
			intEng = Integer.valueOf(eng);
			intMeth = Integer.valueOf(meth);
			intSc = Integer.valueOf(sc);
			intMu = Integer.valueOf(mu);
			
		} catch (Exception e) {
			return "모든 점수는 숫자값만 입력하세요";		
		}

		if(intKor > 100 || intEng > 100 || intMeth > 100 || intSc > 100 || intMu > 100) {
			return "점수는 100점을 넘길 수 없습니다."; 
		}
		
		int totalScore = nService.totalScore(intKor, intEng, intMeth, intSc, intMu);
		int avgScore = nService.avgScore(intKor, intEng, intMeth, intSc, intMu);
		
		String res = String.format("국어,영어,수학,과학,음악 총점 : %d, 평균 : %d", totalScore, avgScore);
		
		return res;
	}
	
	/*
	 * 매개변수로 Model 클래스를 설정하고
	 * model 객체에 addAttribute("변수명", 값) 형식으로 내용을 추가하고
	 * jsp 파일을 return 하면
	 * 
	 * Rendering
	 * spring, tomcat은
	 * 	model에 담겨있는 값과 jsp 파일을 비교하여  
	 */
	@RequestMapping(value="/scorejsp",produces="text/html;charset=UTF-8")
	public String scorejsp(String kor, String eng, String meth, String sc, String mu, Model model) {
		
		int intKor = 0;
		int intEng = 0;
		int intMeth = 0;
		int intSc = 0;
		int intMu = 0;
		
		try {
			intKor = Integer.valueOf(kor);
			intEng = Integer.valueOf(eng);
			intMeth = Integer.valueOf(meth);
			intSc = Integer.valueOf(sc);
			intMu = Integer.valueOf(mu);
			
		} catch (Exception e) {
			return "모든 점수는 숫자값만 입력하세요";		
		}

		if(intKor > 100 || intEng > 100 || intMeth > 100 || intSc > 100 || intMu > 100) {
			return "점수는 100점을 넘길 수 없습니다."; 
		}
		
		int totalScore = nService.totalScore(intKor, intEng, intMeth, intSc, intMu);
		int avgScore = nService.avgScore(intKor, intEng, intMeth, intSc, intMu);
		
		// TOTALSCORE이라는 이름의 변수에 totalScore(총점)값을 담아서 전달하겠다.
		model.addAttribute("TOTALSCORE", totalScore);
		
		// AVGSCORE이라는 이름의 변수에 avgScore(평균)값을 담아서 전달하겠다.
		model.addAttribute("AVGSCORE", avgScore);
		
		// score.jsp파일을 읽어서 model에 담겨서 전달된 변수들과 Rendering을 수행하라
		return "score";
	}
	
	/*
	 * 사용자가 입력창을 통해
	 * 총점과 계산을 하고 싶다고 요청을 한다.
	 * 
	 * 그러면 학생의 과목 점수를 입력할 수 있는 화면 == score_input.jsp 으로 보내주는 역할을 수행한다.
	 */
	@RequestMapping(value="/score_input", method=RequestMethod.GET)
	public String score_input() {
		return "score_input";
	}
	
	@RequestMapping(value="/score_input", method=RequestMethod.POST)
	public String score_input(ScoreVO scoreVO, Model model) {

		nService.score(scoreVO);
		model.addAttribute("scoreVO", scoreVO);
		return "score_input";
	}
	
	
}
