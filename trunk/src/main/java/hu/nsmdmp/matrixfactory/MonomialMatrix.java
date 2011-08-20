package hu.nsmdmp.matrixfactory;

import hu.nsmdmp.ApfloatUtils;
import hu.nsmdmp.utils.Precision;

import java.util.HashMap;
import java.util.Map;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

class MonomialMatrix extends AbstractPolynomialMatrix {

	/**
	 * Cached monomial values of degree and variables.
	 * 
	 */
	private Map<Integer, Map<Apfloat, Apfloat>> solutions = new HashMap<Integer, Map<Apfloat, Apfloat>>();

	MonomialMatrix() {
		super();
	}

	/**
	 * This method returns the nth monomial value.
	 * 
	 * @param n
	 *            degree of the monomial function
	 * @param x
	 *            value at which the monomial is evaluated
	 * @return monomial value.
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
	 * @return returns the nth monomial value.
	 * 
	 */
	private Apfloat getValue(final int n, final Apfloat value) {
		if (n == 0 && value.signum() == 0) {
			return ApfloatUtils.ONE;
		}
		if (n != 0 && value.signum() == 0) {
			return ApfloatUtils.ZERO;
		}

		return ApfloatMath.pow(value, n).precision(Precision.SCALE);
	}

	@Override
	protected void clearMemory() {
		solutions.clear();
	}
}
