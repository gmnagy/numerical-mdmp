package hu.nsmdmp2.polynomialmatrixfactory;

import hu.nsmdmp2.numerics.Value;

import java.util.Map;

import org.opensourcephysics.numerics.Polynomial;

import com.google.common.collect.Maps;

public class ChebyshevTMatrix extends AbstractPolynomialMatrix {

	/**
	 * Cached Chebyshev T polynomials.
	 * 
	 */
	private ChebyshevTCachedPolynomials cachedPolynomials = new ChebyshevTCachedPolynomials();

	/**
	 * Cached polynomial values of degree and variables.
	 * 
	 */
	private Map<Integer, Map<Value, Value>> solutions = Maps.newHashMap();

	public static Value[][] generateChebyshevTMatrix(final Value[][] set, final int maxOrder) {
		return new ChebyshevTMatrix().create(set, maxOrder);
	}

	/**
	 * This method returns the nth polynomial value of type Chebyshev T. <br />
	 * The nth polynomial value is cached.
	 * 
	 * @param n
	 *            degree of the polynomial function
	 * @param x
	 *            value at which the polynomial is evaluated
	 * @return polynomial value.
	 */
	@Override
	protected Value getPolynomialValue(int n, Value value) {

		// solutions of n
		Map<Value, Value> solutionsNth = solutions.get(n);
		if (null == solutionsNth) {
			solutionsNth = Maps.newHashMap();
			solutions.put(n, solutionsNth);
		}

		// solution of n and x
		Value solution = solutionsNth.get(value);
		if (null == solution) {
			solution = getChebyshevNth(n, value);
			solutionsNth.put(value, solution);
		}

		return solution;
	}

	/**
	 * @return returns the nth polynomial value of type Chebyshev T.
	 * 
	 */
	private Value getChebyshevNth(final int n, final Value value) {
		Polynomial polynom = cachedPolynomials.getPolynomial(n);

		Value r = Value.zero(value.getType());
		int i = 0;

		for (double coef : polynom.getCoefficients()) {
			Value power = pow(value, i);
			Value m = power.multiply(coef);
			r = r.add(m);

			i++;
		}

		return r;
	}

	private Value pow(final Value value, final int n) {

		if (n == 0 && value.signum() == 0)
			return Value.one(value.getType());

		if (n != 0 && value.signum() == 0) {
			return Value.zero(value.getType());
		}

		return value.pow(n);
	}
}
