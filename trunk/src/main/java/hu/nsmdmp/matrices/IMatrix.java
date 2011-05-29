package hu.nsmdmp.matrices;

import org.apfloat.Apfloat;

public interface IMatrix {

	/**
	 * Get calculated matrix.
	 * 
	 * @return calculated matrix.
	 */
	Apfloat[][] getMatrix();

	/**
	 * Copy the internal two-dimensional array.
	 * 
	 * @return two-dimensional array copy of matrix elements.
	 */
	Apfloat[][] getArrayCopy();

	/**
	 * Get row dimension.
	 * 
	 * @return m, the number of rows.
	 */
	int getRowDimension();

	/**
	 * Get column dimension.
	 * 
	 * @return n, the number of columns.
	 */
	int getColumnDimension();

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
	 * @exception ArrayIndexOutOfBoundsException
	 *                Submatrix indices
	 */
	IMatrix getSubMatrix(int[] r, int j0, int j1);

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
	Matrix getSubMatrix(int i0, int i1, int j0, int j1);
}
