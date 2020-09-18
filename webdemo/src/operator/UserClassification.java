package operator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import servlet.newuserclass;

public class UserClassification {
	public double clustersswc;
    private ArrayList<double[]> clusterUnion;
    private ArrayList<ArrayList<double[]>> clustergroup;//将聚类随机分组后分组集合
    //private ArrayList<ArrayList<double[]>> clusterset;  //分组聚类结果集
    private ArrayList<double[]> clusterset;       //分组聚类中心结果
    private ArrayList<double[]> CenterClusterSet;  //各个中心集合再次聚类后得到的中心
    private ArrayList<ArrayList<double[]>> CenterCluster; //各个中心集合后再次聚类后得到的聚类结果
    public double clusterdbi;
    public ArrayList<double[]> getCenterClusterSet(){
    	return CenterClusterSet;
    }
    public ArrayList<ArrayList<double[]>> getCenterCluster(){
    	return CenterCluster;
    }
    public ArrayList<double[]> getclusterUnion(){
    	return clusterUnion;
    }
    public ArrayList<ArrayList<double[]>> getclustergroup(){
    	return clustergroup;
    }
    public ArrayList<double[]> getclusterset(){
    	return clusterset;
    }
    private void Setdata(ArrayList<double[]> dArrayList) {
    	clustergroup = new ArrayList<ArrayList<double[]>>();
    	clusterUnion = new ArrayList<double[]>(dArrayList);
    	clusterset = new ArrayList<double[]>();
    	CenterCluster = new ArrayList<ArrayList<double[]>>();
    	CenterClusterSet = new ArrayList<double[]>();
    }
    private void initdata() {
    	clustergroup = new ArrayList<ArrayList<double[]>>();
    	clusterUnion = new ArrayList<double[]>();
        double[][] dataSetArray = new double[][] { { 8, 2 }, { 3, 4 }, { 2, 5 },
             { 4, 2 }, { 7, 3 }, { 6, 2 }, { 4, 7 }, { 6, 3 }, { 5, 3 },
             { 6, 3 }, { 6, 9 }, { 1, 6 }, { 3, 9 }, { 4, 1 }, { 8, 6 },{1,0} };
        for (int i = 0; i < dataSetArray.length; i++) {
              clusterUnion.add(dataSetArray[i]);
         }
    }
    //输入一组聚类结果，将聚类结果融合
    private void unioncluter(ArrayList<double[]> arrayList) {
    	//遍历输入数组
    	for (int i = 0;i<arrayList.size();i++) {
    		clusterUnion.add(arrayList.get(i));
    	}
    }
    //将所有聚类结果随机分成g组
    private void randomgroup(int g) {
    	int k = clusterUnion.size();
    	System.out.println(Arrays.toString(clusterUnion.get(12)));
    	System.out.println("k:"+k);
    	int s = k/g;  // 每一组簇数
    	int y = k%g;  // 最后一组簇数
    	if(y>0) {
    		//除不尽的情况
    		s++;
    		y = k - s*(g-1);
    	}	
    	System.out.println("s:"+s+"  y:"+y);
    	Collections.shuffle(clusterUnion);
    	for(int i = 0; i<(g-1);i++) {
    		ArrayList<double[]> arrayList = new ArrayList<double[]>();
    		for(int j = 0 ;j<s;j++) {
    			//System.out.println(1);
    			arrayList.add(clusterUnion.get(0));
    			clusterUnion.remove(0);
    		}
    		clustergroup.add(arrayList);
  		    //System.out.println("完成了"+i);
    	}
    	clustergroup.add(clusterUnion);
	}
    //分组聚类  将各个分组聚类后中心集合得到clusterset
    private void groupcluter() {
		for(int i = 0 ;i<clustergroup.size();i++) {
			int numpattern = clustergroup.get(i).size();
	        int dimension = clustergroup.get(i).get(0).length;
	        int cata = 10;
	        int maxcycle = 10000;
	        double m = 2;
	        double limit = 0.00001f;
			ArrayList<double[]> arrayList = new ArrayList<double[]>();
			ArrayList<double[]> dataArrayList = new ArrayList<double[]>();
			//haar jHaar =new haar(clustergroup.get(i));
			//jHaar.executeDwt();
			//ArrayList<double[]> arrayList = jHaar.LData;
			arrayList = clustergroup.get(i);
			
			double max= 0.0f;
			/*
			FuzzyCMeans fuzzyCMeans = new FuzzyCMeans(numpattern, dimension, cata, maxcycle, m, limit);
            fuzzyCMeans.executeFCM(arrayList);
            for(int j = 0;j<fuzzyCMeans.center.length;j++)
            	dataArrayList.add(fuzzyCMeans.center[j]);
            	*/
			kmeans kmeans = new kmeans(10,arrayList);
			kmeans.execute();
			dataArrayList = new ArrayList<double[]>(kmeans.getcenter());
			/*
		    for(int k = 10; k<=10 ;k++) {
		    	
		        kmeans kmeans = new kmeans(k, arrayList);
		        kmeans.execute();
		        double sswc= kmeans.getSswc();
		        //System.out.println(k+":"+sswc);
		        if(sswc>max) {
		          max = sswc;
		    	  dataArrayList = new ArrayList<double[]>(kmeans.getcenter());
		      }
		    }
		    System.out.println(max+ " ");
		    */
		    for(int j = 0 ;j <dataArrayList.size() ; j++) {
		    	clusterset.add(dataArrayList.get(j));
		    }
		}
	}
    public UserClassification(ArrayList<double[]> arrayList) {
    	clustergroup = new ArrayList<ArrayList<double[]>>();
    	clusterUnion = new ArrayList<double[]>(arrayList);
    	clusterset = new ArrayList<double[]>();
    	CenterCluster = new ArrayList<ArrayList<double[]>>();
    	CenterClusterSet = new ArrayList<double[]>();
    }
  
