package hu.nsmdmp.specialvectors;

import hu.nsmdmp.numerics.matrix.Vector;

public class Discrete {

	public static <T> Vector<T> discreteVector(final int length, final int changePos, final T base, final T value) {

		Vector<T> v = new Vector<T>(length);

		for (int i = 0; i < length; i++)
			if (i >= changePos)
				v.set(i, value);
			else
				v.set(i, base);

		return v;
	}
}
