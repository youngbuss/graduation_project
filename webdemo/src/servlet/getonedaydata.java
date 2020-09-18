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

import operator.mysql;
import operator.statisticsdata;

/**
 * Servlet implementation class getonedaydata
 */
@WebServlet("/getonedaydata")
public class getonedaydata extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getonedaydata() {
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
		String user = request.getParameter("name");
		String sday = request.getParameter("start");
		String eday = request.getParameter("end");
		mysql mysql = new mysql();
		ArrayList<double[]> usedata = new ArrayList<double[]>();
		ArrayList<double[]> datas= mysql.getdaysdata(user, sday,eday);
		usedata = new ArrayList<double[]>(datas);
		System.out.println("datas size:"+usedata.size());
		statisticsdata statisticsdata =new statisticsdata(datas);
		statisticsdata.execute();
		usedata.add(statisticsdata.statisarray);
		Gson gson = new Gson();
		//String info=gson.toJson(datainfo);
		String info=gson.toJson(usedata);
	    //System.out.println(info);
	    PrintWriter out = response.getWriter();   
	    out.write(info);
	}

}
