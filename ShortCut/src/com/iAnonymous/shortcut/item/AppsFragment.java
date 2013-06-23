package com.iAnonymous.shortcut.item;

import java.util.List;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iAnonymous.shortcut.R;
import com.iAnonymous.shortcut.bean.AppInfo;
import com.iAnonymous.shortcut.util.Util;

public final class AppsFragment extends Fragment {
	private static String TAG = AppsFragment.class.getSimpleName();

	private TextView textView1;
	private TextView textView2;
	private TextView textView3;
	private TextView textView4;
	private TextView textView5;
	private TextView textView6;
	private TextView textView7;
	private TextView textView8;

	private ImageView imageView1;
	private ImageView imageView2;
	private ImageView imageView3;
	private ImageView imageView4;
	private ImageView imageView5;
	private ImageView imageView6;
	private ImageView imageView7;
	private ImageView imageView8;

	private TextView[] textViews;
	private ImageView[] imageViews;
	private List<AppInfo> mAppInfos;
	
	public static AppsFragment newInstance(List<AppInfo> list ) {
		AppsFragment fragment = new AppsFragment();
		fragment.mAppInfos = list;
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.viewpager_itemview, null);

		textView1 = (TextView) view.findViewById(R.id.textView1);
		textView2 = (TextView) view.findViewById(R.id.textView2);
		textView3 = (TextView) view.findViewById(R.id.textView3);
		textView4 = (TextView) view.findViewById(R.id.textView4);
		textView5 = (TextView) view.findViewById(R.id.textView5);
		textView6 = (TextView) view.findViewById(R.id.textView6);
		textView7 = (TextView) view.findViewById(R.id.textView7);
		textView8 = (TextView) view.findViewById(R.id.textView8);

		imageView1 = (ImageView) view.findViewById(R.id.imageView1);
		imageView2 = (ImageView) view.findViewById(R.id.imageView2);
		imageView3 = (ImageView) view.findViewById(R.id.imageView3);
		imageView4 = (ImageView) view.findViewById(R.id.imageView4);
		imageView5 = (ImageView) view.findViewById(R.id.imageView5);
		imageView6 = (ImageView) view.findViewById(R.id.imageView6);
		imageView7 = (ImageView) view.findViewById(R.id.imageView7);
		imageView8 = (ImageView) view.findViewById(R.id.imageView8);

		textViews = new TextView[] { textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8 };
		imageViews = new ImageView[] { imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8 };
		for (int i = 0; i < mAppInfos.size(); i++) {
			textViews[i].setText(mAppInfos.get(i).appName);
			imageViews[i].setImageDrawable(mAppInfos.get(i).appIcon);
		}

		Util.setOnClickListeners(clickListener, imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8);

		return view;
	}

	OnClickListener clickListener = new OnClickListener() {
		int index = -1;

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.imageView1:
				index = 0;
				break;
			case R.id.imageView2:
				index = 1;
				break;
			case R.id.imageView3:
				index = 2;
				break;
			case R.id.imageView4:
				index = 3;
				break;
			case R.id.imageView5:
				index = 4;
				break;
			case R.id.imageView6:
				index = 5;
				break;
			case R.id.imageView7:
				index = 6;
				break;
			case R.id.imageView8:
				index = 7;
				break;
			default:
				break;
			}
			openApp(mAppInfos.get(index).packageName);
		}
	};

	private void openApp(String packageName) {
		Log.d(TAG, "openApp-->packageName:" + packageName);
		PackageInfo pi = null;
		try {
			pi = this.getActivity().getPackageManager().getPackageInfo(packageName, 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

		Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
		resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		resolveIntent.setPackage(pi.packageName);

		List<ResolveInfo> apps = this.getActivity().getPackageManager().queryIntentActivities(resolveIntent, 0);

		ResolveInfo ri = apps.iterator().next();
		if (ri != null) {

			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_LAUNCHER);

			ComponentName cn = new ComponentName(ri.activityInfo.packageName, ri.activityInfo.name);

			intent.setComponent(cn);
			this.getActivity().startActivity(intent);
		}
	}
}
