package hu.nsmdmp.polynomialmatrixfactory.cachedpolynomials;

import java.util.HashMap;
import java.util.Map;

import org.opensourcephysics.numerics.Polynomial;

public abstract class AbstractCachedPolynomials {

	private Map<Integer, Polynomial> polynomials = new HashMap<Integer, Polynomial>();

	protected abstract Polynomial get(final int n);

	public Polynomial getPolynomial(final int n) {

		Polynomial p = polynomials.get(Integer.valueOf(n));

		if (null == p) {
			p = get(n);
			polynomials.put(Integer.valueOf(n), p);
		}

		return p;
	}
}
