package hu.nsmdmp.matrix;

import org.junit.Assert;
import org.junit.Test;

public class GetSubMatrixTest {

	@Test
	public void test1() {
		double[][] m = { { 1, 2, 2 }, { 3, 1, 4 }, { 5, 6, 7 }};
		Matrix A = new Matrix(m);
		int[] r={0,2};
		Matrix resultA = A.getSubMatrix(r, 0, 2);

		double[][] expected = { { 1, 2, 2 }, { 5, 6, 7 }};
		Matrix expectedA = new Matrix(expected);

		if (!expectedA.equals(resultA)) {
			System.out.println(expectedA);
			System.err.println(resultA);

			Assert.assertTrue("L: ", false);
		}
	}

	
	@Test
	public void test2() {
		double[][] m = { { 1, 2, 2 }, { 3, 1, 4 }, { 5, 6, 7 }};
		Matrix A = new Matrix(m);
		int[] r={0,2};
		Matrix resultA = A.transpose().getSubMatrix(r, 0, 2).transpose();

		double[][] expected = { { 1, 2 }, { 3, 4 }, { 5, 7 }};
		Matrix expectedA = new Matrix(expected);

		if (!expectedA.equals(resultA)) {
			System.out.println(expectedA);
			System.err.println(resultA);

			Assert.assertTrue("L: ", false);
		}
	}

}
