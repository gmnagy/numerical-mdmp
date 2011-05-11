package hu.nsmdmp.utils;

import org.apfloat.Apfloat;

public class Converters {

	public static Apfloat[][] convert(final double[][] value) {
		Apfloat[][] a = new Apfloat[value.length][];

		for (int i = 0; i < value.length; i++) {
			a[i] = new Apfloat[value[i].length];

			for (int j = 0; j < value[i].length; j++) {
				a[i][j] = new Apfloat(value[i][j], Precision.SCALE);
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
			a[i] = new Apfloat(value[i], Precision.SCALE);
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
}
