package servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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
import operator.haar;
import operator.kmeans;
import operator.mysql;
import operator.readcsv;

/**
 * Servlet implementation class show
 */
@WebServlet("/show")
public class show extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public show() {
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
		//ArrayList<double[]> Mdata = new ArrayList<double[]>();
		String mapKey = " ";
		HashMap<String, ArrayList<double[]>> hashMap = new HashMap<String, ArrayList<double[]>>();
		for(Map.Entry<String, ArrayList<double[]>> entry : data.tempDataMap.entrySet()){
		    mapKey = entry.getKey();
		    //System.out.println(mapKey);
		    ArrayList<double[]> mapValue = entry.getValue();
		    //System.out.println("  "+mapValue.size());
		    haar haar = new haar(mapValue);
			haar.executeDwt();
			//System.out.println("HData.size="+haar.HData.size()+" HData[1]length="+haar.HData.get(0).length);
			double max= -100;
		    ArrayList<double[]> Mdata = new ArrayList<double[]>();
		    for(int i = 2; i<=10 ;i++) {
		      kmeans kmeans = new kmeans(i, haar.LData);
		      kmeans.execute();
		      double sswc= kmeans.getSswc();
		      //System.out.println(i+":"+sswc);
		      if(sswc>max) {
		        max = sswc;
		    	Mdata = new ArrayList<double[]>(kmeans.getcenter());
		      }
		    }	
		    //System.out.println(max);	    
		    //HandleData handleData =new HandleData();
		    //handleData.addModeData(mapKey, Mdata);
		    hashMap.put(mapKey, Mdata);
		   // mysql mysql = new mysql();
			//mysql.add_mode(mapKey, Mdata);
		}
		mysql mysql = new mysql();
	    mysql.add_all_mode(hashMap);
	    //System.out.println(data.modeMap.keySet());		
	    Gson gson = new Gson();
		String info=gson.toJson(hashMap.get(mapKey));
	    //System.out.println(info);
	    PrintWriter out = response.getWriter();   
	    out.write(info);
	}
    
}
