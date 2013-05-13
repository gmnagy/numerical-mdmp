package hu.nsmdmp.operations;

import java.math.BigDecimal;

import org.apfloat.Apfloat;
import org.apfloat.Apint;
import org.apfloat.FixedPrecisionApfloatHelper;

public final class ApfloatOperation implements IOperation<Apfloat> {

	public static final long PRECISION = 100;

	private static final FixedPrecisionApfloatHelper FIXED_PRECISION = new FixedPrecisionApfloatHelper(PRECISION);

	@Override
	public Class<Apfloat> getType() {
		return Apfloat.class;
	}

	@Override
	public Apfloat zero() {
		return Apint.ZERO;
	}

	@Override
	public Apfloat one() {
		return Apint.ONE;
	}

	@Override
	public Apfloat two() {
		return new Apint(2);
	}

	@Override
	public int signum(final Apfloat value) {
		return value.signum();
	}

	@Override
	public Apfloat add(final Apfloat x, final Apfloat y) {
		return FIXED_PRECISION.add(x, y);
	}

	@Override
	public Apfloat subtract(final Apfloat x, final Apfloat y) {
		return FIXED_PRECISION.subtract(x, y);
	}

	@Override
	public Apfloat multiply(final Apfloat x, final Apfloat y) {
		return FIXED_PRECISION.multiply(x, y);
	}

	@Override
	public Apfloat divide(final Apfloat x, final Apfloat y) {
		return FIXED_PRECISION.divide(x, y);
	}

	@Override
	public Apfloat pow(final Apfloat x, final int n) {
		return FIXED_PRECISION.pow(x, n);
	}

	@Override
	public Apfloat negate(final Apfloat x) {
		return FIXED_PRECISION.negate(x);
	}

	@Override
	public Apfloat abs(final Apfloat x) {
		return FIXED_PRECISION.abs(x);
	}

	@Override
	public Apfloat sqrt(final Apfloat x) {
		return FIXED_PRECISION.sqrt(x);
	}

	@Override
	public int compareTo(final Apfloat x, final Apfloat y) {
		return x.compareTo(y);
	}

	@Override
	public Apfloat valueOf(final double d) {
		return new Apfloat(d, PRECISION);
	}

	@Override
	public Apfloat valueOf(final long l) {
		return new Apfloat(l, PRECISION);
	}

	@Override
	public Apfloat valueOf(final BigDecimal d) {
		return new Apfloat(d, PRECISION);
	}

	@Override
	public double toDouble(final Apfloat value) {
		return value.doubleValue();
	}
}
