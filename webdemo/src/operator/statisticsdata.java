package operator;

import java.util.ArrayList;

public class statisticsdata {
	private ArrayList<double[]> userdata = new ArrayList<double[]>();
	public double[] statisdata ;
	public double[] statisarray  = {0,0,0,0,0};
	public void statis() {
		statisdata = new double[userdata.size()];
		for(int i =0;i<userdata.size();i++) {
			double sum = 0.0f;
			for(int j = 0;j<userdata.get(i).length;j++) {
				sum+=userdata.get(i)[j];
			}
			statisdata[i]=sum;
			if(sum>=0&&sum<1000) statisarray[0]++;
			if(sum>=1000&&sum<2000) statisarray[1]++;
			if(sum>=2000&&sum<3000) statisarray[2]++;
			if(sum>=3000&&sum<4000) statisarray[3]++;
			if(sum>=4000) {
				statisarray[4]++;
			}
		}
	}
	public statisticsdata(ArrayList<double[]> arrayList) {
		userdata = new ArrayList<double[]>(arrayList);
	}
	public void execute() {
		statis();
	}
}
