package hu.nsmdmp.moments;

import static hu.nsmdmp.math.SubSequencesGenerator.getSubSequences;
import static hu.nsmdmp.math.TotalOrder.getTotalOrderOfMomentMembers;
import static hu.nsmdmp.math.Variation.createVariation;
import static hu.nsmdmp.utils.Utils.arrayToString;
import hu.nsmdmp.math.CombinationGenerator;
import hu.nsmdmp.math.Math;
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

public class MultivariateMoments {

	/**
	 * @param probabilities
	 * @param n
	 *            number of events
	 * @param m
	 *            the maximum order of the intersections
	 * @param dim
	 *            dimensional
	 * @return {a(1), ..., a(s)}-order cross-binomial moments
	 */
	public static List<Moment> createBinomialMoments(final Apfloat[] probabilities, final int n, final int m, final int dim, final int subSeqSize) {
		List<Moment> binomialMoments = new ArrayList<Moment>();

		if (probabilities.length == 0)
			return binomialMoments;

		Map<String, Apfloat> orderedProb = orderProbabilities(probabilities, n, m);

		List<int[]> alphasList = getTotalOrderOfMomentMembers(m, dim);

//		for (int l = m; l <= n - m; l++) {

		List<int[]> indexSubSequences = getSubSequences(n, subSeqSize, dim);

		Iterator<int[]> itAlphas = alphasList.iterator();

		// skip first: (0,0, ... 0)
		binomialMoments.add(new Moment(itAlphas.next(), ApfloatUtils.ONE));

		while (itAlphas.hasNext()) {
			int[] alphas = itAlphas.next();

			List<String[]> indexCombinationsOfAlphaSet = new ArrayList<String[]>();

			int i = 0;
			for (int alpha : alphas) {

				if (alpha > 0) {
					String[] indexCombinationsOfAlpha = combinations(indexSubSequences.get(i), alpha);
					indexCombinationsOfAlphaSet.add(indexCombinationsOfAlpha);
				}

				i++;
			}

			Apfloat ithBinomMom = ApfloatUtils.ZERO;

			for (String[] indexVariations : createVariation(indexCombinationsOfAlphaSet)) {
				ithBinomMom = ithBinomMom.add(orderedProb.get(arrayToString(indexVariations)));
			}

			binomialMoments.add(new Moment(alphas, ithBinomMom));
		}

		return binomialMoments;
	}

	/**
	 * Order probabilities by the number of events and the maximum order of the intersections.
	 * 
	 */
	private static Map<String, Apfloat> orderProbabilities(final Apfloat[] probabilities, final int n, final int m) {
		Map<String, Apfloat> map = new HashMap<String, Apfloat>();

		int j = 0;
		for (int i = 1; i <= m; i++) {

			CombinationGenerator g = new CombinationGenerator(n, i);
			while (g.hasNext()) {
				map.put(arrayToString(g.next()), probabilities[j]);
				j++;
			}

		}

		return map;
	}

	static String[] combinations(final int[] array, final int r) {
		int length = array.length;
		CombinationGenerator g = new CombinationGenerator(length, r);

		String[] result = new String[(int) g.getTotal()];

		int t = 0;
		while (g.hasNext()) {
			int[] index = g.next();

			StringBuilder sb = new StringBuilder();

			for (int i : index) {
				sb.append(array[i]);
			}

			result[t] = sb.toString();
			t++;
		}

		return result;
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
