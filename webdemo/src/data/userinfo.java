package data;

import java.util.ArrayList;

public class userinfo {
    public String startday;
    public String endday;
    public ArrayList<double[]> datArrayList ;
    public String[] dayStrings;
    public ArrayList<ArrayList<double[]>> datainfo;
    
    public userinfo(String a,String b,ArrayList<double[]> c) {
    	startday = a;
    	endday = b;
    	datArrayList = c;
    }
    public userinfo(ArrayList<ArrayList<double[]>> a,String b,String c) {
    	this.startday = b;
    	this.endday = c;
    	this.datainfo = new ArrayList<ArrayList<double[]>>(a);
    }
}
