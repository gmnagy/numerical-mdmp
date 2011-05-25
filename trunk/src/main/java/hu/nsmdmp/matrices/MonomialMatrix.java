package hu.nsmdmp.matrices;

import hu.nsmdmp.matrixmath.MatrixMath;
import hu.nsmdmp.utils.Precision;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

class MonomialMatrix extends AbstractMatrix {

	MonomialMatrix() {
		super();
	}

	@Override
	protected Apfloat getPolynomialValue(final int n, final Apfloat value) {

		if (n == 0 && value.signum() == 0) {
			return MatrixMath.ONE;
		}
		if (n != 0 && value.signum() == 0) {
			return MatrixMath.ZERO;
		}

		return ApfloatMath.pow(value, n).precision(Precision.SCALE);
	}
}
