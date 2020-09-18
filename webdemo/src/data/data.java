package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class data {
	public static int a = 10;
	public static int vserion = 1;
	public static HashMap<String , ArrayList<double[]>> modeMap = new HashMap<String, ArrayList<double[]>>();
	public static HashMap<String , ArrayList<double[]>> dataMap = new HashMap<String, ArrayList<double[]>>();
    private Map<String, ArrayList<double[]>> user_mode;
    private static Map<String, ArrayList<double[]>> user_data;
    public static ArrayList<ArrayList<double[]>> usergroup = new ArrayList<ArrayList<double[]>>();
    public static ArrayList<double[]> usergroupcenter = new ArrayList<double[]>();
   
    //临时存储数据
    public static ArrayList<ArrayList<double[]>> tempDataArrayList = new ArrayList<ArrayList<double[]>>();
    public static HashMap<String, ArrayList<double[]>> tempDataMap = new HashMap<String, ArrayList<double[]>>();
    public static HashMap<String, HashMap<String, String>> tempdataHashMap = new HashMap<String, HashMap<String,String>>();
    public data() {
    	user_data = new HashMap<String, ArrayList<double[]>>();
    	user_mode = new HashMap<String, ArrayList<double[]>>();
    }
    //根据用户id 获取用户的聚类模式
    public ArrayList<double[]> getMode(String username) {
		ArrayList<double[]> modeArrayList = new ArrayList<double[]>();
		modeArrayList = user_mode.get(username);
    	return modeArrayList; 
	}
    //根据用户id 获取用户的用电数据
    public ArrayList<double[]> getUserData(String username){
    	ArrayList<double[]> dataArrayList = new ArrayList<double[]>();
		dataArrayList = user_data.get(username);
    	return dataArrayList;
    }
    //获取所有用电模式    
    public Map<String, ArrayList<double[]>> getModeMap(){
    	return user_mode;
    }
    //获取所有用户的用电数据
    public Map<String, ArrayList<double[]>> getDataMap() {
		return user_data;
	}
    //添加用户用电模式数据 如果用户已经存在就 覆盖添加  不存在就新添加
    public void addModeData(String username, ArrayList<double[]> mode){
    	if(modeMap.containsKey(username)) {
    		ArrayList<double[]> datArrayList = user_mode.get(username);
    		for(int i = 0; i<mode.size();i++)
    			datArrayList.add(mode.get(i));
    	    //user_mode.put(username, datArrayList);
    		modeMap.put(username, datArrayList);
    	}
    	//不包含这个用户
    	else {
    		modeMap.put(username, mode);
		}
    }
    public void addUserData(String username, ArrayList<double[]> data){
    	if(user_data.containsKey(username)) {
    		ArrayList<double[]> datArrayList = user_data.get(username);
    		for(int i = 0; i<data.size();i++)
    			datArrayList.add(data.get(i));
    	    user_data.put(username, datArrayList);
    	}
    	//不包含这个用户
    	else {
			user_data.put(username, data);
		}
    }
    
}
