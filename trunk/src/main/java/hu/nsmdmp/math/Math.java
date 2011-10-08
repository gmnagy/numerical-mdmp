package hu.nsmdmp.math;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class Math {

	/**
	 * Binomial coefficient.
	 * 
	 */
	public static long binomial(final int n, final int k) {
		if (0 > k || k > n) {
			throw new RuntimeException("Binomial: 0 <= k and k <= n required, but n was " + n + " and k was " + k);
		}

		long coeff = 1;

		for (int i = n - k + 1; i <= n; i++) {
			coeff *= i;
		}

		for (int i = 1; i <= k; i++) {
			coeff /= i;
		}

		return coeff;
	}

	/**
	 * nth factorial.
	 * 
	 */
	public static long factorial(final int n) {
		if (n <= 1) {
			return 1;
		}

		return n * factorial(n - 1);
	}

	/**
	 * Stirling number of the first kind.
	 * 
	 * @param n
	 *            nth falling factorial (3th: x(x-1)(x-2) = x^3 - 3x^2 + 2x)
	 * @param k
	 *            power
	 */
	public static long stirling(final int n, final int k) {

		if ((n == 0) && (k == 0)) {
			return 1;
		}

		else if ((n >= 1) && (k > 0)) {
			return stirling(n - 1, k - 1) - (n - 1) * stirling(n - 1, k);
		}

		else if (n > 0) {
			return 0;
		}

		return 0;
	}

	/**
	 * The number of k-combinations from a given set.
	 * 
	 */
	public static <T extends Comparable<? super T>> List<List<T>> combinations(final Collection<T> elements, final int k) {
		List<List<T>> result = new ArrayList<List<T>>();

		if (k == 0) {
			result.add(new ArrayList<T>());

			return result;
		}

		List<List<T>> combinations = combinations(elements, k - 1);
		for (List<T> combination : combinations) {
			for (T element : elements) {
				if (combination.contains(element)) {
					continue;
				}

				List<T> list = new ArrayList<T>();
				list.addAll(combination);

				if (list.contains(element)) {
					continue;
				}

				list.add(element);
				Collections.sort(list);

				if (result.contains(list)) {
					continue;
				}

				result.add(list);
			}
		}

		return result;
	}

}
