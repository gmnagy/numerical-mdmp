package hu.nsmdmp.matrix;

import hu.nsmdmp.ApfloatUtils;

import org.apfloat.Apfloat;
import org.junit.Assert;
import org.junit.Test;

public class InverseTest {

	private static final Apfloat ZERO = ApfloatUtils.ZERO;
	private static final Apfloat ONE = ApfloatUtils.ONE;
	private static final Apfloat TWO = ApfloatUtils.valueOf(2);
	private static final Apfloat THREE = ApfloatUtils.valueOf(3);
	private static final Apfloat SIX = ApfloatUtils.valueOf(6);

	@Test
	public void test1() {
		double[][] m = { { 2, 4 }, { 2, 1 } };
		Matrix A = new Matrix(m);

		Matrix inverseA = A.inverse();

		// { { -1.0 / 6, 2.0 / 3.0 }, { 1.0 / 3.0, -1.0 / 3.0 } }
		Apfloat[][] expected = { { ONE.divide(SIX).negate(), TWO.divide(THREE) }, //
				{ ONE.divide(THREE), ONE.divide(THREE).negate() } };
		Matrix expectedA = new Matrix(expected);

		if (!expectedA.equals(inverseA)) {
			System.out.println(expectedA);
			System.err.println(inverseA);

			Assert.assertTrue("L: ", false);
		}
	}

	@Test
	public void test2() {
		double[][] m = { { 1, 2, 3 }, { 4, 2, 2 }, { 5, 1, 7 } };
		Matrix A = new Matrix(m);

		Matrix inverseA = A.inverse();

		// { { -(2d / 7d), 11d / 42d, 1d / 21d }, { 3d / 7d, 4d / 21d, -(5d / 21d) }, { 1d / 7d, -(3d / 14d), 1d / 7d } }
		Apfloat[][] expected = { { ApfloatUtils.valueOf(-2).divide(ApfloatUtils.valueOf(7)), ApfloatUtils.valueOf(11).divide(ApfloatUtils.valueOf(42)), ONE.divide(ApfloatUtils.valueOf(21)) }, //
				{ THREE.divide(ApfloatUtils.valueOf(7)), ApfloatUtils.valueOf(4).divide(ApfloatUtils.valueOf(21)), ApfloatUtils.valueOf(-5).divide(ApfloatUtils.valueOf(21)) }, //
				{ ONE.divide(ApfloatUtils.valueOf(7)), ApfloatUtils.valueOf(-3).divide(ApfloatUtils.valueOf(14)), ONE.divide(ApfloatUtils.valueOf(7)) } };
		Matrix expectedA = new Matrix(expected);

		if (!expectedA.equals(inverseA)) {
			System.out.println(expectedA);
			System.err.println(inverseA);

			Assert.assertTrue("L: ", false);
		}
	}

	@Test
	public void test3() {
		double[][] m = { { 1, 2 }, { 3, 4 }, { 5, 6 } };
		Matrix A = new Matrix(m);

		Matrix inverseA = A.inverse();

		// { { -(4d / 3d), -(1d / 3d), 2d / 3d }, { 13d / 12d, 1d / 3d, -(5d / 12d) } }
		Apfloat[][] expected = { { ApfloatUtils.valueOf(-4).divide(THREE), ApfloatUtils.valueOf(-1).divide(THREE), TWO.divide(THREE) }, //
				{ ApfloatUtils.valueOf(13).divide(ApfloatUtils.valueOf(12)), ONE.divide(THREE), ApfloatUtils.valueOf(-5).divide(ApfloatUtils.valueOf(12)) } };
		Matrix expectedA = new Matrix(expected);

		if (!expectedA.equals(inverseA)) {
			System.out.println(expectedA);
			System.err.println(inverseA);

			Assert.assertTrue("L: ", false);
		}
	}

	@Test
	public void test4() {
		double[][] m = { { 1, 2, 2 }, { 3, 1, 4 } };
		Matrix A = new Matrix(m);

		Matrix inverseA = A.inverse();

		// { { -(1d / 5d), 14d / 65d }, { 3d / 5d, -(17d / 65d) }, { 0, 2d / 13d } }
		Apfloat[][] expected = { { ApfloatUtils.valueOf(-1).divide(ApfloatUtils.valueOf(5)), ApfloatUtils.valueOf(14).divide(ApfloatUtils.valueOf(65)) }, //
				{ THREE.divide(ApfloatUtils.valueOf(5)), ApfloatUtils.valueOf(-17).divide(ApfloatUtils.valueOf(65)) }, //
				{ ZERO, TWO.divide(ApfloatUtils.valueOf(13)) } };
		Matrix expectedA = new Matrix(expected);

		if (!expectedA.equals(inverseA)) {
			System.out.println(expectedA);
			System.err.println(inverseA);

			Assert.assertTrue("L: ", false);
		}
	}

	@Test
	public void test5() {
		double[][] m = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		Matrix A = new Matrix(m);

		try {
			A.inverse();

		} catch (RuntimeException e) {
			Assert.assertEquals("Matrix is singular.", e.getMessage());

			return;
		}
		System.out.println(A.inverse());

		Assert.assertTrue("Szingularis matrixot nem lehet invertalni!!! ", false);
	}

	@Test
	public void testVandermonde() {

//		int maxOrder = 6;
//		double[][] vectorSet = new double[1][maxOrder + 1];
//		for (double i = 0; i <= maxOrder; i++) {
//			vectorSet[0][(int) i] = i;
//		}
//
//		//Matrix A = MatrixFactory.getMonomialMatrix(Converters.convert(vectorSet), maxOrder);
//		Matrix A = MatrixFactory.getSimpleMatrix(Converters.convert(vectorSet), maxOrder);
//		System.out.println(A);
//
//		Matrix inverseA = A.inverse();
//		System.out.println(inverseA);
//		Matrix resultA = Multiplication.multiply(A, inverseA);
//		Matrix expectedA = MatrixMath.identity(maxOrder + 1, maxOrder + 1);
//
//		if (!expectedA.equals(resultA)) {
//			System.out.println(expectedA);
//			System.err.println(resultA);
//
//			Assert.assertTrue("L: ", false);
//		}
	}

}
