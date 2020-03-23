package com.biz.bucket.service;

import java.util.List;

import com.biz.bucket.domain.BucketVO;

public interface BucketService {
	
	// tbl_bucket_list의 모든 데이터를 출력할 method
	public List<BucketVO> selectAll();
	// seq로 한개의 데이터만 출력할 method
	public BucketVO findBySeq(long bk_seq);
	// 데이터를 입력받아 subject와 일치하는 값을 출력할 method
	public List<BucketVO> findBySubject(String bkSubject);
	
	// insert를 수행할 method
	public int insert(BucketVO bucketVO);
	// update를 수행할 method
	public int update(BucketVO bucketVO);
	// seq값으로 한개의 데이터를 delete할 method
	public int delete(String id);
	
	// 수행여부 세팅을 위한 method
	public void compStateUpdate(String id);
	

}
