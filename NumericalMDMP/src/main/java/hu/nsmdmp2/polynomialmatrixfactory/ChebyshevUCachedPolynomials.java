package hu.nsmdmp2.polynomialmatrixfactory;

import org.opensourcephysics.numerics.Polynomial;
import org.opensourcephysics.numerics.specialfunctions.Chebyshev;

class ChebyshevUCachedPolynomials extends AbstractCachedPolynomials {

	@Override
	protected Polynomial get(final int n) {
		return Chebyshev.getPolynomialU(n);
	}

}
