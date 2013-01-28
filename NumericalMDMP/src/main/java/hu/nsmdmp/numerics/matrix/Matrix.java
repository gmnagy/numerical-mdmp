package hu.nsmdmp.numerics.matrix;

import static hu.nsmdmp.numerics.matrix.math.MatrixMath.identity;
import static hu.nsmdmp.numerics.matrix.operations.OperationFactory.selectOperation;
import hu.nsmdmp.numerics.matrix.operations.IOperations;

public class Matrix<T> extends MultiArray<T> {

	/**
	 * Column dimensions.
	 */
	protected final int column;

	@SuppressWarnings("unchecked")
	public Matrix(final int row, final int column) {
		super(row);

		this.column = column;

		this.M = (T[][]) new Object[row][column];
	}

	public Matrix(final T[][] A) {
		super(A);

		this.column = A[0].length;

		for (int i = 0; i < row; i++)
			if (A[i].length != column)
				throw new IllegalArgumentException("All rows must have the same length.");

	}

	public int getColumnDimension() {
		return column;
	}

	public double[][] getDoubleArray() {

		IOperations<T> op = selectOperation(getArray());
		double[][] d = new double[row][column];

		int i = 0;
		for (T[] array : M) {

			int j = 0;
			for (T item : array) {
				d[i][j] = op.toDouble(item);

				j++;
			}

			i++;
		}

		return d;
	}

	public Matrix<T> transpose() {
		Matrix<T> X = new Matrix<T>(column, row);

		for (int i = 0; i < row; i++)
			for (int j = 0; j < column; j++)
				X.set(j, i, M[i][j]);

		return X;
	}

	public Matrix<T> getSubMatrix(final int[] r, final int i0, final int i1) {
		Matrix<T> X = new Matrix<T>(r.length, i1 - i0 + 1);

		for (int i = 0; i < r.length; i++)
			for (int j = i0; j <= i1; j++)
				X.set(i, j - i0, M[r[i]][j]);

		return X;
	}

	public Matrix<T> getSubMatrix(final int i0, final int i1, final int j0, final int j1) {
		Matrix<T> X = new Matrix<T>(i1 - i0 + 1, j1 - j0 + 1);

		for (int i = i0; i <= i1; i++)
			for (int j = j0; j <= j1; j++)
				X.set(i - i0, j - j0, M[i][j]);

		return X;
	}

	/**
	 * Get LU Decomposition.
	 * 
	 */
	public LUDecomposition<T> getLU() {
		return new LUDecomposition<T>(this);
	}

	/**
	 * Get QR Decomposition.
	 * 
	 */
	public QRDecomposition<T> getQR() {
		return new QRDecomposition<T>(this);
	}

	/**
	 * Matrix inverse or pseudoinverse.
	 * 
	 * @return inverse(A) if A is square, pseudoinverse otherwise.
	 */
	public Matrix<T> inverse() {
		Matrix<T> identity = identity(row, row, getElementType());

		if (isSquare()) {
			return getLU().solve(identity);
		} else if (row > column) {
			return getQR().solve(identity);
		} else {
			Matrix<T> transposed = transpose();
			return transposed.getQR().solve(identity(column, column, getElementType())).transpose();
		}
	}

	public boolean isSquare() {
		return row == column;
	}
}
