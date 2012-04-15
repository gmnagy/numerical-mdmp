package hu.nsmdmp.utils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apfloat.Apfloat;

public class Converters {

	public static Apfloat[][] convert(final double[][] value) {
		Apfloat[][] a = new Apfloat[value.length][];

		for (int i = 0; i < value.length; i++) {
			a[i] = new Apfloat[value[i].length];

			for (int j = 0; j < value[i].length; j++) {
				a[i][j] = ApfloatUtils.valueOf(value[i][j]);
			}
		}

		return a;
	}

	public static double[][] convert(final Apfloat[][] value) {
		double[][] a = new double[value.length][];

		for (int i = 0; i < value.length; i++) {
			a[i] = new double[value[i].length];

			for (int j = 0; j < value[i].length; j++) {
				a[i][j] = value[i][j].doubleValue();
			}
		}

		return a;
	}

	public static Apfloat[] convert(final double[] value) {
		Apfloat[] a = new Apfloat[value.length];

		for (int i = 0; i < value.length; i++) {
			a[i] = ApfloatUtils.valueOf(value[i]);
		}

		return a;
	}

	public static double[] convert(final Apfloat[] value) {
		double[] a = new double[value.length];

		for (int i = 0; i < value.length; i++) {
			a[i] = value[i].doubleValue();
		}

		return a;
	}

	public static Apfloat[][] convert(final List<Apfloat[]> value) {
		Apfloat[][] a = new Apfloat[value.size()][];

		int i = 0;
		for (Apfloat[] v : value) {
			a[i] = Arrays.copyOf(v, v.length);
			i++;
		}

		return a;
	}

	public static <T> List<T[]> convert(final T[][] value) {
		List<T[]> list = new ArrayList<T[]>();
		for (T[] v : value) {
			list.add(v);
		}

		return list;
	}

	/**
	 * Convert an array of strings to one string. Put the 'separator' string between each element.
	 * 
	 */
	public static String arrayToString(int[] a, String separator) {
		StringBuffer result = new StringBuffer();
		if (a.length > 0) {
			result.append(a[0]);
			for (int i = 1; i < a.length; i++) {
				result.append(separator);
				result.append(a[i]);
			}
		}
		return result.toString();
	}

	public static <T> String arrayToString(T[] a, String separator) {
		StringBuffer result = new StringBuffer();
		if (a.length > 0) {
			result.append(a[0]);
			for (int i = 1; i < a.length; i++) {
				result.append(separator);
				result.append(a[i]);
			}
		}
		return result.toString();
	}
}
