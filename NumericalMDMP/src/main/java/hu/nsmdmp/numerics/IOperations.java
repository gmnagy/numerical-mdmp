package hu.nsmdmp.numerics;

public interface IOperations<T> {

	T zero();

	T one();

	T two();

	int signum(T value);

	T add(T x, T y);

	T subtract(T x, T y);

	T multiply(T x, T y);

	T divide(T x, T y);

	T pow(T x, int n);

	int compareTo(T x, T y);

	T valueOf(double d);
}
