package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import data.data;
import operator.UserClassification;



/**
 * Servlet implementation class UserClass
 */
@WebServlet("/UserClass")
public class UserClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserClass() {
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
		
		ArrayList<double[]> allModeArrayList = new ArrayList<double[]>();
		for(String key:data.modeMap.keySet()) {
			ArrayList<double[]> moArrayList = data.modeMap.get(key);
			for (int i =0;i<moArrayList.size();i++) {
				allModeArrayList.add(moArrayList.get(i));
			}
		}
		UserClassification userClass = new UserClassification(allModeArrayList);
		userClass.classexecute();
		ArrayList<double[]> fuzzyClass = userClass.getCenterClusterSet();
		for(int i = 0;i < fuzzyClass.size();i++)
            System.out.println(i + " : "+ Arrays.toString(fuzzyClass.get(i)));
		Gson gson = new Gson();
		String info=gson.toJson(fuzzyClass);
	    System.out.println(userClass.clusterdbi);
	    
	    PrintWriter out = response.getWriter();   
	    out.write(info);
	}

}
