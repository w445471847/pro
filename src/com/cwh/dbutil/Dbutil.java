package com.cwh.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;


public class Dbutil {
	private static final String url = "jdbc:mysql://localhost:3306/stardb";
	private static final String name = "root";
	private static final String pwd = "ED10A";

	public static Connection getconn() throws SQLException {
		// 
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DriverManager.getConnection(url, name, pwd);
	}
//	增加删除
	public static boolean exUpdate(String sql, Object... obj) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement psmt = null;
		int n = 0;
		try {
			// 连接
			conn = getconn();
			//预编译
			psmt = conn.prepareStatement(sql);
			//占位符
			setpre(psmt, obj);
			//执行
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//释放
			closs(null, psmt, conn);
		}
		return n > 0;
	}
	//查询
	public static List exQuery(String sql,Class cla,Object...obj) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		List<Object> list =new ArrayList<>();
		try {
			// 连接
			conn=getconn();
			//预编译
			psmt=conn.prepareStatement(sql);
			//占位符
			setpre(psmt, obj);
			//执行
			rs=psmt.executeQuery();
			
			
			// 这里对 cla 判断一下
			if (cla.newInstance().getClass().getName().equals("java.lang.Object")) {
				if (rs.next()) {
					list.add(rs.getObject(1)); // 集合只有一个元素，int类型值
				}
			} else {
			//遍历
			while (rs.next()) {
				Object object=conver(rs, cla);
				list.add(object);
				
			}
			}
		} catch ( SQLException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//释放
			closs(rs,psmt,conn);
		}
		return list;
	}
	public static Object conver(ResultSet rs ,Class cla) {
		// TODO Auto-generated method stub
		 
		try {
			
			Object obj =cla.newInstance();
		
			ResultSetMetaData medate=rs.getMetaData();
			for (int i = 1; i <=medate.getColumnCount(); i++) {
				//创建对象
				String str =medate.getColumnLabel(i);
				//
				//if (rs.getObject(i)!=null) {
					BeanUtils.setProperty(obj, str, rs.getObject(i));
				//}	
			}
			return obj;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//占位符
	public static void setpre(PreparedStatement psmt, Object... obj) {
		// TODO Auto-generated method stub
		if (psmt != null && obj != null) {
			for (int i = 0; i < obj.length; i++) {
				try {
					psmt.setObject(i + 1, obj[i]);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	

	/**
	 * 
	 * @param sql
	 * @param cla
	 * @param page
	 *            页码
	 * @param pageSize
	 *            每页显示的记录数
	 * @param params
	 * @return
	 */
//	分页
	public static PageData exQueryByPage(String sql, Class cla, int page, int pageSize, Object... params) {
		// 问题:这里来加limit 还是传递过来之间就加好limit？
		// select .... from ... where ? ... limit ?,?

		String newsql = "select count(1) from (" + sql + ")  as t";
		// 如果传递的是object对象，查询操作得到就是单个结果
		List list1 = exQuery(newsql, Object.class, params);

		int totalCount = Integer.parseInt(list1.get(0) + "");
		// 可以加page的判断
		if (page < 1) {
			page = 1;
		}

		int start = (page - 1) * pageSize;
		sql = sql + " limit " + start + "," + pageSize;

		//页面展示的数据集
		List data = exQuery(sql, cla, params);
		
		
		PageData pageData = new PageData<>(data, page, totalCount, pageSize);

		return pageData;
	}
	
	
//	释放
	public static void closs(ResultSet rs,PreparedStatement psmt,Connection conn) {
		// TODO Auto-generated method stub
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (psmt!=null) {
			try {
				psmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
