package hu.nsmdmp.numerics;

public class OperationFactory {

	@SuppressWarnings("unchecked")
	public static <T> IOperations<T> selectOperation(final Class<T> type) {

		if (Double.class.isAssignableFrom(type))
			return (IOperations<T>) new DoubleOperations();

		throw new IllegalArgumentException(type + " not implemented operation!");
	}
}
