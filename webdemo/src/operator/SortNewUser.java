package operator;

import java.util.ArrayList;


public class SortNewUser {
	//新用户聚类模式
    private ArrayList<double[]> usercluster;
    //用户分组类别特征
    private ArrayList<ArrayList<double[]>> userClassType;
    //存储与各个类别特征的距离
    private double[] distancearray; 
    //记录第几个类别最用户分组类别
    public int type;
    //构造函数
    public SortNewUser(ArrayList<double[]> arrayList,ArrayList<ArrayList<double[]>> arrayList2) {
    	usercluster = new ArrayList<double[]>(arrayList);
    	userClassType = new ArrayList<ArrayList<double[]>>(arrayList2);
    }
    //计算两个数组之间的距离
    private double countdistance(double[] f1, double[] f2) {
    	double distance = 0.0f;
    	int k  = f1.length;
    	for(int i =0;i<k;i++) {
    		distance += (f1[i]-f2[i])*(f1[i]-f2[i]);
    	}
    	distance = Math.sqrt(distance);
    	return distance;
    }
    
    private double Calculationsimilarity(ArrayList<double[]> arrayList) { 	
    	
    	double distance = 0.0;
    	//double min = Double.MAX_VALUE;
    	for(int i = 0;i<usercluster.size();i++) {
    		double d=0.0;
    		double min = Double.MAX_VALUE;
    		//计算第i个负荷模式与类别特征的最小聚类
    		for (int j = 0; j <arrayList.size() ; j++) {
    			d = countdistance(usercluster.get(i), arrayList.get(j));
    			if(d<min) {
    				min = d ;
    			}
			}
    		distance += min;
    	}
    	distance  = distance/usercluster.size();
    	return distance;
    }
    
    //计算聚类模式与各个分组类别特征的相似性
    public void newuserclass(){
    	double distance = 0.0;
    	distancearray = new double[userClassType.size()];
    	for(int i = 0 ; i<userClassType.size();i++) {
    	   distance  = Calculationsimilarity(userClassType.get(i)); 
    	   distancearray[i] = distance;
    	}
    	//寻找最小距离
    	double min = Double.MAX_VALUE;
    	int num = 0;
    	for(int i = 0; i<distancearray.length;i++) {
    		if(distancearray[i]<min) {
    			min = distancearray[i];
    			num = i;
    		}
    	}
    	type = num;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
