package hu.nsmdmp.matrixfactory;

import hu.nsmdmp.ApfloatUtils;
import hu.nsmdmp.math.TotalOrder;
import hu.nsmdmp.math.Variation;
import hu.nsmdmp.matrix.Matrix;

import java.util.List;

import org.apfloat.Apfloat;

abstract class AbstractPolynomialMatrix {

	/**
	 * This method returns the nth polynomial value.
	 * 
	 * @param n
	 *            degree of the polynomial function
	 * @param x
	 *            value at which the polynomial is evaluated
	 * @return polynomial value.
	 */
	protected abstract Apfloat getPolynomialValue(final int n, final Apfloat x);

	protected abstract void clearMemory();

	/**
	 * Matrix keszitese a <tt>vectorSet</tt>-bol.
	 * 
	 * @param vectorSet
	 * @param maxOrder
	 */
	Matrix create(final Apfloat[][] vectorSet, final int maxOrder) {
		int s = vectorSet.length;

		List<int[]> exponents = TotalOrder.getOrders(maxOrder, s);
		int m = exponents.size();
		int n = Variation.getVariationsNumber(vectorSet);
		Matrix M = new Matrix(m, n);
		Apfloat[][] matrix = M.getArray();

		for (int j = 0; j < n; j++) {

			Apfloat[] variations = Variation.getVariation(j, vectorSet);

			for (int i = 0; i < m; i++) {
				matrix[i][j] = getMatrixElement(exponents.get(i), variations);
			}
		}

		return M;
	}

	/**
	 * Az <tt>exponents</tt> halmaz k.-ik tagjabol n-ed foku polinomot allitunk elo, majd a
	 * <tt>variation</tt> k.-ik tagjat a valtozo helyere behelyetesitjuk. A kapott ertekeket
	 * osszeszorozuk.
	 * 
	 * @param exponents
	 *            s darab kitevo
	 * @param variation
	 *            s darab valtozo
	 * @return
	 */
	private Apfloat getMatrixElement(final int[] exponents, final Apfloat[] variation) {
		int s = variation.length;
		Apfloat b = ApfloatUtils.ONE;

		for (int k = 0; k < s; k++) {
			b = b.multiply(getPolynomialValue(exponents[k], variation[k]));
		}

		return b;
	}
}
