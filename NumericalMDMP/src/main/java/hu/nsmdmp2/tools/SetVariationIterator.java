package hu.nsmdmp2.tools;

import static com.google.common.base.Preconditions.checkNotNull;
import hu.nsmdmp2.numerics.Value;

public final class SetVariationIterator {

	public final Value[][] set;
	public final int setLength;
	public final int numberOfVariation;

	private int index = 0;

	public static Value[][] generateSetVariation(final Value[][] set) {
		SetVariationIterator it = new SetVariationIterator(set);

		Value[][] result = new Value[it.numberOfVariation][0];
		int i = 0;
		while (it.hasNext()) {
			result[i] = it.next();
			i++;
		}

		return result;
	}

	public SetVariationIterator(final Value[][] set) {
		checkNotNull(set, "The multiarray set is NULL.");

		this.set = set;
		this.setLength = set.length;
		this.numberOfVariation = getNumberOfVariation(set);
	}

	public static int getNumberOfVariation(final Value[][] set) {
		int n = 1;
		for (Value[] t : set) {
			n *= t.length;
		}

		return n;
	}

	public boolean hasNext() {
		return index < numberOfVariation;
	}

	public Value[] next() {
		Value[] variation = new Value[setLength];

		int a = 1;
		int j = 0;
		for (Value[] vector : set) {
			int l = vector.length;
			int x = (index / a) % l;

			variation[j] = vector[x];
			a *= l;
			j++;
		}

		index++;

		return variation;
	}
}
