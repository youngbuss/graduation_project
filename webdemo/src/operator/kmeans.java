package operator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
public class kmeans {
	private double Maxdistance;
	private int maxloop;
	private double changediatance;
	private int numOfCluster;// 分成多少簇
    private int timeOfIteration;// 迭代次数
    private int dataSetLength;// 数据集元素个数，即数据集的长度
    private ArrayList<double[]> dataSet;// 数据集链表
    private ArrayList<double[]> pre_center;
    private ArrayList<double[]> center;// 中心链表
    private ArrayList<ArrayList<double[]>> cluster; //簇
    private ArrayList<Double> sumOfErrorSquare;// 误差平方和
    private Random random;
    private double SSWC;
    public double DBI;
    private double[][] ave_distance;
    private double[][] center_distance;
    /**
     * 设置需分组的原始数据集
     *
     * @param dataSet
     */

    public void setDataSet(ArrayList<double[]> dataSet) {
        this.dataSet = dataSet;
    }

    /**
     * 获取结果分组
     *
     * @return 结果集
     */

    public ArrayList<ArrayList<double[]>> getCluster() {
        return cluster;
    }
    public ArrayList<double[]> getcenter(){
    	return center;
    }
    public double getSswc() {
    	return SSWC;
    }
    /**
     * 构造函数，传入需要分成的簇数量
     *
     * @param numOfCluster
     *    簇数量,若numOfCluster<=0时，设置为1，若numOfCluster大于数据源的长度时，置为数据源的长度
     */
    public kmeans(int numOfCluster) {
        if (numOfCluster <= 0) {
            numOfCluster = 1;
        }
        this.numOfCluster = numOfCluster;
    }
    public kmeans(int numOfCluster,ArrayList<double[]> data) {
    	if (numOfCluster <= 0) {
            numOfCluster = 1;
        }
        this.numOfCluster = numOfCluster;
        dataSet = new ArrayList<double[]>(data);
    }
    /**
     * 初始化
     */
    private void init() {
    	maxloop = 100;
    	changediatance = 1;
        timeOfIteration = 0;
        random = new Random();
        //如果调用者未初始化数据集，则采用内部测试数据集
        if (dataSet == null || dataSet.size() == 0) {
            initDataSet();
        }
        dataSetLength = dataSet.size();
        //若numOfCluster大于数据源的长度时，置为数据源的长度
        if (numOfCluster > dataSetLength) {
            numOfCluster = dataSetLength;
        }
        center = initCenters();
        cluster = initCluster();
        sumOfErrorSquare = new ArrayList<Double>();
    }

    /**
     * 如果调用者未初始化数据集，则采用内部测试数据集
     */
    private void initDataSet() {
        dataSet = new ArrayList<double[]>();
        // 其中{6,3}是一样的，所以长度为15的数据集分成14簇和15簇的误差都为0
        double[][] dataSetArray = new double[][] { { 8, 2 }, { 3, 4 }, { 2, 5 },
                { 4, 2 }, { 7, 3 }, { 6, 2 }, { 4, 7 }, { 6, 3 }, { 5, 3 },
                { 6, 3 }, { 6, 9 }, { 1, 6 }, { 3, 9 }, { 4, 1 }, { 8, 6 } };

        for (int i = 0; i < dataSetArray.length; i++) {
            dataSet.add(dataSetArray[i]);
        }
    }

    /**
     * 初始化中心数据链表，分成多少簇就有多少个中心点
     *
     * @return 中心点集
     */
    private ArrayList<double[]> initCenters() {
        ArrayList<double[]> center = new ArrayList<double[]>();
        int[] randoms = new int[numOfCluster];
        boolean flag;
        int temp = random.nextInt(dataSetLength);
        randoms[0] = temp;
        //randoms数组中存放数据集的不同的下标
        for (int i = 1; i < numOfCluster; i++) {
            flag = true;
            while (flag) {
                temp = random.nextInt(dataSetLength);

                int j=0;
                for(j=0; j<i; j++){
                    if(randoms[j] == temp){
                        break;
                    }
                }

                if (j == i) {
                    flag = false;
                }
            }
            randoms[i] = temp;
        }

        // 测试随机数生成情况
        /*
         for(int i=0;i<numOfCluster;i++)
         {
         System.out.println("test1:randoms["+i+"]="+randoms[i]);
         }

         System.out.println();
        */
        for (int i = 0; i < numOfCluster; i++) {
            center.add(dataSet.get(randoms[i]));// 生成初始化中心链表
        }
        return center;
    }

    /**
     * 初始化簇集合
     *
     * @return 一个分为k簇的空数据的簇集合
     */
    private ArrayList<ArrayList<double[]>> initCluster() {
        ArrayList<ArrayList<double[]>> cluster = new ArrayList<ArrayList<double[]>>();
        for (int i = 0; i < numOfCluster; i++) {
            cluster.add(new ArrayList<double[]>());
        }

        return cluster;
    }

