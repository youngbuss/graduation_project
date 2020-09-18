package data;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import servlet.newuserclass;
public class HandleData {
	//public static Map<String , ArrayList<double[]>> modeMap = new HashMap<String, ArrayList<double[]>>();
    private Map<String, ArrayList<double[]>> user_mode;
    private static Map<String, ArrayList<double[]>> user_data;
    
    public HandleData() {
		// TODO Auto-generated constructor stub
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
    	if(data.modeMap.containsKey(username)) {
    		ArrayList<double[]> datArrayList = data.modeMap.get(username);
    		for(int i = 0; i<mode.size();i++)
    			datArrayList.add(mode.get(i));
    	    //user_mode.put(username, datArrayList);
    		data.modeMap.put(username, datArrayList);
    	}
    	//����������û�
    	else {
    		data.modeMap.put(username, mode);
		}
    }
    public void addUserData(String username, ArrayList<double[]> use){
    	if(data.dataMap.containsKey(username)) {
    		ArrayList<double[]> datArrayList = data.dataMap.get(username);
    		for(int i = 0; i<use.size();i++)
    			datArrayList.add(use.get(i));
    	    data.dataMap.put(username, datArrayList);
    	    
    	}
    	//����������û�
    	else {
    		//System.out.println("�������");
    		data.dataMap.put(username, use);
			//user_data.put(username, data);		
		}
    }
    
    public void addTempData(ArrayList<ArrayList<double[]>> Tdata) {
      //���������	
      data.tempDataArrayList = new ArrayList<ArrayList<double[]>>(Tdata);
    }
    public static void mapCopy(HashMap<String,ArrayList<double[]>> paramsMap) {
	    data.tempDataMap.clear();
	    data.tempDataMap.putAll(paramsMap);
	}
    public void updateuserclass(ArrayList<ArrayList<double[]>> arrayList,ArrayList<double[]> arrayList2) {
    	data.usergroup = new ArrayList<ArrayList<double[]>>(arrayList);
    	data.usergroupcenter = new ArrayList<double[]>(arrayList2);
    }
    public void adduserhashmap(HashMap<String, HashMap<String, String>> hashMap) {
    	data.tempdataHashMap.clear();
    	data.tempdataHashMap.putAll(hashMap);
    }
    public static Map<String, double[]> sortMapByKey(Map<String, double[]> map) {
    	if (map == null || map.isEmpty()) {
            return null;
        }
       
       //Map<String, String> sortMap = new TreeMap<String, String>(new  MapKeyConparator());
       Map<String, double[]> sortMap = new TreeMap<String, double[]>(new  Comparator<String>() {
    	   public int compare(String obj1, String obj2) {
               return obj1.compareTo(obj2);//��������
           }
       });
       sortMap.putAll(map);
        return sortMap;
    }
    
}

