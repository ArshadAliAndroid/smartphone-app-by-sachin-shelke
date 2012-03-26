package com.sks.demo.custom.listitem;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomListItemDemoActivity extends Activity {

	ListView listView;
	ArrayList<String[]> educationalDetails;
	SKSAdapter adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		listView = (ListView) findViewById(R.id.mylistView);

		educationalDetails = setDetails();

		adapter = new SKSAdapter(this, R.layout.custom_list_item_layout, educationalDetails);
		listView.setAdapter(adapter);

	}

	// Sample Data

	private ArrayList<String[]> setDetails() {
		ArrayList<String[]> dtl = new ArrayList<String[]>();
		String[] edu1 =
		{
				"MCA",
				"University of Pune",
				"2010",
				"70.00"
		};
		String[] edu2 =
		{
				"BCA",
				"University of Pune",
				"2007",
				"66.66"
		};
		String[] edu3 =
		{
				"HSC",
				"Maharashtra",
				"2004",
				"77.77"
		};
		String[] edu4 =
		{
				"SSC",
				"University of Pune",
				"2010",
				"70.00"
		};
		String[] edu5 =
		{
				"Diploma",
				"SMU",
				"2010",
				"99.99"
		};
		String[] edu6 =
		{
				"XYZ",
				"USA",
				"2012",
				"99.99"
		};
		dtl.add(edu1);
		dtl.add(edu2);
		dtl.add(edu3);
		dtl.add(edu4);
		dtl.add(edu5);
		dtl.add(edu6);

		return dtl;
	}

	private class SKSAdapter extends ArrayAdapter<String[]> {

		private ArrayList<String[]> items;

		public SKSAdapter(Context context, int textViewResourceId, ArrayList<String[]> items) {
			super(context, textViewResourceId, items);
			this.items = items;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View listItemView = convertView;
			if (listItemView == null) {
				LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				listItemView = layoutInflater.inflate(R.layout.custom_list_item_layout, null);
			}
			String[] details = items.get(position);
			if (details != null) {
				final TextView course = (TextView) listItemView.findViewById(R.id.course);
				TextView university = (TextView) listItemView.findViewById(R.id.university);
				TextView year = (TextView) listItemView.findViewById(R.id.year);
				Button per = (Button) listItemView.findViewById(R.id.per);

				if (course != null) {
					course.setText(details[0]);
				}
				if (university != null) {
					university.setText(details[1]);
				}
				if (year != null) {
					year.setText(details[2]);
				}
				if (per != null) {
					per.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {

							Toast.makeText(getApplicationContext(), "Course : " + course.getText().toString(), Toast.LENGTH_SHORT).show();

						}
					});
				}
			}
			return listItemView;
		}
	}
}