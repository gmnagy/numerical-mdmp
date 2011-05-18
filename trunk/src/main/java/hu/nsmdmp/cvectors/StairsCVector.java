package hu.nsmdmp.cvectors;

import hu.nsmdmp.matrixmath.MatrixMath;

import org.apfloat.Apfloat;

public class StairsCVector extends AbstractCVector {

	@Override
	Apfloat function(final Apfloat[] variation) {
		int n = variation.length;

//		Apfloat d = MatrixMath.ZERO;
		for (int i = 0; i < n; i++) {
			if (variation[i].compareTo(MatrixMath.ZERO) != 0) {
				return MatrixMath.ONE;
			}
//			d = d.add(variation[i]);
		}

//		if (d.compareTo(MatrixMath.ZERO) == 0) {
//			return MatrixMath.ZERO;
//		}

		return MatrixMath.ZERO;
	}
}
