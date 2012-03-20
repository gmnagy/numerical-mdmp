package hu.nsmdmp.matrixfactory;

import hu.nsmdmp.matrix.Matrix;
import hu.nsmdmp.matrixfactory.cachedpolynomials.ChebyshevTCachedPolynomials;

public class MonomialToChebTMatrix extends AbstractTransformationMatrix {

	/**
	 * Monomial ChebyshevT transormation matrix.
	 * 
	 * @param maxOrder
	 *            a1 + a2 + ... <= maxOrder
	 * @param s
	 *            vectorset size
	 */
	public static Matrix getMatrix(final int maxOrder, final int s) {
		return new MonomialToChebTMatrix().getTransformationMatrix(maxOrder, s);
	}

	private MonomialToChebTMatrix() {
		super(new ChebyshevTCachedPolynomials());
	}
}
