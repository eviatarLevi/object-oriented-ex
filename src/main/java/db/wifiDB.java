package main.java.db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import main.java.tools.myFunction;
import main.java.wifiScan.Point;
import main.java.wifiScan.Wifi;
import main.java.wifiScan.wifiContiner;

public class wifiDB {
	private ArrayList<wifiOne> list;
	public wifiDB(scanDB sW)
	{
		list = new ArrayList<wifiOne>();
		
		for (int i = 0; i < sW.getSize(); i++) {
			wifiContiner wc = sW.getIndex(i);
			for (int j = 0; j < wc.getSize() ; j++) {
				Wifi w = wc.getWifi(j);
				list.add(new wifiOne(w, wc.getPoint(), myFunction.weightC(w.getSignal())));
			}
		}
		sort();
		
	}
	public wifiDB(ArrayList<wifiOne> list)
	{
		this.list = list;
	}
	public void sort()
	{
		Collections.sort(list, new Comparator<wifiOne>(){
			public int compare(wifiOne s1, wifiOne s2) {
				return s1.getMac().compareToIgnoreCase(s2.getMac());
			}
		});
	}
	public String findLoc(String MAC)
	{
		for (int i = 0; i < getSize(); i++) {
			wifiOne w = list.get(i);
			if (w.getMac().equals(MAC)) {
				Point p = w.getPoint();
				return "lat=" + p.getLat() + " lon=" + p.getLon() + " alt=" + p.getAlt();
			}
		}
		return null;
	}
	public int getSize()
	{
		return list.size();
	}
	public boolean remove(int i)
	{
		return list.remove(list.get(i));
	}
	public wifiOne getIndex(int i)
	{
		return list.get(i);
	}
	public void setList(ArrayList<wifiOne> list)
	{
		this.list = list;
	}
	
	
	
}
