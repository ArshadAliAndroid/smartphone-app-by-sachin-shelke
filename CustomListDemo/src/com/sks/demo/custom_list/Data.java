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
			return new Pair<Boolean, List<Composer>>(page * 5 < flattenedData.size(), flattenedData.subList((page) * 5,
					Math.min(page * 5, flattenedData.size())));
		}
	}

	public static Pair<String, List<Composer>> getOneSection(int index) {
		String[] titles =
		{
				"Maharashtra",
				"Gujarat",
				"Jammu",
				"Punjab"
		};
		Composer[][] composerss =
		{
				{
						new Composer("Amravati", "2,607,160"),
						new Composer("Aurangabad", "2,897,103"),
						new Composer("Khandala", "21,043"),
						new Composer("Mumbai", "11,914,398"),
						new Composer("Nagpur", "2,420,000"),
						new Composer("Pune", "4,485,000"),
				},
				{
						new Composer("Ahmedabad", "3,913,793"),
						new Composer("Surat", "3,344,135"),
						new Composer("Vadodara", "1,513,758"),
						new Composer("Rajkot", "1,395,026"),
						new Composer("Gandhinagar", "271,331"),
						new Composer("Bhavnagar", "600,594"),
				},
				{
						new Composer("Kathua", "550,084"),
						new Composer("Jammu", "1,343,756"),
						new Composer("Samba", "245,016"),
						new Composer("Udhampur", "475,068"),
						new Composer("Reasi", "268,441"),
						new Composer("Rajouri", "483,284"),
				},
				{
						new Composer("Amritsar", "1,183,705"),
						new Composer("Firozpur", "110,091"),
						new Composer("Ludhiana", "1,613,878"),
						new Composer("Chandigarh", "27,704,236"),
						new Composer("Jalandhar ", "873,725"),
						new Composer("Patiala", "445,196"),
				},
		};
		return new Pair<String, List<Composer>>(titles[index], Arrays.asList(composerss[index]));
	}
}
