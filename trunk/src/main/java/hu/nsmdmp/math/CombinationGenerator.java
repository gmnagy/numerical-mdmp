package hu.nsmdmp.math;



public class CombinationGenerator {

	private int[] a;
	private int n;
	private int r;
	private long numLeft;
	private long total;

	public CombinationGenerator(int n, int r) {
		if (r > n) {
			throw new IllegalArgumentException();
		}
		if (n < 1) {
			throw new IllegalArgumentException();
		}
		this.n = n;
		this.r = r;
		this.a = new int[r];

		long nFact = Math.factorial(n);
		long rFact = Math.factorial(r);
		long nminusrFact = Math.factorial(n - r);
		this.total = nFact / (rFact * nminusrFact);

		reset();
	}

	public void reset() {
		for (int i = 0; i < a.length; i++) {
			a[i] = i;
		}
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
		while (a[i] == n - r + i) {
			i--;
		}
		a[i] = a[i] + 1;
		for (int j = i + 1; j < r; j++) {
			a[j] = a[i] + j - i;
		}

		numLeft--;

		return a;
	}

	public String nextString() {
		StringBuilder sb = new StringBuilder();

		for (int i : next()) {
			sb.append((i + 1));
		}

		return sb.toString();
	}
}
