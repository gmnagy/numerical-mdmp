package hu.nsmdmp2.numerics.matrix;

import hu.nsmdmp2.numerics.Value;

/**
 * QR Decomposition from Jama(A Java Matrix Package):
 * http://math.nist.gov/javanumerics/jama/
 * 
 * <P>
 * For an m-by-n matrix A with m >= n, the QR decomposition is an m-by-n
 * orthogonal matrix Q and an n-by-n upper triangular matrix R so that A = Q*R.
 * <P>
 * The QR decompostion always exists, even if the matrix does not have full
 * rank, so the constructor will never fail. The primary use of the QR
 * decomposition is in the least squares solution of nonsquare systems of
 * simultaneous linear equations. This will fail if isFullRank() returns false.
 * 
 */
final class QRDecomposition {

	/**
	 * Array for internal storage of decomposition.
	 * 
	 * @serial internal array storage.
	 */
	private Value[][] QR;

	/**
	 * Row and column dimensions.
	 * 
	 * @serial column dimension.
	 * @serial row dimension.
	 */
	private int m, n;

	/**
	 * Array for internal storage of diagonal of R.
	 * 
	 * @serial diagonal of R.
	 */
	private Value[] Rdiag;

	Class<? extends Comparable<?>> valueType;

	/**
	 * QR Decomposition, computed by Householder reflections.
	 * 
	 * @param A
	 *            Rectangular matrix
	 * @return Structure to access R and the Householder vectors and compute Q.
	 */
	public QRDecomposition(final Value[][] A) {

		valueType = A[0][0].getType();

		QR = MatrixMath.clone(A);
		m = A.length;
		n = A[0].length;
		Rdiag = new Value[n];

		// Main loop.
		for (int k = 0; k < n; k++) {
			// Compute 2-norm of k-th column without under/overflow.
			Value nrm = Value.zero(valueType);
			for (int i = k; i < m; i++) {
				nrm = hypot(nrm, QR[i][k]);
			}

			if (nrm.signum() != 0) {
				// Form k-th Householder vector.
				if (QR[k][k].signum() < 0) {
					nrm = nrm.negate();
				}

				for (int i = k; i < m; i++) {
					QR[i][k] = QR[i][k].divide(nrm);
				}

				QR[k][k] = QR[k][k].add(Value.one(valueType));

				// Apply transformation to remaining columns.
				for (int j = k + 1; j < n; j++) {
					Value s = Value.zero(valueType);
					for (int i = k; i < m; i++) {
						s = s.add(QR[i][k].multiply(QR[i][j]));
					}

					s = s.negate().divide(QR[k][k]);

					for (int i = k; i < m; i++) {
						QR[i][j] = QR[i][j].add(s.multiply(QR[i][k]));
					}
				}
			}

			Rdiag[k] = nrm.negate();
		}
	}

	private Value hypot(final Value a, final Value b) {
		Value r;

		if (a.abs().compareTo(b.abs()) > 0) {
			r = b.divide(a);
			Value x = r.multiply(r).add(Value.one(valueType));
			r = a.abs().multiply(x.sqrt());
		} else if (b.signum() != 0) {
			r = a.divide(b);
			Value x = r.multiply(r).add(Value.one(valueType));
			r = b.abs().multiply(x.sqrt());
		} else {
			r = Value.zero(valueType);
		}

		return r;
	}

	/**
	 * Return the upper triangular factor
	 * 
	 * @return R
	 */
	Value[][] getR() {
		Value[][] X = new Value[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i < j) {
					X[i][j] = QR[i][j];
				} else if (i == j) {
					X[i][j] = Rdiag[i];
				} else {
					X[i][j] = Value.zero(valueType);
				}
			}
		}

		return X;
	}

	/**
	 * Generate and return the (economy-sized) orthogonal factor
	 * 
	 * @return Q
	 */
	Value[][] getQ() {
		Value[][] X = new Value[m][n];

		for (int k = n - 1; k >= 0; k--) {
			for (int i = 0; i < m; i++) {
				X[i][k] = Value.zero(valueType);
			}

			X[k][k] = Value.one(valueType);

			for (int j = k; j < n; j++) {
				if (QR[k][k].signum() != 0) {
					Value s = Value.zero(valueType);

					for (int i = k; i < m; i++) {
						s = s.add(QR[i][k].multiply(X[i][j]));
					}

					s = s.negate().divide(QR[k][k]);

					for (int i = k; i < m; i++) {
						X[i][j] = X[i][j].add(s.multiply(QR[i][k]));
					}
				}
			}
		}

		return X;
	}

	/**
	 * Is the matrix full rank?
	 * 
	 * @return true if R, and hence A, has full rank.
	 */
	private boolean isFullRank() {
		for (int j = 0; j < n; j++) {
			if (Rdiag[j].signum() == 0) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Least squares solution of A*X = B
	 * 
	 * @param B
	 *            A Matrix with as many rows as A and any number of columns.
	 * @return X that minimizes the two norm of Q*R*X-B.
	 * @exception IllegalArgumentException
	 *                Matrix row dimensions must agree.
	 * @exception RuntimeException
	 *                Matrix is rank deficient.
	 */
	public Value[][] solve(final Value[][] B) {
		if (B.length != m) {
			throw new IllegalArgumentException("Matrix row dimensions must agree.");
		}
		if (!this.isFullRank()) {
			throw new RuntimeException("Matrix is rank deficient.");
		}

		// Copy right hand side
		int nx = B[0].length;
		Value[][] X = MatrixMath.clone(B);

		// Compute Y = transpose(Q)*B
		for (int k = 0; k < n; k++) {
			for (int j = 0; j < nx; j++) {
				Value s = Value.zero(valueType);
				for (int i = k; i < m; i++) {
					s = s.add(QR[i][k].multiply(X[i][j]));
				}

				s = s.negate().divide(QR[k][k]);

				for (int i = k; i < m; i++) {
					X[i][j] = X[i][j].add(s.multiply(QR[i][k]));
				}
			}
		}

		// Solve R*X = Y;
		for (int k = n - 1; k >= 0; k--) {
			for (int j = 0; j < nx; j++) {
				X[k][j] = X[k][j].divide(Rdiag[k]);
			}

			for (int i = 0; i < k; i++) {
				for (int j = 0; j < nx; j++) {
					X[i][j] = X[i][j].subtract(X[k][j].multiply(QR[i][k]));
				}
			}
		}

		return MatrixMath.getSubMatrix(X, 0, n - 1, 0, nx - 1);
	}
}
