package operator;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;

import operator.kmeans;
import operator.union;


public class haar {
    private ArrayList<double[]> OriginalDatArrayList;
    public ArrayList<double[]> LData;
    public ArrayList<double[]> HData;
    private ArrayList<double[]> zscoreData;
    private double[] d1;
    private double[] d2;
    private double[] d3;
    private ArrayList<double[]> tempLData;
    private ArrayList<double[]> temphData;
    private ArrayList<ArrayList<double[]>> centerData;
    private ArrayList<ArrayList<double[]>> creserved;
    private ArrayList<ArrayList<double[]>> ctemp;
    private int datalength;
    private int timeOfDwt;
    
    private void initDataSet() {
        double[][] dataSetArray = new double[][]{ { 8, 2, 4, 6, 10, 8 }, { 3, 4, 2, 5, 4, 2 }, 
        	{ 7, 3, 6, 2, 4, 7 }, { 6, 3, 5, 3, 6, 3 }, { 6, 9, 1, 6, 3, 9 }, { 4, 1, 8, 6, 10, 8 } };

        for (int i = 0; i < dataSetArray.length; i++) {
            OriginalDatArrayList.add(dataSetArray[i]);
        }
    }
    public haar(ArrayList<double[]> arrayList) {
    	OriginalDatArrayList = new ArrayList<double[]>(arrayList);  	
    }
    public  haar() {
		// TODO Auto-generated constructor stub
	}
    private void init() {  	
    	//OriginalDatArrayList = new ArrayList<double[]>();
        LData = new ArrayList<double[]>();
        HData = new ArrayList<double[]>();
        zscoreData = new ArrayList<double[]>();
        temphData = new ArrayList<double[]>();
        tempLData = new ArrayList<double[]>();
        centerData = new ArrayList<ArrayList<double[]>>();
        creserved = new ArrayList<ArrayList<double[]>>();
        ctemp = new ArrayList<ArrayList<double[]>>();
        timeOfDwt = 2;
        //如果调用者未初始化数据集，则采用内部测试数据集
        if (OriginalDatArrayList == null || OriginalDatArrayList.size() == 0) {
            initDataSet();
        }
        datalength = OriginalDatArrayList.size();
        //若numOfCluster大于数据源的长度时，置为数据源的长度
    }
    
    //求均值函数
    private double getAverage(double[] data) {
		double sum = 0;
		for (int i = 0;i<data.length;i++) {
			sum +=data[i];
		}
		return (double)(sum/data.length);
	}
    
    //求标准差函数
    private double getStandardDevition(double[] data,double average) {
    	double sum = 0;
    	for(int i = 0;i<data.length;i++) {
    	  sum += (data[i] -average) * (data[i] -average);
    	}
		return (double)Math.sqrt(sum/data.length);
	}
    
    private void dohaar(ArrayList<double[]> Data) {
    	for(int i=0;i<Data.size();i++) {
    		double tmp1,tmp2;
    		int size = Data.get(i).length;
    		int length = size/2;
    		//长度为奇数,最后一位补0
    		if(size%2 == 1) 
    		{
    			length++;
    		}
    		d1 = new double[length];
    		d2 = new double[length];
    		for(int j=0; j<length;j++) {
    			tmp1 = Data.get(i)[2*j];
    			if(2*j+1==size) 
    	          tmp2 = 0;
    			else
    			  tmp2 = Data.get(i)[2*j+1];
    			d1[j]=(tmp1+tmp2)/2;
    			d2[j]=(tmp1-tmp2)/2;
    		}
    		tempLData.add(d1);
    		temphData.add(d2);	
			d1=null;
			d2=null;
    	}
    	//printDataArray(tempLData, "TLdata");
    }
    
