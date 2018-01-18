package main.java.wifiScan;

import java.util.Date;

public class Location extends Point{
	protected Date date;
	protected String deviceID;
	public Location(Point p,Date date,String deviceID)
	{
		super(p);
		this.date = date;
		this.deviceID = deviceID;
	}
	public Location(double lat,double lon,double alt,Date date,String deviceID)
	{
		super(lat,lon,alt);
		this.date = date;
		this.deviceID = deviceID;
	}
	public Location(Location L)
	{
		super(L.lat,L.lon,L.alt);
		this.date = L.date;
		this.deviceID = L.deviceID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	public Point getPoint(){
		return new Point(lat,lon,alt);
	}
}
