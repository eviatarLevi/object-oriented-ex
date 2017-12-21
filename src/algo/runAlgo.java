package algo;

import java.util.ArrayList;

import db.scanDB;
import db.wifiDB;
import db.wifiOne;
import wifiScan.Point;
import wifiScan.wifiContiner;

public class runAlgo {
	
	public static void Algo2(scanDB DBnoGps,scanDB DB)
	{
		for (int i = 0; i < DBnoGps.getSize(); i++) {
			wifiContiner wc = DBnoGps.getIndex(i);
			weightCal A = new weightCal(DB,wc);
			double sumLat=0, sumLon=0,sumAlt=0,sumWeight=0;
			for (int j = 0; j < A.size; j++) {
				sumLat += A.top3[j].getLat()*A.top3[j].getWeight();
				sumLon += A.top3[j].getLon()*A.top3[j].getWeight();
				sumAlt += A.top3[j].getAlt()*A.top3[j].getWeight();
				sumWeight += A.top3[j].getWeight();
			}
			wc.setLat(sumLat/sumWeight);
			wc.setLon(sumLon/sumWeight);
			wc.setAlt(sumAlt/sumWeight);
		}
		System.out.println("algo2 complite");
	}
	public static wifiDB Algo1(scanDB wc)
	{
		wifiDB wd = new wifiDB(wc);
		ArrayList<wifiOne> newList  = new ArrayList<wifiOne>();
		double sumLat=0, sumLon=0,sumAlt=0,sumWeight=0;
		for (int i = 0; i < wd.getSize(); i++) {
			wifiOne w = wd.getIndex(i);
			Point p = w.getPoint();
			sumLat += p.getLat() * w.getWeight();
			sumLon += p.getLon() * w.getWeight();
			sumAlt += p.getAlt() * w.getWeight();
			sumWeight += w.getWeight();;
			try {
				if(i<wd.getSize()-1){
					if(!w.getMac().equals(wd.getIndex(i+1).getMac())){
						newList.add(new wifiOne(w,new Point(sumLat/sumWeight,
											sumLon/sumWeight,sumAlt/sumLon)));
						sumLat =0; sumLon=0; sumAlt=0; sumWeight=0;
					}
				}
				else
					newList.add(new wifiOne(w,new Point(sumLat/sumWeight,
							sumLon/sumWeight,sumAlt/sumLon)));
			} catch (Exception e) {
				System.err.println(e);
			}
			
		}
		System.out.println("Done algo 1");
		return new wifiDB(newList);
	}
	
}
