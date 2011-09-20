package hu.nsmdmp.moments;

import hu.nsmdmp.ApfloatUtils;
import hu.nsmdmp.utils.Precision;

import org.apfloat.Apfloat;

public class UnivariateMoments {

	public static Apfloat[] createBinomialMoments(final Apfloat[] probabilities, final int n, final int m) {

		Apfloat[] binomialMoments = new Apfloat[m + 1];
		binomialMoments[0] = ApfloatUtils.ONE;

		int probInd = 0;
		for (int i = 1; i <= m; i++) {
			Apfloat ithBinomMom = ApfloatUtils.ZERO;

			for (int j = 1; j <= binomial(n, i); j++) {
				ithBinomMom = ithBinomMom.add(probabilities[probInd]);
				probInd++;
			}

			binomialMoments[i] = ithBinomMom;
		}

		return binomialMoments;
	}

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

	public static Apfloat[] convertBinomMomToPowerMom(final Apfloat[] binomMoms) {
		Apfloat[] powerMoments = new Apfloat[binomMoms.length];
		powerMoments[0] = ApfloatUtils.ONE;
		powerMoments[1] = binomMoms[1];

		for (int i = 2; i < binomMoms.length; i++) {
			Apfloat ithPowerMom = binomMoms[i].multiply(new Apfloat(factorial(i), Precision.SCALE));

			for (int j = 1; j < i; j++) {
				Apfloat jthPowerMom = powerMoments[j].multiply(new Apfloat(-1 * stirling(i, j), Precision.SCALE));
				ithPowerMom = ithPowerMom.add(jthPowerMom);
			}

			powerMoments[i] = ithPowerMom;
		}

		return powerMoments;
	}
}
