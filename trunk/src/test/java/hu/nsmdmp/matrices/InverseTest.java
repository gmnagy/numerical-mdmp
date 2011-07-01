package hu.nsmdmp.matrices;

import hu.nsmdmp.matrixmath.MatrixMath;
import hu.nsmdmp.matrixmath.Multiplication;

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
			System.err.println(inverseA);

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
			System.err.println(inverseA);

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
			System.err.println(inverseA);

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
		
		
		int maxOrder=10;
		double[][] vectorSet = new double[1][maxOrder+1];
		for (double i = 0; i <= maxOrder; i++) {
			vectorSet[0][(int) i] = i;
		}

		// normalized vector set
		Matrix normVSet = MatrixMath.normalize(new Matrix(vectorSet));
		Matrix A = MatrixFactory.getMonomialMatrix(normVSet.getArray(), maxOrder);
		
		
		Matrix inverseA = A.inverse();
		Matrix resultA=Multiplication.multiply(A, inverseA);
		Matrix expectedA=MatrixMath.identity(maxOrder+1, maxOrder+1);
		
		System.out.println(resultA);
		
		if (!expectedA.equals(resultA)) {
			System.err.println(resultA);

			Assert.assertTrue("L: ", false);
		}
		
		
		
		
		/*double[][] expected = { { -(2d / 7d), 11d / 42d, 1d / 21d }, { 3d / 7d, 4d / 21d, -(5d / 21d) }, { 1d / 7d, -(3d / 14d), 1d / 7d } };
		Matrix expectedA = new Matrix(expected);

		if (!expectedA.equals(inverseA)) {
			System.out.println(expectedA);
			System.err.println(inverseA);

			Assert.assertTrue("L: ", false);
		}
		*/
	}

}
