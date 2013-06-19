package com.iAnonymous.shortcut;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.iAnonymous.shortcut.util.Util;

public class MainActivity extends Activity implements OnClickListener {
	private static String TAG = MainActivity.class.getSimpleName();

	private Button button1;
	private Button button2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
	}

	private void initView() {
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);

		Util.setOnClickListeners(this, button1, button2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			Log.d(TAG, "hasShortcut:" + Util.hasShortcut(this));
			// Util.addShortcut(this);
			Util.addChildShortcut(this, "txl", R.drawable.ic_launcher);
			break;
		case R.id.button2:
			Util.delChildShortcut(this, "txl");
			// Util.delShortcut(this);
			break;
		default:
			break;
		}
	}
}
