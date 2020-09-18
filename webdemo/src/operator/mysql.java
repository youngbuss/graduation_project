package operator;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class mysql {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/demo?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    static final String USER = "root";
    static final String PASS = "root";
    public mysql() {
    	
    }
    public void add_data (String user,String day,String data) {
    	 Connection conn = null;
         Statement stmt = null;
    	 try {
    		 Class.forName(JDBC_DRIVER);
    	        
             // 打开链接
             System.out.println("连接数据库...");
             conn = DriverManager.getConnection(DB_URL,USER,PASS);
             // 执行查询
             System.out.println(" 实例化Statement对象...");
             stmt = conn.createStatement();
             String sql;
             //sql = "Show tables";
             sql ="INSERT INTO `demo`.`user_data`(`user_id`, `day`, `data`) VALUES ('"+user+"','"+day+"','"+data+"')";
             System.out.println(sql);
             boolean flag = stmt.execute(sql);
            // ResultSet rs = stmt.execute(sql);
            // while (rs.next()) {
            //	 String aString = rs.getString(1);
            //	 System.out.println(aString);
           //  }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    public void add_data (HashMap<String, HashMap<String, String>> hashMap) {
      	 Connection conn = null;
         Statement stmt = null;
      	 try {
      		 Class.forName(JDBC_DRIVER);    	        
               // 打开链接
               System.out.println("连接数据库...");
               conn = DriverManager.getConnection(DB_URL,USER,PASS);
               // 执行查询
               System.out.println(" 实例化Statement对象...");
               stmt = conn.createStatement();
               String sql;
               for(Map.Entry<String,HashMap<String, String>> entry : hashMap.entrySet()){
               	 String key = entry.getKey();
               	 HashMap<String, String> map = entry.getValue();
               	 for(Map.Entry<String, String> entry2 :map.entrySet()) {
               		 String day = entry2.getKey();
               		 String data = entry2.getValue();
               		 sql ="INSERT INTO `demo`.`user_data`(`user_id`, `day`, `data`) VALUES ('"+key+"','"+day+"','"+data+"')";
               		 //System.out.println(sql);
                     boolean flag = stmt.execute(sql);
               	 }
               }
   		} catch (Exception e) {
   			// TODO: handle exception
   			e.printStackTrace();
   		}
      }
    public void add_mode (String user,ArrayList<double[]> arrayList) {
     	 Connection conn = null;
          Statement stmt = null;
     	 try {
     		 Class.forName(JDBC_DRIVER);
     	        
              // 打开链接
              System.out.println("连接数据库...");
              conn = DriverManager.getConnection(DB_URL,USER,PASS);
              // 执行查询
              System.out.println(" 实例化Statement对象...");
              stmt = conn.createStatement();
              String sql;           
        	  String data = Double.toString(arrayList.get(0)[0]);
        	  for(int j = 1;j<arrayList.get(0).length;j++) {
        		  data = data+"-"+arrayList.get(0)[j];
        	  }
        	  sql ="INSERT INTO `demo`.`user_mode`(`user_id`, `mode0`) VALUES ('"+user+"','"+data+"')";
        	  System.out.println(sql);
              boolean flag = stmt.execute(sql);
              for(int i =1;i<arrayList.size();i++) {
            	  String mode = "mode"+i;
            	  String data2 = Double.toString(arrayList.get(i)[0]);
            	  for(int j = 1;j<arrayList.get(i).length;j++) {
            		  data2 = data2+"-"+arrayList.get(i)[j];
            	  }
            	  //sql ="INSERT INTO `demo`.`user_mode`(`user_id`, `"+mode+"`) VALUES ('"+user+"','"+data2+"')";
            	  sql = "UPDATE `demo`.`user_mode` SET `"+mode+"` = '"+data2+"' where `user_id` = '"+user+"'";
            	  System.out.println(sql);
                  boolean flag2 = stmt.execute(sql);
              }
  		} catch (Exception e) {
  			// TODO: handle exception
  			e.printStackTrace();
  		}
     }
    public void add_all_mode(HashMap<String, ArrayList<double[]>> hashMap) {
    	Connection conn = null;
        Statement stmt = null;
        try {
    		 Class.forName(JDBC_DRIVER);
    	        
             // 打开链接
             System.out.println("连接数据库...");
             conn = DriverManager.getConnection(DB_URL,USER,PASS);
             // 执行查询
             System.out.println(" 实例化Statement对象...");
             stmt = conn.createStatement();
             String sql;   
         	for(Map.Entry<String,ArrayList<double[]>> entry : hashMap.entrySet()){
         		
        		String user  = entry.getKey();
        		System.out.println(user);
        		ArrayList<double[]> arrayList = entry.getValue();
        		String data = Double.toString(arrayList.get(0)[0]);
             	for(int j = 1;j<arrayList.get(0).length;j++) {
             		data = data+"+"+arrayList.get(0)[j];
             	}
             	sql ="INSERT INTO `demo`.`user_mode`(`user_id`, `mode0`) VALUES ('"+user+"','"+data+"')";
             	//System.out.println(sql);
                boolean flag = stmt.execute(sql);
                for(int i =1;i<arrayList.size();i++) {
             	    String mode = "mode"+i;
             	    String data2 = Double.toString(arrayList.get(i)[0]);
             	    for(int j = 1;j<arrayList.get(i).length;j++) {
             		  data2 = data2+"+"+arrayList.get(i)[j];
             	    }
             	  //sql ="INSERT INTO `demo`.`user_mode`(`user_id`, `"+mode+"`) VALUES ('"+user+"','"+data2+"')";
             	    sql = "UPDATE `demo`.`user_mode` SET `"+mode+"` = '"+data2+"' where `user_id` = '"+user+"'";
             	   // System.out.println(sql);
                    boolean flag2 = stmt.execute(sql);
               }       		
        	}
                	  
 		} catch (Exception e) {
 			// TODO: handle exception
 			e.printStackTrace();
 		}
    
    }
    public ArrayList<double[]> getallmode(){
    	 Connection conn = null;
         Statement stmt = null;
         ArrayList<double[]> arrayList = new ArrayList<double[]>();
    	 try {
    		 Class.forName(JDBC_DRIVER);
    	        
             // 打开链接
             System.out.println("连接数据库...");
             conn = DriverManager.getConnection(DB_URL,USER,PASS);
             // 执行查询
             System.out.println(" 实例化Statement对象...");
             stmt = conn.createStatement();
             String sql;
             sql = "Select * from demo.user_mode ";         
             ResultSet rs = stmt.executeQuery(sql);
             while (rs.next()) {
            	 int i =2;
            	 while(rs.getString(i)!= null&&i<11) {
            		 double[] data = new double[24];
            		 String aString = rs.getString(i);
            		 String[] strings = aString.split("\\+");
            		 //System.out.println(aString+" ");
            		 for(int j = 0;j<strings.length;j++) {
            			 //System.out.print(strings[j]+" ");
            			 Double double1 = Double.parseDouble(strings[j]);
            			 data[j] = double1;
            		 }
            		 arrayList.add(data);
                	 i++;
            	 }     
             }
             
          
             //return arrayList;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	 return arrayList;
    }
    public ArrayList<double[]> getusermode(String user){
    	Connection conn = null;
        Statement stmt = null;
        ArrayList<double[]> arrayList = new ArrayList<double[]>();
   	    try {
   		    Class.forName(JDBC_DRIVER);
   	        
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "Select * from demo.user_mode where user_id = '"+user+"' ";  
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
           	 int i =2;
           	 while(rs.getString(i)!= null&&i<11) {
           		 double[] data = new double[24];
           		 String aString = rs.getString(i);
           		 String[] strings = aString.split("\\+");
           		 //System.out.println(aString+" ");
           		 for(int j = 0;j<strings.length;j++) {
           			 //System.out.print(strings[j]+" ");
           			 Double double1 = Double.parseDouble(strings[j]);
           			 data[j] = double1;
           		 }
           		 arrayList.add(data);
               	 i++;
           	 }     
            }
            
           
            //return arrayList;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
   	 return arrayList;
   }
    public ArrayList<double[]> getorigindata(String user){
    	Connection conn = null;
        Statement stmt = null;
        ArrayList<double[]> arrayList = new ArrayList<double[]>();
   	 try {
   		 Class.forName(JDBC_DRIVER);
   	        
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "Select  data from demo.user_data  where user_id = '"+user+"'";         
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
           	// int i =2;          	 
           		 double[] data = new double[96];
           		 String aString = rs.getString(1);
           		 String[] strings = aString.split("\\+");
           		// System.out.println(aString+" ");
           		 for(int j = 0;j<strings.length;j++) {
           			 //System.out.print(strings[j]+" ");
           			 Double double1 = Double.parseDouble(strings[j]);
           			 data[j] = double1;
           		 }
           		 arrayList.add(data); 
            }           
            //return arrayList;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
   	 return arrayList;
    }
    public ArrayList<double[]> getdaysdata(String user,String start,String end){
    	Connection conn = null;
        Statement stmt = null;
        ArrayList<double[]> arrayList = new ArrayList<double[]>();
   	 try {
   		 Class.forName(JDBC_DRIVER);
   	        
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "Select  data from demo.user_data  where user_id = '"+user+"' and day>= '"+start+"' and day <= '"+end+"'";         
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
           	// int i =2;          	 
           		 double[] data = new double[96];
           		 String aString = rs.getString(1);
           		 String[] strings = aString.split("\\+");
           		// System.out.println(aString+" ");
           		 for(int j = 0;j<strings.length;j++) {
           			 //System.out.print(strings[j]+" ");
           			 Double double1 = Double.parseDouble(strings[j]);
           			 data[j] = double1;
           		 }
           		 arrayList.add(data); 
            }           
            //return arrayList;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
   	 return arrayList;
    }
    public String[] getuserlist(){
    	Connection conn = null;
        Statement stmt = null;
        
        ArrayList<String> myList = new ArrayList<String>();
   	    try {
   		   Class.forName(JDBC_DRIVER);
   	        
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            
            sql = "Select distinct user_id from demo.user_data ";         
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);      
            while (rs.next()) {
           	// int i =2;          	 
           		//double[] data = new double[96];
           		 String aString = rs.getString(1);
           		 //String[] strings = aString.split("\\+");
           		 myList.add(aString);
           		// System.out.println(aString+" ");
           		
            }          
            //return arrayList;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
   	 String[] lists = myList.toArray(new String[myList.size()]);
   	 return lists;
    }
    public void deleteuserinfo(String user) {
    	Connection conn = null;
        Statement stmt = null;
        ArrayList<double[]> arrayList = new ArrayList<double[]>();
   	    try {
   		   Class.forName(JDBC_DRIVER);  
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "DELETE FROM demo.user_data where user_id = '"+user+"'";         
            System.out.println(sql);
            boolean flag = stmt.execute(sql);
            sql = "DELETE FROM demo.user_mode where user_id = '"+user+"'";         
            System.out.println(sql);
            boolean flag2 = stmt.execute(sql);
            //return arrayList;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    public void add_group_data (int k,ArrayList<ArrayList<double[]>> arrayList,ArrayList<double[]> arrayList2) {
     	 Connection conn = null;
        Statement stmt = null;
     	 try {
     		 Class.forName(JDBC_DRIVER);    	        
              // 打开链接
              System.out.println("连接数据库...");
              conn = DriverManager.getConnection(DB_URL,USER,PASS);
              // 执行查询
              System.out.println(" 实例化Statement对象...");
              stmt = conn.createStatement();
              String sql;
              boolean flag;
              if(k>3) {
            	  int v = k -2;
            	  sql ="delete  from `demo`.`user_group` where `group_id` = '"+v+"'";
            	  flag = stmt.execute(sql);
            	  sql ="delete  from `demo`.`user_group_detail` where `group_id` = '"+v+"'";
            	  flag = stmt.execute(sql);
              }
             sql ="INSERT INTO `demo`.`user_group`(`group_id`) VALUES ('"+k+"')";
         	 flag = stmt.execute(sql);
              for(int i =0;i<arrayList.size();i++){
            	 String gString = "group"+(i+1);           	
            	 sql ="INSERT INTO `demo`.`user_group_detail`(`group_id`,`group`) VALUES ('"+k+"','"+i+"')";
            	 System.out.println(sql);
            	 flag = stmt.execute(sql);
            	 String data =  Double.toString(arrayList.get(i).get(0)[0]);           	 
            	 for(int m = 1;m<arrayList.get(i).get(0).length;m++) {
          			 data = data + "+" +arrayList.get(i).get(0)[m];
          		 }  
              	 for(int j = 1;j<arrayList.get(i).size();j++) {
              		String data2 = Double.toString(arrayList.get(i).get(j)[0]);;
              		for(int m = 1;m<arrayList.get(i).get(j).length;m++) {
             			 data2 = data2 + "+" +arrayList.get(i).get(j)[m];
             		 }  
              		 data = data + "%" +data2;         		
              	 }
              	 sql = "UPDATE `demo`.`user_group_detail` SET  `all_group` = '"+data+"' where `group_id` = '"+k+"' and `group` = '"+i+"'";
              	 //System.out.println(sql);
                 flag = stmt.execute(sql);
                 String data3= "";
                 data3 = Double.toString(arrayList2.get(i)[0]);
                 for(int j = 1;j<arrayList2.get(i).length;j++) {
               			 data3 = data3 + "+" +arrayList2.get(i)[j];  
                  }
                  sql = "UPDATE `demo`.`user_group` SET  `"+gString+"` = '"+data3+"' where `group_id` = '"+k+"'";
           		  //System.out.println(sql);
                  flag = stmt.execute(sql);
               }
              
  		} catch (Exception e) {
  			// TODO: handle exception
  			e.printStackTrace();
  		}
     }
    public ArrayList<double[]> getgroup(int group){
    	Connection conn = null;
        Statement stmt = null;
        ArrayList<double[]> arrayLists = new ArrayList<double[]>();
   	 try {
   		 Class.forName(JDBC_DRIVER);
   	        
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;          
        	sql = "Select  `group1`, `group2`,`group3`,`group4`,`group5`,`group6`,`group7`from demo.user_group  where `group_id` = '"+group+"'";
        	ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
            	for(int i = 1;i<=7;i++) {
            		String aString = rs.getString(i);
                	//System.out.println(aString);
              		String[] strings = aString.split("\\+");
              		//System.out.println(i+":"+strings[0]);
              		double[] data = new double[24];
              		for(int j = 0;j<strings.length;j++) {
              			Double double1 = Double.parseDouble(strings[j]);
                  		data[j] = double1;
              		}
              		arrayLists.add(data);
            	}
            }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
   	 return arrayLists;
    }
    public ArrayList<ArrayList<double[]>> getgroupdetail(int group){
    	Connection conn = null;
        Statement stmt = null;
        ArrayList<ArrayList<double[]>> arrayLists = new ArrayList<ArrayList<double[]>>();
   	 try {
   		 Class.forName(JDBC_DRIVER);
   	        
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;          
            for(int i =0;i<7;i++) {
            	ArrayList<double[]> arrayList = new ArrayList<double[]>();
            	sql = "Select  `all_group` from demo.user_group_detail  where `group_id` = '"+group+"' and `group` = '"+i+"'";
            	ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                	String aString = rs.getString(1);
                	//System.out.println(aString);
              		String[] strings = aString.split("\\%");
              		System.out.println(i+":"+strings[0]);
              		for(int j = 0;j<strings.length;j++) {
              			double[] data = new double[24];
              			String[] strings2 = strings[j].split("\\+");
              			for(int k=0;k<strings2.length;k++) {
              				Double double1 = Double.parseDouble(strings2[k]);
                  			data[k] = double1;
              			}
              			arrayList.add(data);
              		}

                }
                arrayLists.add(arrayList);
            }                  
          
            /*
            for(int i = 0;i<arrayLists.size();i++) {
           	  for(int k = 0;k<arrayLists.get(i).size();k++) {
           		for(int j = 0;j<arrayLists.get(i).get(k).length;j++) {
              		 System.out.print(i+" "+k+" "+arrayLists.get(i).get(k)[j]+" ");
              	 }
           		System.out.println();
           	  }       	
            }
            */
            //return arrayList;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
   	 return arrayLists;
    }
    public void clearalldataset() {
    	Connection conn = null;
        Statement stmt = null;
   	 try {
   		   Class.forName(JDBC_DRIVER);   	        
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "drop table `demo.user_data`,`demo.user_mode`,`demo.user_group`,`demo.user_group_detail`;";
            
            boolean result = stmt.execute(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    public void deletealluserdata() {
    	Connection conn = null;
        Statement stmt = null;
   	 try {
   		   Class.forName(JDBC_DRIVER);   	        
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "Truncate table `demo.user_data`,`demo.user_mode`,`demo.user_group`,`demo.user_group_detail`;";
            
            boolean result = stmt.execute(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    public String[] selectday2day(String name) {
    	Connection conn = null;
        Statement stmt = null;
        String[] days = new String[2];
   	 try {
   		   Class.forName(JDBC_DRIVER);   	        
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            String s="",e="";
            sql = "select `day` from user_data where user_id = '"+name+"' order by day desc limit 1;;";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
            	e  = rs.getString(1);
            }
            sql = "select `day` from user_data where user_id = '"+name+"' order by day  limit 1;;";
            ResultSet rs2 = stmt.executeQuery(sql);
            while (rs2.next()) {
            	s  = rs2.getString(1);
            }
            System.out.println(s+" "+e);
            days[0] = s;
            days[1] = e;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
   	 return days;
    }
    public void initdataset() {
    	Connection conn = null;
        Statement stmt = null;
   	 try {
   		   Class.forName(JDBC_DRIVER);   	        
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            boolean result;
            sql = "create table if not exists `user_data` (\r\n" + 
            		"   `user_id` VARCHAR(100) NOT NULL,\r\n" + 
            		"   `day`  VARCHAR(100) NOT NULL,\r\n" + 
            		"   `data` VARCHAR(15000),\r\n" + 
            		"   PRIMARY KEY ( `user_id` ,`day`)\r\n" + 
            		") ";
            result = stmt.execute(sql);
            sql = "create table if not exists `user_mode` (\r\n" + 
            		"   `user_id` VARCHAR(100) NOT NULL,\r\n" + 
            		"   `mode0` VARCHAR(1500),\r\n" + 
            		"   `mode1` VARCHAR(1500),\r\n" + 
            		"   `mode2` VARCHAR(1500),\r\n" + 
            		"   `mode3` VARCHAR(1500),\r\n" + 
            		"   `mode4` VARCHAR(1500),\r\n" + 
            		"   `mode5` VARCHAR(1500),\r\n" + 
            		"   `mode6` VARCHAR(1500),\r\n" + 
            		"   `mode7` VARCHAR(1500),\r\n" + 
            		"   `mode8` VARCHAR(1500),\r\n" + 
            		"   `mode9` VARCHAR(1500),\r\n" + 
            		"   PRIMARY KEY ( `user_id`)\r\n" + 
            		") ;";
            result = stmt.execute(sql);
            sql = "create table if not exists `user_group` (\r\n" + 
            		"   `group_id` VARCHAR(100) NOT NULL,\r\n" + 
            		"   `group1` VARCHAR(2000),\r\n" + 
            		"   `group2` VARCHAR(2000),\r\n" + 
            		"   `group3` VARCHAR(2000),\r\n" + 
            		"   `group4` VARCHAR(2000),\r\n" + 
            		"   `group5` VARCHAR(2000),\r\n" + 
            		"   `group6` VARCHAR(2000),\r\n" + 
            		"   `group7` VARCHAR(2000),\r\n" + 
            		"   PRIMARY KEY ( `group_id`)\r\n" + 
            		") ;";
            result = stmt.execute(sql);
            sql = "create table if not exists `user_group_detail` (\r\n" + 
            		"   `group` VARCHAR(100) NOT NULL,\r\n" + 
            		"   `group_id` VARCHAR(100) NOT NULL,\r\n" + 
            		"   `all_group` VARCHAR(15000),\r\n" + 
            		"   PRIMARY KEY ( `group`,`group_id`)\r\n" + 
            		") ;";
            //先清空保存的分类结果
            result = stmt.execute(sql);
            sql = "Truncate `user_group`";
            result = stmt.execute(sql);
            sql = "Truncate `user_group_detail`";
            result = stmt.execute(sql);
            
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    public static void main(String[] args) {
    	 Connection conn = null;
         Statement stmt = null;
    	 try {
    		 Class.forName(JDBC_DRIVER);
    	        
             // 打开链接
             System.out.println("连接数据库...");
             conn = DriverManager.getConnection(DB_URL,USER,PASS);
             // 执行查询
             System.out.println(" 实例化Statement对象...");
             stmt = conn.createStatement();
             String sql;
             sql = "Show tables";
             ResultSet rs = stmt.executeQuery(sql);
             while (rs.next()) {
            	 String aString = rs.getString(1);
            	 System.out.println(aString);
             }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    }
    
}
