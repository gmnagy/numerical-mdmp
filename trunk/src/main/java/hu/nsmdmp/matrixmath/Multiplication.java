package hu.nsmdmp.matrixmath;

import org.apfloat.Apfloat;

public final class Multiplication {

	static final Apfloat[] multiply(Apfloat[][] matrix, Apfloat[] vector) {

		Apfloat[] result = new Apfloat[matrix.length];

		for (int i = 0; i < matrix.length; i++) {

			if (matrix[i].length != vector.length) {
				throw new MatrixMathException(String.format("Nem lehet osszeszorozni mert a meret nem megfelelo %d != %d !!!", matrix[i].length, vector.length));
			}

			Apfloat x = MatrixMath.ZERO;

			for (int j = 0; j < matrix[i].length; j++) {
				Apfloat a = matrix[i][j].multiply(vector[j]);
				x = x.add(a);
			}

			result[i] = x;
		}

		return result;
	}
}
