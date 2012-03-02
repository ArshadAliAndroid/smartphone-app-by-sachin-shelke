package com.sks.demo.custom_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.os.SystemClock;
import android.util.Pair;

public class Data {
	public static List<Pair<String, List<Composer>>> getAllData() {
		List<Pair<String, List<Composer>>> res = new ArrayList<Pair<String, List<Composer>>>();

		for (int i = 0; i < 4; i++) {
			res.add(getOneSection(i));
		}

		return res;
	}

	public static List<Composer> getFlattenedData() {
		List<Composer> res = new ArrayList<Composer>();

		for (int i = 0; i < 4; i++) {
			res.addAll(getOneSection(i).second);
		}

		return res;
	}

	public static Pair<Boolean, List<Composer>> getRows(int page) {
		List<Composer> flattenedData = getFlattenedData();
		if (page == 1) {
			return new Pair<Boolean, List<Composer>>(true, flattenedData.subList(0, 5));
		} else {
			SystemClock.sleep(2000); // simulate loading
			return new Pair<Boolean, List<Composer>>(page * 5 < flattenedData.size(), flattenedData.subList((page - 1) * 5,
					Math.min(page * 5, flattenedData.size())));
		}
	}

	public static Pair<String, List<Composer>> getOneSection(int index) {
		String[] titles =
		{
				"Carpenter",
				"Electrician",
				"Plumber",
				"Painter"
		};
		Composer[][] composerss =
		{
				{
						new Composer("Carpenter - 1", "Swarget"),
						new Composer("Carpenter - 2", "Nigdi"),
						new Composer("Carpenter - 3", "Shivaji Nagar"),
						new Composer("Carpenter - 4", "Nigdi"),
						new Composer("Carpenter - 5", "Kothrud"),
						new Composer("Carpenter - 6", "SB Road"),
				},
				{
						new Composer("Electrician - 1", "Swarget"),
						new Composer("Electrician - 2", "Nigdi"),
						new Composer("Electrician - 3", "Shivaji Nagar"),
						new Composer("Electrician - 4", "Nigdi"),
						new Composer("Electrician - 5", "Kothrud"),
						new Composer("Electrician - 6", "SB Road"),
				},
				{
						new Composer("Plumber - 1", "Swarget"),
						new Composer("Plumber - 2", "Nigdi"),
						new Composer("Plumber - 3", "Shivaji Nagar"),
						new Composer("Plumber - 4", "Nigdi"),
						new Composer("Plumber - 5", "Kothrud"),
						new Composer("Plumber - 6", "SB Road"),
				},
				{
						new Composer("Painter - 1", "Swarget"),
						new Composer("Painter - 2", "Nigdi"),
						new Composer("Painter - 3", "Shivaji Nagar"),
						new Composer("Painter - 4", "Nigdi"),
						new Composer("Painter - 5", "Kothrud"),
						new Composer("Painter - 6", "SB Road"),
				},
		};
		return new Pair<String, List<Composer>>(titles[index], Arrays.asList(composerss[index]));
	}
}
