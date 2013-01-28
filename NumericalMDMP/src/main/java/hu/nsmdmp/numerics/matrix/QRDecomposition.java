package hu.nsmdmp.numerics.matrix;

import static hu.nsmdmp.numerics.matrix.operations.OperationFactory.selectOperation;
import hu.nsmdmp.numerics.matrix.operations.IOperations;

import java.lang.reflect.Array;

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
final class QRDecomposition<T> {

	/**
	 * Array for internal storage of decomposition.
	 * 
	 * @serial internal array storage.
	 */
	private T[][] QR;

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
	private T[] Rdiag;

	private IOperations<T> op;

	/**
	 * QR Decomposition, computed by Householder reflections.
	 * 
	 * @param A
	 *            Rectangular matrix
	 * @return Structure to access R and the Householder vectors and compute Q.
	 */
	@SuppressWarnings("unchecked")
	public QRDecomposition(final Matrix<T> A) {

		op = selectOperation(A.getElementType());

		QR = A.clone().getArray();
		m = A.getRowDimension();
		n = A.getColumnDimension();
		Rdiag = (T[]) Array.newInstance(op.getType(), n);

		// Main loop.
		for (int k = 0; k < n; k++) {
			// Compute 2-norm of k-th column without under/overflow.
			T nrm = op.zero();
			for (int i = k; i < m; i++) {
				nrm = hypot(nrm, QR[i][k]);
			}

			if (op.signum(nrm) != 0) {
				// Form k-th Householder vector.
				if (op.signum(QR[k][k]) < 0) {
					nrm = op.negate(nrm);
				}

				for (int i = k; i < m; i++) {
					QR[i][k] = op.divide(QR[i][k], nrm);
				}

				QR[k][k] = op.add(QR[k][k], op.one());

				// Apply transformation to remaining columns.
				for (int j = k + 1; j < n; j++) {
					T s = op.zero();
					for (int i = k; i < m; i++) {
						s = op.add(s, op.multiply(QR[i][k], QR[i][j]));
					}

					s = op.divide(op.negate(s), QR[k][k]);

					for (int i = k; i < m; i++) {
						QR[i][j] = op.add(QR[i][j], op.multiply(s, QR[i][k]));
					}
				}
			}

			Rdiag[k] = op.negate(nrm);
		}
	}

	private T hypot(final T a, final T b) {
		T r;

		if (op.compareTo(op.abs(a), op.abs(b)) > 0) {
			r = op.divide(b, a);
			T x = op.add(op.multiply(r, r), op.one());
			r = op.multiply(op.abs(a), op.sqrt(x));
		} else if (op.signum(b) != 0) {
			r = op.divide(a, b);
			T x = op.add(op.multiply(r, r), op.one());
			r = op.multiply(op.abs(b), op.sqrt(x));
		} else {
			r = op.zero();
		}

		return r;
	}

	/**
	 * Return the upper triangular factor
	 * 
	 * @return R
	 */
	Matrix<T> getR() {
		Matrix<T> X = new Matrix<T>(n, n);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i < j) {
					X.set(i, j, QR[i][j]);
				} else if (i == j) {
					X.set(i, j, Rdiag[i]);
				} else {
					X.set(i, j, op.zero());
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
	Matrix<T> getQ() {
		Matrix<T> X = new Matrix<T>(m, n);

		for (int k = n - 1; k >= 0; k--) {
			for (int i = 0; i < m; i++) {
				X.set(i, k, op.zero());
			}

			X.set(k, k, op.one());

			for (int j = k; j < n; j++) {
				if (op.signum(QR[k][k]) != 0) {
					T s = op.zero();

					for (int i = k; i < m; i++) {
						s = op.add(s, op.multiply(QR[i][k], X.get(i, j)));
					}

					s = op.divide(op.negate(s), QR[k][k]);

					for (int i = k; i < m; i++) {
						X.set(i, j, op.add(X.get(i, j), op.multiply(s, QR[i][k])));
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
			if (op.signum(Rdiag[j]) == 0) {
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
	public Matrix<T> solve(final Matrix<T> B) {
		if (B.getRowDimension() != m) {
			throw new IllegalArgumentException("Matrix row dimensions must agree.");
		}
		if (!this.isFullRank()) {
			throw new RuntimeException("Matrix is rank deficient.");
		}

		// Copy right hand side
		int nx = B.getColumnDimension();
		T[][] X = B.clone().getArray();

		// Compute Y = transpose(Q)*B
		for (int k = 0; k < n; k++) {
			for (int j = 0; j < nx; j++) {
				T s = op.zero();
				for (int i = k; i < m; i++) {
					s = op.add(s, op.multiply(QR[i][k], X[i][j]));
				}

				s = op.divide(op.negate(s), QR[k][k]);

				for (int i = k; i < m; i++) {
					X[i][j] = op.add(X[i][j], op.multiply(s, QR[i][k]));
				}
			}
		}

		// Solve R*X = Y;
		for (int k = n - 1; k >= 0; k--) {
			for (int j = 0; j < nx; j++) {
				X[k][j] = op.divide(X[k][j], Rdiag[k]);
			}

			for (int i = 0; i < k; i++) {
				for (int j = 0; j < nx; j++) {
					X[i][j] = op.subtract(X[i][j], op.multiply(X[k][j], QR[i][k]));
				}
			}
		}

		return new Matrix<T>(X).getSubMatrix(0, n - 1, 0, nx - 1);
	}
}
