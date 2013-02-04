package hu.nsmdmp.specialvectors;

import static hu.nsmdmp.numerics.matrix.operations.OperationFactory.selectOperation;
import hu.nsmdmp.numerics.matrix.Vector;
import hu.nsmdmp.numerics.matrix.operations.IOperations;

/**
 * f(z1,z2) = 1 if z1<=x and z2<=y, 0 otherwise .
 * 
 */
public class CumProbPoisson {

	public static <T> Vector<T> createCumProbPoisson(final T[][] sets, final T... limit) {
		IOperations<T> op = selectOperation(sets);

		int n = sets.length;
		Vector<T> C = new Vector<T>(n);

		for (int i = 0; i < n; i++) {
			T d = function(sets[i], limit, op);
			C.set(i, d);
		}

		return C;
	}

	private static <T> T function(final T[] set, final T[] limit, final IOperations<T> op) {
		int n = set.length;

		if (n != limit.length) {
			throw new IllegalArgumentException(String.format("A variaciok szama[%s] meg kell egyezen a limittek szamaval[%s]!", n, limit.length));
		}

		for (int i = 0; i < n; i++) {
			if (op.compareTo(set[i], limit[i]) > 0) {
				return op.zero();
			}
		}

		return op.one();
	}
}
