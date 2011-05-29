package hu.nsmdmp.matrixmath;

import hu.nsmdmp.matrices.IMatrix;

import org.apfloat.Apfloat;

final class Multiplication {

	static Apfloat[] multiply(IMatrix matrix, Apfloat[] vector) {
		int m = matrix.getRowDimension();
		int n = matrix.getColumnDimension();
		Apfloat[][] M = matrix.getMatrix();

		Apfloat[] result = new Apfloat[m];

		for (int i = 0; i < m; i++) {

			if (n != vector.length) {
				throw new MatrixMathException(String.format("Nem lehet osszeszorozni mert a meret nem megfelelo %d != %d !!!", n, vector.length));
			}

			Apfloat x = MatrixMath.ZERO;

			for (int j = 0; j < n; j++) {
				Apfloat a = M[i][j].multiply(vector[j]);
				x = x.add(a);
			}

			result[i] = x;
		}

		return result;
	}
}
