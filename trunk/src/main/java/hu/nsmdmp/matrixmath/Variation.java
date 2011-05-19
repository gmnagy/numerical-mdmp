package hu.nsmdmp.matrixmath;

import org.apfloat.Apfloat;

final class Variation {

	static Apfloat[][] createVariation(Apfloat[][] vectorSet) {
		int s = vectorSet.length;
		int n = MatrixMath.getVariationsNumber(vectorSet);

		Apfloat[][] variations = new Apfloat[n][s];

		for (int j = 0; j < n; j++) {
			variations[j] = getVariation(j, vectorSet);
		}

		return variations;
	}

	static Apfloat[] getVariation(final int j, final Apfloat[][] vectorSet) {
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
