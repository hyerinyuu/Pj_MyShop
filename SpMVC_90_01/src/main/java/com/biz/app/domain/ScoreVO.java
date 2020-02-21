package com.biz.app.domain;

public class ScoreVO {

	private int kor;
	private int eng;
	private int math;
	private int sc;
	private int mu;
	
	private int sum;
	private int avg;
	
	public ScoreVO(int kor, int eng, int math, int sc, int mu, int sum, int avg) {
		super();
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.sc = sc;
		this.mu = mu;
		this.sum = sum;
		this.avg = avg;
	}
	
	public ScoreVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getSc() {
		return sc;
	}
	public void setSc(int sc) {
		this.sc = sc;
	}
	public int getMu() {
		return mu;
	}
	public void setMu(int mu) {
		this.mu = mu;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public int getAvg() {
		return avg;
	}
	public void setAvg(int avg) {
		this.avg = avg;
	}
	
	
}
