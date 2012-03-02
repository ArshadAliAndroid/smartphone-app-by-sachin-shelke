package com.sks.animation.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class ScreenTwo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.screen_two);


		((Button) findViewById(R.id.btn_screen2_next)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(ScreenTwo.this, ScreenThree.class);
				startActivity(intent);
				overridePendingTransition(R.anim.next_outgoing, R.anim.next_incoming);

			}
		});


		((Button) findViewById(R.id.btn_screen2_back)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(ScreenTwo.this, ActivityAnimationDemoActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.back_outgoing, R.anim.back_incoming);

			}
		});


	}


	@Override
	public void onBackPressed()
	{
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.back_outgoing, R.anim.back_incoming);
	}

}
