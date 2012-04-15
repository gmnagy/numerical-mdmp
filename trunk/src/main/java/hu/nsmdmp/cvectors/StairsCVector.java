package hu.nsmdmp.cvectors;

import hu.nsmdmp.utils.ApfloatUtils;

import org.apfloat.Apfloat;

class StairsCVector extends AbstractCVector {

	private final Apfloat[] limit;

	StairsCVector(final Apfloat[] limit) {
		this.limit = limit;
	}

	@Override
	Apfloat function(final Apfloat[] variation) {
		int n = variation.length;

		if (n != limit.length) {
			throw new IllegalArgumentException(String.format("A variaciok szama[%s] meg kell egyezen a limittek szamaval[%s]!", n, limit.length));
		}

		for (int i = 0; i < n; i++) {
			if (variation[i].compareTo(limit[i]) != 0) {
				return ApfloatUtils.ONE;
			}
		}

		return ApfloatUtils.ZERO;
	}
}
