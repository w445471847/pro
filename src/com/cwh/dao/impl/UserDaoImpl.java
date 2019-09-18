package com.cwh.dao.impl;

import java.util.List;

import com.cwh.dao.UserDao;
import com.cwh.dbutil.Dbutil;
import com.cwh.dbutil.PageData;
import com.cwh.entity.User;

public class UserDaoImpl implements UserDao {
////增加
//	@Override
//	public boolean add(User user) {
//		// TODO Auto-generated method stub
//		String sql="INSERT into `user`(id,username,iphone,sex,level,state) VALUES(?,?,?,?,?,?)";
//		
//		return Dbutil.exUpdate(sql,user.getId(),user.getUsername(),user.getIphone(),user.getSex(),user.getLevel(),user.getState());
//	}
//	
//删除
	@Override
	public boolean del(User user) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM `user2` WHERE `id` = ? ";
		
		return Dbutil.exUpdate(sql,user.getId());
	}
	
//修改
	@Override
	public boolean update(User user) {
		// TODO Auto-generated method stub
		String sql="update `user2`set username = ?, email = ? ,sex = ? ,city = ? ,sign = ? where id = ?";
		
		return Dbutil.exUpdate(sql,user.getUsername(),user.getEmail(),user.getSex(),user.getCity(),user.getSign(),user.getId());
	}


////	验证
//	public User checkLogin(User user) {
//		String sql = "select * from `user` where username = ? and pwd = ?";
//		List<User> list =Dbutil.exQuery(sql, User.class, user.getUsername(), user.getPwd());
//		if(list.size()>0)
//			return  list.get(0);
//		return null; 
//				
//	}
////	条件查找
//	@Override
//		public List<User> getfind(User user) {
//			// TODO Auto-generated method stub
//		String sql="select * from `user` where id like ? or username like ? or iphone like ? or sex like ? " ;
//		List<User> list =Dbutil.exQuery(sql, User.class,user.getId(),user.getUsername(),user.getIphone(), user.getSex());
//		if(list.size()>0)
//			return  list;
//			return null;
//		}
//	分页
	@Override
		public PageData<User> getUsersByPage(int page, int pageSize, String strLike) {
			// TODO Auto-generated method stub
		String sql = "SELECT * FROM user2 where username like ?";
		return Dbutil.exQueryByPage(sql, User.class, page, pageSize, "%"+strLike+"%");
		}

}
