package com.biz.bucket.repository;

import java.util.List;

import com.biz.bucket.domain.BucketVO;

public interface BucketDao {

	public List<BucketVO> selectAll();

	public int insert(BucketVO bucketVO);
}
