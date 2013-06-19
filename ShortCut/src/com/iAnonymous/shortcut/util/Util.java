package com.iAnonymous.shortcut.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcelable;
import android.view.View;
import android.view.View.OnClickListener;

import com.iAnonymous.shortcut.R;
import com.iAnonymous.shortcut.TestShortcutActivity;

public class Util {

	/**
	 * 为当前应用添加桌面快捷方式
	 * 
	 * @param context
	 * @param shortName
	 *            快捷方式名称
	 * @param shortIcon
	 */
	public static void addChildShortcut(Context context, String shortName, int shortIcon) {
		Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");

		Intent shortcutIntent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
		shortcutIntent.setAction("android.intent.action.MAIN");
		shortcutIntent.addCategory("android.intent.category.LAUNCHER");
		shortcutIntent.setComponent(new ComponentName(context, TestShortcutActivity.class));

		shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
		// 快捷方式名称
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortName);
		// 不允许重复创建（不一定有效）
		shortcut.putExtra("duplicate", false);
		// 快捷方式的图标
		Parcelable iconResource = Intent.ShortcutIconResource.fromContext(context, shortIcon);
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconResource);

		context.sendBroadcast(shortcut);
	}

	/**
	 * 为当前应用添加桌面快捷方式
	 * 
	 * @param context
	 * @param appName
	 *            快捷方式名称
	 */
	public static void addShortcut(Context context) {
		Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");

		Intent shortcutIntent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
		// 获取当前应用名称
		String title = null;
		try {
			final PackageManager pm = context.getPackageManager();
			title = pm.getApplicationLabel(pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA)).toString();
		} catch (Exception e) {
		}
		// 快捷方式名称
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
		// 不允许重复创建（不一定有效）
		shortcut.putExtra("duplicate", false);
		// 快捷方式的图标
		Parcelable iconResource = Intent.ShortcutIconResource.fromContext(context, R.drawable.ic_launcher);
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconResource);

		context.sendBroadcast(shortcut);
	}

	/**
	 * 删除当前应用的桌面快捷方式
	 * 
	 * @param context
	 */
	public static void delShortcut(Context context) {
		Intent shortcut = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");

		// 获取当前应用名称
		String title = null;
		try {
			final PackageManager pm = context.getPackageManager();
			title = pm.getApplicationLabel(pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA)).toString();
		} catch (Exception e) {
		}
		// 快捷方式名称
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
		Intent shortcutIntent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
		context.sendBroadcast(shortcut);
	}

	/**
	 * 删除当前应用的桌面快捷方式
	 * 
	 * @param context
	 */
	public static void delChildShortcut(Context context, String shortName) {
		Intent shortcut = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");

		// 快捷方式名称
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortName);

		Intent shortcutIntent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
		shortcutIntent.setAction("android.intent.action.MAIN");
		shortcutIntent.addCategory("android.intent.category.LAUNCHER");
		shortcutIntent.setComponent(new ComponentName(context, TestShortcutActivity.class));

		shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
		context.sendBroadcast(shortcut);
	}

	/**
	 * 判断桌面是否已添加快捷方式
	 * 
	 * @param context
	 * @param titleName
	 *            快捷方式名称
	 * @return
	 */
	public static boolean hasShortcut(Context context) {
		boolean result = false;
		// 获取当前应用名称
		String title = null;
		try {
			final PackageManager pm = context.getPackageManager();
			title = pm.getApplicationLabel(pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA)).toString();
		} catch (Exception e) {
		}

		final String uriStr;
		if (android.os.Build.VERSION.SDK_INT < 8) {
			uriStr = "content://com.android.launcher.settings/favorites?notify=true";
		} else {
			uriStr = "content://com.android.launcher2.settings/favorites?notify=true";
		}
		final Uri CONTENT_URI = Uri.parse(uriStr);
		final Cursor c = context.getContentResolver().query(CONTENT_URI, null, "title=?", new String[] { title }, null);
		if (c != null && c.getCount() > 0) {
			result = true;
		}
		return result;
	}

	public static void setOnClickListeners(OnClickListener context, View... views) {
		for (View view : views) {
			view.setOnClickListener(context);
		}
	}
}