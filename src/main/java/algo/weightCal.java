package main.java.algo;

import main.java.db.scanDB;
import main.java.tools.myParameters;
import main.java.wifiScan.Wifi;
import main.java.wifiScan.wifiContiner;

public class weightCal {
	 wifiContinerAndW[] top3 = new wifiContinerAndW[3];
	 int size = 0;
	public weightCal(scanDB db,wifiContiner wcNoGps){
		for (int i = 0; i < db.getSize(); i++) {
			wifiContiner wc = db.getIndex(i);
			double weight = 1;
			for (int j = 0; j < wcNoGps.getSize(); j++) {
				Wifi wNoGps = wcNoGps.getWifi(j);
				boolean eq = false;
				for (int k = 0; k < wc.getSize(); k++) {
					Wifi w = wc.getWifi(k);
					if(w.getMac().equals(wNoGps.getMac()))
					{
						int dif = Math.max(Math.abs(w.getSignal()-wNoGps.getSignal()),
										myParameters.MIN_DIF);
						weight *= (myParameters.NORM/(Math.pow(dif,myParameters.SIG_DIF)
									*Math.pow(wNoGps.getSignal(), myParameters.POWER) ));
						eq = true; 
						break;
					}
				}
				if (!eq){
					int dif = myParameters.NO_SIG_DIF;
					weight *= (myParameters.NORM/(Math.pow(dif,myParameters.SIG_DIF)
							*Math.pow(wNoGps.getSignal(), myParameters.POWER) ));
				}
			}
			inTop(wc, weight);
		}
	}
	public void inTop(wifiContiner wc,double weight)
	{
		if (size < 3)
		{
			top3[size++] = new wifiContinerAndW(wc, weight);
		}
		else
		{
			int weightMinIndex = 0;
			for (int i = 1; i < size; i++) {
				if(top3[weightMinIndex].getWeight() > top3[i].getWeight())
					weightMinIndex = i;
			}
			if (top3[weightMinIndex].getWeight() < weight)
				top3[weightMinIndex] = new wifiContinerAndW(wc,weight); 
				
		}
		
	}
}
