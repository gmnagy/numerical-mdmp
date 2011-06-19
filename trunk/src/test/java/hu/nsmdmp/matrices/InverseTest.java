package hu.nsmdmp.matrices;

import org.junit.Assert;
import org.junit.Test;

public class InverseTest {

	@Test
	public void test1() {
		double[][] m = { { 2, 4 }, { 2, 1 } };
		Matrix A = new Matrix(m);

		Matrix inverseA = A.inverse();

		double[][] expected = { { -1.0 / 6, 2.0 / 3.0 }, { 1.0 / 3.0, -1.0 / 3.0 } };
		Matrix expectedA = new Matrix(expected);

		if (!expectedA.equals(inverseA)) {
			System.out.println(expectedA);
			System.out.println(inverseA);

			Assert.assertTrue("L: ", false);
		}
	}

	@Test
	public void test2() {
		double[][] m = { { 1, 2, 3 }, { 4, 2, 2 }, { 5, 1, 7 } };
		Matrix A = new Matrix(m);

		Matrix inverseA = A.inverse();

		double[][] expected = { { -(2d / 7d), 11d / 42d, 1d / 21d }, { 3d / 7d, 4d / 21d, -(5d / 21d) }, { 1d / 7d, -(3d / 14d), 1d / 7d } };
		Matrix expectedA = new Matrix(expected);

		if (!expectedA.equals(inverseA)) {
			System.out.println(expectedA);
			System.out.println(inverseA);

			Assert.assertTrue("L: ", false);
		}
	}

	@Test
	public void test3() {
		double[][] m = { { 1, 2 }, { 3, 4 }, { 5, 6 } };
		Matrix A = new Matrix(m);

		Matrix inverseA = A.inverse();

		double[][] expected = { { -(4d / 3d), -(1d / 3d), 2d / 3d }, { 13d / 12d, 1d / 3d, -(5d / 12d) } };
		Matrix expectedA = new Matrix(expected);

		if (!expectedA.equals(inverseA)) {
			System.out.println(expectedA);
			System.out.println(inverseA);

			Assert.assertTrue("L: ", false);
		}
	}

	@Test
	public void test4() {
		double[][] m = { { 1, 2, 2 }, { 3, 1, 4 } };
		Matrix A = new Matrix(m);

		Matrix inverseA = A.inverse();

		double[][] expected = { { -(1d / 5d), 14d / 65d }, { 3d / 5d, -(17d / 65d) }, { 0, 2d / 13d } };
		Matrix expectedA = new Matrix(expected);

		if (!expectedA.equals(inverseA)) {
			System.out.println(expectedA);
			System.out.println(inverseA);

			Assert.assertTrue("L: ", false);
		}
	}
}
