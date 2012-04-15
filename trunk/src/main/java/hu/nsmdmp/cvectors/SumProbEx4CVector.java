package hu.nsmdmp.cvectors;

import hu.nsmdmp.utils.ApfloatUtils;

import org.apfloat.Apfloat;

public class SumProbEx4CVector extends AbstractCVector {

	/**
	 * f(z1,z2) = 0 if z1+z2<6, 1 otherwise .
	 */
	@Override
	Apfloat function(final Apfloat[] variation) {
		if (variation.length != 2) {
			throw new RuntimeException("SumProbEx4CVector: z1,z2 kell!");
		}

		Apfloat z1 = variation[0];
		Apfloat z2 = variation[1];

		if (z1.add(z2).doubleValue() < 6)
			return ApfloatUtils.ZERO;
		else
			return ApfloatUtils.ONE;
	}

}
