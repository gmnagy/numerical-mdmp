package hu.nsmdmp.matrixforms;

import hu.nsmdmp.utils.MatrixOperations;
import hu.nsmdmp.utils.Precision;

import java.util.HashMap;
import java.util.Map;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;
import org.opensourcephysics.numerics.Polynomial;
import org.opensourcephysics.numerics.specialfunctions.Chebyshev;

public class ChebyshevUMatrix extends NormalizedMatrix {

	Map<Integer, Polynomial> polynomials = new HashMap<Integer, Polynomial>();

	ChebyshevUMatrix() {
	}

	@Override
	protected Apfloat getPolynomialValue(final int n, final Apfloat value) {

		Polynomial p = polynomials.get(Integer.valueOf(n));
		if (null == p) {
			p = Chebyshev.getPolynomialU(n);
			polynomials.put(Integer.valueOf(n), p);
		}

		Apfloat r = MatrixOperations.ZERO;

		int i = 0;
		for (double coef : p.getCoefficients()) {
			Apfloat m = ApfloatMath.pow(value, i).multiply(new Apfloat(coef));
			r = r.add(m);

			i++;
		}

		return r.precision(Precision.SCALE);
	}
}
