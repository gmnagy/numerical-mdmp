package hu.nsmdmp.matrixfactory;

import hu.nsmdmp.ApfloatUtils;
import hu.nsmdmp.utils.Precision;

import java.util.HashMap;
import java.util.Map;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;
import org.opensourcephysics.numerics.Polynomial;
import org.opensourcephysics.numerics.specialfunctions.Chebyshev;

/**
 * The Chebyshev polynomials matrix of the second kind.
 * 
 * 
 */
class ChebyshevUMatrix extends AbstractPolynomialMatrix {

	/**
	 * Cached Chebyshev U polynomials.
	 * 
	 */
	private Map<Integer, Polynomial> polynomials = new HashMap<Integer, Polynomial>();

	/**
	 * Cached polynomial values of degree and variables.
	 * 
	 */
	private Map<Integer, Map<Apfloat, Apfloat>> solutions = new HashMap<Integer, Map<Apfloat, Apfloat>>();

	ChebyshevUMatrix() {
	}

	/**
	 * This method returns the nth polynomial value of type Chebyshev U. <br />
	 * The nth polynomial value is cached.
	 * 
	 * @param n
	 *            degree of the polynomial function
	 * @param x
	 *            value at which the polynomial is evaluated
	 * @return polynomial value.
	 */
	@Override
	protected Apfloat getPolynomialValue(final int n, final Apfloat x) {

		// solutions of n
		Map<Apfloat, Apfloat> solutionsN = solutions.get(n);
		if (null == solutionsN) {
			solutionsN = new HashMap<Apfloat, Apfloat>();
			solutions.put(n, solutionsN);
		}

		// solution of n and x
		Apfloat solution = solutionsN.get(x);
		if (null == solution) {
			solution = getValue(n, x);
			solutionsN.put(x, solution);
		}

		return solution;
	}

	/**
	 * @return returns the nth polynomial value of type Chebyshev U.
	 * 
	 */
	private Apfloat getValue(final int n, final Apfloat value) {
		Polynomial polynom = getPolynomial(n);

		Apfloat r = ApfloatUtils.ZERO;
		int i = 0;
		for (double coef : polynom.getCoefficients()) {
			Apfloat power = pow(value, i);
			Apfloat m = power.multiply(ApfloatUtils.valueOf(coef));
			r = r.add(m);

			i++;
		}

		return r.precision(Precision.SCALE);
	}

	/**
	 * @return cached nth polynomial value of type Chebyshev U.
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

	/**
	 * Integer power.
	 * 
	 * @param x
	 *            base of the power operator
	 * @param n
	 *            exponent of the power operator
	 * @return <tt>x</tt> to the <tt>n</tt>:th power
	 */
	private Apfloat pow(final Apfloat x, final int n) {
		if (n == 0 && x.signum() == 0) {
			return ApfloatUtils.ONE;
		}
		if (n != 0 && x.signum() == 0) {
			return ApfloatUtils.ZERO;
		}

		return ApfloatMath.pow(x, n).precision(Precision.SCALE);
	}

	@Override
	protected void clearMemory() {
		polynomials.clear();
		solutions.clear();
	}
}
