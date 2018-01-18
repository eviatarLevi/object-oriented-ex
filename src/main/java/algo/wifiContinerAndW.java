package main.java.algo;

import main.java.wifiScan.wifiContiner;

public class wifiContinerAndW extends wifiContiner {
	protected double weight;
	
	public wifiContinerAndW(wifiContiner wc,double weight) {
		super(wc);
		this.weight = weight;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}

	
	
}
