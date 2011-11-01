package hu.nsmdmp.moments;

import hu.nsmdmp.utils.Converters;

import org.apfloat.Apfloat;

class StirlingNumber {

	final Apfloat number;

	final int[] exponents;

	StirlingNumber(final Apfloat stirlingNumber, final int exponent) {
		this.number = stirlingNumber;
		this.exponents = new int[] { exponent };
	}

	StirlingNumber(final Apfloat stirlingNumber, final int[] exponents) {
		this.number = stirlingNumber;
		this.exponents = exponents;
	}

	@Override
	public String toString() {
		return String.format("[%s, %s]", number, Converters.arrayToString(exponents, ","));
	}

	StirlingNumber multiply(final StirlingNumber sn) {
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

		return new StirlingNumber(number.multiply(sn.number), exp);
	}

	String getConcatenateExponents() {
		return Converters.arrayToString(exponents, "");
	}
}
