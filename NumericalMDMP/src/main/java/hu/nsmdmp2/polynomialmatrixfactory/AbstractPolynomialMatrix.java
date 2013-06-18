package hu.nsmdmp2.polynomialmatrixfactory;

import static hu.nsmdmp2.tools.TotalOrderGenerator.generateTotalOrderOfMomentMembers;
import hu.nsmdmp2.numerics.Value;
import hu.nsmdmp2.tools.SetVariationIterator;

import java.util.List;

abstract class AbstractPolynomialMatrix {

	Value[][] create(final Value[][] set, final int maxOrder) {

		Class<? extends Comparable<?>> valueType = set[0][0].getType();

		int size = set.length;
		SetVariationIterator itV = new SetVariationIterator(set);
		List<int[]> exponents = generateTotalOrderOfMomentMembers(maxOrder, size);

		int row = exponents.size();
		int column = itV.numberOfVariation;
		Value[][] M = new Value[row][column];

		int j = 0;
		while (itV.hasNext()) {
			Value[] variation = itV.next();

			for (int i = 0; i < row; i++)
				M[i][j] = getMatrixElement(exponents.get(i), variation, valueType);

			j++;
		}

		return M;
	}

	/**
	 * Az <tt>exponents</tt> halmaz k.-ik tagjabol n-ed foku polinomot allitunk
	 * elo, majd a <tt>variation</tt> k.-ik tagjat a valtozo helyere
	 * behelyetesitjuk. A kapott ertekeket osszeszorozuk.
	 * 
	 */
	private Value getMatrixElement(final int[] exponents, final Value[] variation, Class<? extends Comparable<?>> valueType) {

		int s = variation.length;
		Value element = Value.one(valueType);

		for (int k = 0; k < s; k++) {
			element = element.multiply(getPolynomialValue(exponents[k], variation[k]));
		}

		return element;
	}

	protected abstract Value getPolynomialValue(final int n, final Value value);

}
