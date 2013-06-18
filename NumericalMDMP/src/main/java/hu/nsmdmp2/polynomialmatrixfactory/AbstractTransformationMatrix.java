package hu.nsmdmp2.polynomialmatrixfactory;

import static hu.nsmdmp2.tools.TotalOrderGenerator.generateTotalOrderOfMomentMembers;
import hu.nsmdmp2.numerics.Value;

import java.util.List;

import org.opensourcephysics.numerics.Polynomial;

abstract class AbstractTransformationMatrix {

	private final AbstractCachedPolynomials cachedPolynomials;

	protected AbstractTransformationMatrix(AbstractCachedPolynomials cachedPolynomials) {
		this.cachedPolynomials = cachedPolynomials;
	}

	protected Value[][] getTransformationMatrix(final int maxOrder, final int dim, final Class<? extends Comparable<?>> valueType) {
		List<int[]> exponentsList = generateTotalOrderOfMomentMembers(maxOrder, dim);

		Value[][] M = new Value[exponentsList.size()][exponentsList.size()];

		// rows
		int i = 0;
		for (int[] exponents : exponentsList) {

			int j = 0; // polynoms
			for (int exp : exponents) {
				Polynomial polynomial = cachedPolynomials.getPolynomial(exp);

				// iterate columns
				int k = 0;
				for (int[] items : exponentsList) {

					@SuppressWarnings("unchecked")
					Value c = Value.valueOf((Class<Comparable<?>>) valueType, polynomial.coefficient(items[j]));

					Value x = M[i][k];
					if (null == x) {
						x = Value.one(valueType);
					}

					x = x.multiply(c);
					M[i][k] = x;

					k++;
				}

				j++;
			}

			i++;
		}

		return M;
	}
}
