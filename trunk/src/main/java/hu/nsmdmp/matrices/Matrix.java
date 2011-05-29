package hu.nsmdmp.matrices;

import hu.nsmdmp.utils.Precision;

import org.apfloat.Apfloat;

public class Matrix extends AbstractMatrix {

	public Matrix(int m, int n) {
		this.m = m;
		this.n = n;
		matrix = new Apfloat[m][n];
	}

	public Matrix(Apfloat[][] matrix) {
		this.m = matrix.length;
		this.matrix = new Apfloat[m][];

		int nn = 0;
		for (int i = 0; i < m; i++) {
			nn = matrix[i].length;
			this.matrix[i] = new Apfloat[nn];

			for (int j = 0; j < nn; j++) {
				this.matrix[i][j] = matrix[i][j];
			}
		}

		this.n = nn;
	}

	public Matrix(double[][] matrix) {
		this.m = matrix.length;
		this.matrix = new Apfloat[m][];

		int nn = 0;
		for (int i = 0; i < m; i++) {
			nn = matrix[i].length;
			this.matrix[i] = new Apfloat[nn];

			for (int j = 0; j < nn; j++) {
				this.matrix[i][j] = new Apfloat(matrix[i][j], Precision.SCALE);
			}
		}

		this.n = nn;
	}
}
