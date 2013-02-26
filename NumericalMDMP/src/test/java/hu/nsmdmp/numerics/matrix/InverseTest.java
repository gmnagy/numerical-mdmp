package hu.nsmdmp.numerics.matrix;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Assert;
import org.junit.Test;

public class InverseTest {

	@Test
	public void test1() {

		Double[][] m = { { 2.0, 4.0 }, { 2.0, 1.0 } };
		Matrix<Double> A = new Matrix<Double>(m);

		Matrix<Double> inverseA = A.inverse();

		Double[][] expected = { { -1.0 / 6, 2.0 / 3.0 }, { 1.0 / 3.0, -1.0 / 3.0 } };
		Matrix<Double> expectedA = new Matrix<Double>(expected);

		for (int i = 0; i < 2; i++)
			assertArrayEquals(expectedA.toDoubleMultiArray()[i], inverseA.toDoubleMultiArray()[i], 0.00001);
	}

	@Test
	public void test2() {

		Double[][] m = { { 1.0, 2.0, 3.0 }, { 4.0, 2.0, 2.0 }, { 5.0, 1.0, 7.0 } };
		Matrix<Double> A = new Matrix<Double>(m);

		Matrix<Double> inverseA = A.inverse();

		Double[][] expected = { { -(2d / 7d), 11d / 42d, 1d / 21d }, { 3d / 7d, 4d / 21d, -(5d / 21d) }, { 1d / 7d, -(3d / 14d), 1d / 7d } };
		Matrix<Double> expectedA = new Matrix<Double>(expected);

		for (int i = 0; i < 3; i++)
			assertArrayEquals(expectedA.toDoubleMultiArray()[i], inverseA.toDoubleMultiArray()[i], 0.00001);
	}

	@Test
	public void test3() {

		Double[][] m = { { 1.0, 2.0 }, { 3.0, 4.0 }, { 5.0, 6.0 } };
		Matrix<Double> A = new Matrix<Double>(m);

		Matrix<Double> inverseA = A.inverse();

		Double[][] expected = { { -(4d / 3d), -(1d / 3d), 2d / 3d }, { 13d / 12d, 1d / 3d, -(5d / 12d) } };
		Matrix<Double> expectedA = new Matrix<Double>(expected);

		for (int i = 0; i < 2; i++)
			assertArrayEquals(expectedA.toDoubleMultiArray()[i], inverseA.toDoubleMultiArray()[i], 0.00001);
	}

	@Test
	public void test4() {

		Double[][] m = { { 1.0, 2.0, 2.0 }, { 3.0, 1.0, 4.0 } };
		Matrix<Double> A = new Matrix<Double>(m);

		Matrix<Double> inverseA = A.inverse();

		// { { -(1d / 5d), 14d / 65d }, { 3d / 5d, -(17d / 65d) }, { 0, 2d / 13d } }
		Double[][] expected = { { -(1d / 5d), 14d / 65d }, { 3d / 5d, -(17d / 65d) }, { 0d, 2d / 13d } };
		Matrix<Double> expectedA = new Matrix<Double>(expected);

		for (int i = 0; i < 3; i++)
			assertArrayEquals(expectedA.toDoubleMultiArray()[i], inverseA.toDoubleMultiArray()[i], 0.00001);
	}

	@Test
	public void test5() {

		Double[][] m = { { 1.0, 2.0, 3.0 }, { 4.0, 5.0, 6.0 }, { 7.0, 8.0, 9.0 } };
		Matrix<Double> A = new Matrix<Double>(m);

		try {
			A.inverse();

		} catch (RuntimeException e) {
			Assert.assertEquals("Matrix is singular.", e.getMessage());

			return;
		}
		System.out.println(A.inverse());

		Assert.assertTrue("Szingularis matrixot nem lehet invertalni!!! ", false);
	}

//	@Test
//	public void testVandermonde() {

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
//	}
}
