package hu.nsmdmp.matrixmath;

import hu.nsmdmp.utils.Precision;

import org.apfloat.Apfloat;

public final class MatrixMath {

	public static final Apfloat ZERO = new Apfloat(0, Precision.SCALE);

	public static final Apfloat ONE = new Apfloat(1, Precision.SCALE);

	public static final Apfloat TWO = new Apfloat(2, Precision.SCALE);

	public static void normalize(final Apfloat[][] matrix) {
		Normalization.normalize(matrix);
	}
}
