package filters;

import java.util.Date;

import db.scanDB;
import wifiScan.wifiContiner;

public class dateFilter implements filter {
	Date start;
	Date end;
	
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
				if(d.before(start) || d.after(end))
				{
					db.remove(i);
				}
			}
		}
	}
	public void runOnNOT(scanDB db) {
		for (int i = 0; i < db.getSize(); i++){
			wifiContiner wc = db.getIndex(i);
			if (wc!=null){
				Date d = wc.getDate();
				if(d.before(end) && d.after(start))
				{
					db.remove(i);
				}
			}
		}
	}
	
}
