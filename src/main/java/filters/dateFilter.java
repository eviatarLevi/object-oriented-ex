package main.java.filters;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.Format;
import java.util.Date;

import main.java.db.scanDB;
import main.java.wifiScan.wifiContiner;

public class dateFilter implements filter,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Date start;
	Date end;

	public String getStart(DateFormat format) {
		if (start != null)
			return format.format(start);
		return "";
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public String getEnd(DateFormat format) {
		if (end != null)
			return format.format(end);
		return "";
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public dateFilter(Date start, Date end) {
		if(start != null && end != null)
		{
			this.start = start;
			this.end = end;
		}
	}
	@Override
	public void runOn(scanDB db) {
		for (int i = 0; i < db.getSize(); i++){
			wifiContiner wc = db.getIndex(i);
			if (wc!=null){
				Date d = wc.getDate();
				if(d.before(start) || d.after(end)){
					db.remove(i);
					i--;
				}
			}
		}
	}
	public void runOnNOT(scanDB db) {
		for (int i = 0; i < db.getSize(); i++){
			wifiContiner wc = db.getIndex(i);
			if (wc!=null){
				Date d = wc.getDate();
				if(d.before(end) && d.after(start)){
					db.remove(i);
					i--;
				}
			}
		}
	}

}
