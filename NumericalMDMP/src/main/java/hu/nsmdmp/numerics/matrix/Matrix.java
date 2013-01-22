package hu.nsmdmp.numerics.matrix;

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
}
