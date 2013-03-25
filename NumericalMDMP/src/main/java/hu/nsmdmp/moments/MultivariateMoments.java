package hu.nsmdmp.moments;

import static hu.nsmdmp.operations.Operations.operation;
import static hu.nsmdmp.tools.Math.factorial;
import static hu.nsmdmp.tools.Math.stirling;
import static hu.nsmdmp.tools.SubSequencesGenerator.getSubSequences;
import static hu.nsmdmp.tools.TotalOrder.generateTotalOrderOfMomentMembers;
import static hu.nsmdmp.utils.Converters.arrayToString;
import hu.nsmdmp.operations.IOperation;
import hu.nsmdmp.tools.CombinationGenerator;
import hu.nsmdmp.tools.SetVariationIterator2;
import hu.nsmdmp.tools.StirlingNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

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
	public static <T> List<Moment<T>> createBinomialMoments(final T[] probabilities, final int n, final int m, final int dim, final int subSeqSize) {
		int[] permutation = new int[n];
		for (int i = 0; i < n; i++)
			permutation[i] = i;

		return createBinomialMoments(probabilities, n, permutation, m, dim, subSeqSize);
	}

	public static <T> List<Moment<T>> createBinomialMoments(final T[] probabilities, final int n, final int[] permutation, final int m, final int dim, final int subSeqSize) {
		List<Moment<T>> binomialMoments = new ArrayList<Moment<T>>();

		if (probabilities.length == 0)
			return binomialMoments;

		IOperation<T> op = operation(probabilities[0].getClass());

		Map<int[], T> orderedProb = orderProbabilities(probabilities, n, m);
		Map<String, T> convertedOrderedProb = convertWithPermutation(orderedProb, permutation);
//		System.out.println(convertedOrderedProb);

		List<int[]> alphasList = generateTotalOrderOfMomentMembers(m, dim);
		/* for(int[] tomb : alphasList){
			System.out.println((Arrays.toString(tomb)));
		}
		*/
		List<int[]> indexSubSequences = getSubSequences(n, subSeqSize, dim);
		/*for(int[] tomb : indexSubSequences){
			System.out.println((Arrays.toString(tomb)));
		}
		*/


		Iterator<int[]> itAlphas = alphasList.iterator();

		// skip first: (0,0, ... 0)
		binomialMoments.add(new Moment<T>(itAlphas.next(), op.one()));

		while (itAlphas.hasNext()) {
			int[] alphas = itAlphas.next();
			//System.out.println((Arrays.toString(alphas)));

			List<String[]> indexCombinationsOfAlphaSet = new ArrayList<String[]>();

			int i = 0;
			for (int alpha : alphas) {

				if (alpha > 0) {
					String[] indexCombinationsOfAlpha = combinations(indexSubSequences.get(i), alpha, permutation);
					indexCombinationsOfAlphaSet.add(indexCombinationsOfAlpha);
				}

				i++;
			}
//			for (String[] s : indexCombinationsOfAlphaSet)
//				System.out.println("a: " + Arrays.toString(s));
//			System.out.println("a: ");

			T ithBinomMom = op.zero();

			SetVariationIterator2<String> it = new SetVariationIterator2<String>(indexCombinationsOfAlphaSet);
			while (it.hasNext()) {
				String[] indexVariations = it.next();

				ithBinomMom = op.add(ithBinomMom, convertedOrderedProb.get(arrayToString(indexVariations)));
			}

			binomialMoments.add(new Moment<T>(alphas, ithBinomMom));
		}

		return binomialMoments;
	}

	/*
	 * probabilities<-> exponents
	 */
	public static  List<Moment<Apfloat>> createBinomialMomentsExponent(Apfloat p, final Double[] probabilities, final int n, final int m, final int dim, final int subSeqSize) {
		int[] permutation = new int[n];
		for (int i = 0; i < n; i++)
			permutation[i] = i;

		return createBinomialMomentsExponent(p, probabilities, n, permutation, m, dim, subSeqSize);
	}
	
	
	
	public static List<Moment<Apfloat>> createBinomialMomentsExponent(Apfloat p, final Double[] probabilities, final int n, final int[] permutation, final int m, final int dim, final int subSeqSize) {
		List<Moment<Apfloat>> binomialMoments = new ArrayList<Moment<Apfloat>>();

		if (probabilities.length == 0)
			return binomialMoments;

		IOperation<Apfloat> op = operation(probabilities[0].getClass());

		Map<int[], Double> orderedProb = orderProbabilities(probabilities, n, m);
		Map<String, Double> convertedOrderedProb = convertWithPermutation(orderedProb, permutation);
//		System.out.println(convertedOrderedProb);

		List<int[]> alphasList = generateTotalOrderOfMomentMembers(m, dim);
		/* for(int[] tomb : alphasList){
			System.out.println((Arrays.toString(tomb)));
		}
		*/
		List<int[]> indexSubSequences = getSubSequences(n, subSeqSize, dim);
		/*for(int[] tomb : indexSubSequences){
			System.out.println((Arrays.toString(tomb)));
		}
		*/


		Iterator<int[]> itAlphas = alphasList.iterator();

		// skip first: (0,0, ... 0)
		binomialMoments.add(new Moment<Apfloat>(itAlphas.next(), op.one()));

		while (itAlphas.hasNext()) {
			int[] alphas = itAlphas.next();
			//System.out.println((Arrays.toString(alphas)));

			List<String[]> indexCombinationsOfAlphaSet = new ArrayList<String[]>();

			int i = 0;
			for (int alpha : alphas) {

				if (alpha > 0) {
					String[] indexCombinationsOfAlpha = combinations(indexSubSequences.get(i), alpha, permutation);
					indexCombinationsOfAlphaSet.add(indexCombinationsOfAlpha);
				}

				i++;
			}
//			for (String[] s : indexCombinationsOfAlphaSet)
//				System.out.println("a: " + Arrays.toString(s));
//			System.out.println("a: ");

			Apfloat ithBinomMom = op.zero();

			SetVariationIterator2<String> it = new SetVariationIterator2<String>(indexCombinationsOfAlphaSet);
			while (it.hasNext()) {
				String[] indexVariations = it.next();

				ithBinomMom = op.add(ithBinomMom,  ApfloatMath.pow( p, convertedOrderedProb.get(arrayToString(indexVariations)).longValue()));
			}

			binomialMoments.add(new Moment<Apfloat>(alphas, ithBinomMom));
		}

		return binomialMoments;
	}

	/**
	 * Order probabilities by the number of events and the maximum order of the intersections.
	 * 
	 */
	private static <T> Map<int[], T> orderProbabilities(final T[] probabilities, final int n, final int m) {
		Map<int[], T> map = new HashMap<int[], T>();

		int j = 0;
		for (int i = 1; i <= m; i++) {

			CombinationGenerator g = new CombinationGenerator(n, i);
			while (g.hasNext()) {
				int[] combination = g.next();

				map.put(Arrays.copyOf(combination, combination.length), probabilities[j]);
				j++;
			}

		}

		return map;
	}

	private static <T> Map<String, T> convertWithPermutation(final Map<int[], T> probabilities, final int[] permutation) {
		Map<String, T> map = new HashMap<String, T>();

		for (Entry<int[], T> item : probabilities.entrySet()) {

			int i = 0;
			int[] key = new int[item.getKey().length];
			StringBuilder sb = new StringBuilder();
			for (int v : item.getKey()) {
				sb.append(permutation[v]);
				key[i] = permutation[v];
				i++;
			}

			map.put(sb.toString(), getProbability(key, probabilities));
		}

		return map;
	}

	private static <T> T getProbability(final int[] key, final Map<int[], T> probabilities) {

		for (Entry<int[], T> item : probabilities.entrySet()) {
			Arrays.sort(key);
			Arrays.sort(item.getKey());

			if (Arrays.equals(item.getKey(), key))
				return item.getValue();
		}

		return null;
	}

	static String[] combinations(final int[] array, final int r, final int[] converterArray) {
		int length = array.length;
		CombinationGenerator g = new CombinationGenerator(length, r);

		String[] result = new String[(int) g.getTotal()];

		int t = 0;
		while (g.hasNext()) {
			int[] index = g.next();

			StringBuilder sb = new StringBuilder();

			for (int i : index) {
				sb.append(converterArray[array[i]]);
			}

			result[t] = sb.toString();
			t++;
		}

		return result;
	}

	public static <T> Collection<Moment<T>> convertBinomMomToPowerMom(final List<Moment<T>> binomialMoments) {
		if (binomialMoments.isEmpty()) {
			return Collections.emptyList();
		}

		Iterator<Moment<T>> binMomIt = binomialMoments.iterator();
		Moment<T> binMom = binMomIt.next();

		IOperation<T> op = operation(binMom.moment.getClass());

		Map<String, Moment<T>> powerMoments = new LinkedHashMap<String, Moment<T>>();

		// skip first: alphas = (0,0, ... 0)

		Moment<T> first = new Moment<T>(binMom.alphas, op.one());
		powerMoments.put(arrayToString(first.alphas), first);

		while (binMomIt.hasNext()) {
			Moment<T> binomialMoment = binMomIt.next();

			// monomials of alpha-combinations polinom
			// S(21) => { [1/2*x^2, 1/2*x], [y] }
			List<StirlingNumber<T>[]> polinomOfAlphas = new ArrayList<StirlingNumber<T>[]>();

			for (int alpha : binomialMoment.alphas) {
				polinomOfAlphas.add(getMonomials(alpha, op));
			}

			StirlingNumber<T> searchedSN = null;
			T powerMom = binomialMoment.moment;

			// multiply monomial members
			// { [1/2*x^2, 1/2*x], [y] } => { 1/2*x^2*y, 1/2*x*y } => { 1/2*u(2,1), 1/2*u(1,1) }
			SetVariationIterator2<StirlingNumber<T>> it = new SetVariationIterator2<StirlingNumber<T>>(polinomOfAlphas);
			while (it.hasNext()) {
				StirlingNumber<T>[] monomialMembers = it.next();

				StirlingNumber<T> monomial = multiplyMonomialMembers(monomialMembers);

				if (Arrays.equals(monomial.exponents, binomialMoment.alphas)) {
					searchedSN = monomial;
				} else {
					// x = 1/2*u(1,1)
					T x = op.multiply(powerMoments.get(monomial.getConcatenateExponents()).moment, monomial.number);
					powerMom = op.subtract(powerMom, x);
				}
			}

			powerMom = op.divide(powerMom, searchedSN.number);
			powerMoments.put(searchedSN.getConcatenateExponents(), new Moment<T>(searchedSN.exponents, powerMom));
		}

		return powerMoments.values();
	}

	/**
	 * Get monomials of alpha-combinations polinom (Pl: x(x-1)(x-2)/3!).
	 * 
	 */
	@SuppressWarnings("unchecked")
	private static <T> StirlingNumber<T>[] getMonomials(final int alpha, final IOperation<T> op) {
		StirlingNumber<T>[] snArray;

		if (alpha == 0) {
			snArray = new StirlingNumber[1];
			snArray[0] = new StirlingNumber<T>(op.one(), 0);
			return snArray;
		}

		snArray = new StirlingNumber[alpha];

		for (int i = 1; i <= alpha; i++) {
			long sn = stirling(alpha, i);
			long f = factorial(alpha);
			T coefficient = op.divide(op.valueOf(sn), op.valueOf(f));
			snArray[i - 1] = new StirlingNumber<T>(coefficient, i);
		}

		return snArray;
	}

	private static <T> StirlingNumber<T> multiplyMonomialMembers(final StirlingNumber<T>[] monomialMembers) {
		if (monomialMembers.length == 0) {
			return null;
		}

		StirlingNumber<T> monomial = monomialMembers[0];
		for (int i = 1; i < monomialMembers.length; i++) {
			monomial = monomial.multiply(monomialMembers[i]);
		}

		return monomial;
	}
}
