package hu.nsmdmp2.numerics;

import org.apfloat.Apfloat;

final class Operations {

	private static final IOperation<Double> DOUBLE = new DoubleOperation();

	private static final IOperation<Apfloat> APFLOAT = new ApfloatOperation();

	private Operations() {
	}

	@SuppressWarnings("unchecked")
	public static <T> IOperation<T> operation(final Class<T> type) {

		if (Double.class.isAssignableFrom(type))
			return (IOperation<T>) DOUBLE;
		if (Apfloat.class.isAssignableFrom(type))
			return (IOperation<T>) APFLOAT;

		throw new IllegalArgumentException("Missing operation: " + type);
	}
}
