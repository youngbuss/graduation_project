package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import operator.haar;
import operator.kmeans;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		System.out.println("Start Post");
		//doGet(request, response);
		String username = request.getParameter("user");
		String pass = request.getParameter("pass");
		System.out.println(username);
		System.out.println(pass);
		//�ж��Ƿ���ȷ
		if(username.equals("admin") && pass.equals("123")){
			System.out.println("������ȷ");
			//��ȷ���������һ��ҳ��
			
			//request.setAttribute("username", username);
			//request.setAttribute("pass", pass);
			//request.getRequestDispatcher("New1.jsp").forward(request, response);
			response.sendRedirect("main.jsp");
			//response.sendRedirect("error.jsp?abc="+x+"&����="+����+"&����="+����+"&����="+����....);
			//response.sendRedirect("New1.jsp?data="+info+"&data2="+info2+"&username="+username+"&pass="+pass);
			//response.sendRedirect("New1.jsp?data=info&data2=info2&username=username&pass=pass");
		}else{
			//����ȷ���򷵻ص�¼ҳ��
			request.getRequestDispatcher("login.html").forward(request, response);
		}
	}

}
