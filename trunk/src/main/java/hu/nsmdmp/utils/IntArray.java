package hu.nsmdmp.utils;

import java.util.Arrays;

public class IntArray {

	public final int[] array;

	public IntArray(final int[] array) {
		this.array = Arrays.copyOf(array, array.length);
	}

	public IntArray(final int size) {
		this.array = new int[size];
	}

	public void set(final int index, final int value) {
		this.array[index] = value;
	}

	@Override
	public boolean equals(final Object obj) {

		if (!(obj instanceof IntArray))
			return false;

		IntArray a = (IntArray) obj;

		return Arrays.equals(array, a.array);
	}

	@Override
	public String toString() {
		return Arrays.toString(array);
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(array);
	}
}
