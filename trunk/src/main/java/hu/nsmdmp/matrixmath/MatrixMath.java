package hu.nsmdmp.matrixmath;

import hu.nsmdmp.matrices.IMatrix;
import hu.nsmdmp.matrices.Matrix;
import hu.nsmdmp.utils.Precision;

import org.apfloat.Apfloat;

public final class MatrixMath {

	public static final Apfloat ZERO = new Apfloat(0, Precision.SCALE);

	public static final Apfloat ONE = new Apfloat(1, Precision.SCALE);

	public static final Apfloat TWO = new Apfloat(2, Precision.SCALE);

	public static IMatrix normalize(final IMatrix matrix) {
		return Normalization.normalize(matrix);
	}

	public static Apfloat[] multiply(final IMatrix matrix, final Apfloat[] vector) {
		return Multiplication.multiply(matrix, vector);
	}

	public static int getVariationsNumber(final Apfloat[][] vectorSet) {
		int n = 1;
		for (Apfloat[] row : vectorSet) {
			n *= row.length;
		}

		return n;
	}

	/**
	 * Get all variations of <tt>vectorSet</tt>.
	 * 
	 */
	public static Apfloat[][] createVariation(final Apfloat[][] vectorSet) {
		return Variation.createVariation(vectorSet);
	}

	/**
	 * Get <tt>j</tt>-th variation of <tt>vectorSet</tt>.
	 * 
	 */
	public static Apfloat[] createVariation(final int j, final Apfloat[][] vectorSet) {
		return Variation.getVariation(j, vectorSet);
	}

	/**
	 * Generate identity matrix
	 * 
	 * @param m
	 *            Number of rows.
	 * @param n
	 *            Number of colums.
	 * @return An m-by-n matrix with ones on the diagonal and zeros elsewhere.
	 */
	public static IMatrix identity(int m, int n) {
		IMatrix A = new Matrix(m, n);
		Apfloat[][] X = A.getMatrix();

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				X[i][j] = (i == j ? ONE : ZERO);
			}
		}
		return A;
	}

	/**
	 * Matrix inverse or pseudoinverse
	 * 
	 * @return inverse(matrix) if matrix is square, pseudoinverse otherwise.
	 */
	public static IMatrix inverse(final IMatrix matrix) {
		int m = matrix.getRowDimension();
		int n = matrix.getColumnDimension();
		IMatrix identity = identity(m, m);

		return (m == n ? (new LUDecomposition(matrix)).solve(identity) : (new QRDecomposition(matrix)).solve(identity));
	}
}
