package operator;

import java.util.ArrayList;

public class FuzzyCMeans {
	//ȷ����һЩ���� Cֵ ,Mֵ��ѡ���д�����
	public int numpattern;//������ 150
	public int dimension;//ÿ���������ά�� 4
	public int cata;//Ҫ����������  3
	public int maxcycle;//���ĵ������� 100
	public double m;//����m 2
    public double limit ; // 0.00001
    //��ʼ��������������
    public double[][] pattern;
    public double[][] umatrix;
    public double[][] center;
    public double[][] distance_matrix  ;
    public ArrayList<ArrayList<double[]>> cluster;
    public double SSWC;
    public double DBI;
    private double[][] ave_distance;
    private double[][] center_distance;
    //��ʼ������
    public FuzzyCMeans(int numpattern, int dimension, int cata, int maxcycle, double m, double limit) {
    	this.numpattern = numpattern;
    	this.dimension = dimension;
    	this.cata = cata;
    	this.maxcycle = maxcycle;
    	this.m = m;
    	this.limit = limit;
    }
    
    //����ʼ���ݱ�������ά����
    public boolean initdataset(ArrayList<double[]> arrayList) {
    	
    	for(int i = 0; i<numpattern;i++) {
    		for(int j = 0;j<dimension;j++) {
    			pattern[i][j] = arrayList.get(i)[j];
    		}
    	}
    	return true;
    }
    //�����������
    public void Normalization(double[][] pattern,int numpattern,int dimension) {
    	double[][] temp = new double[numpattern][dimension];
    	double[] row = new double[numpattern];
    	double[] min_max = new double[2];
    	for(int i =0;i<numpattern;i++) {
    		for(int j = 0; j<dimension;j++)
    			temp[i][j] = pattern[i][j];
    	}
    	//��񻯹���Ϊ����ÿһ�����ֵ��Сֵ���õ�ǰֵ��ȥ��Сֵ/(���ֵ-��Сֵ��
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
    //��ĳ���������ֵ��Сֵ
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
    //��ĳһ�о���֮�� ���ڳ�ʼ��������ȷ����һ��������֮��Ϊ1
    public double sumrow(double[][] temp ,int j) {
    	double sum  = 0;
    	for(int i =0;i<cata;i++) {
    		sum+=temp[i][j];
    	}
    	return sum;
    }
    //��ʼ��������
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
    //FCMѭ����������
    public void FCM() {
    	int time = 1;//��������
    	boolean flag = true; //������ɱ�־
    	double[][] temp_umatrix = new double[cata][numpattern];
    	while(flag) {
    		//�������ǰ��������
    		for(int i = 0;i<cata;i++) {
    			for (int j = 0; j < numpattern; j++) {
					temp_umatrix[i][j] = umatrix[i][j];
				}
    		}
    		//���¾�������
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
    		//����������
    				
    		//�ȼ��������� 
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
    		
    		//�ж��Ƿ�������ﵽ����������
    		double cha[][] = new double[cata][numpattern];
			for(int i=0;i<temp_umatrix.length;i++)
				for(int j=0;j<temp_umatrix[i].length;j++){
					cha[i][j]=Math.abs(temp_umatrix[i][j]-umatrix[i][j]);
				}
			
			double f=0;
			for(int i=0;i<cata;i++){
				for(int j=0;j<numpattern;j++){
					if(cha[i][j]>f)
						f=cha[i][j];//f���������ֵ
				}
			}
			
			if(f<=limit)
    		  flag = false;
			time++;
			//System.out.println(time);
    	}
    	
    }
    //��������֮��ľ���
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
    //ִ��FCM�㷨
    public void executeFCM(ArrayList<double[]> arrayList) {
    	pattern = new double[numpattern][dimension];
    	umatrix = new double[cata][numpattern];
    	center = new double[cata][dimension];
    	distance_matrix= new double[cata][numpattern];
    	//��ʼ�����ݼ�
    	initdataset(arrayList);
        //ִ���㷨
    	if (cata >= numpattern || m <= 1)
			return ;
    	//�������  	
    	Normalization(pattern,numpattern,dimension);
    	//System.out.println("�������");
    	//��ʼ����������
    	initumatrix();
    	//System.out.println("��ʼ����������");
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
    			//������ĵľ���
    			a = distance(cluster.get(i).get(j), center[i]);
    			//System.out.println("������ľ��룺"+a);
    			//�����������ĵ��������
    			for(int k=0;k<cata;k++) {
    				if(k!=i) {
    					b=distance(cluster.get(i).get(j), center[k]);
    					if(b*b<min*min)
    						min =b;
    				}
    			}			
    			sswc+=(min-a)/Math.max(a, min);
    			//System.out.println("sswc������"+a+" "+min+" "+sswc);
    		}
    	}
    	SSWC = sswc/numpattern ;
    }
  //�������ƽ������
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
    //����ÿ�����ĵ�֮��ľ���
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
        //����DBI����
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
        // ����{6,3}��һ���ģ����Գ���Ϊ15�����ݼ��ֳ�14�غ�15�ص���Ϊ0
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
        System.out.println("��ʼ������");
        for(int i = 0;i<numpattern;i++) {
        	for(int j=0;j<dimension;j++)
        		System.out.print(fuzzyCMeans.pattern[i][j]+" ");
        	System.out.println();
        }
        System.out.println("�����Ⱦ���");
        for(int i = 0;i<cata;i++) {
        	for(int j=0;j<numpattern;j++)
        		System.out.print(fuzzyCMeans.umatrix[i][j]+" ");
        	System.out.println();
        }
        System.out.println("���ľ���");
        for(int i = 0;i<cata;i++) {
        	for(int j=0;j<dimension;j++)
        		System.out.print(fuzzyCMeans.center[i][j]+" ");
        	System.out.println();
        }
        System.out.println("�������");
        for(int i = 0;i<cata;i++) {
        	for(int j=0;j<numpattern;j++)
        		System.out.print(fuzzyCMeans.distance_matrix[i][j]+" ");
        	System.out.println();
        }
        //System.out.print(fuzzyCMeans.pattern);
	}

}
