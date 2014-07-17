package com.abhi.addicted;


import android.os.Bundle;
import android.preference.PreferenceActivity;

public class setTime extends PreferenceActivity {

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.time);
	}

}
