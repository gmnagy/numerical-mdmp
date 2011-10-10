package hu.nsmdmp;

import hu.nsmdmp.utils.Precision;

import org.apfloat.Apfloat;

public class ApfloatUtils {

	public static final Apfloat ZERO = new Apfloat(0, Precision.SCALE);

	public static final Apfloat ONE = new Apfloat(1, Precision.SCALE);

	public static final Apfloat TWO = new Apfloat(2, Precision.SCALE);

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
