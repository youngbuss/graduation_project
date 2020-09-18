package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
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
import operator.mysql;
import operator.readcsv;

/**
 * Servlet implementation class readzip
 */
@WebServlet("/readzip")
public class readzip extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public readzip() {
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
		System.out.println("前端传递过来的文件夹是"+request.getParameter("name"));
		readcsv readcsv =new readcsv();
		HandleData Hdata = new HandleData();
		String fileString = request.getParameter("name");
		String srcfile = "F:\\javaPro\\webdemo\\data\\" + fileString;
		File srcFile = new File(srcfile);     		
        String destDirPath = "F:\\javaPro\\webdemo\\data\\user\\3";
        readcsv.unZip(srcFile, destDirPath);
        ArrayList<File> files = readcsv.getFiles(destDirPath);
        ArrayList<String> fileArrayList = new ArrayList<String>(); 
        HashMap<String, ArrayList<double[]>> tMap = new HashMap<String, ArrayList<double[]>>();
        ArrayList<ArrayList<double[]>> tArrayList = new ArrayList<ArrayList<double[]>>();
       // int i =0;
        HashMap<String, HashMap<String, String>> userHashMap = new HashMap<String, HashMap<String,String>>();
        String k = "";
        String startday ="",endday = "";
    	for(File f : files){		
        	//Thread.sleep(100);
            //System.out.println(f.getName());
            String user = f.getName().split("\\.")[0];
            k  = user;
            String filepath = destDirPath + "\\"+f.getName();
            ArrayList<double[]> arrayList = new ArrayList<double[]>();
           
             
    		HashMap<String, String> userdataMap = new HashMap<String, String>();	
    		Map<String, double[]> bMap = readcsv.readCsv2(filepath);
     		Map<String, double[]> aMap = Hdata.sortMapByKey(bMap);
     		int size = aMap.size();
     		int s = 1;
     		
    		//System.out.println("完成读取文件2");
    	    for(Map.Entry<String,double[]> entry : aMap.entrySet()){
    			 String key = entry.getKey();
    			 
    			 //System.out.println(key+" :");			 
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
    			 s++;
    			// System.out.println(dataString);
    		 }
    	    userHashMap.put(user, userdataMap);
    	   
    	  //将数据添加到临时存储介质
    		tArrayList.add(arrayList);
    		tMap.put(user, arrayList);
            fileArrayList.add(f.getName());
        }
    	
        Hdata.addTempData(tArrayList);
        Hdata.mapCopy(tMap);
        Hdata.adduserhashmap(userHashMap);
        System.out.println("开始保存数据");
        //mysql mysql =new mysql();
        //mysql.add_data(userHashMap);
        System.out.println("保存完数据"+ k);
        //System.out.println(data.dataMap.keySet());
        System.out.println(" 临时存储介质："+data.tempDataMap.keySet());
        System.out.println(" 临时用户："+data.tempdataHashMap.keySet());
        ArrayList<double[]> firstArrayList = data.tempDataMap.get(k);  
        userinfo userinfo = new userinfo(startday,endday,firstArrayList);
        readcsv.deleteDir(destDirPath);
        Gson gson = new Gson();
        //String info=gson.toJson(firstArrayList);
        String info=gson.toJson(userinfo);
	    //System.out.println(info);
	    PrintWriter out = response.getWriter();   
	    out.write(info);
	    
	}

}
