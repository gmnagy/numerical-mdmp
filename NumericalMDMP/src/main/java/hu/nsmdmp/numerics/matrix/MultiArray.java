package hu.nsmdmp.numerics.matrix;

import static com.google.common.base.Preconditions.checkElementIndex;
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
	protected final T[][] M;

	/**
	 * Row dimensions.
	 */
	protected final int rowLength;

	protected final Class<T> valueType;

	/**
	 * Create empty multiarray. 
	 */
	public MultiArray(final int rowLength, final Class<T> valueType) {
		this.rowLength = rowLength;
		this.valueType = valueType;

		@SuppressWarnings("unchecked")
		T[][] tmp = (T[][]) new Object[rowLength][];
		M = tmp;
	}

	@SuppressWarnings("unchecked")
	public MultiArray(final T[][] A) {
		checkNotNull(A, "The A multiarray is NULL.");

		this.rowLength = A.length;

		T[][] tmp = (T[][]) new Object[rowLength][];
		M = tmp;

		Class<T> type = null;

		for (int i = 0; i < rowLength; i++) {

			int columnIndex = A[i].length;
			tmp[i] = (T[]) new Object[columnIndex];

			for (int j = 0; j < columnIndex; j++) {
				if (null != A[i][j] && null == type)
					type = (Class<T>) A[i][j].getClass();

				tmp[i][j] = A[i][j];
			}
		}

		checkNotNull(type, "The A multiarray elements are NULL.");
		this.valueType = type;
	}

	public int getRowDimension() {
		return rowLength;
	}

	public Class<T> getValueType() {
		return valueType;
	}

	public T[] getRow(final int rowIndex) {
		return M[rowIndex];
	}

	public void setRow(final int rowIndex, final T[] array) {
		checkElementIndex(rowIndex, this.rowLength);

		M[rowIndex] = array;
	}

	public T get(final int rowIndex, final int columnIndex) {
		return M[rowIndex][columnIndex];
	}

	public void set(final int rowIndex, final int columnIndex, final T value) {
		checkElementIndex(rowIndex, this.rowLength);
		checkElementIndex(columnIndex, M[rowIndex].length);

		M[rowIndex][columnIndex] = value;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		for (T[] row : M)
			sb.append(Arrays.toString(row) + "\n");

		return sb.toString();
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();

		for (T[] row : M)
			builder.append(row);

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
		for (T[] row : M) {
			builder.append(row, ma.M[i]);
			i++;
		}

		return builder.isEquals();
	}

	@Override
	public MultiArray<T> clone() {
		return new MultiArray<T>(this.M);
	}
}
