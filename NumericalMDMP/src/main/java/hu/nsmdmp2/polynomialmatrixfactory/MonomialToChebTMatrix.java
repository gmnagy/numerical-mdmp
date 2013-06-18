package hu.nsmdmp2.polynomialmatrixfactory;

import hu.nsmdmp2.numerics.Value;

public class MonomialToChebTMatrix extends AbstractTransformationMatrix {

	public static Value[][] generateMonomialChebTTransformationMatrix(final int maxOrder, final int dim, final Class<? extends Comparable<?>> valueType) {
		return new MonomialToChebTMatrix().getTransformationMatrix(maxOrder, dim, valueType);
	}

	private MonomialToChebTMatrix() {
		super(new ChebyshevTCachedPolynomials());
	}

}
