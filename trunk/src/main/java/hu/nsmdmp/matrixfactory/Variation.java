package hu.nsmdmp.matrixfactory;

import org.apfloat.Apfloat;

public final class Variation {

	public static int getVariationsNumber(final Apfloat[][] vectorSet) {
		int n = 1;
		for (Apfloat[] row : vectorSet) {
			n *= row.length;
		}

		return n;
	}

	public static Apfloat[][] createVariation(final Apfloat[][] vectorSet) {
		int s = vectorSet.length;
		int n = getVariationsNumber(vectorSet);

		Apfloat[][] variations = new Apfloat[n][s];

		for (int j = 0; j < n; j++) {
			variations[j] = getVariation(j, vectorSet);
		}

		return variations;
	}

	public static Apfloat[] getVariation(final int j, final Apfloat[][] vectorSet) {
		int s = vectorSet.length;
		Apfloat[] variation = new Apfloat[s];

		int a = 1;
		for (int i = 0; i < s; i++) {
			int x = (j / a) % vectorSet[i].length;
			variation[i] = vectorSet[i][x];

			a *= vectorSet[i].length;
		}

		return variation;
	}
}
