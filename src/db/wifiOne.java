package db;

import wifiScan.*;

public class wifiOne extends Wifi {
	protected Point P;
	protected double weight;
	public wifiOne(Wifi w, Point P){
		super(w);
		this.P = new Point(P);
	}
	public wifiOne(Wifi w, Point P,double weight){
		super(w);
		this.P = new Point(P);
		this.weight = weight;
	}
	public Point getPoint() {
		return P;
	}
	public void settPoint(Point p) {
		P = p;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String toString()
	{
		return getMac() + "," + getSsid() + "," + getChanel() + "," +
					P.getLat() + "," + P.getLon() + "," + P.getAlt();
	}
}
