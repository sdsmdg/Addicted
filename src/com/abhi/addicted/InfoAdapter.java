package com.abhi.addicted;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class InfoAdapter extends BaseAdapter {
	long[] time;
	Activity context;
	int size;
	int[] days;

	public InfoAdapter(Activity mainActivity, long[] array, int[] dayarray) {
		// TODO Auto-generated constructor stub
		this.context = mainActivity;
		this.time = array;
		this.size = array.length;
		this.days = dayarray;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public class ViewHolder {
		TextView Date;
		TextView Hours;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		LayoutInflater inflater = context.getLayoutInflater();

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.list, null);
			holder = new ViewHolder();

			holder.Date = (TextView) convertView.findViewById(R.id.tvdat);
			holder.Hours = (TextView) convertView.findViewById(R.id.tvtim);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// PackageInfo packageInfo = (PackageInfo) getItem(position);
		// Drawable appIcon = packagemanager
		// .getApplicationIcon(packageInfo.applicationInfo);
		String date = "Usage of Device on " + days[position] + "was :";
		// appIcon.setBounds(0, 0, 40, 40);
		// holder..setCompoundDrawables(appIcon, null, null, null);
		// holder.apkname.setCompoundDrawablePadding(15);
		holder.Date.setText(date);
		holder.Hours.setText(" " + time[position] + "Hours");

		return convertView;
	}

}
