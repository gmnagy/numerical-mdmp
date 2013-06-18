package hu.nsmdmp2.numerics;

import static com.google.common.base.Preconditions.checkNotNull;

import java.math.BigDecimal;

import org.apfloat.Apfloat;
import org.apfloat.FixedPrecisionApfloatHelper;

public final class ApfloatOperation implements IOperation<Apfloat> {

	public static final long PRECISION = 100;
	private static final FixedPrecisionApfloatHelper FIXED_PRECISION = new FixedPrecisionApfloatHelper(PRECISION);

	@Override
	public Class<Apfloat> getType() {
		return Apfloat.class;
	}

	@Override
	public int signum(Apfloat value) {
		checkNotNull(value, "The value is NULL.");

		return value.signum();
	}

	@Override
	public Apfloat zero() {
		return Apfloat.ZERO;
	}

	@Override
	public Apfloat one() {
		return Apfloat.ONE;
	}

	@Override
	public Apfloat two() {
		return new Apfloat(2);
	}

	@Override
	public Apfloat add(Apfloat x, Apfloat y) {
		return FIXED_PRECISION.add(x, y);
	}

	@Override
	public Apfloat subtract(Apfloat x, Apfloat y) {
		return FIXED_PRECISION.subtract(x, y);
	}

	@Override
	public Apfloat multiply(Apfloat x, Apfloat y) {
		return FIXED_PRECISION.multiply(x, y);
	}

	@Override
	public Apfloat divide(Apfloat x, Apfloat y) {
		return FIXED_PRECISION.divide(x, y);
	}

	@Override
	public Apfloat pow(Apfloat x, int n) {
		return FIXED_PRECISION.pow(x, n);
	}

	@Override
	public Apfloat negate(Apfloat value) {
		return FIXED_PRECISION.negate(value);
	}

	@Override
	public Apfloat abs(Apfloat value) {
		return FIXED_PRECISION.abs(value);
	}

	@Override
	public Apfloat sqrt(Apfloat value) {
		return FIXED_PRECISION.sqrt(value);
	}

	@Override
	public int compareTo(Apfloat x, Apfloat y) {
		return x.compareTo(y);
	}

	@Override
	public Apfloat valueOf(double d) {
		return new Apfloat(d, PRECISION);
	}

	@Override
	public Apfloat valueOf(long d) {
		return new Apfloat(d, PRECISION);
	}

	@Override
	public Apfloat valueOf(BigDecimal d) {
		return new Apfloat(d, PRECISION);
	}

	@Override
	public double toDouble(Apfloat value) {
		return new Double(value.toString()).doubleValue();
	}
}
