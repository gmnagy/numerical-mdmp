package hu.nsmdmp2.polynomialmatrixfactory;

import java.util.Map;

import org.opensourcephysics.numerics.Polynomial;

import com.google.common.collect.Maps;

abstract class AbstractCachedPolynomials {

	private Map<Integer, Polynomial> polynomials = Maps.newHashMap();

	protected abstract Polynomial get(final int n);

	Polynomial getPolynomial(final int n) {

		Polynomial p = polynomials.get(Integer.valueOf(n));

		if (null == p) {
			p = get(n);
			polynomials.put(Integer.valueOf(n), p);
		}

		return p;
	}
}
