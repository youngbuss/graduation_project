package operator;

import java.util.ArrayList;

public class FuzzyCMeans {
	//确定的一些参数 C值 ,M值的选定有待考虑
	public int numpattern;//样本数 150
	public int dimension;//每个样本点的维数 4
	public int cata;//要聚类的类别数  3
	public int maxcycle;//最大的迭代次数 100
	public double m;//参数m 2
    public double limit ; // 0.00001
    //初始化保存数据数组
    public double[][] pattern;
    public double[][] umatrix;
    public double[][] center;
    public double[][] distance_matrix  ;
    public ArrayList<ArrayList<double[]>> cluster;
    public double SSWC;
    public double DBI;
    private double[][] ave_distance;
    private double[][] center_distance;
    //初始化参数
    public FuzzyCMeans(int numpattern, int dimension, int cata, int maxcycle, double m, double limit) {
    	this.numpattern = numpattern;
    	this.dimension = dimension;
    	this.cata = cata;
    	this.maxcycle = maxcycle;
    	this.m = m;
    	this.limit = limit;
    }
    
    //将初始数据保存至二维数组
    public boolean initdataset(ArrayList<double[]> arrayList) {
    	
    	for(int i = 0; i<numpattern;i++) {
    		for(int j = 0;j<dimension;j++) {
    			pattern[i][j] = arrayList.get(i)[j];
    		}
    	}
    	return true;
    }
    //规格化样本数据
    public void Normalization(double[][] pattern,int numpattern,int dimension) {
    	double[][] temp = new double[numpattern][dimension];
    	double[] row = new double[numpattern];
    	double[] min_max = new double[2];
    	for(int i =0;i<numpattern;i++) {
    		for(int j = 0; j<dimension;j++)
    			temp[i][j] = pattern[i][j];
    	}
    	//规格化过程为计算每一列最大值最小值，用当前值减去最小值/(最大值-最小值）
    	for(int j = 0;j<dimension;j++) {
    		for(int i = 0;i<numpattern;i++) {
    			row[i] = pattern[i][j];
    		}
    		min_max = min_max_fun(row);
    		double min = min_max[0];
    		double max = min_max[1];
    		for(int i = 0;i<numpattern;i++) {
    			pattern[i][j] = (temp[i][j] - min)/(max - min);
    		}
    	}
     
    }
    //求某列数据最大值最小值
    public static double[] min_max_fun(double []a){
		
		double minmax[] = new double[2];
		double minValue=a[0];
		double maxValue=a[1];
		for(int i=1;i<a.length;i++){
			if(a[i]<minValue)
				minValue=a[i];
			if(a[i]>maxValue)
				maxValue=a[i];
		}
		minmax[0]=minValue;
		minmax[1]=maxValue;
		return minmax;		
	}
    //求某一列矩阵之和 用于初始化隶属度确保这一列隶属度之和为1
    public double sumrow(double[][] temp ,int j) {
    	double sum  = 0;
    	for(int i =0;i<cata;i++) {
    		sum+=temp[i][j];
    	}
    	return sum;
    }
    //初始化隶属度
    public void initumatrix() {
    	double[][] temp = new double[cata][numpattern];
    	for(int i = 0;i<cata;i++) {
    		for(int j = 0;j<numpattern;j++) {
    			umatrix[i][j] = (double)Math.random();
    			temp[i][j] = umatrix[i][j];
    		}
    	}
    	for(int i = 0;i<cata;i++) {
    		for(int j = 0;j<numpattern;j++) {
    			umatrix[i][j] = temp[i][j]/sumrow(temp, j);
    		}
    	}
    }
    //FCM循环迭代过程
    public void FCM() {
    	int time = 1;//迭代次数
    	boolean flag = true; //迭代完成标志
    	double[][] temp_umatrix = new double[cata][numpattern];
    	while(flag) {
    		//保存更新前的隶属度
    		for(int i = 0;i<cata;i++) {
    			for (int j = 0; j < numpattern; j++) {
					temp_umatrix[i][j] = umatrix[i][j];
				}
    		}
    		//更新聚类中心
    		for(int i = 0;i<cata;i++) {
    			for(int j = 0;j<dimension;j++) {
    				double a = 0,b=0;
    				for(int k = 0;k<numpattern;k++) {
    					a+= Math.pow(umatrix[i][k], m)*pattern[k][j];
    					//System.out.print(a);
    					b+= Math.pow(umatrix[i][k], m);
    				}
    				center[i][j] = a/b;
    			}
    		}
    		//更新隶属度
    				
    		//先计算距离矩阵 
    		countdistance();
    		for(int j = 0;j<cata;j++) {
    			for(int i = 0;i<numpattern;i++) {
    				double dummy = 0;
    				for(int k = 0;k<cata;k++) {
    					dummy += Math.pow((distance_matrix[j][i])/(distance_matrix[k][i]), (2/(m-1)));
    				}
    				umatrix[j][i] = 1/dummy;
    			}
    		}
    		
    		//判断是否收敛或达到最大迭代次数
    		double cha[][] = new double[cata][numpattern];
			for(int i=0;i<temp_umatrix.length;i++)
				for(int j=0;j<temp_umatrix[i].length;j++){
					cha[i][j]=Math.abs(temp_umatrix[i][j]-umatrix[i][j]);
				}
			
			double f=0;
			for(int i=0;i<cata;i++){
				for(int j=0;j<numpattern;j++){
					if(cha[i][j]>f)
						f=cha[i][j];//f矩阵中最大值
				}
			}
			
			if(f<=limit)
    		  flag = false;
			time++;
			//System.out.println(time);
    	}
    	
    }
    //计算两点之间的距离
    public void countdistance() {
    	for(int i = 0 ;i<cata;i++) {
    		for(int j = 0;j<numpattern;j++) {
    			double d = 0;
    			for(int k = 0;k<dimension;k++) {
    				d+= Math.pow(center[i][k]-pattern[j][k], 2);
    			}
    			d =  Math.sqrt(d);
    			distance_matrix[i][j] = (double)d;
    		}
    	}
    }
    //执行FCM算法
    public void executeFCM(ArrayList<double[]> arrayList) {
    	pattern = new double[numpattern][dimension];
    	umatrix = new double[cata][numpattern];
    	center = new double[cata][dimension];
    	distance_matrix= new double[cata][numpattern];
    	//初始化数据集
    	initdataset(arrayList);
        //执行算法
    	if (cata >= numpattern || m <= 1)
			return ;
    	//规格化样本  	
    	Normalization(pattern,numpattern,dimension);
    	//System.out.println("规格化样本");
    	//初始化隶属矩阵
    	initumatrix();
    	//System.out.println("初始化隶属矩阵");
    	FCM();
    	savecluster();
    	sswc();
    	DBI();
    }
    
