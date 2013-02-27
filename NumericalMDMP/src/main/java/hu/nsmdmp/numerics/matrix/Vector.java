package hu.nsmdmp.numerics.matrix;

public class Vector<T> extends Matrix<T> {

	public Vector(final int columnLength, final Class<T> valueType) {
		super(1, columnLength, valueType);
	}

	@SuppressWarnings("unchecked")
	public Vector(final T[] V) {
		super((T[][]) new Object[][] { V });
	}

	public T get(final int columnIndex) {
		return get(0, columnIndex);
	}

	public void set(final int columnIndex, final T value) {
		set(0, columnIndex, value);
	}

	public double[] toDoubleArray() {
		return super.toDoubleMultiArray()[0];
	}

	public Vector<T> getSubVector(final int from, final int to) {

		Vector<T> sub = new Vector<T>(to - from + 1, valueType);

		for (int i = from; i <= to; i++) {
			sub.set(i - from, get(i));
		}

		return sub;
	}
}
