package hu.nsmdmp.matrixfactory;

import hu.nsmdmp.matrix.Matrix;
import hu.nsmdmp.matrixfactory.cachedpolynomials.ChebyshevUCachedPolynomials;

public class MonomialToChebUMatrix extends AbstractTransformationMatrix {

	/**
	 * Monomial ChebyshevU transormation matrix.
	 * 
	 * @param maxOrder
	 *            a1 + a2 + ... <= maxOrder
	 * @param s
	 *            vectorset size
	 */
	public static Matrix getMatrix(final int maxOrder, final int s) {
		return new MonomialToChebUMatrix().getTransformationMatrix(maxOrder, s);
	}

	private MonomialToChebUMatrix() {
		super(new ChebyshevUCachedPolynomials());
	}
}