    /**
     * 计算两个点之间的距离
     *
     * @param element
     *            点1
     * @param center
     *            点2
     * @return 距离
     */
    private double distance(double[] element, double[] center) {
        double distance = 0.0f;
        double z = 0.0f;
        double x= 0.0f;
        for(int i=0;i<element.length;i++) {
        	x=element[i]-center[i];
        	z+=x*x;
        }
        //double x = element[0] - center[0];
        //double y = element[1] - center[1];
        //double z = x * x + y * y;
        distance = (double) Math.sqrt(z);

        return distance;
    }

    /**
     * 获取距离集合中最小距离的位置
     *
     * @param distance
     *            距离数组
     * @return 最小距离在距离数组中的位置
     */
    private int minDistance(double[] distance) {
        double minDistance = distance[0];
        int minLocation = 0;
        for (int i = 1; i < distance.length; i++) {
            if (distance[i] <= minDistance) {
                minDistance = distance[i];
                minLocation = i;
            }
        }
        return minLocation;
    }

    /**
     * 核心，将当前元素放到最小距离中心相关的簇中
     */
    private void clusterSet() {
        double[] distance = new double[numOfCluster];
        for (int i = 0; i < dataSetLength; i++) {
            for (int j = 0; j < numOfCluster; j++) {
                distance[j] = distance(dataSet.get(i), center.get(j));
                // System.out.println("test2:"+"dataSet["+i+"],center["+j+"],distance="+distance[j]);
            }
            int minLocation = minDistance(distance);
            cluster.get(minLocation).add(dataSet.get(i));// 核心，将当前元素放到最小距离中心相关的簇中

        }
    }

    /**
     * 求两点误差平方的方法
     *
     * @param element
     *            点1
     * @param center
     *            点2
     * @return 误差平方
     */
    private double errorSquare(double[] element, double[] center) {
        double x = element[0] - center[0];
        double y = element[1] - center[1];

        double errSquare = x * x + y * y;

        return errSquare;
    }

    /**
     * 计算误差平方和准则函数方法
     */
    private void countRule() {
        double jcF = 0;
        for (int i = 0; i < cluster.size(); i++) {
            for (int j = 0; j < cluster.get(i).size(); j++) {
                jcF += errorSquare(cluster.get(i).get(j), center.get(i));

            }
        }
        sumOfErrorSquare.add(jcF);
    }
    
    private void judgeifdone() {
		double distance = 0;
		double max = 0;
		for (int i = 0; i < pre_center.size(); i++) {
			double x = pre_center.get(i)[0] - center.get(i)[0];
	        double y = pre_center.get(i)[1] - center.get(i)[1];
	        distance = x * x + y * y;
	        //System.out.println(distance);
			if (distance > max)
			{
				//System.out.println(distance);
				max = distance;
			}
        }
		Maxdistance = max;
		//System.out.println(Maxdistance);
	}
    
    /**
     * 设置新的簇中心方法
     */
    private void setNewCenter() {
        for (int i = 0; i < numOfCluster; i++) {
        	//System.out.println(1);
            int n = cluster.get(i).size();
            //System.out.println(i+"n:"+n);        
            if (n != 0) {
            	int l = cluster.get(i).get(0).length;
                //System.out.println("Cluster.size"+n+" "+l);
                //double[] newCenter = { 0, 0 };
                double[] newCenter = new double[l];
                for(int k =  0; k<n; k++) {
                    for (int j = 0; j < l; j++) {                   	
                        newCenter[j] += cluster.get(i).get(k)[j];
                     }
                    //newCenter[k] = newCenter[k] / l;
                }
                //System.out.println(Arrays.toString(newCenter));
                for(int k = 0;k<l;k++)
                  newCenter[k] = newCenter[k] /n;
                //System.out.println(Arrays.toString(newCenter));
                // 设置一个平均值
                //newCenter[0] = newCenter[0] / n;
                //newCenter[1] = newCenter[1] / n;
                center.set(i, newCenter);
            }
        }
        
    }

    /**
     * 打印数据，测试用
     *
     * @param dataArray
     *            数据集
     * @param dataArrayName
     *            数据集名称
     */
    public void printDataArray(ArrayList<double[]> dataArray,
                               String dataArrayName) {
    	System.out.println(1);
        for (int i = 0; i < dataArray.size(); i++) {
        	 System.out.print("print:" + dataArrayName + "[" + i + "]=");
      	   System.out.println(Arrays.toString(dataArray.get(i)));
        }
        System.out.println("===================================");
    }

  
    /**
     * Kmeans算法核心过程方法
     */
    private void kmeans() {
        init();
        // 循环分组，直到误差不变为止
        while (true) {
        	// 聚类分组过程
            clusterSet();
            // 计算误差过程
            countRule();
            // 误差不变了，分组完成
            if (timeOfIteration != 0) {
                if (sumOfErrorSquare.get(timeOfIteration) - sumOfErrorSquare.get(timeOfIteration - 1) == 0) {
                   break;
                }
            }       
            setNewCenter();
            timeOfIteration++;
            cluster.clear();
            cluster = initCluster();
        }
    }

