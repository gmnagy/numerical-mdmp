package hu.nsmdmp.utils;

import static hu.nsmdmp.numerics.matrix.operations.ApfloatOperations.PRECISION;

import java.lang.reflect.Array;
import java.util.List;

import org.apfloat.Apfloat;

public final class Converters {

	public static Double[][] primitiveToDouble(final double[][] set) {

		Double[][] result = new Double[set.length][];

		for (int i = 0; i < set.length; i++) {

			result[i] = new Double[set[i].length];

			for (int j = 0; j < set[i].length; j++)
				result[i][j] = set[i][j];

		}

		return result;
	}

	public static double[][] doubleToPrimitive(final Double[][] set) {

		double[][] result = new double[set.length][];

		for (int i = 0; i < set.length; i++) {

			result[i] = new double[set[i].length];

			for (int j = 0; j < set[i].length; j++)
				result[i][j] = set[i][j];

		}

		return result;
	}

	public static Apfloat[][] toApfloat(final double[][] set) {

		Apfloat[][] result = new Apfloat[set.length][];

		for (int i = 0; i < set.length; i++) {

			result[i] = new Apfloat[set[i].length];

			for (int j = 0; j < set[i].length; j++)
				result[i][j] = new Apfloat(set[i][j], PRECISION);

		}

		return result;
	}

	public static Apfloat[] toApfloat(final double[] set) {

		Apfloat[] result = new Apfloat[set.length];

		for (int i = 0; i < set.length; i++) {
			result[i] = new Apfloat(set[i], PRECISION);
		}

		return result;
	}

	public static String arrayToString(final int[] array) {
		StringBuilder sb = new StringBuilder();

		for (int a : array) {
			sb.append(a);
		}

		return sb.toString();
	}

	public static <T> String arrayToString(final T[] array) {
		StringBuilder sb = new StringBuilder();

		for (T a : array) {
			sb.append(a);
		}

		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public static <T> T[][] toMultiArray(final List<T[]> list) {
		T[][] A = (T[][]) Array.newInstance(list.get(0)[0].getClass(), list.size());

		int i = 0;
		for (T[] array : list) {
			A[i] = array;

			i++;
		}

		return A;
	}
}
