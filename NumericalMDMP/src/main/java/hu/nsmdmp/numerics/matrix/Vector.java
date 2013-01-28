package hu.nsmdmp.numerics.matrix;

import static com.google.common.base.Preconditions.checkNotNull;
import static hu.nsmdmp.numerics.matrix.operations.OperationFactory.selectOperation;
import hu.nsmdmp.numerics.matrix.operations.IOperations;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class Vector<T> {

	private Matrix<T> matrix;

	@SuppressWarnings("unchecked")
	public Vector(final T[] A) {

		T[][] M = (T[][]) new Object[1][A.length];
		M[0] = A;

		matrix = new Matrix<T>(M);
	}

	public Vector(final int column) {
		matrix = new Matrix<T>(1, column);
	}

	public int getColumnDimension() {
		return matrix.getColumnDimension();
	}

	public double[] getDoubleArray() {

		IOperations<T> op = selectOperation(getArray());
		double[] d = new double[getColumnDimension()];

		int i = 0;
		for (T item : matrix.getArray()[0]) {
			d[i] = op.toDouble(item);

			i++;
		}

		return d;
	}

	public T[] getArray() {
		return matrix.getRow(0);
	}

	public void setArray(final T[] A) {
		checkNotNull(A, "The A multiarray is NULL.");

		matrix.setRow(0, A);
	}

	public T get(final int column) {
		return matrix.get(0, column);
	}

	public void set(final int column, final T value) {
		checkNotNull(value, "The value is NULL.");

		matrix.set(0, column, value);
	}

	@Override
	public String toString() {
		return matrix.toString();
	}

	@Override
	public int hashCode() {
		return matrix.hashCode();
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Vector))
			return false;

		Vector<T> v = (Vector<T>) obj;

		return new EqualsBuilder().append(getArray(), v.getArray()).isEquals();
	}

	@Override
	public Vector<T> clone() {
		return new Vector<T>(getArray());
	}

}
