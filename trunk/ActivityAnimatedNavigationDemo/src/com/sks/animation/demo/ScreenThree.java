package com.sks.animation.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class ScreenThree extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.screen_three);


		((Button) findViewById(R.id.btn_screen3_back)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(ScreenThree.this, ScreenTwo.class);
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
