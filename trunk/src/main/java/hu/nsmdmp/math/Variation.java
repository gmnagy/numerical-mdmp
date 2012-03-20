package hu.nsmdmp.math;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public final class Variation {

	@SuppressWarnings("unchecked")
	private static <T> T[] getIthVariation(final int i, final List<T[]> vectorSet) {
		int s = vectorSet.size();
		Class<?> t = vectorSet.get(0).getClass();
		T[] variation = (T[]) Array.newInstance(t.getComponentType(), s);

		int a = 1;
		int j = 0;
		for (T[] vector : vectorSet) {
			int l = vector.length;
			int x = (i / a) % l;

			variation[j] = vector[x];
			a *= l;
			j++;
		}

		return variation;
	}

	@SuppressWarnings("unchecked")
	private static <T> T[] getIthVariation(final int i, final T[][] vectorSet) {
		int s = vectorSet.length;
		Class<?> t = vectorSet[0].getClass();
		T[] variation = (T[]) Array.newInstance(t.getComponentType(), s);

		int a = 1;
		int j = 0;
		for (T[] vector : vectorSet) {
			int l = vector.length;
			int x = (i / a) % l;

			variation[j] = vector[x];
			a *= l;
			j++;
		}

		return variation;
	}

	public static <T> int getPieceVariation(final List<T[]> vectorSet) {
		int piece = 1;
		for (T[] t : vectorSet) {
			piece *= t.length;
		}

		return piece;
	}

	public static <T> int getPieceVariation(final T[][] vectorSet) {
		int piece = 1;
		for (T[] t : vectorSet) {
			piece *= t.length;
		}

		return piece;
	}

	public static <T> List<T[]> createVariation(final List<T[]> vectorSet) {
		int piece = getPieceVariation(vectorSet);

		List<T[]> variations = new ArrayList<T[]>();

		for (int i = 0; i < piece; i++) {
			variations.add(getIthVariation(i, vectorSet));
		}

		return variations;
	}

	public static <T> List<T[]> createVariation(final T[][] vectorSet) {
		int piece = getPieceVariation(vectorSet);

		List<T[]> variations = new ArrayList<T[]>();

		for (int i = 0; i < piece; i++) {
			variations.add(getIthVariation(i, vectorSet));
		}

		return variations;
	}
}
