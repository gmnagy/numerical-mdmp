package hu.nsmdmp.matrixforms;

import hu.nsmdmp.utils.Exponents;
import hu.nsmdmp.utils.MatrixOperations;

import java.util.List;

import org.apfloat.Apfloat;

public abstract class AbstractMatrix {

	Apfloat[][] matrix;

	AbstractMatrix() {
	}

	public Apfloat[][] getMatrix() {
		return matrix;
	}

	protected abstract Apfloat getPolynomialValue(final int n, final Apfloat value);

	void create(final Apfloat[][] vectorSet, final int maxOrder) {
		int s = vectorSet.length;

		List<int[]> exponents = Exponents.getExponents(maxOrder, s);
		int n = getNumber(vectorSet);

		matrix = new Apfloat[exponents.size()][n];

		for (int j = 0; j < n; j++) {

			Apfloat[] z = new Apfloat[s];

			int a = 1;
			for (int i = 0; i < s; i++) {
				int x = (j / a) % vectorSet[i].length;
				z[i] = vectorSet[i][x];

				a *= vectorSet[i].length;
			}

			for (int i = 0; i < exponents.size(); i++) {
				Apfloat b = MatrixOperations.ONE;

				for (int k = 0; k < s; k++) {
					int exp = exponents.get(i)[k];

					b = b.multiply(getPolynomialValue(exp, z[k]));

				}

				matrix[i][j] = b;
			}
		}
	}

	private int getNumber(final Apfloat[][] vectorSet) {
		int n = 1;
		for (Apfloat[] row : vectorSet) {
			n *= row.length;
		}

		return n;
	}

	@Override
	public String toString() {
		return MatrixOperations.print(matrix);
	}

	@Override
	public boolean equals(final Object obj) {

		if (!obj.getClass().isArray()) {
			return false;
		}

		Apfloat[][] m = (Apfloat[][]) obj;

		if (m.length != matrix.length) {
			return false;
		}

		for (int i = 0; i < m.length; i++) {
			if (m[i].length != matrix[i].length) {
				return false;
			}

			for (int j = 0; j < m[i].length; j++) {
				if (m[i][j].compareTo(matrix[i][j]) != 0) {
					return false;
				}
			}
		}

		return true;
	}
}
