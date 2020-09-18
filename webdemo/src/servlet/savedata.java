package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import data.data;
import operator.mysql;

/**
 * Servlet implementation class savadata
 */
@WebServlet("/savedata")
public class savedata extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public savedata() {
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
		System.out.println("±£´æÊý¾Ý");
		System.out.println(data.tempdataHashMap.keySet());
		mysql mysql = new mysql();
		//System.out.println(data.tempdataHashMap.keySet());
		mysql.add_data(data.tempdataHashMap);
		//System.out.println(data.tempdataHashMap.keySet());
		Gson gson = new Gson();
        String info=gson.toJson("save");
	    //System.out.println(info);
	    PrintWriter out = response.getWriter();   
	    out.write(info);
	}

}
