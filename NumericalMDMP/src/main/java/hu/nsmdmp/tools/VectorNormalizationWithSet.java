package hu.nsmdmp.tools;

import static hu.nsmdmp.numerics.matrix.operations.OperationFactory.selectOperation;
import static hu.nsmdmp.utils.Converters.arrayToString;
import hu.nsmdmp.numerics.matrix.Vector;
import hu.nsmdmp.numerics.matrix.operations.IOperations;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VectorNormalizationWithSet {

	public static <T> Vector<T> normailzeByGergo(final T[][] vectorSet, final int maxOrder, final Vector<T> V) {
		int dim = vectorSet.length;
		IOperations<T> op = selectOperation(V.getElementType());

		// normalized vector
		Vector<T> nV = new Vector<T>(V.getColumnDimension());

		// α1,α2,..αs
		List<int[]> alphasList = TotalOrder.generateTotalOrderOfMomentMembers(maxOrder, dim);

		Map<String, T> moments = orderMomentsByAlphas(V, alphasList);

		T[] aConstants = getAConstants(vectorSet, op);

		int i = 0;
		for (int[] alphas : alphasList) {
			T x = stepper(alphas, 0, new int[alphas.length], aConstants, moments, op);

			T bConstant = getBConstant(alphas, vectorSet, op);

			nV.set(i, op.multiply(bConstant, x));

			i++;
		}

		return nV;
	}

	/**
	 * ai = zi0 + zin
	 */
	private static <T> T[] getAConstants(final T[][] vectorSet, final IOperations<T> op) {
		int s = vectorSet.length;

		@SuppressWarnings("unchecked")
		T[] a = (T[]) Array.newInstance(op.getType(), s);

		for (int i = 0; i < s; i++) {
			a[i] = op.add(vectorSet[i][0], vectorSet[i][vectorSet[i].length - 1]);
			a[i] = op.negate(a[i]);
		}

		return a;
	}

	/**
	 * 1 / ( (z1n - z10)^α1 * (z2n - z20)^α2 * ... * (zsn - zs0)^αs )
	 */
	private static <T> T getBConstant(final int[] alphas, final T[][] vectorSet, final IOperations<T> op) {
		int s = vectorSet.length;

		T b = op.one();

		for (int i = 0; i < s; i++) {
			T bi = op.subtract(vectorSet[i][vectorSet[i].length - 1], vectorSet[i][0]);
			bi = op.pow(bi, alphas[i]);

			b = op.multiply(b, bi);
		}

		return op.divide(op.one(), b);
	}

	private static <T> T stepper(final int[] alphas, final int i, final int[] level, final T[] aConstants, final Map<String, T> moments, final IOperations<T> op) {
		T x = op.zero();

		if (i == alphas.length) {

			x = op.pow(op.two(), sum(level));

			int j = 0;
			for (long exp : exps(alphas, level)) {
				x = op.multiply(x, 0 == exp ? op.one() : op.pow(aConstants[j], (int) exp));
				j++;
			}

			for (long binomial : binomials(alphas, level)) {
				x = op.multiply(x, op.valueOf(binomial));
			}

			T moment = moments.get(arrayToString(level));
			x = op.multiply(x, moment);

		} else {
			for (int k = 0; k <= alphas[i]; k++) {
				level[i] = k;
				x = op.add(x, stepper(alphas, i + 1, level, aConstants, moments, op));
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

	private static <T> Map<String, T> orderMomentsByAlphas(final Vector<T> V, List<int[]> alphasList) {
		if (V.getColumnDimension() != alphasList.size()) {
			throw new RuntimeException("Vector and alphasList dimensions must agree!");
		}

		Map<String, T> map = new HashMap<String, T>();

		int i = 0;
		for (int[] alphas : alphasList) {
			String tag = arrayToString(alphas);
			map.put(tag, V.get(i));
			i++;
		}

		return map;
	}
}
