package com.cwh.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cwh.dbutil.Dbutil;

public class Test1 {
 public static void main(String[] args) {
	 Connection con = null;
	 try {
		con=Dbutil.getconn();
		con.setAutoCommit(false);
		String str="update blog set name='小A' where id=1";
		PreparedStatement ps=con.prepareStatement(str);	
		int a=ps.executeUpdate();
		
		String str2="update blog set name='小B' where id=2";
		ps=con.prepareStatement(str2);	
		int b=ps.executeUpdate();
		if (a>0&&b>0) {
			System.out.println("事务提交");
			con.commit();
		}else {
			System.out.println("事务回滚");
			con.rollback();
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("事务回滚");
		try {
			con.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	 
}
}
