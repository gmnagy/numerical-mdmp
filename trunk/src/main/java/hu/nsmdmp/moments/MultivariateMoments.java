package hu.nsmdmp.moments;

import hu.nsmdmp.math.CombinationGenerator;
import hu.nsmdmp.math.Math;
import hu.nsmdmp.math.TotalOrder;
import hu.nsmdmp.math.Variation;
import hu.nsmdmp.utils.ApfloatUtils;
import hu.nsmdmp.utils.Converters;
import hu.nsmdmp.utils.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apfloat.Apfloat;
import org.junit.Test;

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
	public static List<Moment> createBinomialMoments(final Apfloat[] probabilities, final int n, final int m, final int s) {
		if (probabilities.length == 0) {
			return Collections.emptyList();
		}

		// provide: n(1) + ... + n(2) = n
		int nj = n / s;

		Map<String, Apfloat> probMap = tagProbabilities(probabilities, n, m);

		// {a(1), ..., a(s)}, where a(1) + ... + a(s) <= m 
		List<int[]> alphasList = TotalOrder.getOrders(m, s);
		Iterator<int[]> it = alphasList.iterator();

		// the results
		List<Moment> binomialMoments = new ArrayList<Moment>();
		// skip first: (0,0, ... 0)
		binomialMoments.add(new Moment(it.next(), ApfloatUtils.ONE));

		while (it.hasNext()) {
			int[] alphas = it.next();

			List<String[]> alphaCombinations = new ArrayList<String[]>();
			for (int j = 0; j < alphas.length; j++) {
				if (alphas[j] == 0) {
					continue;
				}

				alphaCombinations.add(getCombinations(nj, alphas[j], j));
			}

			Apfloat ithBinomMom = ApfloatUtils.ZERO;

			for (String[] tag : Variation.createVariation(alphaCombinations)) {
				ithBinomMom = ithBinomMom.add(probMap.get(Converters.arrayToString(tag, "")));
			}

			binomialMoments.add(new Moment(alphas, ithBinomMom));
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
	private static String[] getCombinations(final int n, final int k, final int offset) {

		CombinationGenerator g = new CombinationGenerator(n, k);
		String[] tags = new String[(int) g.getTotal()];

		int j = 0;
		while (g.hasNext()) {
			StringBuilder sb = new StringBuilder();

			for (int i : g.next()) {
				sb.append((i + 1 + (n * offset)));
			}

			tags[j] = sb.toString();
			j++;
		}

		return tags;
	}

	public static Collection<Moment> convertBinomMomToPowerMom(final List<Moment> binomialMoments) {
		if (binomialMoments.isEmpty()) {
			return Collections.emptyList();
		}

		Iterator<Moment> binMomIt = binomialMoments.iterator();

		Map<String, Moment> powerMoments = new LinkedHashMap<String, Moment>();
		// skip first: alphas = (0,0, ... 0)
		Moment first = new Moment(binMomIt.next().alphas, ApfloatUtils.ONE);
		powerMoments.put(Converters.arrayToString(first.alphas, ""), first);

		while (binMomIt.hasNext()) {
			Moment binomialMoment = binMomIt.next();
//			System.out.println(binomialMoment);

			// monomials of alpha-combinations polinom
			// S(21) => { [1/2*x^2, 1/2*x], [y] }
			List<StirlingNumber[]> polinomOfAlphas = new ArrayList<StirlingNumber[]>();

			for (int alpha : binomialMoment.alphas) {
				polinomOfAlphas.add(getMonomials(alpha));
			}

			StirlingNumber searchedSN = null;
			Apfloat powerMom = binomialMoment.moment;

			// multiply monomial members
			// { [1/2*x^2, 1/2*x], [y] } => { 1/2*x^2*y, 1/2*x*y } => { 1/2*u(2,1), 1/2*u(1,1) }
			for (StirlingNumber[] monomialMembers : Variation.createVariation(polinomOfAlphas)) {
				StirlingNumber monomial = multiplyMonomialMembers(monomialMembers);

				if (Utils.equals(monomial.exponents, binomialMoment.alphas)) {
					searchedSN = monomial;
				} else {
					// x = 1/2*u(1,1)
					Apfloat x = powerMoments.get(monomial.getConcatenateExponents()).moment.multiply(monomial.number);
					powerMom = powerMom.subtract(x);
				}
			}

			powerMom = powerMom.divide(searchedSN.number);
			powerMoments.put(searchedSN.getConcatenateExponents(), new Moment(searchedSN.exponents, powerMom));
		}

		return powerMoments.values();
	}

	/**
	 * Get monomials of alpha-combinations polinom (Pl: x(x-1)(x-2)/3!).
	 * 
	 */
	private static StirlingNumber[] getMonomials(final int alpha) {
		StirlingNumber[] snArray;

		if (alpha == 0) {
			snArray = new StirlingNumber[1];
			snArray[0] = new StirlingNumber(ApfloatUtils.ONE, 0);
			return snArray;
		}

		snArray = new StirlingNumber[alpha];

		for (int i = 1; i <= alpha; i++) {
			long sn = Math.stirling(alpha, i);
			long f = Math.factorial(alpha);
			Apfloat coefficient = ApfloatUtils.valueOf(sn).divide(ApfloatUtils.valueOf(f));
			snArray[i - 1] = new StirlingNumber(coefficient, i);
		}

		return snArray;
	}

	@Test
	public void test() {
		for (StirlingNumber sn : getMonomials(2)) {
			System.out.println(sn);
		}
	}

	private static StirlingNumber multiplyMonomialMembers(final StirlingNumber[] monomialMembers) {
		if (monomialMembers.length == 0) {
			return null;
		}

		StirlingNumber monomial = monomialMembers[0];
		for (int i = 1; i < monomialMembers.length; i++) {
			monomial = monomial.multiply(monomialMembers[i]);
		}

		return monomial;
	}
}
