package main.java.update;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import java.text.ParseException;
import java.util.Date;


import main.java.mySQL.MySQL_101;
import main.java.tools.myParameters;

public abstract class upSQL {
	Date lastUp;
	Toolkit toolkit;
	Timer timer;
	public upSQL() {
		this.lastUp = new Date();
		toolkit = Toolkit.getDefaultToolkit();
        timer = new Timer();
        timer.schedule(new RemindTask(),
                       0, myParameters.TIME_UP*1000);  //subsequent rate
        new RemindTask();
	}
	 class RemindTask extends TimerTask {
		 
	        public void run() {
	        	Date up = null;
	        	try {
					up = MySQL_101.getUpdate();
					if(up!=null && up.after(lastUp))
					{
						lastUp = up;
						onRun();
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	        }
	       
	    }
	 protected abstract void onRun();
	
	
	
}
