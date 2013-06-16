package com.link.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParseUtils {

	private static final Object obj = new Object();

	public static int paseInt(String s, int defaultV) {
		int r = defaultV;
		try {
			r = Integer.parseInt(s);
		} catch (Exception e) {
		}
		return r;
	}

	public static long paseLong(String s, long defaultV) {
		long r = defaultV;
		try {
			r = Long.parseLong(s);
		} catch (Exception e) {
		}
		return r;
	}

	public static float parseFloat(String s, float defaultV) {
		float r = defaultV;
		try {
			r = Float.parseFloat(s);
		} catch (Exception e) {
		}
		return r;
	}

	public static int[] parseIntArray(String[] s, int defaultValue) {
		if (s == null) {
			return null;
		}
		int[] x_ret = new int[s.length];
		for (int i = 0; i < s.length; i++) {
			x_ret[i] = paseInt(s[i], defaultValue);
		}
		return x_ret;
	}

	public static Map<Integer, Object> parseIntKeyMap(String s, String split) {
		if (s == null || s.length() == 0) {
			return null;
		}
		if (split == null || split.length() == 0) {
			split = ",";
		}
		String[] arrayStr = s.split(split);
		int[] arrayInt = parseIntArray(arrayStr, 0);
		if (arrayInt == null) {
			return null;
		}

		Map<Integer, Object> map = new HashMap<Integer, Object>();
		for (int i : arrayInt) {
			map.put(i, obj);
		}

		return map;
	}

	public static List<Integer> parseIntList(String s, String split) {
		if (s == null || s.length() == 0) {
			return null;
		}
		if (split == null || split.length() == 0) {
			split = ",";
		}
		String[] arrayStr = s.split(split);
		int[] arrayInt = parseIntArray(arrayStr, 0);
		if (arrayInt == null) {
			return null;
		}

		List<Integer> x_ret = new ArrayList<Integer>();
		for (int i : arrayInt) {
			x_ret.add(i);
		}

		return x_ret;
	}
}
