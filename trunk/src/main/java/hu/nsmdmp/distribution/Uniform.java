package hu.nsmdmp.distribution;

import hu.nsmdmp.matrixmath.MatrixMath;

import org.apfloat.Apfloat;

public class Uniform extends AbstractDistribution {

	@Override
	Apfloat getDistributionItem(final Apfloat[] variation) {
		int n = variation.length;

		Apfloat d = MatrixMath.ZERO;
		for (int i = 0; i < n; i++) {
			d = d.add(variation[i]);
		}

		if (d.compareTo(MatrixMath.ZERO) == 0) {
			return MatrixMath.ZERO;
		}

		return MatrixMath.ONE;
	}
}
