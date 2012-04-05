package com.sks.demo.contact;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;

public class PhoneContactsActivity extends Activity {

	ListView lvContact;

	Cursor cursorContact;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

	}

	@Override
	protected void onStart() {

		super.onStart();

		lvContact = (ListView) findViewById(R.id.lvContact);

		cursorContact = getContact();

		for (String clm : cursorContact.getColumnNames()) {
			Log.v("Contact", "Column : " + clm);
		}

		ContentResolver cr = getContentResolver();

		Log.d("Contact", "TotalCount : " + cursorContact.getCount());
		cursorContact.moveToFirst();
		while (cursorContact.moveToNext()) {

			String id = cursorContact.getString(cursorContact.getColumnIndex(ContactsContract.Data._ID));
			Log.v("Contact", "ID: " + id);
			Log.v("Contact", "Name: " + cursorContact.getString(cursorContact.getColumnIndex(ContactsContract.Data.DISPLAY_NAME)));

			String[] projection = new String[]
			{
					ContactsContract.Contacts._ID,
					ContactsContract.Contacts.DISPLAY_NAME,
					Phone.NUMBER
			};

			if (Integer.parseInt(cursorContact.getString(cursorContact.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
				Cursor pCur = cr.query(Phone.CONTENT_URI, projection, Phone.CONTACT_ID + " = ?", new String[]
				{
					id
				}, null);
				while (pCur.moveToNext()) {

					Log.v("Contact", "Data Column : " + pCur.getColumnName(1));

					// for (String clm : pCur.getColumnNames()) {
					// Log.v("Contact", "Data Column : " + clm);
					// }
					Log.d("Contact", "Mobile: " + pCur.getString(pCur.getColumnIndex(Phone.DATA1)));
					// Log.d("Contact", "Email : " +
					// pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA)));
				}
				pCur.close();
			}

			// Log.v("Contact", "Mobile: " + cursorContact.getString(cursorContact.getColumnIndex("Phone")));
			// Log.v("Contact", "Mobile: " + cursorContact.getString(cursorContact.getColumnIndex(Phone.NUMBER)));
			Log.i("Contact", "-----------------------------------------------------------------------------------------------");
		}

		// while (cursorContact.moveToNext()) {
		// cursorContact.getString(cursorContact.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
		// }

	}

	private Cursor getContact() {

		Uri uri = ContactsContract.Contacts.CONTENT_URI;
		String[] projection = new String[]
		{
				ContactsContract.Contacts._ID,
				ContactsContract.Contacts.DISPLAY_NAME,
				ContactsContract.Contacts.HAS_PHONE_NUMBER
		};
		String selection = ContactsContract.Contacts.IN_VISIBLE_GROUP + " = '" + ("1") + "'";
		String[] selectionArgs = null;
		String sortOrder = ContactsContract.Contacts._ID + " ASC";

		return managedQuery(uri, null, null, selectionArgs, sortOrder);
	}

	private class ContactAdapter extends CursorAdapter {

		public ContactAdapter(Context context, Cursor c, boolean autoRequery) {
			super(context, c, autoRequery);
		}

		int resourseLayout;

		ArrayList<Cursor> object;

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;

			if (v != null) {
				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				v = inflater.inflate(R.layout.contact_item, null);
			}

			return super.getView(position, convertView, parent);
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			// TODO Auto-generated method stub

		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			// TODO Auto-generated method stub
			return null;
		}

	}
}