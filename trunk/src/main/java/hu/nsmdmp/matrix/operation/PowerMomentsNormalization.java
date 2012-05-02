package hu.nsmdmp.matrix.operation;

import hu.nsmdmp.math.Math;
import hu.nsmdmp.math.TotalOrder;
import hu.nsmdmp.utils.ApfloatUtils;
import hu.nsmdmp.utils.Converters;
import hu.nsmdmp.utils.Precision;
import hu.nsmdmp.vector.Vector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

public class PowerMomentsNormalization {

	public static Vector normailze(final Apfloat[][] vectorSet, final int maxOrder, final Vector V) {
		int s = vectorSet.length;

		// normalized vector
		Vector nV = new Vector(V.getColumnDimension());

		// α1,α2,..αs
		List<int[]> alphasList = TotalOrder.getOrders(maxOrder, s);

		Map<String, Apfloat> moments = orderMomentsByAlphas(V, alphasList);

		Apfloat[] aConstants = getAConstants(vectorSet);

		int i = 0;
		for (int[] alphas : alphasList) {
			Apfloat x = stepper(alphas, 0, new int[alphas.length], aConstants, moments);

			Apfloat bConstant = getBConstant(alphas, vectorSet);

			nV.set(i, bConstant.multiply(x).precision(Precision.SCALE));

			i++;
		}

		return nV;
	}

	/**
	 * ai = zi0 + zin
	 */
	private static Apfloat[] getAConstants(final Apfloat[][] vectorSet) {
		int s = vectorSet.length;

		Apfloat[] a = new Apfloat[s];

		for (int i = 0; i < s; i++) {
			a[i] = vectorSet[i][0].add(vectorSet[i][vectorSet[i].length - 1]);
			a[i] = a[i].negate();
		}

		return a;
	}

	/**
	 * 1 / ( (z1n - z10)^α1 * (z2n - z20)^α2 * ... * (zsn - zs0)^αs )
	 */
	private static Apfloat getBConstant(final int[] alphas, final Apfloat[][] vectorSet) {
		int s = vectorSet.length;

		Apfloat b = ApfloatUtils.ONE;

		for (int i = 0; i < s; i++) {
			Apfloat bi = vectorSet[i][vectorSet[i].length - 1].subtract(vectorSet[i][0]);
			bi = ApfloatMath.pow(bi, alphas[i]);

			b = b.multiply(bi);
		}

		return ApfloatUtils.ONE.divide(b);
	}

	private static Apfloat stepper(final int[] alphas, final int i, final int[] level, final Apfloat[] aConstants, final Map<String, Apfloat> moments) {
		Apfloat x = ApfloatUtils.ZERO;

		if (i == alphas.length) {

			x = ApfloatMath.pow(ApfloatUtils.TWO, sum(level));

			int j = 0;
			for (long exp : exps(alphas, level)) {
				x = x.multiply(0 == exp ? ApfloatUtils.ONE : ApfloatMath.pow(aConstants[j], exp));
				j++;
			}

			for (long binomial : binomials(alphas, level)) {
				x = x.multiply(ApfloatUtils.valueOf(binomial));
			}

			Apfloat moment = moments.get(Converters.arrayToString(level, ""));
			x = x.multiply(moment);

		} else {
			for (int k = 0; k <= alphas[i]; k++) {
				level[i] = k;
				x = x.add(stepper(alphas, i + 1, level, aConstants, moments));
			}
		}

		return x;
	}

	/**
	 * k1 + k2 + ... + ks
	 */
	private static int sum(final int[] k) {
		int sum = 0;

		for (int a : k) {
			sum += a;
		}

		return sum;
	}

	/**
	 * α1-k1, α2-k2, ... , αs-ks
	 */
	private static int[] exps(final int[] alphas, final int[] k) {
		int l = alphas.length;

		int[] exps = new int[l];

		for (int i = 0; i < l; i++) {
			exps[i] = alphas[i] - k[i];
		}

		return exps;
	}

	/**
	 * (α1 k1), (α2 k2), ... , (αs ks)
	 */
	private static long[] binomials(final int[] alphas, final int[] k) {
		int l = alphas.length;

		long[] binomials = new long[l];

		for (int i = 0; i < l; i++) {
			binomials[i] = Math.binomial(alphas[i], k[i]);
		}

		return binomials;
	}

	private static Map<String, Apfloat> orderMomentsByAlphas(final Vector V, List<int[]> alphasList) {
		if (V.getColumnDimension() != alphasList.size()) {
			throw new RuntimeException("Vector and alphasList dimensions must agree!");
		}

		Map<String, Apfloat> map = new HashMap<String, Apfloat>();

		int i = 0;
		for (int[] alphas : alphasList) {
			String tag = Converters.arrayToString(alphas, "");
			map.put(tag, V.get(i));
			i++;
		}

		return map;
	}
}
