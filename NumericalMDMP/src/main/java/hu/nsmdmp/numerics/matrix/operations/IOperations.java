package hu.nsmdmp.numerics.matrix.operations;

import java.math.BigDecimal;

public interface IOperations<T> {

	Class<T> getType();

	T zero();

	T one();

	T two();

	int signum(T value);

	T add(T x, T y);

	T subtract(T x, T y);

	T multiply(T x, T y);

	T divide(T x, T y);

	T pow(T x, int n);

	T negate(T value);

	T abs(T value);

	T sqrt(T value);

	int compareTo(T x, T y);

	T valueOf(double d);

	T valueOf(long d);

	T valueOf(BigDecimal d);

	double toDouble(T value);
}
