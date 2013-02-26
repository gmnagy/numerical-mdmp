package hu.nsmdmp.tools;

import static hu.nsmdmp.operations.Operations.operation;
import static hu.nsmdmp.utils.Converters.arrayToString;
import hu.nsmdmp.operations.IOperation;

import java.util.Arrays;

public final class StirlingNumber<T> {

	public final T number;

	public final int[] exponents;

	private final IOperation<T> operations;

	public StirlingNumber(final T stirlingNumber, final int exponent) {
		this.number = stirlingNumber;
		this.exponents = new int[] { exponent };
		this.operations = operation(stirlingNumber.getClass());
	}

	public StirlingNumber(final T stirlingNumber, final int[] exponents) {
		this.number = stirlingNumber;
		this.exponents = exponents;
		this.operations = operation(stirlingNumber.getClass());
	}

	@Override
	public String toString() {
		return String.format("[%s, %s]", number, Arrays.toString(exponents));
	}

	public StirlingNumber<T> multiply(final StirlingNumber<T> sn) {
		int[] exp = new int[exponents.length + sn.exponents.length];

		int i = 0;
		for (int e : exponents) {
			exp[i] = e;
			i++;
		}
		for (int e : sn.exponents) {
			exp[i] = e;
			i++;
		}

		return new StirlingNumber<T>(operations.multiply(number, sn.number), exp);
	}

	public String getConcatenateExponents() {
		return arrayToString(exponents);
	}
}
