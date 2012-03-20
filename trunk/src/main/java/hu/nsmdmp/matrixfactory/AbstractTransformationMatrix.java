package hu.nsmdmp.matrixfactory;

import hu.nsmdmp.ApfloatUtils;
import hu.nsmdmp.math.TotalOrder;
import hu.nsmdmp.matrix.Matrix;
import hu.nsmdmp.matrixfactory.cachedpolynomials.AbstractCachedPolynomials;

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

		Matrix M = new Matrix(exponentsList.size(), maxOrder + 1, ApfloatUtils.ZERO);

		// rows
		int i = 0;
		for (int[] exponents : exponentsList) {

			for (int exp : exponents) {
				Polynomial polynomial = cachedPolynomials.getPolynomial(exp);

				// columns
				double[] coefs = polynomial.getCoefficients();
				for (int j = 0; j < coefs.length; j++) {
					Apfloat c = ApfloatUtils.valueOf(coefs[j]);
					if (c.compareTo(ApfloatUtils.ZERO) == 0) {
						continue;
					}

					Apfloat x = M.get(i, j);
					if (x.compareTo(ApfloatUtils.ZERO) == 0) {
						x = ApfloatUtils.ONE;
					}

					Apfloat newValue = x.multiply(c);
					M.set(i, j, newValue);
				}
			}

			i++;
		}

		return M;
	}
}
