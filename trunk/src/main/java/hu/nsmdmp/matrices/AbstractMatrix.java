package hu.nsmdmp.matrices;

import org.apfloat.Apfloat;

abstract class AbstractMatrix implements IMatrix {

	/**
	 * The matrix;
	 * 
	 */
	protected Apfloat[][] matrix;

	/**
	 * Row and column dimensions.
	 * 
	 * @serial row dimension.
	 * @serial column dimension.
	 */
	protected int m, n;

	/**
	 * Get the matrix.
	 * 
	 * @return Pointer to the two-dimensional array of matrix elements.
	 */
	@Override
	public Apfloat[][] getMatrix() {
		return matrix;
	}

	/**
	 * Copy the internal two-dimensional array.
	 * 
	 * @return two-dimensional array copy of matrix elements.
	 */
	@Override
	public Apfloat[][] getArrayCopy() {
		Apfloat[][] C = new Apfloat[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				C[i][j] = matrix[i][j];
			}
		}

		return C;
	}

	/**
	 * Get row dimension.
	 * 
	 * @return m, the number of rows.
	 */
	@Override
	public int getRowDimension() {
		return m;
	}

	/**
	 * Get column dimension.
	 * 
	 * @return n, the number of columns.
	 */
	@Override
	public int getColumnDimension() {
		return n;
	}

	/**
	 * Get a submatrix.
	 * 
	 * @param r
	 *            Array of row indices.
	 * @param j0
	 *            Initial column index
	 * @param j1
	 *            Final column index
	 * @return A(r(:),j0:j1)
	 */
	@Override
	public IMatrix getSubMatrix(int[] r, int j0, int j1) {
		IMatrix X = new Matrix(r.length, j1 - j0 + 1);
		Apfloat[][] B = X.getMatrix();

		for (int i = 0; i < r.length; i++) {
			for (int j = j0; j <= j1; j++) {
				B[i][j - j0] = matrix[r[i]][j];
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
	 */
	@Override
	public Matrix getSubMatrix(int i0, int i1, int j0, int j1) {
		Matrix X = new Matrix(i1 - i0 + 1, j1 - j0 + 1);
		Apfloat[][] B = X.getMatrix();

		for (int i = i0; i <= i1; i++) {
			for (int j = j0; j <= j1; j++) {
				B[i - i0][j - j0] = matrix[i][j];
			}
		}

		return X;
	}

	@Override
	public String toString() {
		return MatrixUtils.print(matrix);
	}
}
