package hu.nsmdmp.numerics.matrix;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static hu.nsmdmp.numerics.matrix.math.MatrixMath.identity;
import static hu.nsmdmp.operations.Operations.operation;
import hu.nsmdmp.numerics.matrix.math.MatrixMath;
import hu.nsmdmp.operations.IOperation;

import java.lang.reflect.Array;

public class Matrix<T> extends MultiArray<T> {

	/**
	 * Column dimensions.
	 */
	protected final int columnLength;

	@SuppressWarnings("unchecked")
	public Matrix(final int rowLength, final int columnLength, final Class<T> valueType) {
		super(rowLength, valueType);

		this.columnLength = columnLength;

		for (int i = 0; i < rowLength; i++)
			M[i] = (T[]) Array.newInstance(valueType, columnLength);
	}

	public Matrix(final T[][] A) {
		super(A);

		this.columnLength = A[0].length;

		for (int i = 0; i < rowLength; i++)
			checkArgument(A[i].length == columnLength, "All rows must have the same length.");
	}

	public int getColumnDimension() {
		return columnLength;
	}

	public double[][] toDoubleMultiArray() {

		double[][] array = new double[rowLength][columnLength];

		int i = 0;
		for (T[] row : M) {

			int j = 0;
			for (T item : row) {

				if (null == item)
					array[i][j] = 0;
				else {
					IOperation<T> op = operation(item.getClass());
					array[i][j] = op.toDouble(item);
				}

				j++;
			}

			i++;
		}

		return array;
	}

	public Matrix<T> transpose() {
		Matrix<T> X = new Matrix<T>(columnLength, rowLength, valueType);

		for (int i = 0; i < rowLength; i++)
			for (int j = 0; j < columnLength; j++)
				X.set(j, i, M[i][j]);

		return X;
	}

	public Matrix<T> getSubMatrix(final int[] r, final int i0, final int i1) {
		Matrix<T> X = new Matrix<T>(r.length, i1 - i0 + 1, valueType);

		for (int i = 0; i < r.length; i++)
			for (int j = i0; j <= i1; j++)
				X.set(i, j - i0, M[r[i]][j]);

		return X;
	}

	public Matrix<T> getSubMatrix(final int i0, final int i1, final int j0, final int j1) {
		Matrix<T> X = new Matrix<T>(i1 - i0 + 1, j1 - j0 + 1, valueType);

		for (int i = i0; i <= i1; i++)
			for (int j = j0; j <= j1; j++)
				X.set(i - i0, j - j0, M[i][j]);

		return X;
	}

	public Matrix<T> getSubMatrix(final int from, final int to) {
		return getSubMatrix(from, to, 0, columnLength - 1);
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
		checkNotNull(get(0, 0));

		Matrix<T> identity = MatrixMath.identity(rowLength, rowLength, valueType);

		if (isSquare()) {
			return getLU().solve(identity);
		} else if (rowLength > columnLength) {
			return getQR().solve(identity);
		} else {
			Matrix<T> transposed = transpose();
			return transposed.getQR().solve(identity(columnLength, columnLength, valueType)).transpose();
		}
	}

	public boolean isSquare() {
		return rowLength == columnLength;
	}

	public Vector<T> multiply(final Vector<T> V) {
		return MatrixMath.multiply(this, V);
	}

	public Matrix<T> multiply(final Matrix<T> A) {
		return MatrixMath.multiply(this, A);
	}

	public T[][] toArray() {
		@SuppressWarnings("unchecked")
		T[][] copy = (T[][]) Array.newInstance(valueType, new int[] { rowLength, columnLength });

		for (int i = 0; i < rowLength; i++) {
			System.arraycopy(M[i], 0, copy[i], 0, M[i].length);
		}

		return copy;
	}
}
