package com.biz.modeles.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.biz.modeles.domain.UsersVO;

public interface UserDao {
	
	@Select("SELECT * FROM tbl_user WHERE userId = #{userId}")
	public UsersVO findByUserId(String userId);
	
	@Insert( "INSERT INTO `books`.`tbl_user`( " + 
			" `userId`, " + 
			" `password`, " + 
			" `userName`, " + 
			" `userRole` " + 
			" )VALUES( " + 
			" #{userId}, " + 
			" #{password}, " + 
			" #{userName}, " + 
			" #{userRole}) ")
	public int insert(UsersVO usersVO);
	
	@Delete("DELETE FROM tbl_user WHERE userId = #{userId}")
	public int delete(String userId);

}
