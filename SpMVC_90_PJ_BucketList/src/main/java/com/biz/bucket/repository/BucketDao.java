package com.biz.bucket.repository;

import java.util.List;

import com.biz.bucket.domain.BucketVO;

public interface BucketDao {

	public List<BucketVO> selectAll();
	public int insert(BucketVO bucketVO);
	public int update(BucketVO bucketVO);
	public int delete(Long bk_seq);

	public BucketVO findById(Long bk_seq);
	public void compStateUpdate(BucketVO bkVO);
}
