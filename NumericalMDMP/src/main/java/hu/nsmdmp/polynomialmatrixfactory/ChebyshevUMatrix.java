package hu.nsmdmp.polynomialmatrixfactory;

import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.polynomialmatrixfactory.cachedpolynomials.ChebyshevUCachedPolynomials;

import java.util.HashMap;
import java.util.Map;

import org.apfloat.Apfloat;
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

	protected ChebyshevUMatrix(final Class<T> valueType) {
		super(valueType);
	}

	public static Matrix<Double> generateDoubleChebyshevUMatrix(final Double[][] set, final int maxOrder) {
		return new ChebyshevUMatrix<Double>(Double.class).create(set, maxOrder);
	}

	public static Matrix<Apfloat> generateApfloatChebyshevUMatrix(final Apfloat[][] set, final int maxOrder) {
		return new ChebyshevUMatrix<Apfloat>(Apfloat.class).create(set, maxOrder);
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

		T r = op.zero();
		int i = 0;

		for (double coef : polynom.getCoefficients()) {
			T power = pow(value, i);
			T m = op.multiply(power, op.valueOf(coef));
			r = op.add(r, m);

			i++;
		}

		return r;
	}

	/**
	 * @return <tt>x</tt> to the <tt>n</tt>:th power
	 */
	private T pow(final T value, final int n) {

		if (n == 0 && op.signum(value) == 0)
			return op.one();

		if (n != 0 && op.signum(value) == 0) {
			return op.zero();
		}

		return op.pow(value, n);
	}
}
