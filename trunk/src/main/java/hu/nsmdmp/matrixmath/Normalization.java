package hu.nsmdmp.matrixmath;

import org.apfloat.Apfloat;

final class Normalization {

	static void normalize(final Apfloat[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {

			Apfloat min = matrix[i][0];
			Apfloat max = matrix[i][0];

			for (int j = 1; j < matrix[i].length; j++) {
				Apfloat x = matrix[i][j];

				if (x.compareTo(min) < 0) {
					min = x;
				}

				if (x.compareTo(max) > 0) {
					max = x;
				}
			}

			// (min + max) / 2;
			Apfloat mid = min.add(max).divide(MatrixMath.TWO);

			for (int j = 0; j < matrix[i].length; j++) {

				// 2 * (vSet[i][j] - mid)
				Apfloat a = matrix[i][j].subtract(mid).multiply(MatrixMath.TWO);

				// max - min
				Apfloat b = max.subtract(min);

				// a / b
				matrix[i][j] = a.divide(b);
			}
		}
	}
}
