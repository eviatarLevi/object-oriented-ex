package main.java.wifiScan;

public class Point {
	protected double lat;
	protected double lon;
	protected double alt;
	public Point()
	{
		
	}
	public Point (double lat,double lon,double alt)
	{
		this.lat = lat;
		this.lon = lon;
		this.alt = alt;
	}
	public Point (Point p)
	{
		if (p != null){
			this.lat = p.lat;
			this.lon = p.lon;
			this.alt = p.alt;
		}
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public double getAlt() {
		return alt;
	}
	public void setAlt(double alt) {
		this.alt = alt;
	}
}
