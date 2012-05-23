package com.sks.demo.sqlite;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SksOpenHelper extends SQLiteOpenHelper {

	final static String DBNAME = "Demo.db";
	final static int version = 1;

	public SksOpenHelper(Context context) {
		super(context, DBNAME, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		// On Create method will call only once in application.
		// if database version change then onUpgrade method will call automatically

		Log.v("MRSP", "onCreate Method Called");

		try {
			String createTable = "create table emp (id INTEGER PRIMARY KEY AUTOINCREMENT not null, name varchar(50) default '-', city varchar(50))";

			String insertData1 = "insert into emp values(null, 'Dravid', 'RR')";
			String insertData2 = "insert into emp values(null, 'Tendulkar', 'MI')";
			String insertData3 = "insert into emp values(null, 'Sehwag', 'DD')";
			String insertData4 = "insert into emp values(null, 'Pathan', 'KKR')";
			String insertData5 = "insert into emp values(null, 'Mahi', 'CSK')";
			String insertData6 = "insert into emp values(null, 'Gambhir', 'KKR')";

			database.execSQL(createTable);
			database.execSQL(insertData1);
			database.execSQL(insertData2);
			database.execSQL(insertData3);
			database.execSQL(insertData4);
			database.execSQL(insertData5);
			database.execSQL(insertData6);

		} catch (SQLException e) {
			Log.e("MSRP", "Sqlite Exception\n" + e.toString());
			e.printStackTrace();
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		Log.v("MRSP", "onUpgrade Method Called");
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
		Log.v("MRSP", "onOpen Method Called");
	}

	@Override
	public synchronized void close() {
		super.close();
		Log.v("MRSP", "Close Method Called");
	}

}
