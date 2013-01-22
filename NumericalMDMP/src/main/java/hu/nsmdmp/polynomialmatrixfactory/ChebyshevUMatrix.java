package hu.nsmdmp.polynomialmatrixfactory;

import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.polynomialmatrixfactory.cachedpolynomials.ChebyshevUCachedPolynomials;

import java.util.HashMap;
import java.util.Map;

import org.opensourcephysics.numerics.Polynomial;

/**
 * The Chebyshev polynomials matrix of the second kind.
 * 
 * 
 */
public class ChebyshevUMatrix<T> extends AbstractPolynomialMatrix<T> {

	/**
	 * Cached Chebyshev U polynomials.
	 * 
	 */
	private ChebyshevUCachedPolynomials cachedPolynomials = new ChebyshevUCachedPolynomials();

	/**
	 * Cached polynomial values of degree and variables.
	 * 
	 */
	private Map<Integer, Map<T, T>> solutions = new HashMap<Integer, Map<T, T>>();

	public static Matrix<Double> generateDoubleChebyshevUMatrix(final Double[][] set, final int maxOrder) {

		ChebyshevUMatrix<Double> matrix = new ChebyshevUMatrix<Double>(Double.class);

		return matrix.create(set, maxOrder);
	}

	protected ChebyshevUMatrix(final Class<T> type) {
		super(type);
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
	protected T getPolynomialValue(final int n, final T x) {

		// solutions of n
		Map<T, T> solutionsNth = solutions.get(n);
		if (null == solutionsNth) {
			solutionsNth = new HashMap<T, T>();
			solutions.put(n, solutionsNth);
		}

		// solution of n and x
		T solution = solutionsNth.get(x);
		if (null == solution) {
			solution = getChebyshevNth(n, x);
			solutionsNth.put(x, solution);
		}

		return solution;
	}

	/**
	 * @return returns the nth polynomial value of type Chebyshev U.
	 * 
	 */
	private T getChebyshevNth(final int n, final T value) {
		Polynomial polynom = cachedPolynomials.getPolynomial(n);

		T r = operations.zero();
		int i = 0;

		for (double coef : polynom.getCoefficients()) {
			T power = pow(value, i);
			T m = operations.multiply(power, operations.valueOf(coef));
			r = operations.add(r, m);

			i++;
		}

		return r;
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
	private T pow(final T value, final int n) {

		if (n == 0 && operations.signum(value) == 0)
			return operations.one();

		if (n != 0 && operations.signum(value) == 0) {
			return operations.zero();
		}

		return operations.pow(value, n);
	}
}
