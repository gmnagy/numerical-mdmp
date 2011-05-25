package hu.nsmdmp.matrixmath;

import hu.nsmdmp.utils.Precision;

import org.apfloat.Apfloat;

public final class MatrixMath {

	public static final Apfloat ZERO = new Apfloat(0, Precision.SCALE);

	public static final Apfloat ONE = new Apfloat(1, Precision.SCALE);

	public static final Apfloat TWO = new Apfloat(2, Precision.SCALE);

	public static Apfloat[][] normalize(final Apfloat[][] matrix) {
		return Normalization.normalize(matrix);
	}

	public static Apfloat[] multiply(final Apfloat[][] matrix, final Apfloat[] vector) {
		return Multiplication.multiply(matrix, vector);
	}

	public static int getVariationsNumber(final Apfloat[][] vectorSet) {
		int n = 1;
		for (Apfloat[] row : vectorSet) {
			n *= row.length;
		}

		return n;
	}

	/**
	 * Get all variations of <tt>vectorSet</tt>.
	 * 
	 */
	public static Apfloat[][] createVariation(final Apfloat[][] vectorSet) {
		return Variation.createVariation(vectorSet);
	}

	/**
	 * Get <tt>j</tt>-th variation of <tt>vectorSet</tt>.
	 * 
	 */
	public static Apfloat[] createVariation(final int j, final Apfloat[][] vectorSet) {
		return Variation.getVariation(j, vectorSet);
	}
}
