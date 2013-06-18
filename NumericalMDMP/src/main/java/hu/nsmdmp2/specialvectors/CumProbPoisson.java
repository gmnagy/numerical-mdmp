package hu.nsmdmp2.specialvectors;

import hu.nsmdmp2.numerics.Value;

/**
 * f(z1,z2) = 1 if z1<=x and z2<=y, 0 otherwise .
 * 
 */
public class CumProbPoisson {

	public static Value[] createCumProbPoisson(final Value[][] sets, final Value... limit) {

		int n = sets.length;
		Value[] C = new Value[n];

		for (int i = 0; i < n; i++) {
			Value d = function(sets[i], limit);
			C[i] = d;
		}

		return C;
	}

	private static Value function(final Value[] set, final Value[] limit) {

		Class<? extends Comparable<?>> valueType = set[0].getType();

		int n = set.length;

		if (n != limit.length) {
			throw new IllegalArgumentException(String.format("A variaciok szama[%s] meg kell egyezen a limittek szamaval[%s]!", n, limit.length));
		}

		for (int i = 0; i < n; i++) {
			if (set[i].compareTo(limit[i]) > 0) {
				return Value.zero(valueType);
			}
		}

		return Value.one(valueType);
	}
}
