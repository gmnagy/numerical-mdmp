package hu.nsmdmp2.tools;

import static hu.nsmdmp.tools.Math.numberOfCombination;

public final class CombinationIterator {

	private int[] a;
	private int n;
	private int r;
	private long numLeft;
	private long total;

	public CombinationIterator(int n, int r) {
		if (r > n)
			throw new IllegalArgumentException(r + " > " + n);

		if (n < 1)
			throw new IllegalArgumentException(n + " < " + 1);

		this.n = n;
		this.r = r;
		this.a = new int[r];

		this.total = numberOfCombination(n, r);

		reset();
	}

	public void reset() {
		for (int i = 0; i < a.length; i++)
			a[i] = i;

		numLeft = total;
	}

	public long getNumLeft() {
		return numLeft;
	}

	public boolean hasNext() {
		return numLeft > 0;
	}

	public long getTotal() {
		return total;
	}

	public int[] next() {

		if (numLeft == total) {
			numLeft--;
			return a;
		}

		int i = r - 1;
		while (a[i] == n - r + i)
			i--;

		a[i] = a[i] + 1;
		for (int j = i + 1; j < r; j++)
			a[j] = a[i] + j - i;

		numLeft--;

		return a;
	}
}
