package hu.nsmdmp2.polynomialmatrixfactory;

import hu.nsmdmp2.numerics.Value;

import java.util.Map;

import com.google.common.collect.Maps;

public class MonomialMatrix extends AbstractPolynomialMatrix {

	/**
	 * Cached monomial values of degree and variables.
	 * 
	 */
	private Map<Integer, Map<Value, Value>> solutions = Maps.newHashMap();

	public static Value[][] generateMonomialMatrix(final Value[][] set, final int maxOrder) {
		return new MonomialMatrix().create(set, maxOrder);
	}

	/**
	 * This method returns the nth monomial value.
	 * 
	 * @param n
	 *            degree of the monomial function
	 * @param value
	 *            value at which the monomial is evaluated
	 * @return monomial value.
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
			solution = getMonomialNth(n, value);
			solutionsNth.put(value, solution);
		}

		return solution;
	}

	private Value getMonomialNth(final int n, final Value value) {

		if (n == 0 && value.signum() == 0)
			return Value.one(value.getType());

		if (n != 0 && value.signum() == 0) {
			return Value.zero(value.getType());
		}

		return value.pow(n);
	}
}
