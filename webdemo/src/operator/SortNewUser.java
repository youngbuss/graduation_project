package operator;

import java.util.ArrayList;


public class SortNewUser {
	//���û�����ģʽ
    private ArrayList<double[]> usercluster;
    //�û������������
    private ArrayList<ArrayList<double[]>> userClassType;
    //�洢�������������ľ���
    private double[] distancearray; 
    //��¼�ڼ���������û��������
    public int type;
    //���캯��
    public SortNewUser(ArrayList<double[]> arrayList,ArrayList<ArrayList<double[]>> arrayList2) {
    	usercluster = new ArrayList<double[]>(arrayList);
    	userClassType = new ArrayList<ArrayList<double[]>>(arrayList2);
    }
    //������������֮��ľ���
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
    		//�����i������ģʽ�������������С����
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
    
    //�������ģʽ������������������������
    public void newuserclass(){
    	double distance = 0.0;
    	distancearray = new double[userClassType.size()];
    	for(int i = 0 ; i<userClassType.size();i++) {
    	   distance  = Calculationsimilarity(userClassType.get(i)); 
    	   distancearray[i] = distance;
    	}
    	//Ѱ����С����
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
