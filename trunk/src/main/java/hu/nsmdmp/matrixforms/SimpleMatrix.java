package hu.nsmdmp.matrixforms;

import hu.nsmdmp.utils.MatrixOperations;
import hu.nsmdmp.utils.Precision;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

public class SimpleMatrix extends AbstractMatrix {

	SimpleMatrix() {
		super();
	}

	@Override
	protected Apfloat getPolynomialValue(final int n, final Apfloat value) {

		if (n == 0 && value.signum() == 0) {
			return MatrixOperations.ONE;
		}

		return ApfloatMath.pow(value, n).precision(Precision.SCALE);
	}
}
