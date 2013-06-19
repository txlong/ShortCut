package com.iAnonymous.shortcut;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.iAnonymous.shortcut.adapter.ViewPagerAdapter;
import com.iAnonymous.shortcut.bean.AppInfo;

public class TestShortcutActivity extends Activity {

	private ViewPager mViewPager;

	/**
	 * 适配器.
	 */
	private ViewPagerAdapter mViewPagerAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_shortcut);

		setupViews();
		getApplications();
	}

	private ArrayList<AppInfo> getApplications() {
		ArrayList<AppInfo> appList = new ArrayList<AppInfo>(); // 用来存储获取的应用信息数据　　　　　
		List<PackageInfo> packages = getPackageManager().getInstalledPackages(0);

		for (int i = 0; i < packages.size(); i++) {
			PackageInfo packageInfo = packages.get(i);
			// Only display the non-system app info
			if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0)
				continue;
			AppInfo tmpInfo = new AppInfo();
			tmpInfo.appName = packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
			tmpInfo.packageName = packageInfo.packageName;
			tmpInfo.versionName = packageInfo.versionName;
			tmpInfo.versionCode = packageInfo.versionCode;
			tmpInfo.appIcon = packageInfo.applicationInfo.loadIcon(getPackageManager());
			appList.add(tmpInfo);// 如果非系统应用，则添加至appList
		}// 好啦 这下手机上安装的应用数据都存在appList里了。

		return appList;
	}

	private void setupViews() {
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mViewPagerAdapter = new ViewPagerAdapter(this, getApplications());
		mViewPager.setAdapter(mViewPagerAdapter);
		mViewPager.setCurrentItem(0);
	}
}
