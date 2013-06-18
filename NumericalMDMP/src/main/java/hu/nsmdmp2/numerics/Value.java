package hu.nsmdmp2.numerics;

import static hu.nsmdmp2.numerics.Operations.operation;

import java.math.BigDecimal;

public class Value {

	private final Class<Comparable<?>> type;
	private final Comparable<?> value;
	private final IOperation<Comparable<?>> op;

	@SuppressWarnings("unchecked")
	public Value(Comparable<?> value) {
		this.value = value;
		this.type = (Class<Comparable<?>>) value.getClass();
		this.op = Operations.operation(type);
	}

	// -------------------------------------------------------------------------------------------------------------------------

	// public static Value valueOf(Comparable<?> value) {
	// return new Value(value);
	// }

	public static Value valueOf(Class<Comparable<?>> type, double value) {
		IOperation<Comparable<?>> op = Operations.operation(type);
		return new Value(op.valueOf(value));
	}

	public static Value valueOf(Class<Comparable<?>> type, long value) {
		IOperation<Comparable<?>> op = Operations.operation(type);
		return new Value(op.valueOf(value));
	}

	public static Value valueOf(Class<Comparable<?>> type, BigDecimal value) {
		IOperation<Comparable<?>> op = Operations.operation(type);
		return new Value(op.valueOf(value));
	}

	public static Value zero(Class<? extends Comparable<?>> type) {
		return new Value(operation(type).zero());
	}

	public static Value one(Class<? extends Comparable<?>> type) {
		return new Value(operation(type).one());
	}

	public static Value two(Class<? extends Comparable<?>> type) {
		return new Value(operation(type).two());
	}

	// -------------------------------------------------------------------------------------------------------------------------

	public Class<Comparable<?>> getType() {
		return type;
	}

	public int signum() {
		return op.signum(value);
	}

	public Value add(Value v) {
		return new Value(op.add(value, v.value));
	}

	public Value subtract(Value v) {
		return new Value(op.subtract(value, v.value));
	}

	public Value multiply(Value v) {
		return new Value(op.multiply(value, v.value));
	}

	public Value multiply(double v) {
		return new Value(op.multiply(value, op.valueOf(v)));
	}

	public Value divide(Value v) {
		return new Value(op.divide(value, v.value));
	}

	public Value divide(double v) {
		return new Value(op.divide(value, op.valueOf(v)));
	}

	public Value inverse() {
		return new Value(op.divide(op.valueOf(1), value));
	}

	public Value pow(int n) {
		return new Value(op.pow(value, n));
	}

	public Value negate() {
		return new Value(op.negate(value));
	}

	public Value abs() {
		return new Value(op.abs(value));
	}

	public Value sqrt() {
		return new Value(op.sqrt(value));
	}

	public int compareTo(Value v) {
		return op.compareTo(value, v.value);
	}

	public double toDouble() {
		return op.toDouble(value);
	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Value))
			return false;

		Value v = (Value) obj;

		return value.equals(v.value);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public String toString() {
		return value.toString();
	}

}
