package com.iAnonymous.shortcut.adapter;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.iAnonymous.shortcut.bean.AppInfo;
import com.iAnonymous.shortcut.item.AppsFragment;
import com.viewpagerindicator.IconPagerAdapter;

public class AppsFragmentAdapter extends FragmentStatePagerAdapter implements IconPagerAdapter {
	private static String TAG = AppsFragmentAdapter.class.getSimpleName();

	private ArrayList<AppInfo> mAppInfos;
	private int mCount;

	public AppsFragmentAdapter(FragmentManager fm, ArrayList<AppInfo> appInfos) {
		super(fm);
		this.mAppInfos = appInfos;
		this.mCount = appInfos.size() / 8 + ((appInfos.size() % 8) > 0 ? 1 : 0);
	}

	@Override
	public Fragment getItem(int position) {
		Log.d(TAG, "getItem-->position:" + position + "");
		Fragment fragment;
		if (position < mCount - 1) {
			fragment = AppsFragment.newInstance(mAppInfos.subList(8 * position, 8 * position + 8));
		} else {
			fragment = AppsFragment.newInstance(mAppInfos.subList(8 * position, mAppInfos.size()));
		}
		return fragment;
	}

	@Override
	public int getCount() {
		return mCount;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return "page:" + position;
	}

	@Override
	public int getIconResId(int index) {
		return index;
	}

	public void setCount(int count) {
		if (count > 0 && count <= mCount) {
			mCount = count;
			notifyDataSetChanged();
		}
	}
}