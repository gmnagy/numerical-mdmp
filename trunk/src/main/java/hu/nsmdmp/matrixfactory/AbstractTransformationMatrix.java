package hu.nsmdmp.matrixfactory;

import hu.nsmdmp.math.TotalOrder;
import hu.nsmdmp.matrix.Matrix;
import hu.nsmdmp.matrixfactory.cachedpolynomials.AbstractCachedPolynomials;
import hu.nsmdmp.utils.ApfloatUtils;

import java.util.List;

import org.apfloat.Apfloat;
import org.opensourcephysics.numerics.Polynomial;

public abstract class AbstractTransformationMatrix {

	private final AbstractCachedPolynomials cachedPolynomials;

	protected AbstractTransformationMatrix(AbstractCachedPolynomials cachedPolynomials) {
		this.cachedPolynomials = cachedPolynomials;
	}

	protected Matrix getTransformationMatrix(final int maxOrder, final int s) {
		List<int[]> exponentsList = TotalOrder.getOrders(maxOrder, s);

		Matrix M = new Matrix(exponentsList.size(), exponentsList.size());

		// rows
		int i = 0;
		for (int[] exponents : exponentsList) {

			int j = 0; // polynoms 
			for (int exp : exponents) {
				Polynomial polynomial = cachedPolynomials.getPolynomial(exp);

				// iterate columns
				int k = 0;
				for (int[] items : exponentsList) {
					Apfloat c = ApfloatUtils.valueOf(polynomial.coefficient(items[j]));

					Apfloat x = M.get(i, k);
					if (null == x) {
						x = ApfloatUtils.ONE;
					}

					x = x.multiply(c);
					M.set(i, k, x);

					k++;
				}

				j++;
			}

			i++;
		}

		return M;
	}
}
