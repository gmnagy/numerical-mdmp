package hu.nsmdmp.operations;

import static com.google.common.base.Preconditions.checkNotNull;

import java.math.BigDecimal;

public class DoubleOperation implements IOperation<Double> {

	@Override
	public Double zero() {
		return 0.0;
	}

	@Override
	public Double one() {
		return 1.0;
	}

	@Override
	public Double two() {
		return 2.0;
	}

	@Override
	public int signum(final Double value) {
		checkNotNull(value, "The value is NULL.");

		return (int) Math.signum(value);
	}

	@Override
	public Double add(final Double x, final Double y) {
		checkNotNull(x, "The x is NULL.");
		checkNotNull(y, "The y is NULL.");

		return x + y;
	}

	@Override
	public Double subtract(final Double x, final Double y) {
		checkNotNull(x, "The x is NULL.");
		checkNotNull(y, "The y is NULL.");

		return x - y;
	}

	@Override
	public Double multiply(final Double x, final Double y) {
		checkNotNull(x, "The x is NULL.");
		checkNotNull(y, "The y is NULL.");

		double r = x * y;
		if (r == 0)
			return 0.0;

		return r;
	}

	@Override
	public Double divide(final Double x, final Double y) {
		checkNotNull(x, "The x is NULL.");
		checkNotNull(y, "The y is NULL.");

		double r = x / y;
		if (r == 0)
			return 0.0;

		return r;
	}

	@Override
	public Double pow(final Double x, final int n) {
		checkNotNull(x, "The x is NULL.");

		return Math.pow(x, n);
	}

	@Override
	public int compareTo(final Double x, final Double y) {
		checkNotNull(x, "The x is NULL.");
		checkNotNull(y, "The y is NULL.");

		return x.compareTo(y);
	}

	@Override
	public Double valueOf(final double d) {
		return d;
	}

	@Override
	public double toDouble(final Double value) {
		checkNotNull(value, "The value is NULL.");

		return value.doubleValue();
	}

	@Override
	public Class<Double> getType() {
		return Double.class;
	}

	@Override
	public Double negate(final Double value) {
		checkNotNull(value, "The value is NULL.");

		return -value;
	}

	@Override
	public Double abs(Double value) {
		checkNotNull(value, "The value is NULL.");

		return Math.abs(value);
	}

	@Override
	public Double sqrt(final Double value) {
		checkNotNull(value, "The value is NULL.");

		return Math.sqrt(value);
	}

	@Override
	public Double valueOf(long d) {
		return Double.valueOf(d);
	}

	@Override
	public Double valueOf(BigDecimal d) {
		checkNotNull(d, "The d is NULL.");

		return d.doubleValue();
	}
}
