package hu.nsmdmp.polynomialmatrixfactory;

import hu.nsmdmp.numerics.matrix.Matrix;

import java.util.HashMap;
import java.util.Map;

import org.apfloat.Apfloat;

public class MonomialMatrix<T> extends AbstractPolynomialMatrix<T> {

	/**
	 * Cached monomial values of degree and variables.
	 * 
	 */
	private Map<Integer, Map<T, T>> solutions = new HashMap<Integer, Map<T, T>>();

	private MonomialMatrix(Class<T> valueType) {
		super(valueType);
	}

	public static Matrix<Double> generateDoubleMonomialMatrix(final Double[][] set, final int maxOrder) {
		return new MonomialMatrix<Double>(Double.class).create(set, maxOrder);
	}

	public static Matrix<Apfloat> generateApfloatMonomialMatrix(final Apfloat[][] set, final int maxOrder) {
		return new MonomialMatrix<Apfloat>(Apfloat.class).create(set, maxOrder);
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
	protected T getPolynomialValue(final int n, final T value) {

		// solutions of n
		Map<T, T> solutionsNth = solutions.get(n);
		if (null == solutionsNth) {
			solutionsNth = new HashMap<T, T>();
			solutions.put(n, solutionsNth);
		}

		// solution of n and x
		T solution = solutionsNth.get(value);
		if (null == solution) {
			solution = getMonomialNth(n, value);
			solutionsNth.put(value, solution);
		}

		return solution;
	}

	private T getMonomialNth(final int n, final T value) {

		if (n == 0 && op.signum(value) == 0)
			return op.one();

		if (n != 0 && op.signum(value) == 0) {
			return op.zero();
		}

		return op.pow(value, n);
	}

}
