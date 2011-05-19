package hu.nsmdmp.cvectors;

import hu.nsmdmp.matrixmath.MatrixMath;

import org.apfloat.Apfloat;

class StairsCVector extends AbstractCVector {

	@Override
	Apfloat function(final Apfloat[] variation) {
		int n = variation.length;

		for (int i = 0; i < n; i++) {
			if (variation[i].compareTo(MatrixMath.ZERO) != 0) {
				return MatrixMath.ONE;
			}
		}

		return MatrixMath.ZERO;
	}
}
