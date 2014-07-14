package com.abhi.addicted;

import java.util.Calendar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Broadcast extends BroadcastReceiver {
	public static int omin=0,fmin=0;
	public static int start = 0, stop = 0;
	public static int ohrs=0,fhrs=0;
	@Override
	public void onReceive(Context arg0, Intent intent) {
		// TODO Auto-generated method stub
		//sets value of start and stop variable to find at what time screen was made active and what time it was deactivated
		Calendar day = Calendar.getInstance();
		int a = day.get(Calendar.DATE);
		if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
			start = day.get(Calendar.MINUTE);
			ohrs=day.get(Calendar.HOUR_OF_DAY);
			omin=(ohrs*60)+start;
			Log.v("Action: ", "Screen_ON"+ohrs+start+omin);
		} else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
			stop = day.get(Calendar.MINUTE);
			fhrs=day.get(Calendar.HOUR_OF_DAY);
			fmin=(fhrs*60)+stop;
			Log.v("Action: ", "Screen Off"+fhrs+stop+fmin);
			
		}
		Intent i = new Intent(arg0, Background.class);
		i.putExtra("day", a);
		i.putExtra("start", omin);
		i.putExtra("stop", fmin);
		i.putExtra("ohrs",ohrs);
		i.putExtra("fhrs", fhrs);
		arg0.startService(i);
		/*
		 * long i = 0; if(arg1.getIntExtra("day", 0)==day){ if
		 * (arg1.getLongExtra("stop", i) == stop && arg1.getLongExtra("start",
		 * i) == start) { time += 0; } else if (arg1.getLongExtra("stop", i) ==
		 * stop && arg1.getLongExtra("start", i) != start) { time +=
		 * arg1.getLongExtra("start", i) - start; start =
		 * arg1.getLongExtra("start", i); } else if (arg1.getLongExtra("stop",
		 * i) != stop && arg1.getLongExtra("start", i) == start) { //time +=
		 * arg1.getLongExtra("stop", i) - start; stop =
		 * arg1.getLongExtra("stop", i); } else { start =
		 * arg1.getLongExtra("start", i); stop = arg1.getLongExtra("stop", i); }
		 * }else{ day=arg1.getIntExtra("day", 0); time=start=stop=0; } }
		 */

	}
}
