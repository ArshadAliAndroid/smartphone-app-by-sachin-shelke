package com.sks.dialog.custom;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CustomDialogExample extends Activity {

	Context context;
	CustomizeDialog customizeDialog = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/** Display Custom Dialog */
		context = this;
		setContentView(R.layout.home);
		Button btn1 = (Button) findViewById(R.id.btnDialog1);

		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				customizeDialog = new CustomizeDialog(context);
				customizeDialog.setTitle("My Title");
				customizeDialog.setMessage("My Custom Dialog Message from \nSmartPhoneBySachin.Blogspot.com ");

				customizeDialog.show();

			}
		});

	}

}