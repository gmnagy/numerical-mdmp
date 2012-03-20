package hu.nsmdmp.matrixfactory.cachedpolynomials;

import java.util.HashMap;
import java.util.Map;

import org.opensourcephysics.numerics.Polynomial;

public abstract class AbstractCachedPolynomials {

	/**
	 * Cached Chebyshev T polynomials.
	 * 
	 */
	private Map<Integer, Polynomial> polynomials = new HashMap<Integer, Polynomial>();

	protected abstract Polynomial get(final int n);

	/**
	 * @return cached nth polynomial value of type Chebyshev T.
	 * 
	 */
	public Polynomial getPolynomial(final int n) {
		Polynomial p = polynomials.get(Integer.valueOf(n));
		if (null == p) {
			p = get(n);
			polynomials.put(Integer.valueOf(n), p);
		}

		return p;
	}
}
