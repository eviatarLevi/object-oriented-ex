package update;

import java.text.ParseException;
import java.util.Date;

import mySQL.MySQL_101;

public class upSQL implements Runnable  {
	Date lastUp;
	public upSQL() {
		this.lastUp = new Date();
	}
	@Override
	public void run() {
		Date up = null;
		try {
			up = MySQL_101.getUpdate();
			if(up!=null && up.after(lastUp))
			{
				lastUp = up;
				//add up
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
