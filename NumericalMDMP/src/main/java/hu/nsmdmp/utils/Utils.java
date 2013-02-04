package hu.nsmdmp.utils;

import java.util.Arrays;

public class Utils {

	public static <T> String toString(final T[][] matrix) {

		StringBuilder sb = new StringBuilder();

		for (T[] array : matrix)
			sb.append(Arrays.toString(array) + "\n");

		return sb.toString();
	}
}
