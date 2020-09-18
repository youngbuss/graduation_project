package operator;

public class waterdata {
	public void water(double[] data) {
		  double a = 0f;
		  double b = 0f;
		  double x = 0f;
		  double y = 0f;
		  int q,w= 0;
		  if(Double.isNaN(data[0])) data[0] = 0.0f;
		  if(Double.isNaN(data[data.length-1])) data[0] = 0.0f;
		  for(int i = 1;i<data.length;i++) {
			  if(Double.isNaN(data[i])) {
				  System.out.println(i);
				  q = i-1;
				  x = data[i-1];
				  int k = 1;
				  for(int j= i+1;j<data.length;j++) {
					  if(!Double.isNaN(data[j])) {
						  y =data[j];
						  w = j;
						  break;
					  }
					  else {
						k++;
					}
				  }
				  a = (y-x)/(w-q);
				  System.out.println(y+" "+x+" "+w+" "+q);
				  for(int l = 1;l<=k;l++) {
					  data[q+l] = x + a*l;
					  
				  }
			  }
		  }
	  }
}
