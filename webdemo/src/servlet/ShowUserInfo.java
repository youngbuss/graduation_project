package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import data.data;
import data.userinfo;
import operator.mysql;
import operator.statisticsdata;

/**
 * Servlet implementation class ShowUserInfo
 */
@WebServlet("/ShowUserInfo")
public class ShowUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowUserInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String name = request.getParameter("name");
		System.out.println("要展示的信息："+name);
		mysql mysql = new mysql();		
		if(name.equals("all")) {
			Gson gson = new Gson();
			String info=gson.toJson(2);
		    //System.out.println(info);
		    PrintWriter out = response.getWriter();   
		    out.write(info);
		}
	    else {
	    	ArrayList<ArrayList<double[]>> datainfo = new ArrayList<ArrayList<double[]>>();
	    	//获取起止日期
	    	String[] days = mysql.selectday2day(name);
	    	String start = days[0];
	    	String end = days[1];
	    	
		    //获取用电数据
			ArrayList<double[]> usedatArrayList =new ArrayList<double[]>(mysql.getorigindata(name));
			//ArrayList<double[]> usedatArrayList =new ArrayList<double[]>(data.dataMap.get(name)); 
			datainfo.add(usedatArrayList);
			System.out.println("1:"+usedatArrayList.size());
			//获取负荷模式
			ArrayList<double[]> modeArrayList = new ArrayList<double[]>(mysql.getusermode(name));
			//ArrayList<double[]> modeArrayList = new ArrayList<double[]>(data.modeMap.get(name));
			System.out.println("2:"+modeArrayList.size());
			//获取统计信息
			statisticsdata statisticsdata =new statisticsdata(usedatArrayList);
			statisticsdata.execute();
			modeArrayList.add(statisticsdata.statisarray);
			datainfo.add(modeArrayList);
			userinfo userinfo  = new userinfo(datainfo, start, end);
			System.out.println("start:"+start+"end:"+end);
			Gson gson = new Gson();
			//String info=gson.toJson(datainfo);
			String info=gson.toJson(userinfo);
		    //System.out.println(info);
		    PrintWriter out = response.getWriter();   
		    out.write(info);
		}
		
	}

}
