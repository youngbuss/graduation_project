package operator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;




public class readcsv {
	 public static ArrayList<double[]> readCsv(String filepath) {
            
	        File csv = new File(filepath); // CSV�ļ�·��
	        csv.setReadable(true);//���ÿɶ�
	        csv.setWritable(true);//���ÿ�д
	        BufferedReader br = null;
	        try {
	            br = new BufferedReader(new FileReader(csv));
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	        String line = "";
	        String everyLine = "";
	        int l = 0;
	        ArrayList<String> allString = new ArrayList<String>();
	        ArrayList<double[]> userdata = new ArrayList<double[]>();
	        double[] fdata = new double[96];
	        try {
	            while ((line = br.readLine()) != null) // ��ȡ�������ݸ�line����
	            {
	            	double d;
	            	l ++;
	            	if(l == 1 ) {
	            		d = 0;
	            		fdata[0] = d;
	            		continue;
	            	}
	            		
	                everyLine = line;
	                
	                try {
	                	String data = everyLine.split(",")[1];
		                d = Double.valueOf(data);
					} catch (Exception e) {
						// TODO: handle exception
						d = Double.NaN;
					}
	                
	                //System.out.println(d);
	                // 96Ϊһ��
	                if(l%96==0) {
	                	fdata[(l-1)%96] = d;
	                	waterdata waterdata = new waterdata();
	                	waterdata.water(fdata);
	                	userdata.add(fdata);
	                	fdata = new double[96];   
	                	//System.out.println("������һ�������");
	                }
	                else {
	                	//System.out.println("���ǵ�"+l+"��"+(l-1)%96);
						fdata[(l-1)%96] = d ;
					}
	            }
	           
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return userdata;     
	    }
	 //��ȡ���ں�����
	 public static Map<String,double[]>readCsv2(String filepath) {           
	        File csv = new File(filepath); // CSV�ļ�·��
	        csv.setReadable(true);//���ÿɶ�
	        csv.setWritable(true);//���ÿ�д
	        BufferedReader br = null;
	        try {
	            br = new BufferedReader(new FileReader(csv));
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	        String line = "";
	        String everyLine = "";
	        int l = 0;
	        int a =0;
	        ArrayList<String> allString = new ArrayList<String>();
	        Map<String, double[]> demoMap = new HashMap<String, double[]>();
	        ArrayList<double[]> userdata = new ArrayList<double[]>();
	        double[] fdata = new double[96];
	        try {
	            while ((line = br.readLine()) != null) // ��ȡ�������ݸ�line����
	            {
	            	l ++;
	            	if(l == 1 ) continue;
	                everyLine = line;
	              //allString.add(d);
	                double d ;
	                String day;
	                try {
	                	String dayString = everyLine.split(",")[0];
	                	day = dayString.substring(3,13);
	                	String data = everyLine.split(",")[1];
	  	               //System.out.println(data);
	  	                 d = Double.valueOf(data);
	  	                //System.out.println(d);
					} catch (Exception e) {
						// TODO: handle exception
						String dayString = everyLine.split(",")[0];
	                	 day = dayString.substring(3,13);
						d  = Double.NaN;
					}
	                if(Double.isNaN(d)) {
          		//d = 0;
          		a++;
          	}
	                // 96Ϊһ��
	                if(l%96==0) {	                	
	                	fdata[(l-1)%96] = d;
	                	waterdata waterdata = new waterdata();
	                	waterdata.water(fdata);
	                	userdata.add(fdata);
	                	demoMap.put(day, fdata);
	                	fdata = new double[96];   
	                	//System.out.println("������һ�������");
	                }
	                else {
	                	//System.out.println("���ǵ�"+l+"��"+(l-1)%96);
						fdata[(l-1)%96] = d ;
					}
	            }
	           // System.out.println("csv���������������" + userdata.size()+"NULLֵ��"+a);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return demoMap;     
	    }
	 //��ѹ�ļ�
	 public static void unZip(File srcFile, String destDirPath) throws RuntimeException {
	     long start = System.currentTimeMillis();
	     // �ж�Դ�ļ��Ƿ����
	     if (!srcFile.exists()) {
	         throw new RuntimeException(srcFile.getPath() + "��ָ�ļ�������");
	     }
	     // ��ʼ��ѹ
	     ZipFile zipFile = null;
	     try {
	         zipFile = new ZipFile(srcFile);
	         Enumeration<?> entries = zipFile.entries();
	         while (entries.hasMoreElements()) {
	             ZipEntry entry = (ZipEntry) entries.nextElement();
	             //System.out.println("��ѹ" + entry.getName());
	             // ������ļ��У��ʹ������ļ���
	             if (entry.isDirectory()) {
	                 String dirPath = destDirPath + "/" + entry.getName();
	                 File dir = new File(dirPath);
	                 dir.mkdirs();
	             } else {
	                 // ������ļ������ȴ���һ���ļ���Ȼ����io��������copy��ȥ
	                 File targetFile = new File(destDirPath + "/" + entry.getName());
	                 // ��֤����ļ��ĸ��ļ��б���Ҫ����
	                 if(!targetFile.getParentFile().exists()){
	                     targetFile.getParentFile().mkdirs();
	                 }
	                 targetFile.createNewFile();
	                 // ��ѹ���ļ�����д�뵽����ļ���     
	                 InputStream is = zipFile.getInputStream(entry);
	                 FileOutputStream fos = new FileOutputStream(targetFile);
	                 int len;
	                 byte[] buf = new byte[512];
	                 while ((len = is.read(buf)) != -1) {
	                     fos.write(buf, 0, len);
	                 }
	                 // ����˳���ȴ򿪵ĺ�ر�
	                 fos.close();
	                 is.close();
	             }
	         }
	         long end = System.currentTimeMillis();
	         System.out.println("��ѹ��ɣ���ʱ��" + (end - start) +" ms");
	     } catch (Exception e) {
	         throw new RuntimeException("unzip error from ZipUtils", e);
	     } finally {
	         if(zipFile != null){
	             try {
	                 zipFile.close();
	             } catch (IOException e) { 
	                 e.printStackTrace();
	             }
	         }
	     }
	 }
	 
	//ѭ����ȡ�ļ� 
     
	 public static ArrayList<File> getFiles(String path){
		 File root = new File(path);
		 ArrayList<File> files = new ArrayList<File>();
		 if(!root.isDirectory()){
		   files.add(root);
		 }
		 else{
		   File[] subFiles = root.listFiles();
		   for(File f : subFiles){
		     files.addAll(getFiles(f.getAbsolutePath()));
		   }
		 }
		 return files;
	  }
	 //ɾ���ļ���
	 public static void deleteDir(String dirPath)
		{
			File file = new File(dirPath);
			if(file.isFile())
			{
				file.delete();
			}else
			{
				File[] files = file.listFiles();
				if(files == null)
				{
					file.delete();
				}else
				{
					for (int i = 0; i < files.length; i++) 
					{
						deleteDir(files[i].getAbsolutePath());
					}
					file.delete();
				}
			}
		}

	 public static  void main(String args[]) {
		 String fileString = "user1.csv";
		 fileString = fileString.split("\\.")[0];
		 System.out.println(fileString);
		 //readcsv cReadcsv= new readcsv();
		 //ArrayList<double[]> arrayList = cReadcsv.readCsv(fileString);
		 //System.out.println("csv�����������" + arrayList.size());
		 /*
		 for (int i = 0 ;i<arrayList.size();i++)
			 System.out.println(Arrays.toString(arrayList.get(i)));
		*/	 
		 
	 }
	 
}
