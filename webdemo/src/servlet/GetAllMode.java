package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import data.data;
import operator.mysql;

/**
 * Servlet implementation class GetAllMode
 */
@WebServlet("/GetAllMode")
public class GetAllMode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllMode() {
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
		//System.out.println("获取所有负荷模式"+ data.modeMap.keySet());
		
		ArrayList<double[]> allModeArrayList = new ArrayList<double[]>();
		/*
		for(String key:data.modeMap.keySet()) {
			ArrayList<double[]> moArrayList = data.modeMap.get(key);
			for (int i =0;i<moArrayList.size();i++) {
				allModeArrayList.add(moArrayList.get(i));
			}
		}
		*/
		mysql mysql = new mysql();
		
		allModeArrayList = mysql.getallmode();
		System.out.println(allModeArrayList.size());
		Gson gson = new Gson();
		String info=gson.toJson(allModeArrayList);
	    //System.out.println(info);
	    PrintWriter out = response.getWriter();   
	    out.write(info);
	}

}
