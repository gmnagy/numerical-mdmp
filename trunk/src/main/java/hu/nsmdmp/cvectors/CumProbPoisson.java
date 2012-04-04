package hu.nsmdmp.cvectors;

import hu.nsmdmp.ApfloatUtils;

import org.apfloat.Apfloat;

/**
 * f(z1,z2) = 1 if z1<=x and z2<=y, 0 otherwise .
 * 
 */
public class CumProbPoisson extends AbstractCVector {

	private final Apfloat[] limit;

	CumProbPoisson(final Apfloat[] limit) {
		this.limit = limit;
	}

	@Override
	Apfloat function(final Apfloat[] variation) {
		int n = variation.length;

		if (n != limit.length) {
			throw new IllegalArgumentException(String.format("A variaciok szama[%s] meg kell egyezen a limittek szamaval[%s]!", n, limit.length));
		}

		for (int i = 0; i < n; i++) {
			if (variation[i].compareTo(limit[i]) > 0) {
				return ApfloatUtils.ZERO;
			}
		}

		return ApfloatUtils.ONE;
	}
}
