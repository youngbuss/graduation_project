package operator;


import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.text.StyledEditorKit.ForegroundAction;


public class union {
    public ArrayList intersect( ArrayList<double[]> ls,  ArrayList<double[]> ls2) {
    	ArrayList<double[]> list = new ArrayList<double[]>();
        for (int i =0; i<ls.size();i++)
        {
        	 System.out.print("print:" + "ls" + "[" + i + "]=");
        	   System.out.println(Arrays.toString(ls.get(i)));
        	for (int j = 0;j<ls2.size();j++) {
        		 System.out.print("print:" + "ls2" + "[" + j + "]=");
            	   System.out.println(Arrays.toString(ls2.get(j)));
        		if (Arrays.equals(ls.get(i), ls2.get(j)))
        		{
        			System.out.println(1);
        			list.add(ls.get(j));
        		}
        	}
        }
        //list.removeAll(ls2);
        return list;
    }  
    public ArrayList union( ArrayList ls,  ArrayList ls2) {
    	ArrayList list = new ArrayList<double[]>(ls);
        list.addAll(ls2);
        return list;
    }  
    public static String getType(Object test) {
		return test.getClass().getName().toString();
					
	}
	public static  void main(String args[]) {
		union union =new union();
		ArrayList<double[]> arrayList = new ArrayList<double[]>();
		double[][] dataSetArray = new double[][]{ {8, 2, 4, 6, 10, 8}, { 3, 4, 2, 5, 4, 2 }, 
	        	{ 7, 3, 6, 2, 4, 7 }, { 6, 3, 5, 3, 6, 3 }, { 6, 9, 1, 6, 3, 9 }, { 4, 1, 8, 6, 10, 8 } };

	    for (int i = 0; i < dataSetArray.length; i++) {
	            arrayList.add(dataSetArray[i]);
	        }
	    ArrayList<double[]> arrayList2 = new ArrayList<double[]>();
		double[][] dataSetArray2 = new double[][]{ { 8, 2, 4, 6, 10, 8 }, { 3, 4, 1, 5, 4, 0 }, 
	        	{ 17,13, 16, 12, 4, 7 }, { 6, 13, 5, 23, 6, 33 }, { 61, 29, 1, 16, 3, 9 }, { 4, 1, 8, 6, 10, 8 } };

	    for (int i = 0; i < dataSetArray2.length; i++) {
	            arrayList2.add(dataSetArray2[i]);
	        }
	    //System.out.println(getType(arrayList.get(0)));
	    /*
		double[] f1 = {1,2,3,4,5};
		double[] f2 = {1,12,13,14,5};
		ArrayList list = new ArrayList<double[]>();
		  for(double str : f1){
		       list.add(str);
		   }
		//Collections.addAll(list, f1);
		/*
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
	
		ArrayList list2 = new ArrayList<double[]>();
		  for(double str : f2){
		       list2.add(str);
		   }
		/*
		list2.add(1);
		list2.add(12);
		list2.add(13);
		list2.add(14);
		list2.add(5);
	    */

		ArrayList<double[]> intersectList = union.intersect(arrayList, arrayList2);
		/*
		 for (int i = 0; i < arrayList.size(); i++) {
        	 System.out.print("print:" + "dataArrayName" + "[" + i + "]=");
      	   System.out.println(Arrays.toString(arrayList.get(i)));
        }
		 for (int i = 0; i < arrayList2.size(); i++) {
        	 System.out.print("print:" + "dataArrayName2" + "[" + i + "]=");
      	   System.out.println(Arrays.toString(arrayList2.get(i)));
        }
        */
	    System.out.println("½»¼¯£º");
	    for (int i = 0; i < intersectList.size(); i++) {
	         System.out.print(Arrays.toString(intersectList.get(i))+ " ");
	        }			   
	    }
}
	   
