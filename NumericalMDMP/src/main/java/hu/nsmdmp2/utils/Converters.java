package hu.nsmdmp2.utils;

import static hu.nsmdmp2.numerics.ApfloatOperation.PRECISION;
import hu.nsmdmp2.numerics.Value;

import org.apfloat.Apfloat;

public final class Converters {

	public static Value[][] primitiveToDoubleValue(final double[][] set) {

		Value[][] result = new Value[set.length][];

		for (int i = 0; i < set.length; i++) {

			result[i] = new Value[set[i].length];

			for (int j = 0; j < set[i].length; j++)
				result[i][j] = new Value(set[i][j]);

		}

		return result;
	}

	public static Value[] primitiveToDoubleValue(final double[] set) {

		Value[] result = new Value[set.length];

		for (int i = 0; i < set.length; i++) {
			result[i] = new Value(set[i]);
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

	// public static <T> String arrayToString(final T[] array) {
	// StringBuilder sb = new StringBuilder();
	//
	// for (T a : array) {
	// sb.append(a);
	// }
	//
	// return sb.toString();
	// }

	// @SuppressWarnings("unchecked")
	// public static <T> T[][] toMultiArray(final List<T[]> list) {
	// T[][] A = (T[][]) Array.newInstance(list.get(0)[0].getClass(),
	// list.size());
	//
	// int i = 0;
	// for (T[] array : list) {
	// A[i] = array;
	//
	// i++;
	// }
	//
	// return A;
	// }

	public static double[][] toPrimitiveDoubleArray(Value[][] M) {
		int row = M.length;
		int col = M[0].length;

		double[][] array = new double[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {

				if (null == M[i][j])
					array[i][j] = 0;
				else {
					array[i][j] = M[i][j].toDouble();
				}

				j++;
			}
		}

		return array;
	}

	public static double[] toPrimitiveDoubleArray(Value[] V) {
		int col = V.length;

		double[] array = new double[col];

		for (int i = 0; i < col; i++) {

			if (null == V[i])
				array[i] = 0;
			else {
				array[i] = V[i].toDouble();
			}
		}

		return array;
	}
}
