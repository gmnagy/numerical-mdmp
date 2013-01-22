package hu.nsmdmp.utils;

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
}
