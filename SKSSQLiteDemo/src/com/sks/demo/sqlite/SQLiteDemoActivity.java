package com.sks.demo.sqlite;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class SQLiteDemoActivity extends Activity {

	String result = "";
	TextView tv;

	Context context;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		tv = (TextView) findViewById(R.id.myData);

		context = this;

		findViewById(R.id.add).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				insertData("XYZ", "BBBB");
			}
		});

		findViewById(R.id.get).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				displayData();
			}
		});

	}

	private void displayData() {
		ArrayList<HashMap<String, String>> data = getData();
		result = "";
		for (int i = 0; i < data.size(); i++) {
			HashMap<String, String> map = data.get(i);
			result += "\nID        : " + map.get("id");
			result += "\nName  : " + map.get("name");
			result += "\nCity      : " + map.get("city");
			result += "\n-------------------------------";

		}

		tv.setText(result);
	}

	private void insertData(String name, String city) {
		SQLiteDatabase sqliteDB = new SksOpenHelper(this).getWritableDatabase();

		try {
			ContentValues cv = new ContentValues();
			cv.put("name", name);
			cv.put("city", city);

			sqliteDB.insert("emp", null, cv);

			// ////////////////////////////////////////////////////////////////
			// Show Alert on data insert successfully
			// you can do same like this in exception clause for failure
			// ////////////////////////////////////////////////////////////////

			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setTitle("Success");
			builder.setMessage("Data Inserted SuccessFully");
			builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {

					displayData();
					dialog.dismiss();
				}
			});

			builder.create().show();

			// ////////////////////////////////////////////////////////////////
			// End Alert Part
			// ////////////////////////////////////////////////////////////////

		} catch (Exception e) {
			Log.e("MSRP", "Some Exception : " + e.toString());
			e.printStackTrace();
		} finally {
			sqliteDB.close();
		}
	}

	private ArrayList<HashMap<String, String>> getData() {
		SQLiteDatabase sqliteDB = new SksOpenHelper(this).getWritableDatabase();
		Cursor cursor = null;

		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		try {
			cursor = sqliteDB.query("emp", new String[]
				{
					"id",
					"name",
					"city" }, null, null, null, null, null);

			cursor.moveToFirst();

			if (cursor.getCount() != 0) {
				do {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("id", "" + cursor.getLong(0));
					map.put("name", cursor.getString(1));
					map.put("city", cursor.getString(2));

					list.add(map);

				} while (cursor.moveToNext());
			}

		} catch (Exception e) {
			Log.e("MSRP", "Some Exception : " + e.toString());
			e.printStackTrace();
		} finally {
			sqliteDB.close();
		}
		return list;
	}

}