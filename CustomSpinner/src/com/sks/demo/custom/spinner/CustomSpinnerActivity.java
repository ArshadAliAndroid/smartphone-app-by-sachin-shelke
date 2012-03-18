package com.sks.demo.custom.spinner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class CustomSpinnerActivity extends Activity {
	
	Context mContext;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mContext = this;
        
        findViewById(R.id.spinnerid).setOnTouchListener(spinnerTouchListener);
        
    }
    
    
    
    
    OnTouchListener spinnerTouchListener = new OnTouchListener() {

		@Override
		public boolean onTouch(View touchedView, MotionEvent event) {

			if (touchedView.getId() == R.id.spinnerid)
			{
				if (event.getAction() == MotionEvent.ACTION_DOWN)
				{

					final String [] options = {"Maharashtra","J & K", "Goa","Punjab","Gujrat"};

					

					ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(touchedView.getContext(), R.layout.dropdown_items, options);

					AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
					builder.setTitle("Select Option");

					builder.setAdapter(itemsAdapter, new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int item) {
							((EditText) findViewById(R.id.spinnerid)).setText(options[item]);
							dialog.dismiss();
						}
					});

					AlertDialog alert = builder.create();
					if (!alert.isShowing())
						alert.show();
				}
			}
			return false;
		}

	};

    
    
}