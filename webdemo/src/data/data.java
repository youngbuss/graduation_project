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
   
    //��ʱ�洢����
    public static ArrayList<ArrayList<double[]>> tempDataArrayList = new ArrayList<ArrayList<double[]>>();
    public static HashMap<String, ArrayList<double[]>> tempDataMap = new HashMap<String, ArrayList<double[]>>();
    public static HashMap<String, HashMap<String, String>> tempdataHashMap = new HashMap<String, HashMap<String,String>>();
    public data() {
    	user_data = new HashMap<String, ArrayList<double[]>>();
    	user_mode = new HashMap<String, ArrayList<double[]>>();
    }
    //�����û�id ��ȡ�û��ľ���ģʽ
    public ArrayList<double[]> getMode(String username) {
		ArrayList<double[]> modeArrayList = new ArrayList<double[]>();
		modeArrayList = user_mode.get(username);
    	return modeArrayList; 
	}
    //�����û�id ��ȡ�û����õ�����
    public ArrayList<double[]> getUserData(String username){
    	ArrayList<double[]> dataArrayList = new ArrayList<double[]>();
		dataArrayList = user_data.get(username);
    	return dataArrayList;
    }
    //��ȡ�����õ�ģʽ    
    public Map<String, ArrayList<double[]>> getModeMap(){
    	return user_mode;
    }
    //��ȡ�����û����õ�����
    public Map<String, ArrayList<double[]>> getDataMap() {
		return user_data;
	}
    //����û��õ�ģʽ���� ����û��Ѿ����ھ� �������  �����ھ������
    public void addModeData(String username, ArrayList<double[]> mode){
    	if(modeMap.containsKey(username)) {
    		ArrayList<double[]> datArrayList = user_mode.get(username);
    		for(int i = 0; i<mode.size();i++)
    			datArrayList.add(mode.get(i));
    	    //user_mode.put(username, datArrayList);
    		modeMap.put(username, datArrayList);
    	}
    	//����������û�
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
    	//����������û�
    	else {
			user_data.put(username, data);
		}
    }
    
}
