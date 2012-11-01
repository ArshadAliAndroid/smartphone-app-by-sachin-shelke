package com.sks.asyncresultback;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.sks.asyncresultback.MySampleAsyncTask.OnAsyncResult;

public class MainActivity extends Activity {
	
	public TextView textView;
	MySampleAsyncTask asyncTask;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textView = (TextView) findViewById(R.id.tv_Result);
		
		findViewById(R.id.btn_clickMe).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				asyncTask = new MySampleAsyncTask();
				asyncTask.setOnResultListener(asynResult);
				asyncTask.execute();
			}
		});
	}
	
	OnAsyncResult asynResult = new OnAsyncResult() {
		
		@Override
		public void onResultSuccess(final int resultCode, final String message) {
			
			// need to add this part in ui thread.
			// as you will then thread exception.
			
			runOnUiThread(new Runnable() {
				public void run() {
					textView.setText("Code : " + resultCode + "\nMessage : " + message);
				}
			});
		}
		
		@Override
		public void onResultFail(final int resultCode, final String errorMessage) {
			// need to add this part in ui thread.
			// as you will then thread exception.
			runOnUiThread(new Runnable() {
				public void run() {
					textView.setText("Code : " + resultCode + "\nMessage : " + errorMessage);
				}
			});
			
		}
	};
	
}
