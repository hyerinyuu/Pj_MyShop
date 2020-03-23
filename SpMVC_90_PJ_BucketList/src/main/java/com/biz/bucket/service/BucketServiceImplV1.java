package com.biz.bucket.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.biz.bucket.domain.BucketVO;
import com.biz.bucket.repository.BucketDao;

@Service
@Qualifier("bucketServiceV1")
public class BucketServiceImplV1 implements BucketService{
	
	protected final BucketDao bkDao;

	public BucketServiceImplV1(BucketDao bkDao) {
		this.bkDao = bkDao;
	}

	@Override
	public List<BucketVO> selectAll() {
		
		return bkDao.selectAll();
	}

	@Override
	public BucketVO findBySeq(long bk_seq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BucketVO> findBySubject(String bkSubject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(BucketVO bucketVO) {
		
		// 날짜 세팅 부분
		Date date = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat st = new SimpleDateFormat("HH:MM:ss");
		
		String curDate = sd.format(date);  // 문자열형 날짜 생성
		String curTime = st.format(date);  // 문자열형 시간 생성
		
		bucketVO.setBk_date(curDate);
		bucketVO.setBk_time(curTime);
		
		// 완성유무 세팅부분
		String strComplete = bucketVO.getBk_complete();
		if(strComplete == null || strComplete.isEmpty()) {
			bucketVO.setBk_complete("N");
		}		
		return bkDao.insert(bucketVO);
	}

	@Override
	public int update(BucketVO bucketVO) {
		return bkDao.update(bucketVO);
	}

	@Override
	public int delete(String id) {
		return bkDao.delete(Long.valueOf(id));
	}

	@Override
	public void compStateUpdate(String id) {

		BucketVO bkVO = bkDao.findById(Long.valueOf(id));
		
		if(bkVO.getBk_complete().equalsIgnoreCase("N")) {
			bkVO.setBk_complete("Y");
		}else {
			bkVO.setBk_complete("N");
		}
		
		bkDao.compStateUpdate(bkVO);
		
	}

}
