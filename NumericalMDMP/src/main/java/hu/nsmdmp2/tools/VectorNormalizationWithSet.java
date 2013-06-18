package hu.nsmdmp2.tools;

import static hu.nsmdmp2.utils.Converters.arrayToString;
import hu.nsmdmp2.numerics.Value;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class VectorNormalizationWithSet {

	public static Value[] normailzeByGergo(final Value[][] vectorSet, final int maxOrder, final Value[] V) {
		int dim = vectorSet.length;

		Class<Comparable<?>> valueType = V[0].getType();

		// normalized vector
		Value[] nV = new Value[V.length];

		// α1,α2,..αs
		List<int[]> alphasList = TotalOrderGenerator.generateTotalOrderOfMomentMembers(maxOrder, dim);

		Map<String, Value> moments = orderMomentsByAlphas(V, alphasList);

		Value[] aConstants = getAConstants(vectorSet);

		int i = 0;
		for (int[] alphas : alphasList) {
			Value x = stepper(alphas, 0, new int[alphas.length], aConstants, moments, valueType);
			Value bConstant = getBConstant(alphas, vectorSet, valueType);

			nV[i] = bConstant.multiply(x);

			i++;
		}

		return nV;
	}

	/**
	 * ai = zi0 + zin
	 */
	private static Value[] getAConstants(final Value[][] vectorSet) {
		int s = vectorSet.length;

		Value[] a = new Value[s];

		for (int i = 0; i < s; i++) {
			a[i] = vectorSet[i][0].add(vectorSet[i][vectorSet[i].length - 1]);
			a[i] = a[i].negate();
		}

		return a;
	}

	/**
	 * 1 / ( (z1n - z10)^α1 * (z2n - z20)^α2 * ... * (zsn - zs0)^αs )
	 */
	private static Value getBConstant(final int[] alphas, final Value[][] vectorSet, final Class<Comparable<?>> valueType) {
		int s = vectorSet.length;

		Value b = Value.one(valueType);

		for (int i = 0; i < s; i++) {
			Value bi = vectorSet[i][vectorSet[i].length - 1].subtract(vectorSet[i][0]);
			bi = bi.pow(alphas[i]);

			b = b.multiply(bi);
		}

		return b.inverse();
	}

	private static Value stepper(final int[] alphas, final int i, final int[] level, final Value[] aConstants, final Map<String, Value> moments,
			final Class<Comparable<?>> valueType) {

		Value x = Value.zero(valueType);

		if (i == alphas.length) {

			x = Value.two(valueType).pow(sum(level));

			int j = 0;
			for (long exp : exps(alphas, level)) {
				x = x.multiply(0 == exp ? Value.one(valueType) : aConstants[j].pow((int) exp));
				j++;
			}

			for (long binomial : binomials(alphas, level)) {
				x = x.multiply(Value.valueOf(valueType, binomial));
			}

			Value moment = moments.get(arrayToString(level));
			x = x.multiply(moment);

		} else {
			for (int k = 0; k <= alphas[i]; k++) {
				level[i] = k;
				x = x.add(stepper(alphas, i + 1, level, aConstants, moments, valueType));
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

	private static Map<String, Value> orderMomentsByAlphas(final Value[] V, List<int[]> alphasList) {
		if (V.length != alphasList.size()) {
			throw new RuntimeException("Vector and alphasList dimensions must agree!");
		}

		Map<String, Value> map = Maps.newHashMap();

		int i = 0;
		for (int[] alphas : alphasList) {
			String tag = arrayToString(alphas);
			map.put(tag, V[i]);
			i++;
		}

		return map;
	}
}