    /**
     * 执行算法
     */
    public void execute() {
        long startTime = System.currentTimeMillis();
        //System.out.println("kmeans begins");
        kmeans();
        long endTime = System.currentTimeMillis();
       // System.out.println("kmeans running time=" + (endTime - startTime)
               // + "ms");
        //System.out.println("kmeans ends");
        //System.out.println();
        sswc();
        countdbi();
    }
    /**
     * 计算SSWC
     * @param args
     */
    public void sswc() {
    	double a,b;
    	
    	double sswc = 0.0f;
    	for (int i = 0;i<cluster.size();i++) {
    		double min = Float.MAX_VALUE;
    		for(int j = 0;j<cluster.get(i).size();j++) {
    			//与簇中心的距离
    			a = distance(cluster.get(i).get(j), center.get(i));
    			//与其他簇中心的最近距离
    			for(int k=0;k<center.size();k++) {
    				if(k!=i) {
    					b=distance(cluster.get(i).get(j), center.get(k));
    					if(b*b<min*min)
    						min =b;
    				}
    			}
    			sswc+=(min-a)/Math.max(a, min);
    			//System.out.println(a+" "+min+" "+sswc);
    		}
    	}
    	SSWC = sswc/dataSet.size() ;
    }
  //计算簇内平均距离
    public void  countclusterdistance() {
    	ave_distance = new double[center.size()][1];
    	for(int i = 0;i<center.size();i++) {
    		double distance = 0.0;
    		for(int j = 0;j<cluster.get(i).size();j++) {
    		    distance += distance(cluster.get(i).get(j),center.get(i));	
    		}
    		distance = distance/cluster.get(i).size();
    		ave_distance[i][0] = distance;
    	}
    }
  //计算每个中心点之间的距离
    public void countcenterdistance() {
    	center_distance= new double[center.size()][center.size()];
    	for(int i =0;i<center.size()-1;i++) {
    		center_distance[i][i] = 0;
    		for(int j = i+1;j<center.size();j++ ) {
    			center_distance[i][j] = distance(center.get(i), center.get(j));
    			center_distance[j][i] = center_distance[i][j];
    		}
    	}
    }
    //计算DBI
    public  void countdbi() {
    	countclusterdistance();;
    	countcenterdistance();
    	//计算DBI过程
    	int cata = center.size();
        double dbi = 0;
        for(int i = 0;i<cata;i++) {
        	double max = Double.MIN_VALUE;
        	double v= 0;
        	for(int j = 0;j<cata;j++) {
        		if(j!=i) {
        			v = (ave_distance[i][0]+ave_distance[j][0])/center_distance[i][j];
        			if(v>max) max = v;
        		}
        	}
        	dbi += max;
        }
        DBI = dbi/cata;
    }
    public static  void main(String args[]) {
    	ArrayList<double[]> data = new ArrayList<double[]>();
        // 其中{6,3}是一样的，所以长度为15的数据集分成14簇和15簇的误差都为0
        double[][] dataSetArray = new double[][] { { 18, 2 }, { 13, 4 }, { 2, 15 },
                { 43, 2 }, { 37, 3 }, { 26, 2 }, { 41, 7 }, { 6, 13 }, { 52, 3 },
                { 61, 3 }, { 62, 9 }, { 1, 16 }, { 31, 19 }, { 41, 1 }, { 18, 6 } };

        for (int i = 0; i < dataSetArray.length; i++) {
            data.add(dataSetArray[i]);
        }
		kmeans k = new kmeans(3,data);
		k.execute();
		ArrayList<ArrayList<double[]>> cluster=k.getCluster();
        //查看结果
        for(int i=0;i<cluster.size();i++)
        {
            k.printDataArray(cluster.get(i), "cluster["+i+"]");
        }
        
        k.printDataArray(k.center, "center");
        
        k.sswc();
        System.out.println(k.SSWC);
        ArrayList<double[]> cArrayList = new ArrayList<double[]>(); 
        for(int j = 0; j<cluster.size();j++) {
			for (int m = 0 ; m<cluster.get(j).size();m++)
				cArrayList.add(cluster.get(j).get(m));
		}
        k.printDataArray(cArrayList, "demo");
	}
}

