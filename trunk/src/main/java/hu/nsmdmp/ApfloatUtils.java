package hu.nsmdmp;

import hu.nsmdmp.utils.Precision;

import org.apfloat.Apfloat;

public class ApfloatUtils {

	public static final Apfloat ZERO = valueOf(0);

	public static final Apfloat ONE = valueOf(1);

	public static final Apfloat TWO = valueOf(2);

	public static Apfloat valueOf(final double value) {
		return new Apfloat(String.valueOf(value), Precision.SCALE);
	}

	public static Apfloat max(final Apfloat a, final Apfloat b) {
		if (a.compareTo(b) > 0) {
			return a;
		}

		return b;
	}
}
