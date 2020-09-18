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


import data.HandleData;
import data.data;
import data.userinfo;
import operator.haar;
import operator.mysql;
import operator.readcsv;
import operator.statisticsdata;

/**
 * Servlet implementation class loadfile
 */
@WebServlet("/loadfile")
public class loadfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loadfile() {
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
		System.out.println("前端传递过来的文件是"+request.getParameter("name"));
		readcsv readcsv =new readcsv();
		String fileString = request.getParameter("name");
		System.out.println(fileString);
		String user = fileString.split("\\.")[0];
		System.out.println(user);
		String name = "F:\\javaPro\\webdemo\\data\\" + request.getParameter("name");
		//String name = "F:\\javaPro\\webdemo\\data\\user1.1.csv";
		System.out.println(name);
		ArrayList<double[]> arrayList = new ArrayList<double[]>();
		//arrayList = readcsv.readCsv(name);
		System.out.println("完成读取文件");
		//将数据加到总数据库里面
		HandleData Hdata = new HandleData();
		//Hdata.addUserData(user, arrayList);
		//System.out.println(data.dataMap.keySet());
		
		
		//将数据添加到数据库
		HashMap<String, String> userdataMap = new HashMap<String, String>();
		HashMap<String, HashMap<String, String>> userHashMap = new HashMap<String, HashMap<String,String>>();
 		Map<String, double[]> bMap = readcsv.readCsv2(name);
 		Map<String, double[]> aMap = Hdata.sortMapByKey(bMap);
 		int size = aMap.size();
 		int s = 1;
 		String startday ="",endday = "";
 				
		System.out.println("完成读取文件2");
	    for(Map.Entry<String,double[]> entry : aMap.entrySet()){
	    	
			 String key = entry.getKey();
			 System.out.println(key+" :");
			 
			 double[] demoArrayList = entry.getValue();
			 if(s == 1) {
				 startday = key;
			 }
			 if(s == size) {
				 endday = key;
			 }
			 arrayList.add(demoArrayList);
			 String dataString  = Double.toString(demoArrayList[0]);
			 for (int i = 1 ;i<demoArrayList.length;i++) {
				 //System.out.print(demoArrayList[i]);
				 dataString = dataString+"+"+String.format("%.4f", demoArrayList[i]);
				 //Double.toString(demoArrayList[i]);
			 }	
			 userdataMap.put(key, dataString);
			 System.out.println(dataString);
			 s++;
			 //mysql mysql = new mysql();
			 //mysql.add_data(user, key, dataString);
			 //System.out.println();
		 }
	    userHashMap.put(user, userdataMap);
	    Hdata.adduserhashmap(userHashMap);
	    mysql mysql = new mysql();
	    mysql.add_data(userHashMap);
	    
	    //将数据添加到临时存储介质用于接下来聚类处理
	  	ArrayList<ArrayList<double[]>> tArrayList = new ArrayList<ArrayList<double[]>>();
	  	HashMap<String, ArrayList<double[]>> tMap = new HashMap<String, ArrayList<double[]>>();
		tMap.put(user, arrayList);
		Hdata.mapCopy(tMap);
		System.out.println("临时存储介质: "+data.tempDataMap.keySet()+arrayList.size()+" "+arrayList.get(1).length+" "+arrayList.get(1)[95]);
		statisticsdata statisticsdata =new statisticsdata(arrayList);
		statisticsdata.execute();
		System.out.println("统计信息：");
		for(int i=0;i<statisticsdata.statisarray.length;i++) {
			System.out.println(statisticsdata.statisarray[i]);
		}
		ArrayList<double[]> arrayList2 = new ArrayList<double[]>(arrayList);
		arrayList2.add(statisticsdata.statisarray);
		userinfo userinfo = new userinfo(startday,endday,arrayList2);
 	    Gson gson = new Gson();
		//String info=gson.toJson(arrayList2);
		String info=gson.toJson(userinfo);
	    //System.out.println(info);
	    PrintWriter out = response.getWriter();   
	    out.write(info);
	}

}
