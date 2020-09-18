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

import data.HandleData;
import data.data;
import operator.FuzzyCMeans;
import operator.UserClassification;
import operator.mysql;

/**
 * Servlet implementation class fcm
 */
@WebServlet("/fcm")
public class fcm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fcm() {
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
		UserClassification userClass = new UserClassification(allModeArrayList);
		userClass.classexecute();
		allModeArrayList = new ArrayList<double[]>(userClass.getclusterset());
		System.out.println("分组数："+allModeArrayList.size());
		int numpattern = allModeArrayList.size();
        int dimension = allModeArrayList.get(0).length;
        int cata = 3;
        int maxcycle = 10000;
        double m = 1.2;
        double limit = 0.000001f;
        //System.out.println(numpattern + "  " + dimension);
        double s = 0.0f;
        double max = 3;
        double sswc= 0;
        double[][] c= new double[cata][dimension];
        ArrayList<ArrayList<double[]>> group = new ArrayList<ArrayList<double[]>>();
        
        for (int c1= 5; c1<=10; c1++) {
        	FuzzyCMeans fuzzyCMeans = new FuzzyCMeans(numpattern, dimension, c1, maxcycle, m, limit);
            fuzzyCMeans.executeFCM(allModeArrayList);
            s = fuzzyCMeans.DBI;
            sswc = fuzzyCMeans.SSWC;
            System.out.println("cata"+c1+":"+s+" "+sswc);        
            if(s<2&&s<max) {
            	c = null;
            	c = fuzzyCMeans.center;
            	group = new ArrayList<ArrayList<double[]>>(fuzzyCMeans.cluster);
            	max = s;       	
            }
		}
        	
        
        ArrayList<double[]> centerArrayList = new ArrayList<double[]>();
        
        //for(int i = 0;i<fuzzyCMeans.center.length;i++)
        	//centerArrayList.add(fuzzyCMeans.center[i]);
        for(int i = 0;i<c.length;i++)
        	centerArrayList.add(c[i]);
        
        System.out.println("SSWC:"+sswc);
        System.out.println("DBI:"+max);
        System.out.println("添加第"+data.vserion+"版本分组信息");
        //添加分组结果至数据库
        HandleData handleData = new HandleData();
        handleData.updateuserclass(group,centerArrayList);
        mysql.add_group_data(data.vserion, group, centerArrayList);
        data.vserion = data.vserion+1;
        /*
        for(int i = 0 ;i<centerArrayList.size();i++) {
        	System.out.println(i+":"+Arrays.toString(centerArrayList.get(i)));
        }
        for(int i =0;i<group.size();i++) {
        	for(int j =0;j<group.get(i).size();j++) {
        		System.out.println("["+i+"]:"+Arrays.toString(group.get(i).get(j)));
        	}
        }
        */
        Gson gson = new Gson();
		String info=gson.toJson(centerArrayList);
	    //System.out.println(info);
	    PrintWriter out = response.getWriter();   
	    out.write(info);
	}

}
