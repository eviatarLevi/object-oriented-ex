package db;

import java.util.ArrayList;
import java.util.Date;

import wifiScan.Wifi;
import wifiScan.wifiContiner;

public class scanDB {
	private ArrayList<wifiContiner> list;
	public scanDB()
	{
		list = new ArrayList<wifiContiner>();
	}
	
	public int addScan(double lat, double lon, double alt, Date date, String deviceID)
	{
		wifiContiner wc = new wifiContiner(lat, lon, alt, date, deviceID);
		list.add(wc);
		return list.indexOf(wc);
	}
	public void addWifi(int i, String mac,String ssid, int chanel, int signal )
	{
		list.get(i).add(mac, ssid, chanel, signal);
	}
	public int add(double lat, double lon, double alt, Date date, String deviceID
						,String mac,String ssid, int chanel, int signal)
	{
		int i = addScan(lat, lon, alt, date, deviceID);
		addWifi(i, mac, ssid, chanel, signal);
		return i;
	}
	public wifiContiner getIndex(int i)
	{
		return list.get(i);
	}
	public Wifi getWifiIndex (int i,int j)
	{
		return list.get(i).getTop(j);
	}
	public int[] indexOf(String mac)
	{
		for (int i = 0; i < list.size(); i++) {
			wifiContiner wc = list.get(i);
			int j = wc.getMacIndex(mac);
			if (j >= 0){
				int[] ans = {i,j};
				return ans;
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
}
