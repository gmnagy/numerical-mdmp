package hu.nsmdmp2.polynomialmatrixfactory;

import hu.nsmdmp2.numerics.Value;

public class MonomialToChebUMatrix extends AbstractTransformationMatrix {

	public static Value[][] generateMonomialChebUTransformationMatrix(final int maxOrder, final int dim, final Class<? extends Comparable<?>> valueType) {
		return new MonomialToChebUMatrix().getTransformationMatrix(maxOrder, dim, valueType);
	}

	private MonomialToChebUMatrix() {
		super(new ChebyshevUCachedPolynomials());
	}

}
