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
import operator.SortNewUser;
import operator.mysql;

/**
 * Servlet implementation class newuserclass
 */
@WebServlet("/newuserclass")
public class newuserclass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public newuserclass() {
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
		String userString = request.getParameter("name");
		if(userString == "") {
			request.getRequestDispatcher("main.jsp").forward(request, response);
		}
		else {
			mysql mysql =  new mysql();
			userString = userString.split("\\.")[0];
			//ArrayList<double[]> newcluster = new ArrayList<double[]>(data.modeMap.get(userString));
			ArrayList<double[]> newcluster = new ArrayList<double[]>(mysql.getusermode(userString));
			System.out.println(userString+"聚类模式：");
			for(int i = 0;i<newcluster.size();i++) {
				System.out.print(Arrays.toString(newcluster.get(i))+" ");
			}
			System.out.println();
			System.out.println("当前聚类版本："+(data.vserion-1));
			ArrayList<ArrayList<double[]>> userclass = mysql.getgroupdetail(data.vserion-1);
			ArrayList<double[]> userclasscenter =mysql.getgroup(data.vserion-1);
			//ArrayList<ArrayList<double[]>> userclass = new ArrayList<ArrayList<double[]>>(data.usergroup);
			//ArrayList<double[]> userclasscenter = new ArrayList<double[]>(data.usergroupcenter);
			SortNewUser sortNewUser = new SortNewUser(newcluster, userclass);
			
			sortNewUser.newuserclass();
			int type = sortNewUser.type;
			double[] group = userclasscenter.get(type);
			ArrayList<double[]> arrayList = new ArrayList<double[]>();
			arrayList.add(group);
			System.out.println(Arrays.toString(group));
			System.out.println("type:"+type);
			Gson gson =new Gson();
			String info = gson.toJson(arrayList);
			PrintWriter out = response.getWriter();   
		    out.write(info);
		}
		
	}

}
