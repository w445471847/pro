package ser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cwh.dao.impl.UserDaoImpl;
import com.cwh.dbutil.PageData;
import com.cwh.entity.User;
import com.cwh.service.Service;
import com.cwh.service.impl.ServiceImpl;
import com.cwh.util.json.jsondata;
import com.google.gson.Gson;

/**
 * Servlet implementation class ajaxservlet
 */
@WebServlet("/as")
public class ajaxservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ajaxservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//数据的模拟[dao ...还没实现]
		//1请求和响应编码[过滤器]
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String op="query";
		if (request.getParameter("op")!=null) {
			op= request.getParameter("op");
		}
		if (op.equals("query")) {
			doQuery(request, response);
		}else if(op.equals("del")) {
			int id=Integer.parseInt(request.getParameter("id"));
			User u=new User();
			u.setId(id);
			boolean flag= new ServiceImpl()	.del(u);
			//返回flag
			response.getWriter().print(flag);
		}else if(op.equals("update")) {
			//接收页面发来数据
			BufferedReader reader=request.getReader();
			
			StringBuffer sbuffer=new StringBuffer();
			String str=null;
			//遍历数据
			while((str=reader.readLine())!=null) {
				sbuffer.append(str);
			}
			//数据转换
			Gson gs=new Gson();
			User u=gs.fromJson(sbuffer+"",User.class);
			Service se= new ServiceImpl();
			boolean flag=se.update(u);
			//返回flag
			response.getWriter().print(flag);
		}
		
		
	}

	public void doQuery(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//2设置预期响应的类型为application/json
		response.setContentType("application/json");
		
		int page = Integer.parseInt(request.getParameter("page"))==0?0:Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"))==0?0:Integer.parseInt(request.getParameter("limit"));
		String strLike = request.getParameter("strLike")==null?"":request.getParameter("strLike");
		//分页查询
		PageData<User> Page=new ServiceImpl().getUsersByPage(page,limit, strLike);
		
		jsondata<User> layuiData = new jsondata<>(0, "", Page.getTotalCount(), Page.getData());
		
		String returnValue = new Gson().toJson(layuiData);
		
		response.getWriter().print(returnValue);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
