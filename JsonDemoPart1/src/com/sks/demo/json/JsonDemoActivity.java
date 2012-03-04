package com.sks.demo.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class JsonDemoActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// String myJsonContent =
		// "{\"menu\": {\"id\": \"file\",\"value\": \"File\",\"popup\": {\"menuitem\": [{\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"},{\"value\": \"Open\",\"onclick\": \"OpenDoc()\"},{\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}]}}}";

		String myJsonContent = "{\"Menu\": {\"menu\":\"File\",\"value\":\"File\",\"popup\":{\"MenuItem\":[{\"subMenu\":\"New\",\"details\":\"Create New File\"},{\"subMenu\":\"Open\",\"details\":\"Open Existing File\"},{\"subMenu\":\"Save\",\"details\":\"Save File\"}]}}}";

		sampleJsonParser(myJsonContent);
	}

	public void sampleJsonParser(String jsonString)
	{
		try
		{
			JSONObject jObject = new JSONObject(jsonString);
			JSONObject menuObject = jObject.getJSONObject("Menu");
			Log.d("JSON", "Menu = " + menuObject.getString("menu"));
			Log.d("JSON", "Value = " + menuObject.getString("value"));
			JSONObject popupObject = menuObject.getJSONObject("popup");
			JSONArray menuitemArray = popupObject.getJSONArray("MenuItem");
			for (int i = 0; i < 3; i++)
			{
				Log.d("JSON", "Name = " + menuitemArray.getJSONObject(i).getString("subMenu").toString());
				Log.d("JSON", "Value = " + menuitemArray.getJSONObject(i).getString("details").toString());
			}
		} catch (JSONException e)
		{
			Log.d("JSON", "Error = " + e.toString());
		}
	}
}