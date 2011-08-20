package hu.nsmdmp.matrix.operation;

import hu.nsmdmp.ApfloatUtils;
import hu.nsmdmp.matrix.Matrix;

import org.apfloat.Apfloat;

final class Normalization {

	static Matrix normalize(final Matrix matrix) {
		int m = matrix.getRowDimension();
		int n = matrix.getColumnDimension();
		Apfloat[][] M = matrix.getArray();

		Matrix NM = new Matrix(m, n);
		Apfloat[][] normM = NM.getArray();

		for (int i = 0; i < m; i++) {
			Apfloat min = M[i][0];
			Apfloat max = M[i][0];

			for (int j = 1; j < n; j++) {
				Apfloat x = M[i][j];

				if (x.compareTo(min) < 0) {
					min = x;
				}

				if (x.compareTo(max) > 0) {
					max = x;
				}
			}

			// (min + max) / 2;
			Apfloat mid = min.add(max).divide(ApfloatUtils.TWO);

			for (int j = 0; j < n; j++) {

				// 2 * (vSet[i][j] - mid)
				Apfloat a = M[i][j].subtract(mid).multiply(ApfloatUtils.TWO);

				// max - min
				Apfloat b = max.subtract(min);

				// a / b
				normM[i][j] = a.divide(b);

			}
		}

		return NM;
	}
}
