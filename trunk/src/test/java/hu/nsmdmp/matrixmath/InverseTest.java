package hu.nsmdmp.matrixmath;

import hu.nsmdmp.matrices.IMatrix;
import hu.nsmdmp.matrices.Matrix;
import hu.nsmdmp.matrices.MatrixUtils;

import org.junit.Assert;
import org.junit.Test;

public class InverseTest {

	@Test
	public void test1() {
		double[][] M = { { 2, 4 }, { 2, 1 } };
		IMatrix A = new Matrix(M);

		IMatrix inverseA = MatrixMath.inverse(A);

		double[][] expected = { { -1.0 / 6, 2.0 / 3.0 }, { 1.0 / 3.0, -1.0 / 3.0 } };
		IMatrix expectedA = new Matrix(expected);
		if (!MatrixUtils.equals(expectedA, inverseA)) {
			System.out.println(expectedA);
			System.out.println(inverseA);

			Assert.assertTrue("L: ", false);
		}
	}

	@Test
	public void test2() {
		double[][] M = { { 1, 2, 3 }, { 4, 2, 2 }, { 5, 1, 7 } };
		IMatrix A = new Matrix(M);

		IMatrix inverseA = MatrixMath.inverse(A);

		double[][] expected = { { -(2d / 7d), 11d / 42d, 1d / 21d }, { 3d / 7d, 4d / 21d, -(5d / 21d) }, { 1d / 7d, -(3d / 14d), 1d / 7d } };
		IMatrix expectedA = new Matrix(expected);
		if (!MatrixUtils.equals(expectedA, inverseA)) {
			System.out.println(expectedA);
			System.out.println(inverseA);

			Assert.assertTrue("L: ", false);
		}
	}

	@Test
	public void test3() {
		double[][] M = { { 1, 2 }, { 3, 4 }, { 5, 6 } };
		IMatrix A = new Matrix(M);

		IMatrix inverseA = MatrixMath.inverse(A);

		double[][] expected = { { -(4d / 3d), -(1d / 3d), 2d / 3d }, { 13d / 12d, 1d / 3d, -(5d / 12d) } };
		IMatrix expectedA = new Matrix(expected);
		if (!MatrixUtils.equals(expectedA, inverseA)) {
			System.out.println(expectedA);
			System.out.println(inverseA);

			Assert.assertTrue("L: ", false);
		}
	}

	@Test
	public void test4() {
		double[][] M = { { 1, 2, 2 }, { 3, 1, 4 } };
		IMatrix A = new Matrix(M);

		IMatrix inverseA = MatrixMath.inverse(A);

		double[][] expected = { { -(1d / 5d), 14d / 65d }, { 3d / 5d, -(17d / 65d) }, { 0, 2d / 13d } };
		IMatrix expectedA = new Matrix(expected);
		if (!MatrixUtils.equals(expectedA, inverseA)) {
			System.out.println(expectedA);
			System.out.println(inverseA);

			Assert.assertTrue("L: ", false);
		}
	}
}
