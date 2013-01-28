package hu.nsmdmp.numerics.matrix.operations;

public class OperationFactory {

	@SuppressWarnings("unchecked")
	public static <T> IOperations<T> selectOperation(final T value) {

		if (value == null)
			throw new RuntimeException("The value is NULL.");

		Class<T> type = (Class<T>) value.getClass();

		return selectOperation(type);
	}

	@SuppressWarnings("unchecked")
	public static <T> IOperations<T> selectOperation(final T[] array) {

		if (array.length == 0)
			throw new RuntimeException("The array is empty.");

		Class<T> type = (Class<T>) array[0].getClass();

		return selectOperation(type);
	}

	@SuppressWarnings("unchecked")
	public static <T> IOperations<T> selectOperation(final T[][] matrix) {

		if (matrix.length == 0 || matrix[0].length == 0)
			throw new RuntimeException("The matrix is empty.");

		Class<T> type = (Class<T>) matrix[0][0].getClass();

		return selectOperation(type);
	}

	@SuppressWarnings("unchecked")
	public static <T> IOperations<T> selectOperation(final Class<T> type) {

		if (Double.class.isAssignableFrom(type))
			return (IOperations<T>) new DoubleOperations();

		throw new IllegalArgumentException(type + " not implemented operation!");
	}
}