    public void savecluster() {
    	cluster  =new ArrayList<ArrayList<double[]>>();
    	for (int i = 0; i < cata; i++) {
            cluster.add(new ArrayList<double[]>());
        }
    	for(int i =0;i<numpattern;i++) {
    		int a = 0;
        	double max = 0;
    		for(int j = 0 ;j<cata;j++) {
    			if(umatrix[j][i]>max) {
    				max = umatrix[j][i];
    				a = j;
    			}
    			
    		}
    		cluster.get(a).add(pattern[i]);
    	}
    }
    public double distance(double[] a ,double[] b) {
    	double d= 0;
    	for(int i = 0;i<dimension;i++) {
    		d+= Math.pow(a[i]-b[i], 2);
    	}
    	d = (double)Math.sqrt(d);
    	return d;
    }
    public void sswc() {
    	double a,b;  	
    	double sswc = 0.0f;
    	
    	for (int i = 0;i<cata;i++) {
    		double min = Float.MAX_VALUE;
    		for(int j = 0;j<cluster.get(i).size();j++) {
    			//与簇中心的距离
    			a = distance(cluster.get(i).get(j), center[i]);
    			//System.out.println("与簇中心距离："+a);
    			//与其他簇中心的最近距离
    			for(int k=0;k<cata;k++) {
    				if(k!=i) {
    					b=distance(cluster.get(i).get(j), center[k]);
    					if(b*b<min*min)
    						min =b;
    				}
    			}			
    			sswc+=(min-a)/Math.max(a, min);
    			//System.out.println("sswc参数："+a+" "+min+" "+sswc);
    		}
    	}
    	SSWC = sswc/numpattern ;
    }
  //计算簇内平均距离
    public void countclusterdistance() {
    	ave_distance = new double[cata][1];
    	for(int i = 0;i<cata;i++) {
    		double distance = 0;
    		for(int j = 0;j<cluster.get(i).size();j++) {
    		    distance += distance(cluster.get(i).get(j),center[i]);	
    		}
    		distance = distance/cluster.get(i).size();
    		ave_distance[i][0] = distance;
    	}
    }
    //计算每个中心点之间的距离
    public void countcenterdistance() {
    	center_distance= new double[cata][cata];
    	for(int i =0;i<cata-1;i++) {
    		center_distance[i][i] = 0;
    		for(int j = i+1;j<cata;j++ ) {
    			center_distance[i][j] = distance(center[i], center[j]);
    			center_distance[j][i] = center_distance[i][j];
    		}
    	}
    }
    public void DBI() {
        countclusterdistance();	
        
        //System.out.println();
        countcenterdistance();
        /*
        for(int i = 0;i<cata;i++) {
        	for(int j = 0;j<cata;j++) {
        		System.out.print(center_distance[i][j]+" ");
        	}
        	System.out.println();
        }
        */
        //计算DBI过程
        double dbi = 0;
        for(int i = 0;i<cata;i++) {
        	double max = Float.MIN_VALUE;
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<double[]>dataSet = new ArrayList<double[]>();
        // 其中{6,3}是一样的，所以长度为15的数据集分成14簇和15簇的误差都为0
        double[][] dataSetArray = new double[][] { { 8, 2 }, { 3, 4 }, { 2, 5 },
                { 4, 2 }, { 7, 3 }, { 6, 2 }, { 4, 7 }, { 6, 3 }, { 5, 3 },
                { 6, 3 }, { 6, 9 }, { 1, 6 }, { 3, 9 }, { 4, 1 }, { 8, 6 } };

        for (int i = 0; i < dataSetArray.length; i++) {
            dataSet.add(dataSetArray[i]);
        }
        int numpattern = dataSet.size();
        int dimension = dataSet.get(0).length;
        int cata = 3;
        int maxcycle = 100;
        double m = 1.5f;
        double limit = 0.00001f;
        System.out.println(numpattern + "  " + dimension);
        FuzzyCMeans fuzzyCMeans = new FuzzyCMeans(numpattern, dimension, cata, maxcycle, m, limit);
        fuzzyCMeans.executeFCM(dataSet);
        System.out.println("初始化矩阵：");
        for(int i = 0;i<numpattern;i++) {
        	for(int j=0;j<dimension;j++)
        		System.out.print(fuzzyCMeans.pattern[i][j]+" ");
        	System.out.println();
        }
        System.out.println("隶属度矩阵：");
        for(int i = 0;i<cata;i++) {
        	for(int j=0;j<numpattern;j++)
        		System.out.print(fuzzyCMeans.umatrix[i][j]+" ");
        	System.out.println();
        }
        System.out.println("中心矩阵：");
        for(int i = 0;i<cata;i++) {
        	for(int j=0;j<dimension;j++)
        		System.out.print(fuzzyCMeans.center[i][j]+" ");
        	System.out.println();
        }
        System.out.println("距离矩阵：");
        for(int i = 0;i<cata;i++) {
        	for(int j=0;j<numpattern;j++)
        		System.out.print(fuzzyCMeans.distance_matrix[i][j]+" ");
        	System.out.println();
        }
        //System.out.print(fuzzyCMeans.pattern);
	}

}
