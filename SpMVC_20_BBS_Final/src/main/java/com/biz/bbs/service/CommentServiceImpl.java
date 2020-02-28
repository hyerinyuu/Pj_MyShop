package com.biz.bbs.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.biz.bbs.domain.CommentVO;
import com.biz.bbs.repository.CommentDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{

	private final CommentDao cmtDao;

	@Override
	public List<CommentVO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentVO findById(long c_id) {
		return cmtDao.findById(c_id);
	}

	@Override
	public List<CommentVO> findByBId(long c_b_id) {
		return cmtDao.findByBId(c_b_id);
	}
	
	@Override
	public List<CommentVO> findByPId(long c_p_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(CommentVO commentVO) {
		
		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss");
		commentVO.setC_date_time(ldt.format(df).toString());
		
		return cmtDao.insert(commentVO);
	}

	@Override
	public int update(CommentVO commentVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long c_id) {
		return cmtDao.delete(c_id);
	}

	

}
