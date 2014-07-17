package com.abhi.addicted;

import java.util.Calendar;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Background extends Service implements OnTouchListener {
	/*
	 * public Background(String name) { super(name); // TODO Auto-generated
	 * constructor stub } public Background(){ super(null); }
	 */
	public static NotificationManager nm=null;
	public final static int id = 12345;
	static BroadcastReceiver mReceiver = null;
	public static int time;
	public static int starts = 0;
	public static int stops = 0;
	public static int day;
	public static int i = 0;
	public static int j = 0, k = 0;
	public static WindowManager windowmanager;
	public static LinearLayout linear;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		// setting WindowManager for capturing Touch event on full view
		mReceiver = new Broadcast();
		/*linear = new LinearLayout(this);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		linear.setLayoutParams(lp);
		linear.setOnTouchListener(this);
		windowmanager = (WindowManager) getSystemService(WINDOW_SERVICE);
		WindowManager.LayoutParams mParams = new WindowManager.LayoutParams(
				WindowManager.LayoutParams.MATCH_PARENT,
				WindowManager.LayoutParams.MATCH_PARENT,
				WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
						| WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
						| WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
				PixelFormat.TRANSLUCENT);
		mParams.gravity = Gravity.LEFT | Gravity.TOP;
		windowmanager.addView(linear, mParams);*/
		registerReceiver(mReceiver, filter);
	}

	// @Override
	/*
	 * protected void onHandleIntent(Intent intent) { // TODO Auto-generated
	 * method stub Calendar day=Calendar.getInstance(); int
	 * a=day.get(Calendar.DATE); }
	 * if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
	 * start=System.currentTimeMillis(); stop=0; }else
	 * if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
	 * stop=System.currentTimeMillis(); start=0; } Intent i=new
	 * Intent("com.abhi.addicted.Broadcast"); i.putExtra("start", start);
	 * i.putExtra("stop", stop); i.putExtra("day", a); startActivity(i); }
	 */

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void onDestroy() {
		// TODO Auto-generated method stub
		Toast.makeText(getBaseContext(), "Service is destroyed",
				Toast.LENGTH_SHORT).show();
		if (mReceiver != null) {
			unregisterReceiver(mReceiver);
		}
		super.onDestroy();
	}

	/*
	 * @Override protected void onHandleIntent(Intent arg1) { // TODO
	 * Auto-generated method stub long i = 0; if (arg1.getIntExtra("day", 0) ==
	 * day) { if (arg1.getLongExtra("stop", i) == stops &&
	 * arg1.getLongExtra("start", i) == starts) { time += 0; } else if
	 * (arg1.getLongExtra("stop", i) == stops && arg1.getLongExtra("start", i)
	 * != starts) { time += arg1.getLongExtra("start", i) - starts; starts =
	 * arg1.getLongExtra("start", i); } else if (arg1.getLongExtra("stop", i) !=
	 * stops && arg1.getLongExtra("start", i) == starts) { // time +=
	 * arg1.getLongExtra("stop", i) - start; stops = arg1.getLongExtra("stop",
	 * i); } else { starts = arg1.getLongExtra("start", i); stops =
	 * arg1.getLongExtra("stop", i); } } else { day = arg1.getIntExtra("day",
	 * 0); time = starts = stops = 0; } Log.v("day:", "" + day); Log.v("time",
	 * "" + time);
	 * 
	 * }
	 */

	@Override
	public int onStartCommand(Intent arg1, int flags, int startId) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// getting and calculating usage time
		if (arg1.getIntExtra("day", 0) == day) {
			if (arg1.getIntExtra("stop", i) == stops
					&& arg1.getIntExtra("start", i) == starts) {
				time += 0;
			} else if (arg1.getIntExtra("stop", i) == stops
					&& arg1.getIntExtra("start", i) != starts) {
				starts = arg1.getIntExtra("start", i);
				Log.v("starts:", "" + starts + stops);
			} else if (arg1.getIntExtra("stop", i) != stops
					&& arg1.getIntExtra("start", i) == starts) {
				// time += arg1.getLongExtra("stop", i) - start;
				if (k == 0) {
					stops = arg1.getIntExtra("stop", i);
					k++;
				}
				{
					stops = arg1.getIntExtra("stop", i);
					time += stops - starts;
					Log.v("stops:", "" + stops + starts);
				}
			} else {
				starts = arg1.getIntExtra("start", i);
				stops = arg1.getIntExtra("stop", i);
			}
		} else {
			day = arg1.getIntExtra("day", 0);
			time = starts = stops = 0;
		}
		Log.v("day:", "" + day);
		Log.v("time", "" + time);
		if (time < 60) {
			MainActivity.time.setText("" + time + "mins");
		} else {
			MainActivity.time.setText("" + time / 60 + "Hours" + time % 60
					+ "mins");
		}
		setArray();
		CheckTime();
		return super.onStartCommand(arg1, flags, startId);
	}

	private void setArray() {
		// TODO Auto-generated method stub
		// setting array for storing data which is used for list
		int i = MainActivity.array.length;
		if (i == 0) {
			MainActivity.dayarray = new int[1];
			MainActivity.array = new long[1];
			MainActivity.dayarray[0] = day;
			MainActivity.array[0] = time;
		} else {
			if (MainActivity.dayarray[i - 1] != day) {
				long[] temp = new long[i];
				int[] temp2 = new int[i];
				for (int j = 0; j < i; j++) {
					temp[j] = MainActivity.array[j];
					temp2[j] = MainActivity.dayarray[j];
				}
				MainActivity.dayarray = new int[i + 1];
				MainActivity.array = new long[i + 1];
				for (int j = 0; j < i; j++) {
					MainActivity.array[j] = temp[j];
					MainActivity.dayarray[j] = temp2[j];
				}
				MainActivity.array[i] = time;
				MainActivity.dayarray[i] = day;
			} else {
				MainActivity.array[i - 1] = time;
			}
		}
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		// for updating usage time via Global Touch event
		if (arg1.getAction() == MotionEvent.ACTION_DOWN) {
			Calendar a = Calendar.getInstance();
			int star = a.get(Calendar.MINUTE);
			time = star - starts;
			starts = star;
			day = a.get(Calendar.DATE);
			Log.v("touchday:", "" + day);
			Log.v("touchtime", "" + time);
			if (time < 60) {
				MainActivity.time.setText("" + time + "mins");
			} else {
				MainActivity.time.setText("" + time / 60 + "Hours" + time % 60
						+ "mins");
			}
			setArray();
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	public void CheckTime() {
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());
		
		if (Integer.parseInt(sp.getString("Hours", ""+0)) == (time / 60) && (time % 60) == 0) {
			nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			Intent i = new Intent(this, MainActivity.class);
			PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);
			String body = "Your Usage time has been reached.";
			String title = "Addicted";
			Notification n = new Notification(R.drawable.ic_launcher, body,
					System.currentTimeMillis());
			n.setLatestEventInfo(this, title, body, pi);
			n.defaults = Notification.DEFAULT_ALL;
			nm.notify(id, n);
		}else if((Integer.parseInt(sp.getString("Hours",""+0))-(time/60))==24 && (time%60)==0){
			nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			Intent i = new Intent(this, MainActivity.class);
			PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);
			String body = "Your Usage time has been reached.";
			String title = "Addicted";
			Notification n = new Notification(R.drawable.ic_launcher, body,
					System.currentTimeMillis());
			n.setLatestEventInfo(this, title, body, pi);
			n.defaults = Notification.DEFAULT_ALL;
			nm.notify(id, n);
		}
	}
}
