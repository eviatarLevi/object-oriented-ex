package main.java.filters;

import java.io.Serializable;

import main.java.db.scanDB;

public class idFilter implements filter,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String id;
	
	public idFilter(String id){
		this.id = id;
	}

	@Override
	public void runOn(scanDB db) {
		for (int i = 0; i < db.getSize(); i++) {
			if(!db.getIndex(i).getDeviceID().equals(id)) {
				db.remove(i);
				i--;
			}
		}
	}
	public void runOnNOT(scanDB db) {
		for (int i = 0; i < db.getSize(); i++) {
			if(db.getIndex(i).getDeviceID().contains(id)) {
				db.remove(i);
				i--;
			}
		}
	}
	public String getId ()
	{
		return id;
	}


}
