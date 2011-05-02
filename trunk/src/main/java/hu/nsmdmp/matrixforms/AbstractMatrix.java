package hu.nsmdmp.matrixforms;

import hu.nsmdmp.utils.Exponents;
import hu.nsmdmp.utils.MatrixOperations;

import java.util.List;

import org.apfloat.Apfloat;

public abstract class AbstractMatrix {

	Apfloat[][] matrix;

	AbstractMatrix() {
	}

	public Apfloat[][] getMatrix() {
		return matrix;
	}

	/**
	 * Egy <tt>n</tt>-ed foku polinom erteke a <tt>value</tt> helyen.
	 * 
	 * @param n
	 *            polinom foka
	 * @param value
	 *            a valtozo erteke
	 * @return <tt>n</tt>-ed foku polinom erteke a <tt>value</tt> helyen
	 */
	protected abstract Apfloat getPolynomialValue(final int n, final Apfloat value);

	/**
	 * Matrix keszitese a <tt>vectorSet</tt>-bol.
	 * 
	 * @param vectorSet
	 * @param maxOrder
	 */
	void create(final Apfloat[][] vectorSet, final int maxOrder) {
		int s = vectorSet.length;

		List<int[]> exponents = Exponents.getExponents(maxOrder, s);
		int n = getNumber(vectorSet);

		matrix = new Apfloat[exponents.size()][n];

		for (int j = 0; j < n; j++) {

			Apfloat[] variation = getVariation(j, vectorSet);

			for (int i = 0; i < exponents.size(); i++) {
				matrix[i][j] = getMatrixElement(exponents.get(i), variation);
			}
		}
	}

	/**
	 * A <tt>vectorSet</tt> mereteinek szorzata.
	 * 
	 * @param vectorSet
	 *            vectorok halmaza
	 * @return <tt>vectorSet</tt> mereteinek szorzata.
	 */
	private int getNumber(final Apfloat[][] vectorSet) {
		int n = 1;
		for (Apfloat[] row : vectorSet) {
			n *= row.length;
		}

		return n;
	}

	/**
	 * A <tt>vectorSet</tt> halmaz <tt>j</tt>.-ik szamu ismetleses variacio tagjai.
	 * 
	 * @param j
	 *            <tt>j</tt>.-ik variacio
	 * @param vectorSet
	 *            vectorok halmaza
	 * @return <tt>j</tt>.-ik variacio tagjai.
	 */
	private Apfloat[] getVariation(final int j, final Apfloat[][] vectorSet) {
		int s = vectorSet.length;
		Apfloat[] variation = new Apfloat[s];

		int a = 1;
		for (int i = 0; i < s; i++) {
			int x = (j / a) % vectorSet[i].length;
			variation[i] = vectorSet[i][x];

			a *= vectorSet[i].length;
		}

		return variation;
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
		Apfloat b = MatrixOperations.ONE;

		for (int k = 0; k < s; k++) {
			int exp = exponents[k];

			b = b.multiply(getPolynomialValue(exp, variation[k]));

		}

		return b;
	}

	@Override
	public String toString() {
		return MatrixOperations.print(matrix);
	}
}