    //z-score标准化
    private void z_score(ArrayList<double[]> Data) {
    	double average,standard;
    	
    	for(int i = 0;i<Data.size();i++) {
    		int length = Data.get(i).length;
    		d3 = new double[length];
    		average = getAverage(Data.get(i));
    		standard = getStandardDevition(Data.get(i),average);
    		//System.out.println("ave:"+average+"stand:"+standard);
    		for(int j=0;j<length;j++) {
    			double k ;
    			if(standard == 0.0) {
    				 k = 0;
    			}
    			else {
    				 k=(Data.get(i)[j]-average)/standard;
				}
    			//double k=(Data.get(i)[j]-average)/standard;
    			d3[j]=k;
    		}
    		zscoreData.add(d3);
    		d3 = null;
    	}
    }
    public void executeDwt() {
    	init();
    	//printDataArray(OriginalDatArrayList, "Origin");
    	int time =1;
    	ArrayList<double[]> tempData = new ArrayList<double[]>(OriginalDatArrayList);
    	while(time<=timeOfDwt) {
    		tempLData.clear();
    		temphData.clear();
    		dohaar(tempData);
    		tempData.clear();
    		tempData = new ArrayList<double[]>(tempLData);
    		time++;
    	}
    	LData = new ArrayList<double[]>(tempLData);
    	//printDataArray(LData, "LData");
    	HData = new ArrayList<double[]>(temphData);
    	//printDataArray(HData, "HData");
    	z_score(LData);
    	LData = new ArrayList<double[]>(zscoreData);
    	//printDataArray(LData, "LData");
    	//printDataArray(HData, "HData");
    }
    public void printDataArray(ArrayList<double[]> dataArray,
            String dataArrayName) {
       for (int i = 0; i < dataArray.size(); i++) {
    	   System.out.print("print:" + dataArrayName + "[" + i + "]=");
    	   System.out.println(Arrays.toString(dataArray.get(i)));
        }
        System.out.println("===================================");
     }
    public void clusterfuse(ArrayList<ArrayList<double[]>> Lcenter,ArrayList<ArrayList<double[]>> Hcenter) {
    	union union = new union();
    	int k;
    	ArrayList<ArrayList<double[]>> ctemp2 = new ArrayList<ArrayList<double[]>>();
    	ArrayList<double[]> cArrayList = new ArrayList<double[]>();
        for (int i = 0 ;i<Lcenter.size();i++) {
 	    	for (int j = 0;j<Hcenter.size();j++) {
 	    	  //将值复制到list中进行求交集
 	  		  //求交集
 	  		  System.out.println("并集：");
 	  		  ArrayList<double[]> list = new ArrayList<double[]>(Lcenter.get(i));
 	  		  ArrayList<double[]> list2 = new ArrayList<double[]>(Hcenter.get(j));
 	  		  ArrayList<double[]> intersectList = union.union(Lcenter.get(i), Hcenter.get(j));
 	 	      for (int m = 0; m < intersectList.size(); m++) {
 	 	         System.out.print(intersectList.get(m) + " ");
 	 	        }
 	 	      //if Ai=Dj C1=C1并AD
 	 	      if(list.equals(list2))
 	 	    	  creserved.add(intersectList);
 	 	      else {
 	 	    	  if(!list.equals(list2) && intersectList!=null && !intersectList.isEmpty()) {
 	 	    		  ctemp.add(intersectList);
 	 	    	  }
 	 	      }
 	 		}
 	    }
        //end for
        int l1 = centerData.size();
        int l2 = ctemp.size();
        //if l2 = 0   C = Creserved
        if (l2 == 0) {
        	System.out.println("l2=0");
        	centerData = new ArrayList<ArrayList<double[]>>(creserved);
        }
        else 
        {
        	System.out.println("l2 length "+ l2);
        	if(l2 > (Math.max(l1, l2) -l1))
        	{ 
        		k = Math.max(l1, l2) -l1;
        	    // kmeans(ctemp,k)
        		//初始化新的数据集重新进行聚类
        		for(int j = 0; j<ctemp.size();j++) {
        			for (int m = 0 ; m<ctemp.get(j).size();m++)
        				cArrayList.add(ctemp.get(j).get(m));
        		}
        	    kmeans kmeans = new kmeans(k,cArrayList);
        	    kmeans.execute();
        	    ctemp2 = kmeans.getCluster();
        	}
        	else {
				ctemp2 = new ArrayList<ArrayList<double[]>>(ctemp);
			}
        	for (int i = 0 ;i<creserved.size();i++)
        		centerData.add(creserved.get(i));
        	for (int i = 0 ;i<ctemp2.size();i++)
        		centerData.add(ctemp2.get(i));
        }
        	
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	   readcsv cReadcsv = new readcsv();
	   ArrayList<double[]> arrayList= new ArrayList<double[]>();
	   String fileString = "F:\\javaPro\\webdemo\\data\\user1.1.csv";
	   System.out.println(fileString);
	   arrayList = cReadcsv.readCsv(fileString);
       haar haar =new haar(arrayList);
       haar.executeDwt();
       //System.out.println("HData.size="+haar.HData.size()+" HData[1]length="+haar.HData.get(0).length);
       /*
       kmeans k = new kmeans(3);
       k.setDataSet(haar.LData);
       k.execute();
        
    
       ArrayList<ArrayList<double[]>> LData_cluster=k.getCluster();
       //查看结果
       for(int i=0;i<LData_cluster.size();i++)
       {
           k.printDataArray(LData_cluster.get(i), "Lcluster["+i+"]");
          
       }
      */
      double max= 0.0f;
      ArrayList<double[]> data = new ArrayList<double[]>();
      for(int i = 2; i<=10 ;i++) {
    	  kmeans kmeans = new kmeans(i, haar.LData);
    	  kmeans.execute();
    	  double sswc= kmeans.getSswc();
    	  System.out.println(i+":"+sswc);
    	  if(sswc>max) {
    		  max = sswc;
    		  data = new ArrayList<double[]>(kmeans.getcenter());
    	  }
      }
      System.out.println(max);
	}
}




