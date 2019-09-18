package com.cwh.service.impl;

import java.util.List;

import com.cwh.dao.UserDao;
import com.cwh.dao.impl.UserDaoImpl;
import com.cwh.dbutil.MD5Util;
import com.cwh.dbutil.PageData;
import com.cwh.entity.User;
import com.cwh.service.Service;

public class ServiceImpl implements Service {
	UserDao ud = new UserDaoImpl();
	MD5Util md=new MD5Util();
	
//
//	// 增加
//	@Override
//	public boolean add(User user) {
//		// TODO Auto-generated method stub
//		user.setPwd(md.getEncodeByMd5(user.getPwd()));
//		return ud.add(user);
//	}
//
	// 删除
	@Override
	public boolean del(User user) {
		// TODO Auto-generated method stub

		return ud.del(user);
	}

	// 修改
	@Override
	public boolean update(User user) {
		// TODO Auto-generated method stub

		return ud.update(user);
	}
////	登陆验证
//	@Override
//	public User checkLogin(String name, String pwd) {
//		// TODO Auto-generated method stub
//		User user = new User();
//		user.setUsername(name);
//		user.setPwd(md.getEncodeByMd5(pwd));
//		User u =ud.checkLogin(user);
//		return u;
//	}
////	条件查找
//	@Override
//	public List<User> getfind(User user) {
//		// TODO Auto-generated method stub
//		return ud.getfind(user);
//	}
//	分页查找
	@Override
	public PageData<User> getUsersByPage(int page, int pageSize, String strLike) {
		// TODO Auto-generated method stub
		return ud.getUsersByPage(page, pageSize, strLike);
	}
}
