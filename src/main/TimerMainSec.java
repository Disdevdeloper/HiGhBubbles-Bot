package main;

import java.util.Timer;
import java.util.TimerTask;

public class TimerMainSec {
	
	static Timer timer = new Timer();
	
	public static void runen() {
		
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				Main.onSecond();
				
				
				
			}
		}, 1*1000,1*1000);
	}
	
		static public void shutdownen() {
			timer.cancel();
			
			
		}


}