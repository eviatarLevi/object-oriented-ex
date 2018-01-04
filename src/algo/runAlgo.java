package algo;

import java.util.ArrayList;

import db.scanDB;
import db.wifiDB;
import db.wifiOne;
import wifiScan.Point;
import wifiScan.wifiContiner;

public class runAlgo {

	public static String Algo2(String noGPS, scanDB DB, boolean is3)
	{
		scanDB DBnoGps = new scanDB();
		String[] country = noGPS.split(",");
		if(is3) {
			for (int i = 0; i < 6; i = i+2) {
				if (country.length >= i)
					DBnoGps.add(0.0, 0.0, 0.0, null, "", country[i], "", 0, Integer.parseInt(country[i+1]));
			}
		}
		else {
			double[] p = new double[3];
			for (int j = 0; j < 3; j++) {
				if(country[2+j].equals("?"))
					p[j] = 0;
				else
					p[j] = Double.parseDouble(country[2+j]);
			}
			int i = DBnoGps.addScan(p[0], p[1],p[2], null, country[1]);
			for (int j = 0; j < 10; j++) {
				if (country.length > j*4 + 6)
					DBnoGps.addWifi(i, country[7+j*4], country[6+j*4], 
							Integer.parseInt(country[8+j*4]), (int)Double.parseDouble(country[9+j*4]));
			} 	
		}
		Algo2(DBnoGps,DB);
		wifiContiner wc = DBnoGps.getIndex(0);
		if(wc!=null)
			return "lat=" + wc.getLat() + " ," +"lon=" + wc.getLon() + " ," +"Alt=" + wc.getAlt();
		return null;
	}

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
		System.out.println("algo 2 complit");
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
								sumLon/sumWeight,sumAlt/sumWeight)));
						sumLat =0; sumLon=0; sumAlt=0; sumWeight=0;
					}
				}
				else
					newList.add(new wifiOne(w,new Point(sumLat/sumWeight,
							sumLon/sumWeight,sumAlt/sumWeight)));
			} catch (Exception e) {
				System.err.println(e);
			}

		}
		System.out.println("algo 1 complit");
		return new wifiDB(newList);
	}

}
