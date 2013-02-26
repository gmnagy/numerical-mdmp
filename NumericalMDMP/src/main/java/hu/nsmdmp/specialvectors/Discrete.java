package hu.nsmdmp.specialvectors;

import static hu.nsmdmp.operations.Operations.operation;
import hu.nsmdmp.numerics.matrix.Vector;
import hu.nsmdmp.operations.IOperation;

public class Discrete {

	public static <T> Vector<T> discreteVector(final int length, final int changePos, final T base, final T value) {
		IOperation<T> op = operation(value.getClass());

		Vector<T> v = new Vector<T>(length, op.getType());

		for (int i = 0; i < length; i++)
			if (i >= changePos)
				v.set(i, value);
			else
				v.set(i, base);

		return v;
	}
}
