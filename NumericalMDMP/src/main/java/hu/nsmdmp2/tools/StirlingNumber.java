package hu.nsmdmp2.tools;

import hu.nsmdmp2.numerics.Value;
import hu.nsmdmp2.utils.Converters;

import java.util.Arrays;

public final class StirlingNumber<T> {

	public final Value number;
	public final int[] exponents;

	public StirlingNumber(final Value stirlingNumber, final int exponent) {
		this.number = stirlingNumber;
		this.exponents = new int[] { exponent };
	}

	public StirlingNumber(final Value stirlingNumber, final int[] exponents) {
		this.number = stirlingNumber;
		this.exponents = exponents;
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

		return new StirlingNumber<T>(number.multiply(sn.number), exp);
	}

	public String getConcatenateExponents() {
		return Converters.arrayToString(exponents);
	}
}
