package main.java.filters;

import main.java.db.scanDB;

public interface filter {
	public String toString();
	public void runOn(scanDB db);
	public void runOnNOT(scanDB db);	
}
