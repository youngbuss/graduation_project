package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import operator.mysql;

/**
 * Servlet implementation class deleteuserinfo
 */
@WebServlet("/deleteuserinfo")
public class deleteuserinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteuserinfo() {
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
		mysql mysql = new mysql();
		String name = request.getParameter("name");
		System.out.println("Ҫɾ���û�����Ϣ��"+name);
		if(name=="all") {
			mysql.deletealluserdata();
		}
		
		mysql.deleteuserinfo(name);
		Gson gson = new Gson();
		String info=gson.toJson("ɾ���ɹ�");
	    //System.out.println(info);
	    PrintWriter out = response.getWriter();   
	    out.write(info);
		
	}

}
