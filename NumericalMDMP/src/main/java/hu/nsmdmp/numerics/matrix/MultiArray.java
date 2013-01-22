package hu.nsmdmp.numerics.matrix;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Arrays;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * MultiArray class with T type elements.
 * 
 */
public class MultiArray<T> {

	/**
	 * Array for internal storage of elements.
	 */
	protected T[][] M;

	/**
	 * Row dimensions.
	 */
	protected final int row;

	MultiArray(final int row) {
		this.row = row;
	}

	@SuppressWarnings("unchecked")
	public MultiArray(final T[][] A) {
		checkNotNull(A, "The A multiarray is NULL.");

		row = A.length;
		M = (T[][]) new Object[row][];

		for (int i = 0; i < row; i++) {

			M[i] = (T[]) new Object[A[i].length];

			for (int j = 0; j < A[i].length; j++)
				M[i][j] = A[i][j];
		}
	}

	public int getRowDimension() {
		return row;
	}

	public T[][] getArray() {
		return M;
	}

	public void setArray(final T[][] A) {
		checkNotNull(A, "The A multiarray is NULL.");
		checkArgument(row != A.length, "The A multiArray row is not equals with defined row.");

		M = A;
	}

	public T[] getRow(final int row) {
		return M[row];
	}

	public void setRow(final int row, final T[] array) {
		checkNotNull(M, "This MultiArray internal storage is NULL.");
		checkNotNull(array, "The array is NULL.");

		M[row] = array;
	}

	public T get(final int row, final int column) {
		return M[row][column];
	}

	public void set(final int row, final int column, final T value) {
		checkNotNull(M, "This MultiArray internal storage is NULL.");
		checkNotNull(value, "The value is NULL.");

		M[row][column] = value;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		for (T[] array : M)
			sb.append(Arrays.toString(array) + "\n");

		return sb.toString();
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();

		for (T[] array : M)
			builder.append(array);

		return builder.toHashCode();
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof MultiArray))
			return false;

		MultiArray<T> ma = (MultiArray<T>) obj;

		EqualsBuilder builder = new EqualsBuilder();

		int i = 0;
		for (T[] array : M) {
			builder.append(array, ma.M[i]);
			i++;
		}

		return builder.isEquals();
	}

	@Override
	public MultiArray<T> clone() {
		return new MultiArray<T>(this.M);
	}

}
