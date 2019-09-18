package com.cwh.dao;

import java.util.List;

import com.cwh.dbutil.PageData;
import com.cwh.entity.User;


public interface UserDao {

//	boolean add(User user);
//
	boolean del(User user);

	boolean update(User user);
	
	// 查询方法 单个?数量?所有?分页?模糊

//	分页
	PageData<User>getUsersByPage(int page,int pageSize,String strLike);
}
