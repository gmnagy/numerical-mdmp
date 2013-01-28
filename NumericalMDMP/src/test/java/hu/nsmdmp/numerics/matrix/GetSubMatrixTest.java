package hu.nsmdmp.numerics.matrix;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

public class GetSubMatrixTest {

	@Test
	public void test1() {
		Double[][] m = { { 1.0, 2.0, 2.0 }, { 3.0, 1.0, 4.0 }, { 5.0, 6.0, 7.0 } };
		Matrix<Double> A = new Matrix<Double>(m);
		int[] r = { 0, 2 };
		Matrix<Double> resultA = A.getSubMatrix(r, 0, 2);

		Double[][] expected = { { 1.0, 2.0, 2.0 }, { 5.0, 6.0, 7.0 } };
		Matrix<Double> expectedA = new Matrix<Double>(expected);

		assertEquals(expectedA, resultA);
	}

	@Test
	public void test2() {
		Double[][] m = { { 1.0, 2.0, 2.0 }, { 3.0, 1.0, 4.0 }, { 5.0, 6.0, 7.0 } };
		Matrix<Double> A = new Matrix<Double>(m);
		int[] r = { 0, 2 };
		Matrix<Double> resultA = A.transpose().getSubMatrix(r, 0, 2).transpose();

		Double[][] expected = { { 1.0, 2.0 }, { 3.0, 4.0 }, { 5.0, 7.0 } };
		Matrix<Double> expectedA = new Matrix<Double>(expected);

		assertEquals(expectedA, resultA);
	}
}
