package hu.nsmdmp.polynomialmatrixfactory;

import static hu.nsmdmp.numerics.matrix.operations.OperationFactory.selectOperation;
import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.numerics.matrix.operations.IOperations;
import hu.nsmdmp.polynomialmatrixfactory.cachedpolynomials.ChebyshevTCachedPolynomials;

import java.util.HashMap;
import java.util.Map;

import org.opensourcephysics.numerics.Polynomial;

/**
 * The Chebyshev polynomials matrix of the first kind.
 * 
 * 
 */
public class ChebyshevTMatrix<T> extends AbstractPolynomialMatrix<T> {

	/**
	 * Cached Chebyshev T polynomials.
	 * 
	 */
	private ChebyshevTCachedPolynomials cachedPolynomials = new ChebyshevTCachedPolynomials();

	/**
	 * Cached polynomial values of degree and variables.
	 * 
	 */
	private Map<Integer, Map<T, T>> solutions = new HashMap<Integer, Map<T, T>>();

	public static Matrix<Double> generateDoubleChebyshevTMatrix(final Double[][] set, final int maxOrder) {
		return new ChebyshevTMatrix<Double>().create(set, maxOrder);
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
	 * @return returns the nth polynomial value of type Chebyshev T.
	 * 
	 */
	private T getChebyshevNth(final int n, final T value) {
		Polynomial polynom = cachedPolynomials.getPolynomial(n);

		IOperations<T> op = selectOperation(value);
		T r = op.zero();
		int i = 0;

		for (double coef : polynom.getCoefficients()) {
			T power = pow(value, i, op);
			T m = op.multiply(power, op.valueOf(coef));
			r = op.add(r, m);

			i++;
		}

		return r;
	}

	private T pow(final T value, final int n, final IOperations<T> op) {

		if (n == 0 && op.signum(value) == 0)
			return op.one();

		if (n != 0 && op.signum(value) == 0) {
			return op.zero();
		}

		return op.pow(value, n);
	}
}
