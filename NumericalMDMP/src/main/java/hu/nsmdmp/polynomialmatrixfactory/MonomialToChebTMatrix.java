package hu.nsmdmp.polynomialmatrixfactory;

import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.polynomialmatrixfactory.cachedpolynomials.ChebyshevTCachedPolynomials;

public class MonomialToChebTMatrix<T> extends AbstractTransformationMatrix<T> {

	public static <T> Matrix<T> generateMonomialChebTTransformationMatrix(final int maxOrder, final int dim, final Class<T> type) {
		return new MonomialToChebTMatrix<T>(type).getTransformationMatrix(maxOrder, dim);
	}

	private MonomialToChebTMatrix(final Class<T> type) {
		super(new ChebyshevTCachedPolynomials(), type);
	}
}
