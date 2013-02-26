package hu.nsmdmp.tools;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Array;
import java.util.List;

public final class SetVariationIterator2<T> {

	public final List<T[]> set;
	public final int setLength;
	public final int numberOfVariation;
	private final Class<T> type;

	private int index = 0;

	@SuppressWarnings("unchecked")
	public SetVariationIterator2(final List<T[]> set) {
		checkNotNull(set, "The multiarray set is NULL.");

		this.set = set;
		this.setLength = set.size();
		this.numberOfVariation = getNumberOfVariation(set);
		this.type = (Class<T>) set.get(0)[0].getClass();
	}

	private int getNumberOfVariation(final List<T[]> set) {
		int n = 1;
		for (T[] t : set) {
			n *= t.length;
		}

		return n;
	}

	public boolean hasNext() {
		return index < numberOfVariation;
	}

	@SuppressWarnings("unchecked")
	public T[] next() {
		T[] variation = (T[]) Array.newInstance(type, setLength);

		int a = 1;
		int j = 0;
		for (T[] vector : set) {
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
