package com.cwh.service;

import java.util.List;

import com.cwh.dbutil.PageData;
import com.cwh.entity.User;

public interface Service {
////	添加
//	boolean add(User user);
	
//	删除
	boolean del(User user);
	
//	修改
	boolean update(User user);
	
////	登陆
//	User checkLogin(String name,String pwd);
	
//	分页
	PageData<User> getUsersByPage(int page, int pageSize, String strLike);
	
}
