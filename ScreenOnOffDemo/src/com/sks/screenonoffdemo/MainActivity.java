package com.sks.screenonoffdemo;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		IntentFilter iff = new IntentFilter();
		iff.addAction(Intent.ACTION_SCREEN_ON);
		iff.addAction(Intent.ACTION_SCREEN_OFF);
		iff.addAction(Intent.ACTION_USER_PRESENT);
		registerReceiver(new MyBroadCastReciever(), iff);

		setContentView(R.layout.activity_main);

	}

	public class MyBroadCastReciever extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {

			Log.d("Check", "intent.getAction() : " + intent.getAction());

			if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
				Log.i("Check", "Screen went OFF");

				Toast.makeText(context, "screen OFF", Toast.LENGTH_LONG).show();
			} else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {

				KeyguardManager km = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
				boolean locked = km.inKeyguardRestrictedInputMode();

				Log.i("Check", "Screen went ON && Keygard Lock : " + locked);
				Toast.makeText(context, "screen ON", Toast.LENGTH_LONG).show();

				KeyguardManager mgr = (KeyguardManager) getSystemService(Activity.KEYGUARD_SERVICE);
				Log.i("Check", "is Keygard Lock : ");

			} else if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
				Log.i("Check", "Screen Unlock");
				Toast.makeText(context, "screen Unlock", Toast.LENGTH_LONG).show();
			}
		}
	}

}
