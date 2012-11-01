package com.sks.asyncresultback;

import java.util.Random;

import android.os.AsyncTask;

public class MySampleAsyncTask extends AsyncTask<Void, Void, Void> {
	
	OnAsyncResult onAsyncResult;
	
	public void setOnResultListener(OnAsyncResult onAsyncResult) {
		if (onAsyncResult != null) {
			this.onAsyncResult = onAsyncResult;
			
		}
	}
	
	@Override
	protected Void doInBackground(Void... params) {
		
		if (onAsyncResult != null) {
			
			// TODO apply your business logic
			int someNumber = new Random().nextInt(999);
			
			if (someNumber % 2 == 0) {
				onAsyncResult.onResultSuccess(0, "Congratulations\nYou Got Success Value");
			} else {
				onAsyncResult.onResultFail(1, "Ohhhhhhhhhhh\n Your Result failed");
			}
		}
		
		return null;
	}
	
	public interface OnAsyncResult {
		
		public abstract void onResultSuccess(int resultCode, String message);
		
		public abstract void onResultFail(int resultCode, String errorMessage);
	}
	
}
