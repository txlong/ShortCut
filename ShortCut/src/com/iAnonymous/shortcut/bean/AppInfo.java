package com.iAnonymous.shortcut.bean;

import android.graphics.drawable.Drawable;
import android.util.Log;

public class AppInfo {
	public String appName = "";
	public String packageName = "";
	public String versionName = "";
	public int versionCode = 0;
	public Drawable appIcon = null;

	public void print() {
		Log.d("app", "Name:" + appName + " Package:" + packageName);
		Log.d("app", "Name:" + appName + " versionName:" + versionName);
		Log.d("app", "Name:" + appName + " versionCode:" + versionCode);
	}

}