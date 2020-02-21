package com.biz.app.domain;

public class AddrVO {
	
	private String name;
	private String tel;
	private String post;
	private String addr;
	private String city;
	
	public AddrVO(String name, String tel, String post, String addr, String city) {
		super();
		this.name = name;
		this.tel = tel;
		this.post = post;
		this.addr = addr;
		this.city = city;
		
	}
	
	public AddrVO() {
		super();
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	

}
