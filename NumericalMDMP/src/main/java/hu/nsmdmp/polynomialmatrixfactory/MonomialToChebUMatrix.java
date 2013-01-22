package hu.nsmdmp.polynomialmatrixfactory;

import hu.nsmdmp.polynomialmatrixfactory.cachedpolynomials.ChebyshevUCachedPolynomials;

public class MonomialToChebUMatrix<T> extends AbstractTransformationMatrix<T> {

	private MonomialToChebUMatrix(final Class<T> type) {
		super(new ChebyshevUCachedPolynomials(), type);
	}
}
