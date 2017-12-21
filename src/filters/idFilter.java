package filters;

import db.scanDB;

public class idFilter implements filter {
	String id;
	
	public idFilter(String id){
		this.id = id;
	}
	
	@Override
	public void runOn(scanDB db) {
		for (int i = 0; i < db.getSize(); i++) {
			if(!db.getIndex(i).getDeviceID().equals(id))
				db.remove(i);
		}
	}
	

}
