package com.sks.demo.custom_list;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.sks.demo.custom_list.list.RealStateAdapter;
import com.sks.demo.custom_list.list.RealStateListView;

public class CustomListDemoActivity extends Activity {

	RealStateListView lsComposer;
	SectionComposerAdapter adapter;

	Context context;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		context = getParent();
	}

	@Override
	protected void onStart() {

		((TextView) findViewById(R.id.title)).setText("Services");

		super.onStart();
		initializedView();
		setupFunctionality();
		setupListeners();
	}

	private void initializedView() {

		lsComposer = (RealStateListView) findViewById(R.id.list_services);
	}

	private void setupFunctionality() {
		lsComposer.setPinnedHeaderView(LayoutInflater.from(this).inflate(R.layout.item_composer_header, lsComposer, false));
		lsComposer.setAdapter(adapter = new SectionComposerAdapter());

		lsComposer.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				// Intent intent = new Intent(CustomListDemoActivity.this,
				// ServiceDestailsScreen.class);
				String name, address, title;

				title = ((TextView) view.findViewById(R.id.list_section_header)).getText().toString();
				name = ((TextView) view.findViewById(R.id.list_section_title1)).getText().toString();
				address = ((TextView) view.findViewById(R.id.list_section_title2)).getText().toString();

				// intent.putExtra("serviceName", title);
				// intent.putExtra("name", name);
				// intent.putExtra("address", address);

				Log.v(Util.TAG, "Name : " + name);
				Log.v(Util.TAG, "Address : " + address);
				Log.v(Util.TAG, "Title: " + title);

				// getParent().startActivity(intent);

			}
		});
	}

	private void setupListeners() {
		// titleBack.setOnClickListener(this);
	}

	class SectionComposerAdapter extends RealStateAdapter {
		List<Pair<String, List<Composer>>> all = Data.getAllData();

		@Override
		public int getCount() {
			int res = 0;
			for (int i = 0; i < all.size(); i++) {
				res += all.get(i).second.size();
			}
			return res;
		}

		@Override
		public Composer getItem(int position) {
			int c = 0;
			for (int i = 0; i < all.size(); i++) {
				if (position >= c && position < c + all.get(i).second.size()) {
					return all.get(i).second.get(position - c);
				}
				c += all.get(i).second.size();
			}
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		protected void onNextPageRequested(int page) {
		}

		@Override
		protected void bindSectionHeader(View view, int position, boolean displaySectionHeader) {
			if (displaySectionHeader) {
				view.findViewById(R.id.list_section_header).setVisibility(View.VISIBLE);
				TextView lSectionTitle = (TextView) view.findViewById(R.id.list_section_header);
				lSectionTitle.setText(getSections()[getSectionForPosition(position)]);
			} else {
				TextView lSectionTitle = (TextView) view.findViewById(R.id.list_section_header);
				lSectionTitle.setText(getSections()[getSectionForPosition(position)]);
				view.findViewById(R.id.list_section_header).setVisibility(View.GONE);
			}
		}

		@Override
		public View getAmazingView(int position, View convertView, ViewGroup parent) {
			View res = convertView;
			if (res == null)
				res = getLayoutInflater().inflate(R.layout.item_composer, null);

			TextView lName = (TextView) res.findViewById(R.id.list_section_title1);
			TextView lYear = (TextView) res.findViewById(R.id.list_section_title2);

			Composer composer = getItem(position);
			lName.setText(composer.name);
			lYear.setText(composer.year);

			return res;
		}

		@Override
		public void configurePinnedHeader(View header, int position, int alpha) {
			TextView lSectionHeader = (TextView) header;
			lSectionHeader.setText(getSections()[getSectionForPosition(position)]);
			// lSectionHeader.setBackgroundColor(alpha << 24 | (0xbbffbb));
			// lSectionHeader.setTextColor(alpha << 24 | (0x000000));
		}

		@Override
		public int getPositionForSection(int section) {
			if (section < 0)
				section = 0;
			if (section >= all.size())
				section = all.size() - 1;
			int c = 0;
			for (int i = 0; i < all.size(); i++) {
				if (section == i) {
					return c;
				}
				c += all.get(i).second.size();
			}
			return 0;
		}

		@Override
		public int getSectionForPosition(int position) {
			int c = 0;
			for (int i = 0; i < all.size(); i++) {
				if (position >= c && position < c + all.get(i).second.size()) {
					return i;
				}
				c += all.get(i).second.size();
			}
			return -1;
		}

		@Override
		public String[] getSections() {
			String[] res = new String[all.size()];
			for (int i = 0; i < all.size(); i++) {
				res[i] = all.get(i).first;
			}
			return res;
		}

	}

}