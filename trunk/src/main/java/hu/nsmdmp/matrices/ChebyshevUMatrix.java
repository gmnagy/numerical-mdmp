package hu.nsmdmp.matrices;

import hu.nsmdmp.matrixmath.MatrixMath;
import hu.nsmdmp.utils.Precision;

import java.util.HashMap;
import java.util.Map;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;
import org.opensourcephysics.numerics.Polynomial;
import org.opensourcephysics.numerics.specialfunctions.Chebyshev;

class ChebyshevUMatrix extends NormalizedMatrix {

	Map<Integer, Polynomial> polynomials = new HashMap<Integer, Polynomial>();

	ChebyshevUMatrix() {
	}

	@Override
	protected Apfloat getPolynomialValue(final int n, final Apfloat value) {
		Polynomial p = getPolynomial(n);

		Apfloat r = MatrixMath.ZERO;

		int i = 0;
		for (double coef : p.getCoefficients()) {
			Apfloat m = ApfloatMath.pow(value, i).multiply(new Apfloat(coef));
			r = r.add(m);

			i++;
		}

		return r.precision(Precision.SCALE);
	}

	/**
	 * Cache-elem a mar lekert n-ed foku polinomot.
	 * 
	 */
	private Polynomial getPolynomial(final int n) {
		Polynomial p = polynomials.get(Integer.valueOf(n));
		if (null == p) {
			p = Chebyshev.getPolynomialU(n);
			polynomials.put(Integer.valueOf(n), p);
		}

		return p;
	}
}
