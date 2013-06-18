package hu.nsmdmp2.polynomialmatrixfactory;

import org.opensourcephysics.numerics.Polynomial;
import org.opensourcephysics.numerics.specialfunctions.Chebyshev;

class ChebyshevTCachedPolynomials extends AbstractCachedPolynomials {

	@Override
	protected Polynomial get(final int n) {
		return Chebyshev.getPolynomialT(n);
	}

}
