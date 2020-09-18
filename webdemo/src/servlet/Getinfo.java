package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import data.data;
import data.userinfo;
import operator.mysql;

/**
 * Servlet implementation class Getinfo
 */
@WebServlet("/Getinfo")
public class Getinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Getinfo() {
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
		int i =0;
		/*
		String[] keys= new String[data.tempDataMap.keySet().size()];
		for(String key : data.tempDataMap.keySet()){
			   keys[i] = key;
			   i++;
	    }
	    */
		mysql mysql = new mysql();
		String[] keys= mysql.getuserlist();
		//userinfo userinfo = new userinfo("20120101","20120202",keys);
		Gson gson = new Gson();
		String info=gson.toJson(keys);
	    //System.out.println(info);
	    PrintWriter out = response.getWriter();   
	    out.write(info);
	}

}
