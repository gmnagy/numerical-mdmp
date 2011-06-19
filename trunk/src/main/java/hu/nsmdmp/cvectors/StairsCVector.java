package hu.nsmdmp.cvectors;

import hu.nsmdmp.ApfloatUtils;

import org.apfloat.Apfloat;

class StairsCVector extends AbstractCVector {

	@Override
	Apfloat function(final Apfloat[] variation) {
		int n = variation.length;

		for (int i = 0; i < n; i++) {
			if (variation[i].compareTo(ApfloatUtils.ZERO) != 0) {
				return ApfloatUtils.ONE;
			}
		}

		return ApfloatUtils.ZERO;
	}
}
