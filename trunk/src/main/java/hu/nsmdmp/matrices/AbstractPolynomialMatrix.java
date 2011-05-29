package hu.nsmdmp.matrices;

import hu.nsmdmp.matrixmath.MatrixMath;
import hu.nsmdmp.utils.Exponents;

import java.util.List;

import org.apfloat.Apfloat;

abstract class AbstractPolynomialMatrix extends AbstractMatrix {

	AbstractPolynomialMatrix() {
	}

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
	void create(final Apfloat[][] vectorSet, final int maxOrder) {
		int s = vectorSet.length;

		List<int[]> exponents = Exponents.getExponents(maxOrder, s);
		m = exponents.size();
		n = MatrixMath.getVariationsNumber(vectorSet);

		matrix = new Apfloat[m][n];

		for (int j = 0; j < n; j++) {

			Apfloat[] variations = MatrixMath.createVariation(j, vectorSet);

			for (int i = 0; i < m; i++) {
				matrix[i][j] = getMatrixElement(exponents.get(i), variations);
			}
		}
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
		Apfloat b = MatrixMath.ONE;

		for (int k = 0; k < s; k++) {
			int exp = exponents[k];

			b = b.multiply(getPolynomialValue(exp, variation[k]));
		}

		return b;
	}
}