    //对分组聚类后中心集合再次聚类得到K个簇
    private void Clusterset() {
    	ArrayList<double[]> dataArrayList = new ArrayList<double[]>();
    	ArrayList<ArrayList<double[]>> dataArrayLists = new ArrayList<ArrayList<double[]>>();
    	double max = 0.0f;
    	double min = Double.MAX_VALUE;
    	for(int k = 5; k<=10 ;k++) {
	        kmeans kmeans = new kmeans(k, clusterset);
	        kmeans.execute();
	        double sswc= kmeans.getSswc();
	        double dbi = kmeans.DBI;
	        System.out.println(k+":"+dbi+":"+sswc);
	        /*
	        if(sswc>max) {
	          max = sswc;
	    	  dataArrayList = new ArrayList<double[]>(kmeans.getcenter());
	    	  dataArrayLists = new ArrayList<ArrayList<double[]>>(kmeans.getCluster());
	      }*/
	        if(dbi<min) {
		          min = dbi;
		    	  dataArrayList = new ArrayList<double[]>(kmeans.getcenter());
		    	  dataArrayLists = new ArrayList<ArrayList<double[]>>(kmeans.getCluster());
		      }
	    }
    	clustersswc = max;
    	clusterdbi = min;
	    CenterClusterSet = new ArrayList<double[]>(dataArrayList);	
	    CenterCluster = new ArrayList<ArrayList<double[]>>(dataArrayLists);
	}
    
    public void classexecute() {
        //userClassification.initdata();
        randomgroup(10);

        groupcluter();
    
        Clusterset();

    }
    public UserClassification() {
    	
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		readcsv cReadcsv = new readcsv();
		ArrayList<double[]> arrayList= new ArrayList<double[]>();
		String fileString = "F:\\javaPro\\webdemo\\data\\user2.csv";
		arrayList = cReadcsv.readCsv(fileString);
	    haar haar =new haar(arrayList);
	    haar.executeDwt();
	    arrayList = new ArrayList<double[]>(haar.LData);
	    kmeans kmeans = new kmeans(50,arrayList);
	    kmeans.execute();
        UserClassification userClassification = new UserClassification(kmeans.getcenter());
        userClassification.classexecute();
        for(int i = 0;i < userClassification.getCenterClusterSet().size();i++)
            System.out.println(i + " : "+ Arrays.toString(userClassification.getCenterClusterSet().get(i)));
        //userClassification.Setdata(kmeans.getcenter());
        /*
        for(int i = 0; i<userClassification.getclusterUnion().size();i++)
        	System.out.println(i+":"+ Arrays.toString(userClassification.getclusterUnion().get(i)));
        //userClassification.initdata();
        
        userClassification.randomgroup(4);
        ArrayList<ArrayList<double[]>> cArrayList = userClassification.getclustergroup();
        for(int i = 0 ;i<cArrayList.size();i++) {
        	System.out.print("第"+i+"组: "+cArrayList.get(i).size()+" "+cArrayList.get(i).get(0).length+" :");
        	for(int j = 0 ;j<cArrayList.get(i).size();j++) {
        		System.out.print(Arrays.toString(cArrayList.get(i).get(j)));
        	}
        	System.out.println();
        }
        userClassification.groupcluter();
        for(int i = 0;i < userClassification.getclusterset().size();i++)
           System.out.println(i + " : "+ Arrays.toString(userClassification.getclusterset().get(i)));
        userClassification.Clusterset();
        for(int i = 0;i < userClassification.getCenterClusterSet().size();i++)
            System.out.println(i + " : "+ Arrays.toString(userClassification.getCenterClusterSet().get(i)));
        for(int i = 0 ;i <userClassification.getCenterCluster().size();i++) {
        	for(int j = 0; j<userClassification.getCenterCluster().get(i).size();j++)
        		System.out.println("["+i+"]"+"["+j+"]"+Arrays.toString(userClassification.getCenterCluster().get(i).get(j)));
        }
        */
	}

	
}
