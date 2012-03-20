package hu.nsmdmp.matrixfactory.cachedpolynomials;


import org.opensourcephysics.numerics.Polynomial;
import org.opensourcephysics.numerics.specialfunctions.Chebyshev;

public class ChebyshevTCachedPolynomials extends AbstractCachedPolynomials {

	@Override
	protected Polynomial get(final int n) {
		return Chebyshev.getPolynomialT(n);
	}
}
