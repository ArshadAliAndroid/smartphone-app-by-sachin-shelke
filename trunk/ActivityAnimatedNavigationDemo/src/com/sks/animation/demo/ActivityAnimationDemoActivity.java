package com.sks.animation.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActivityAnimationDemoActivity extends Activity {

	/** Called when the activity is first created. */


	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);


		((Button) findViewById(R.id.btn_screen1_next)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{

				Intent intent = new Intent(ActivityAnimationDemoActivity.this, ScreenTwo.class);
				// intent.setFlags(flags)
				startActivity(intent);
				
				overridePendingTransition(R.anim.next_outgoing, R.anim.next_incoming);


			}
		});
	}
	
	
	@Override
	public void onBackPressed()
	{
		// TODO Auto-generated method stub
		super.onBackPressed();
	}
}