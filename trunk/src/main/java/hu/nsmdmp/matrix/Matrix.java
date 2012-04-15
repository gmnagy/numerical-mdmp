package hu.nsmdmp.matrix;

import hu.nsmdmp.matrix.operation.LUDecomposition;
import hu.nsmdmp.matrix.operation.MatrixMath;
import hu.nsmdmp.matrix.operation.QRDecomposition;
import hu.nsmdmp.utils.ApfloatUtils;
import hu.nsmdmp.utils.Utils;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

/**
 * Matrix class with {@link Apfloat} matrix elements.
 * 
 */
public class Matrix {

	/**
	 * Array for internal storage of elements.
	 * 
	 */
	protected Apfloat[][] A;

	/**
	 * Row and column dimensions.
	 * 
	 * @serial row dimension.
	 * @serial column dimension.
	 */
	protected int m, n;

	/**
	 * Construct a empty matrix.
	 * 
	 * @param m
	 *            Number of rows.
	 * @param n
	 *            Number of colums.
	 */
	public Matrix(final int m, final int n) {
		this.m = m;
		this.n = n;
		this.A = new Apfloat[m][n];
	}

	/**
	 * Construct a matrix with <tt>value</tt>.
	 * 
	 * @param m
	 *            Number of rows.
	 * @param n
	 *            Number of colums.
	 */
	public Matrix(final int m, final int n, final Apfloat value) {
		this.m = m;
		this.n = n;
		this.A = new Apfloat[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				this.A[i][j] = value;
			}
		}
	}

	/**
	 * Construct a matrix from a 2-D array.
	 * 
	 * @param A
	 *            Two-dimensional array of {@link Apfloat}.
	 */
	public Matrix(final Apfloat[][] A) {
		m = A.length;
		n = A[0].length;
		this.A = new Apfloat[m][n];

		for (int i = 0; i < m; i++) {
			if (A[i].length != n) {
				throw new IllegalArgumentException("All rows must have the same length.");
			}

			for (int j = 0; j < n; j++) {
				this.A[i][j] = A[i][j];
			}
		}
	}

	/**
	 * Construct a matrix from a 2-D array.
	 * 
	 * @param A
	 *            Two-dimensional array of doubles.
	 */
	public Matrix(final double[][] A) {
		m = A.length;
		n = A[0].length;
		this.A = new Apfloat[m][n];

		for (int i = 0; i < m; i++) {
			if (A[i].length != n) {
				throw new IllegalArgumentException("All rows must have the same length.");
			}

			for (int j = 0; j < n; j++) {
				this.A[i][j] = ApfloatUtils.valueOf(A[i][j]);
			}
		}
	}

	/**
	 * Get the internal two-dimensional array.
	 * 
	 * @return Pointer to the two-dimensional array of matrix elements.
	 */
	public Apfloat[][] getArray() {
		return A;
	}

	/**
	 * Get row dimension.
	 * 
	 * @return m, the number of rows.
	 */
	public int getRowDimension() {
		return m;
	}

	/**
	 * Get column dimension.
	 * 
	 * @return n, the number of columns.
	 */
	public int getColumnDimension() {
		return n;
	}

	/**
	 * Is <code>true</code> if this row dimension equals with column dimension, <code>false</code>
	 * otherwise.
	 * 
	 * @return <code>true</code> if this row dimension equals with column dimension,
	 *         <code>false</code> otherwise.
	 */
	public boolean isSquare() {
		return m == n;
	}

	/**
	 * Copy the internal two-dimensional array.
	 * 
	 * @return Two-dimensional array copy of matrix elements.
	 */
	public Apfloat[][] getArrayCopy() {
		Apfloat[][] C = new Apfloat[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				C[i][j] = A[i][j];
			}
		}

		return C;
	}

	/**
	 * Make a deep copy of a matrix.
	 * 
	 */
	public Matrix copy() {
		Matrix X = new Matrix(m, n);
		Apfloat[][] C = X.getArray();

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				C[i][j] = A[i][j];
			}
		}

		return X;
	}

	/**
	 * Clone the {@link Matrix} object.
	 * 
	 */
	public Object clone() {
		return copy();
	}

	/**
	 * Get i-th row.
	 * 
	 * @param i
	 *            Row index.
	 * @return i-th row.
	 * @exception ArrayIndexOutOfBoundsException
	 */
	public Apfloat[] getRow(final int i) {
		return A[i];
	}

	public void setRow(final int i, final Apfloat[] row) {
		A[i] = row;
	}

	/**
	 * Get a single element.
	 * 
	 * @param i
	 *            Row index.
	 * @param j
	 *            Column index.
	 * @return A(i,j)
	 * @exception ArrayIndexOutOfBoundsException
	 */
	public Apfloat get(final int i, final int j) {
		return A[i][j];
	}

	/**
	 * Set a single element.
	 * 
	 * @param i
	 *            Row index.
	 * @param j
	 *            Column index.
	 * @param s
	 *            A(i,j).
	 * @exception ArrayIndexOutOfBoundsException
	 */
	public void set(final int i, final int j, final Apfloat s) {
		A[i][j] = s;
	}

	/**
	 * One norm.
	 * 
	 * @return maximum column sum.
	 */
	public Apfloat norm1() {
		Apfloat f = ApfloatUtils.ZERO;

		for (int j = 0; j < n; j++) {
			Apfloat s = ApfloatUtils.ZERO;

			for (int i = 0; i < m; i++) {
				s = s.add(ApfloatMath.abs(A[i][j]));
			}

			f = ApfloatUtils.max(f, s);
		}

		return f;
	}

	/**
	 * Get a submatrix.
	 * 
	 * @param r
	 *            Array of row indices.
	 * @param i0
	 *            Initial column index
	 * @param i1
	 *            Final column index
	 * @return A(r(:),j0:j1)
	 * @exception ArrayIndexOutOfBoundsException
	 *                Submatrix indices
	 */
	public Matrix getSubMatrix(final int[] r, final int i0, final int i1) {
		Matrix X = new Matrix(r.length, i1 - i0 + 1);
		Apfloat[][] B = X.getArray();

		for (int i = 0; i < r.length; i++) {
			for (int j = i0; j <= i1; j++) {
				B[i][j - i0] = A[r[i]][j];
			}
		}

		return X;
	}

	/**
	 * Get a submatrix.
	 * 
	 * @param i0
	 *            Initial row index
	 * @param i1
	 *            Final row index
	 * @param j0
	 *            Initial column index
	 * @param j1
	 *            Final column index
	 * @return A(i0:i1,j0:j1)
	 * @exception ArrayIndexOutOfBoundsException
	 *                Submatrix indices
	 */
	public Matrix getSubMatrix(final int i0, final int i1, final int j0, final int j1) {
		Matrix X = new Matrix(i1 - i0 + 1, j1 - j0 + 1);
		Apfloat[][] B = X.getArray();

		for (int i = i0; i <= i1; i++) {
			for (int j = j0; j <= j1; j++) {
				B[i - i0][j - j0] = A[i][j];
			}
		}

		return X;
	}

	/**
	 * Matrix transpose.
	 * 
	 * @return A'
	 */
	public Matrix transpose() {
		Matrix X = new Matrix(n, m);
		Apfloat[][] C = X.getArray();

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				C[j][i] = A[i][j];
			}
		}

		return X;
	}

	/**
	 * Get LU Decomposition.
	 * 
	 * @return LUDecomposition
	 * @see LUDecomposition
	 */
	public LUDecomposition getLU() {
		return new LUDecomposition(this);
	}

	/**
	 * Get QR Decomposition.
	 * 
	 * @return QRDecomposition
	 * @see QRDecomposition
	 */
	public QRDecomposition getQR() {
		return new QRDecomposition(this);
	}

	/**
	 * Matrix inverse or pseudoinverse.
	 * 
	 * @return inverse(A) if A is square, pseudoinverse otherwise.
	 */
	public Matrix inverse() {
		Matrix identity = MatrixMath.identity(m, m);

		if (isSquare()) {
			return getLU().solve(identity);
		} else if (m > n) {
			return getQR().solve(identity);
		} else {
			Matrix transposed = transpose();
			return transposed.getQR().solve(MatrixMath.identity(n, n)).transpose();
		}
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof Matrix)) {
			return false;
		}

		return Utils.equals(getArray(), ((Matrix) obj).getArray());
	}

	@Override
	public String toString() {
		return Utils.print(A);
	}
}
