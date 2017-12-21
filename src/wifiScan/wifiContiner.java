package wifiScan;

import java.util.Date;

public class wifiContiner extends Location {
	private Wifi[] top10;
	private int size;
	public wifiContiner(double lat, double lon, double alt, Date date, String deviceID) {
		super(lat, lon, alt, date, deviceID);
		this.top10 = new Wifi[10];
		this.size = 0;
	}
	public wifiContiner(wifiContiner wc)
	{
		super(wc.lat, wc.lon, wc.alt, wc.date, wc.deviceID);
		this.top10 = new Wifi[10];
		this.size = wc.size;
		for (int i = 0; i < wc.size; i++) {
			top10[i] = wc.top10[i];
		}
	}
	public void add(String mac,String ssid, int chanel, int signal)
	{
		Wifi w = new Wifi(mac,ssid,chanel,signal);
		if(size<10){
			top10[size++] = w;
		}
		else
		{
			int rssiMin = 0;
			for (int i = 1; i < size; i++) {
				if (top10[i].getSignal() > top10[rssiMin].getSignal())
					rssiMin = i;
			}
			if (top10[rssiMin].getSignal() > signal)
				top10[rssiMin] = w;
		}
	}
	public Wifi getTop(int i) {
		return top10[i];
	}
	public int getSize() {
		return size;
	}
	public int getMacIndex(String mac)
	{
		for (int i = 0; i < size; i++) {
			if(top10[i].mac.equals(mac))
				return i;
		}
		return -1;
	}
	public Wifi getWifi(int i)
	{
		if (top10[i] != null)
			return top10[i];
		return null;
	}

}
