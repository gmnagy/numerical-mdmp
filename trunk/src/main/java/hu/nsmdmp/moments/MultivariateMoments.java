package hu.nsmdmp.moments;

import hu.nsmdmp.ApfloatUtils;
import hu.nsmdmp.math.CombinationGenerator;
import hu.nsmdmp.math.TotalOrder;
import hu.nsmdmp.math.Variation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apfloat.Apfloat;

public class MultivariateMoments {

	/**
	 * @param probabilities
	 * @param n
	 *            number of events
	 * @param m
	 *            total order of the moment
	 * @param s
	 *            dimensional
	 * @return {a(1), ..., a(s)}-order cross-binomial moments
	 */
	public static Apfloat[] createBinomialMoments(final Apfloat[] probabilities, final int n, final int m, final int s) {
		// provide: n(1) + ... + n(2) = n
		int nj = n / s;

		Map<String, Apfloat> probMap = tagProbabilities(probabilities, n, m);

		// {a(1), ..., a(s)}, where a(1) + ... + a(s) <= m 
		List<int[]> alphasList = TotalOrder.getOrders(m, s);
		Iterator<int[]> it = alphasList.iterator();
		it.next(); // skip first: (0,0)

		// the results
		Apfloat[] binomialMoments = new Apfloat[alphasList.size()];
		binomialMoments[0] = ApfloatUtils.ONE;

		int i = 1;
		while (it.hasNext()) {
			int[] alphas = it.next();

			List<List<String>> alphaCombinations = new ArrayList<List<String>>();
			for (int j = 0; j < alphas.length; j++) {
				if (alphas[j] == 0) {
					continue;
				}

				alphaCombinations.add(getCombinations(nj, alphas[j], j));
			}

			Apfloat ithBinomMom = ApfloatUtils.ZERO;

			for (String tag : createVariations(alphaCombinations)) {
				ithBinomMom = ithBinomMom.add(probMap.get(tag));
			}

			binomialMoments[i] = ithBinomMom;
			i++;
		}

		return binomialMoments;
	}

	/**
	 * Tag the <code>probabilities</code> set with C(n,1), C(n,2), ... C(n,k).
	 * 
	 */
	private static Map<String, Apfloat> tagProbabilities(final Apfloat[] probabilities, final int n, final int k) {
		Map<String, Apfloat> map = new HashMap<String, Apfloat>();

		int j = 0;
		for (int i = 1; i <= k; i++) {

			CombinationGenerator g = new CombinationGenerator(n, i);
			while (g.hasNext()) {
				String key = g.nextString();
				map.put(key, probabilities[j]);
				j++;
			}

		}

		return map;
	}

	/**
	 * Generate all k-combination of a n size set.
	 * 
	 */
	private static List<String> getCombinations(final int n, final int k, final int offset) {
		List<String> tags = new ArrayList<String>();

		CombinationGenerator g = new CombinationGenerator(n, k);
		while (g.hasNext()) {
			StringBuilder sb = new StringBuilder();

			for (int i : g.next()) {
				sb.append((i + 1 + (n * offset)));
			}

			tags.add(sb.toString());
		}

		return tags;
	}

	/**
	 * Get variation of <code>vectorSet</code>.
	 * 
	 */
	private static List<String> createVariations(List<List<String>> vectorSet) {

		String[][] array = new String[vectorSet.size()][];
		int i = 0;
		for (List<String> v : vectorSet) {
			array[i] = v.toArray(new String[] {});
			i++;
		}

		List<String> variations = new ArrayList<String>();
		for (String[] variation : Variation.createVariation(array)) {
			StringBuilder sb = new StringBuilder();
			for (String v : variation) {
				sb.append(v);
			}
			variations.add(sb.toString());
		}

		return variations;
	}
}
