package hu.nsmdmp.matrices;

import hu.nsmdmp.ApfloatUtils;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

/**
 * QR Decomposition from Jama(A Java Matrix Package): http://math.nist.gov/javanumerics/jama/
 * 
 * <P>
 * For an m-by-n matrix A with m >= n, the QR decomposition is an m-by-n orthogonal matrix Q and an
 * n-by-n upper triangular matrix R so that A = Q*R.
 * <P>
 * The QR decompostion always exists, even if the matrix does not have full rank, so the constructor
 * will never fail. The primary use of the QR decomposition is in the least squares solution of
 * nonsquare systems of simultaneous linear equations. This will fail if isFullRank() returns false.
 * 
 */
public final class QRDecomposition {

	/**
	 * Array for internal storage of decomposition.
	 * 
	 * @serial internal array storage.
	 */
	private Apfloat[][] QR;

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
	private Apfloat[] Rdiag;

	/**
	 * QR Decomposition, computed by Householder reflections.
	 * 
	 * @param A
	 *            Rectangular matrix
	 * @return Structure to access R and the Householder vectors and compute Q.
	 */
	QRDecomposition(final Matrix A) {

		QR = A.getArrayCopy();
		m = A.getRowDimension();
		n = A.getColumnDimension();
		Rdiag = new Apfloat[n];

		// Main loop.
		for (int k = 0; k < n; k++) {
			// Compute 2-norm of k-th column without under/overflow.
			Apfloat nrm = ApfloatUtils.ZERO;
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

				QR[k][k] = QR[k][k].add(ApfloatUtils.ONE);

				// Apply transformation to remaining columns.
				for (int j = k + 1; j < n; j++) {
					Apfloat s = ApfloatUtils.ZERO;
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

	private Apfloat hypot(final Apfloat a, final Apfloat b) {
		Apfloat r;

		if (ApfloatMath.abs(a).compareTo(ApfloatMath.abs(b)) > 0) {
			r = b.divide(a);
			Apfloat x = r.multiply(r).add(ApfloatUtils.ONE);
			r = ApfloatMath.abs(a).multiply(ApfloatMath.sqrt(x));
		} else if (b.signum() != 0) {
			r = a.divide(b);
			Apfloat x = r.multiply(r).add(ApfloatUtils.ONE);
			r = ApfloatMath.abs(b).multiply(ApfloatMath.sqrt(x));
		} else {
			r = ApfloatUtils.ZERO;
		}

		return r;
	}

	/**
	 * Return the upper triangular factor
	 * 
	 * @return R
	 */
	Matrix getR() {
		Matrix X = new Matrix(n, n);
		Apfloat[][] R = X.getArray();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i < j) {
					R[i][j] = QR[i][j];
				} else if (i == j) {
					R[i][j] = Rdiag[i];
				} else {
					R[i][j] = ApfloatUtils.ZERO;
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
	Matrix getQ() {
		Matrix X = new Matrix(m, n);
		Apfloat[][] Q = X.getArray();

		for (int k = n - 1; k >= 0; k--) {
			for (int i = 0; i < m; i++) {
				Q[i][k] = ApfloatUtils.ZERO;
			}

			Q[k][k] = ApfloatUtils.ONE;

			for (int j = k; j < n; j++) {
				if (QR[k][k].signum() != 0) {
					Apfloat s = ApfloatUtils.ZERO;

					for (int i = k; i < m; i++) {
						s = s.add(QR[i][k].multiply(Q[i][j]));
					}

					s = s.negate().divide(QR[k][k]);

					for (int i = k; i < m; i++) {
						Q[i][j] = Q[i][j].add(s.multiply(QR[i][k]));
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
	public Matrix solve(final Matrix B) {
		if (B.getRowDimension() != m) {
			throw new IllegalArgumentException("Matrix row dimensions must agree.");
		}
		if (!this.isFullRank()) {
			throw new RuntimeException("Matrix is rank deficient.");
		}

		// Copy right hand side
		int nx = B.getColumnDimension();
		Apfloat[][] X = B.getArrayCopy();

		// Compute Y = transpose(Q)*B
		for (int k = 0; k < n; k++) {
			for (int j = 0; j < nx; j++) {
				Apfloat s = ApfloatUtils.ZERO;
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

		return new Matrix(X).getSubMatrix(0, n - 1, 0, nx - 1);
	}
}
