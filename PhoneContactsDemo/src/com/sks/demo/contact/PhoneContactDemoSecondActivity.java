package com.sks.demo.contact;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;
import android.widget.ListView;

public class PhoneContactDemoSecondActivity extends Activity {
	ListView lvContact;

	String TAG = "Contact";

	ContentResolver cr;

	Uri contactUri = ContactsContract.Contacts.CONTENT_URI;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		cr = getContentResolver();

	}

	@Override
	protected void onStart() {
		super.onStart();
		lvContact = (ListView) findViewById(R.id.lvContact);

		Cursor crContact = getContact();

		int totalContacts = crContact.getCount();
		Log.d(TAG, "Total Contacts : " + totalContacts);
		Log.d(TAG, "Total Columns : " + crContact.getColumnCount());
		// Log.w(TAG, "Columns Names");
		// for (int i = 0; i < crContact.getColumnCount(); i++) {
		// Log.d(TAG, "   - " + crContact.getColumnName(i));
		// }
		crContact.moveToFirst();

		boolean nextItem = totalContacts > 0 ? true : false;

		for (int i = 0; i < totalContacts && nextItem == true; i++)

		{
			String contactId = crContact.getString(crContact.getColumnIndex(ContactsContract.Contacts._ID));
			Log.v(TAG, "ID : " + contactId);
			Log.v(TAG, "NAME : " + crContact.getString(crContact.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
			Log.v(TAG, "Photo URL : " + crContact.getString(crContact.getColumnIndex(ContactsContract.Data.PHOTO_ID)));

			Cursor crPhone = getPhoneNumber(contactId);
			if (crPhone != null) {

				Log.i(TAG, " ------ Phone Numbers : " + crPhone.getCount());
				Log.i(TAG, " ------ Phone Columns : " + crPhone.getColumnCount());

				while (crPhone.moveToNext()) {

					String number = crPhone.getString(crPhone.getColumnIndex(Phone.NUMBER));
					int type = crPhone.getInt(crPhone.getColumnIndex(Phone.TYPE));
					String icon = crPhone.getString(crPhone.getColumnIndex(Phone.CONTACT_STATUS_ICON));

					Log.v(TAG, " Phone: " + number);
					Log.v(TAG, " Type : " + type);
					Log.v(TAG, " Status : " + icon);
				}
				crPhone.close();

			}
			Log.w(TAG, " ---------------------------");

			Cursor crEmail = getEmail(contactId);
			if (crEmail != null) {

				Log.i(TAG, " ------ Emails : " + crEmail.getCount());
				Log.i(TAG, " ------ Email Columns : " + crEmail.getColumnCount());

				while (crEmail.moveToNext()) {

					String email = crEmail.getString(crEmail.getColumnIndex(Email.DATA));
					int type = crEmail.getInt(crEmail.getColumnIndex(Phone.TYPE));
					String icon = crEmail.getString(crEmail.getColumnIndex(Phone.CONTACT_STATUS_ICON));

					Log.v(TAG, " Email: " + email);
					Log.v(TAG, " Type : " + type);
					Log.v(TAG, " Status : " + icon);
				}
				crEmail.close();

			}

			Log.e(TAG, "************************************************************************************************");
			nextItem = crContact.moveToNext();
		}

	}

	@SuppressWarnings("unused")
	protected Cursor getEmail(String id) {

		String[] projection = new String[]
		{
				ContactsContract.Contacts._ID,
				ContactsContract.Contacts.DISPLAY_NAME,
				Email.DATA,
				Email.TYPE,
				Email.CONTACT_STATUS_ICON
		};

		String sortOrder = ContactsContract.Contacts._ID + " ASC";

		// @formatter:off
		
		return cr.query(Email.CONTENT_URI, null, Email.CONTACT_ID + " = ?", new String[]{id}, sortOrder);
		
		
		
		// @formatter:on

	}

	@SuppressWarnings("unused")
	protected Cursor getPhoneNumber(String id) {

		String[] projection = new String[]
		{
				ContactsContract.Contacts._ID,
				ContactsContract.Contacts.DISPLAY_NAME,
				Phone.NUMBER,
				Phone.TYPE,
				Phone.CONTACT_STATUS_ICON
		};

		String sortOrder = ContactsContract.Contacts._ID + " DESC";

		Cursor pCur = cr.query(Phone.CONTENT_URI, null, Phone.CONTACT_ID + " = ?", new String[]
		{
			id
		}, sortOrder);

		return pCur;
	}

	@SuppressWarnings("unused")
	protected Cursor getContact() {

		String[] projection = new String[]
		{
				ContactsContract.Contacts._ID,
				ContactsContract.Contacts.DISPLAY_NAME,
				ContactsContract.Data.PHOTO_ID
		};
		String selection = ContactsContract.Contacts._ID + " = '1532'";
		String[] selectionArgs = null;
		String sortOrder = ContactsContract.Contacts._ID + " ASC";

		return managedQuery(contactUri, null, selection, selectionArgs, sortOrder);
	}
}
