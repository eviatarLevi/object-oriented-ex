package main.java.filters;

import java.io.Serializable;

import main.java.db.scanDB;

public class locationFilter implements filter,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	double x;
	double y;
	double dist;

	public locationFilter(double x,double y,double dist) {
		this.x = x;
		this.y = y;
		this.dist = dist;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getDist() {
		return dist;
	}

	public void setDist(double dist) {
		this.dist = dist;
	}

	@Override
	public void runOn(scanDB db) {
		for (int i = 0; i < db.getSize(); i++) 
			if (db.getIndex(i)!=null){
				Double Xn = db.getIndex(i).getLat();
				Double Yn = db.getIndex(i).getLon();
				Double dx = Math.pow(Xn - x,2);
				Double dy = Math.pow(Yn - y,2);
				if (!(dx+dy<=Math.pow(dist, 2))) {
					db.remove(i);
					i--;
				}
			}
	}
	public void runOnNOT(scanDB db) {
		for (int i = 0; i < db.getSize(); i++) 
			if (db.getIndex(i)!=null){
				Double Xn = db.getIndex(i).getLat();
				Double Yn = db.getIndex(i).getLon();
				Double dx = Math.pow(Xn - x,2);
				Double dy = Math.pow(Yn - y,2);
				if ((dx+dy<=Math.pow(dist, 2))) {
					db.remove(i);
					i--;
				}
			}
	}

}
