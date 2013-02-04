package hu.nsmdmp.polynomialmatrixfactory;

import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.polynomialmatrixfactory.cachedpolynomials.ChebyshevUCachedPolynomials;

public class MonomialToChebUMatrix<T> extends AbstractTransformationMatrix<T> {

	private MonomialToChebUMatrix(final Class<T> type) {
		super(new ChebyshevUCachedPolynomials(), type);
	}

	public static <T> Matrix<T> generateMonomialChebUTransformationMatrix(final int maxOrder, final int dim, final Class<T> type) {
		return new MonomialToChebUMatrix<T>(type).getTransformationMatrix(maxOrder, dim);
	}
}
