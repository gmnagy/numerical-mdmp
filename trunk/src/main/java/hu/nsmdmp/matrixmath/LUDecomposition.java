package hu.nsmdmp.matrixmath;

import hu.nsmdmp.matrices.IMatrix;
import hu.nsmdmp.matrices.Matrix;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

final class LUDecomposition {

	/**
	 * Array for internal storage of decomposition.
	 * 
	 * @serial internal array storage.
	 */
	Apfloat[][] LU;

	/**
	 * Row and column dimensions, and pivot sign.
	 * 
	 * @serial column dimension.
	 * @serial row dimension.
	 * @serial pivot sign.
	 */
	private int m, n, pivsign;

	/**
	 * Internal storage of pivot vector.
	 * 
	 * @serial pivot vector.
	 */
	private int[] piv;

	/**
	 * LU Decomposition
	 * 
	 * @param A
	 *            Rectangular matrix
	 * @return Structure to access L, U and piv.
	 */
	LUDecomposition(final IMatrix A) {

		// Use a "left-looking", dot-product, Crout/Doolittle algorithm.

		LU = A.getArrayCopy();
		m = A.getRowDimension();
		n = A.getColumnDimension();

		piv = new int[m];
		for (int i = 0; i < m; i++) {
			piv[i] = i;
		}
		pivsign = 1;
		Apfloat[] LUrowi;
		Apfloat[] LUcolj = new Apfloat[m];

		// Outer loop.
		for (int j = 0; j < n; j++) {

			// Make a copy of the j-th column to localize references.
			for (int i = 0; i < m; i++) {
				LUcolj[i] = LU[i][j];
			}

			// Apply previous transformations.
			for (int i = 0; i < m; i++) {
				LUrowi = LU[i];

				// Most of the time is spent in the following dot product.
				int kmax = Math.min(i, j);
				Apfloat s = MatrixMath.ZERO;
				for (int k = 0; k < kmax; k++) {
					s = s.add(LUrowi[k].multiply(LUcolj[k]));
				}

				LUrowi[j] = LUcolj[i] = LUcolj[i].subtract(s);
			}

			// Find pivot and exchange if necessary.
			int p = j;
			for (int i = j + 1; i < m; i++) {
				if (ApfloatMath.abs(LUcolj[i]).compareTo(ApfloatMath.abs(LUcolj[p])) > 0) {
					p = i;
				}
			}
			if (p != j) {
				for (int k = 0; k < n; k++) {
					Apfloat t = LU[p][k];
					LU[p][k] = LU[j][k];
					LU[j][k] = t;
				}
				int k = piv[p];
				piv[p] = piv[j];
				piv[j] = k;
				pivsign = -pivsign;
			}

			// Compute multipliers.

			// if (j < m & LU[j][j] != 0.0) {
			if (j < m & LU[j][j].signum() != 0) {
				for (int i = j + 1; i < m; i++) {
					LU[i][j] = LU[i][j].divide(LU[j][j]);
				}
			}
		}
	}

	/**
	 * Is the matrix nonsingular?
	 * 
	 * @return true if U, and hence A, is nonsingular.
	 */
	boolean isNonsingular() {
		for (int j = 0; j < n; j++) {
			if (LU[j][j].signum() == 0)
				return false;
		}
		return true;
	}

	/**
	 * Return lower triangular factor
	 * 
	 * @return L
	 */
	IMatrix getL() {
		IMatrix X = new Matrix(m, n);

		Apfloat[][] L = X.getMatrix();

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i > j) {
					L[i][j] = LU[i][j];
				} else if (i == j) {
					L[i][j] = MatrixMath.ONE;
				} else {
					L[i][j] = MatrixMath.ZERO;
				}
			}
		}

		return X;
	}

	/**
	 * Return upper triangular factor
	 * 
	 * @return U
	 */
	IMatrix getU() {
		IMatrix X = new Matrix(n, n);

		Apfloat[][] U = X.getMatrix();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i <= j) {
					U[i][j] = LU[i][j];
				} else {
					U[i][j] = MatrixMath.ZERO;
				}
			}
		}

		return X;
	}

//	/**
//	 * Return pivot permutation vector
//	 * 
//	 * @return piv
//	 */
//
//	public int[] getPivot() {
//		int[] p = new int[m];
//		for (int i = 0; i < m; i++) {
//			p[i] = piv[i];
//		}
//		return p;
//	}

//	/**
//	 * Return pivot permutation vector as a one-dimensional double array
//	 * 
//	 * @return (double) piv
//	 */
//
//	public double[] getDoublePivot() {
//		double[] vals = new double[m];
//		for (int i = 0; i < m; i++) {
//			vals[i] = (double) piv[i];
//		}
//		return vals;
//	}

//	/**
//	 * Determinant
//	 * 
//	 * @return det(A)
//	 * @exception IllegalArgumentException
//	 *                Matrix must be square
//	 */
//
//	public double det() {
//		if (m != n) {
//			throw new IllegalArgumentException("Matrix must be square.");
//		}
//		double d = (double) pivsign;
//		for (int j = 0; j < n; j++) {
//			d *= LU[j][j];
//		}
//		return d;
//	}

	/**
	 * Solve A*X = B
	 * 
	 * @param B
	 *            A Matrix with as many rows as A and any number of columns.
	 * @return X so that L*U*X = B(piv,:)
	 * @exception IllegalArgumentException
	 *                Matrix row dimensions must agree.
	 * @exception RuntimeException
	 *                Matrix is singular.
	 */
	IMatrix solve(final IMatrix B) {
		if (B.getRowDimension() != m) {
			throw new IllegalArgumentException("Matrix row dimensions must agree.");
		}
		if (!this.isNonsingular()) {
			throw new RuntimeException("Matrix is singular.");
		}

		// Copy right hand side with pivoting
		int nx = B.getColumnDimension();
		IMatrix Xmat = B.getSubMatrix(piv, 0, nx - 1);
		Apfloat[][] X = Xmat.getMatrix();

		// Solve L*Y = B(piv,:)
		for (int k = 0; k < n; k++) {
			for (int i = k + 1; i < n; i++) {
				for (int j = 0; j < nx; j++) {
					X[i][j] = X[i][j].subtract(X[k][j].multiply(LU[i][k]));
				}
			}
		}

		// Solve U*X = Y;
		for (int k = n - 1; k >= 0; k--) {
			for (int j = 0; j < nx; j++) {
				X[k][j] = X[k][j].divide(LU[k][k]);
			}
			for (int i = 0; i < k; i++) {
				for (int j = 0; j < nx; j++) {
					X[i][j] = X[i][j].subtract(X[k][j].multiply(LU[i][k]));
				}
			}
		}

		return Xmat;
	}
}
