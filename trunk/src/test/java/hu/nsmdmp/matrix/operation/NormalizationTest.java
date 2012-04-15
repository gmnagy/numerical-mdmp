package hu.nsmdmp.matrix.operation;

import hu.nsmdmp.matrix.Matrix;
import hu.nsmdmp.utils.ApfloatUtils;

import org.apfloat.Apfloat;
import org.junit.Assert;
import org.junit.Test;

/**
 * Check normalization of vector set.
 * 
 * @author nzcs
 * 
 */
public class NormalizationTest {

	private static final Apfloat ZERO = ApfloatUtils.ZERO;
	private static final Apfloat ONE = ApfloatUtils.ONE;
	private static final Apfloat TWO = ApfloatUtils.valueOf(2);
	private static final Apfloat THREE = ApfloatUtils.valueOf(3);

	@Test
	public void normalizationTest1() {
		double[][] vSet = { { 1, 2, 3, 4 }, { 0, 1, 2, 3 } };
		Matrix M = new Matrix(vSet);
		Matrix normalized = Normalization.normalize(M);

		// { { -1.0, -(1.0 / 3.0), (1.0 / 3.0), 1 }, { -1.0, -(1.0 / 3.0), (1.0 / 3.0), 1 } }
		Apfloat[][] expected = { { ONE.negate(), ONE.divide(THREE).negate(), ONE.divide(THREE), ONE }, { ONE.negate(), ONE.divide(THREE).negate(), ONE.divide(THREE), ONE } };
		Matrix em = new Matrix(expected);

		if (!normalized.equals(em)) {
			System.out.println(em);
			System.err.println(normalized);

			Assert.assertTrue(false);
		}
	}

	@Test
	public void normalizationTest2() {
		double[][] vSet = { { 0 - 4, 1 - 4, 2 - 4, 3 - 4 }, { 0, 1, 2, 3 } };
		Matrix M = new Matrix(vSet);
		Matrix normalized = Normalization.normalize(M);

		// { { -1.0, -(1.0 / 3.0), (1.0 / 3.0), 1 }, { -1.0, -(1.0 / 3.0), (1.0 / 3.0), 1 } }
		Apfloat[][] expected = { { ONE.negate(), ONE.divide(THREE).negate(), ONE.divide(THREE), ONE }, { ONE.negate(), ONE.divide(THREE).negate(), ONE.divide(THREE), ONE } };
		Matrix em = new Matrix(expected);

		if (!normalized.equals(em)) {
			System.out.println(em);
			System.err.println(normalized);

			Assert.assertTrue(false);
		}
	}

	@Test
	public void normalizationTest3() {
		double[][] vSet = { { 0, 1, 2, 3 }, { 0, 1, 2, 3 } };
		Matrix M = new Matrix(vSet);
		Matrix normalized = Normalization.normalize(M);

		// { { -1.0, -(1.0 / 3.0), (1.0 / 3.0), 1 }, { -1.0, -(1.0 / 3.0), (1.0 / 3.0), 1 } }
		Apfloat[][] expected = { { ONE.negate(), ONE.divide(THREE).negate(), ONE.divide(THREE), ONE }, { ONE.negate(), ONE.divide(THREE).negate(), ONE.divide(THREE), ONE } };
		Matrix em = new Matrix(expected);

		if (!normalized.equals(em)) {
			System.out.println(em);
			System.err.println(normalized);

			Assert.assertTrue(false);
		}
	}
}
