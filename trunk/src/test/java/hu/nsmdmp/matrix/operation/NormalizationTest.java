package hu.nsmdmp.matrix.operation;

import hu.nsmdmp.matrix.Matrix;
import hu.nsmdmp.matrix.operation.Normalization;

import org.junit.Assert;
import org.junit.Test;

/**
 * Check normalization of vector set.
 * 
 * @author nzcs
 * 
 */
public class NormalizationTest {

	@Test
	public void normalizationTest1() {
		double[][] vSet = { { 1, 2, 3, 4 }, { 0, 1, 2, 3 } };
		Matrix M = new Matrix(vSet);
		Matrix normalized = Normalization.normalize(M);

		double[][] expected = { { -1.0, -(1.0 / 3.0), (1.0 / 3.0), 1 }, { -1.0, -(1.0 / 3.0), (1.0 / 3.0), 1 } };
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

		double[][] expected = { { -1.0, -(1.0 / 3.0), (1.0 / 3.0), 1 }, { -1.0, -(1.0 / 3.0), (1.0 / 3.0), 1 } };
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

		double[][] expected = { { -1.0, -(1.0 / 3.0), (1.0 / 3.0), 1 }, { -1.0, -(1.0 / 3.0), (1.0 / 3.0), 1 } };
		Matrix em = new Matrix(expected);

		if (!normalized.equals(em)) {
			System.out.println(em);
			System.err.println(normalized);

			Assert.assertTrue(false);
		}
	}
}
