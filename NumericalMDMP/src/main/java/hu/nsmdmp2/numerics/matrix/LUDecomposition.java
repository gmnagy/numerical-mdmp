package hu.nsmdmp2.numerics.matrix;

import hu.nsmdmp2.numerics.Value;

/**
 * LU Decomposition from Jama(A Java Matrix Package):
 * http://math.nist.gov/javanumerics/jama/
 * 
 * <P>
 * For an m-by-n matrix A with m >= n, the LU decomposition is an m-by-n unit
 * lower triangular matrix L, an n-by-n upper triangular matrix U, and a
 * permutation vector piv of length m so that A(piv,:) = L*U. If m < n, then L
 * is m-by-m and U is m-by-n.
 * <P>
 * The LU decompostion with pivoting always exists, even if the matrix is
 * singular, so the constructor will never fail. The primary use of the LU
 * decomposition is in the solution of square systems of simultaneous linear
 * equations. This will fail if isNonsingular() returns false.
 * 
 */
final class LUDecomposition {

	/**
	 * Array for internal storage of decomposition.
	 * 
	 * @serial internal array storage.
	 */
	Value[][] LU;

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

	Class<? extends Comparable<?>> valueType;

	/**
	 * LU Decomposition
	 * 
	 * @param A
	 *            Rectangular matrix
	 * @return Structure to access L, U and piv.
	 */
	LUDecomposition(final Value[][] A) {

		// Use a "left-looking", dot-product, Crout/Doolittle algorithm.

		valueType = A[0][0].getType();

		LU = MatrixMath.clone(A);
		m = A.length;
		n = A[0].length;

		piv = new int[m];
		for (int i = 0; i < m; i++) {
			piv[i] = i;
		}
		pivsign = 1;
		Value[] LUrowi;
		Value[] LUcolj = new Value[m];

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
				Value s = Value.zero(valueType);
				for (int k = 0; k < kmax; k++) {
					s = s.add(LUrowi[k].multiply(LUcolj[k]));
				}

				LUrowi[j] = LUcolj[i] = LUcolj[i].subtract(s);
			}

			// Find pivot and exchange if necessary.
			int p = j;
			for (int i = j + 1; i < m; i++) {
				if (LUcolj[i].abs().compareTo(LUcolj[p].abs()) > 0) {
					p = i;
				}
			}
			if (p != j) {
				for (int k = 0; k < n; k++) {
					Value t = LU[p][k];
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
	Value[][] getL() {
		Value[][] X = new Value[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i > j) {
					X[i][j] = LU[i][j];
				} else if (i == j) {
					X[i][j] = Value.one(valueType);
				} else {
					X[i][j] = Value.zero(valueType);
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
	Value[][] getU() {
		Value[][] X = new Value[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i <= j) {
					X[i][j] = LU[i][j];
				} else {
					X[i][j] = Value.zero(valueType);
				}
			}
		}

		return X;
	}

	// /**
	// * Return pivot permutation vector
	// *
	// * @return piv
	// */
	//
	// public int[] getPivot() {
	// int[] p = new int[m];
	// for (int i = 0; i < m; i++) {
	// p[i] = piv[i];
	// }
	// return p;
	// }

	// /**
	// * Return pivot permutation vector as a one-dimensional double array
	// *
	// * @return (double) piv
	// */
	//
	// public double[] getDoublePivot() {
	// double[] vals = new double[m];
	// for (int i = 0; i < m; i++) {
	// vals[i] = (double) piv[i];
	// }
	// return vals;
	// }

	// /**
	// * Determinant
	// *
	// * @return det(A)
	// * @exception IllegalArgumentException
	// * Matrix must be square
	// */
	//
	// public double det() {
	// if (m != n) {
	// throw new IllegalArgumentException("Matrix must be square.");
	// }
	// double d = (double) pivsign;
	// for (int j = 0; j < n; j++) {
	// d *= LU[j][j];
	// }
	// return d;
	// }

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
	Value[][] solve(final Value[][] B) {
		if (B.length != m) {
			throw new IllegalArgumentException("Matrix row dimensions must agree.");
		}
		if (!this.isNonsingular()) {
			throw new RuntimeException("Matrix is singular.");
		}

		// Copy right hand side with pivoting
		int nx = B[0].length;
		Value[][] Xmat = MatrixMath.getSubMatrix(B, piv, 0, nx - 1);

		// Solve L*Y = B(piv,:)
		for (int k = 0; k < n; k++) {
			for (int i = k + 1; i < n; i++) {
				for (int j = 0; j < nx; j++) {
					Xmat[i][j] = Xmat[i][j].subtract(Xmat[k][j].multiply(LU[i][k]));
				}
			}
		}

		// Solve U*X = Y;
		for (int k = n - 1; k >= 0; k--) {
			for (int j = 0; j < nx; j++) {
				Xmat[k][j] = Xmat[k][j].divide(LU[k][k]);
			}
			for (int i = 0; i < k; i++) {
				for (int j = 0; j < nx; j++) {
					Xmat[i][j] = Xmat[i][j].subtract(Xmat[k][j].multiply(LU[i][k]));
				}
			}
		}

		return Xmat;
	}
}
