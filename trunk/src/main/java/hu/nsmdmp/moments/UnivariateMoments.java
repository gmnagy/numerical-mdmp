package hu.nsmdmp.moments;

import hu.nsmdmp.math.Math;
import hu.nsmdmp.utils.ApfloatUtils;
import hu.nsmdmp.utils.Precision;

import org.apfloat.Apfloat;
import org.junit.Test;

public class UnivariateMoments {

	@Test
	public void test() {
		System.out.println(Math.binomial(2, 0));
	}

	public static Apfloat[] createBinomialMoments(final Apfloat[] probabilities, final int n, final int m) {

		Apfloat[] binomialMoments = new Apfloat[m + 1];
		binomialMoments[0] = ApfloatUtils.ONE;

		int probInd = 0;
		for (int i = 1; i <= m; i++) {
			Apfloat ithBinomMom = ApfloatUtils.ZERO;

			for (int j = 1; j <= Math.binomial(n, i); j++) {
				ithBinomMom = ithBinomMom.add(probabilities[probInd]);
				probInd++;
			}

			binomialMoments[i] = ithBinomMom;
		}

		return binomialMoments;
	}

	public static Apfloat[] convertBinomMomToPowerMom(final Apfloat[] binomMoms) {
		Apfloat[] powerMoments = new Apfloat[binomMoms.length];
		powerMoments[0] = ApfloatUtils.ONE;
		powerMoments[1] = binomMoms[1];

		for (int i = 2; i < binomMoms.length; i++) {
			Apfloat ithPowerMom = binomMoms[i].multiply(ApfloatUtils.valueOf(Math.factorial(i)));

			for (int j = 1; j < i; j++) {
				Apfloat jthPowerMom = powerMoments[j].multiply(new Apfloat(-1 * Math.stirling(i, j), Precision.SCALE));
				ithPowerMom = ithPowerMom.add(jthPowerMom);
			}

			powerMoments[i] = ithPowerMom;
		}

		return powerMoments;
	}
}
