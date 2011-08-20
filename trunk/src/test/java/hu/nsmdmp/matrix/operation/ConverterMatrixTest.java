package hu.nsmdmp.matrix.operation;

import hu.nsmdmp.matrix.Matrix;
import hu.nsmdmp.matrix.operation.MatrixMath;

import org.junit.Assert;
import org.junit.Test;

public class ConverterMatrixTest {

	@Test
	public void testConverterMatrix1() {
		double[][] matrix1 = { { 2, 4 }, { 2, 1 } };
		Matrix M1 = new Matrix(matrix1);
		double[][] matrix2 = { { 2, 4 }, { 2, 1 } };
		Matrix M2 = new Matrix(matrix2);

		Matrix result = MatrixMath.solve(M1, M2);

		double[][] expected = { { 1, 0 }, { 0, 1 } };
		Matrix em = new Matrix(expected);

		if (!em.equals(result)) {
			System.out.println(em);
			System.err.println(result);

			Assert.assertTrue(false);
		}
	}

	@Test
	public void testConverterMatrix2() {
		double[][] matrix1 = { { 2, 4 }, { 2, 1 } };
		Matrix M1 = new Matrix(matrix1);
		double[][] matrix2 = { { 2, 4 }, { 2, 1 } };
		Matrix M2 = new Matrix(matrix2);

		Matrix result = MatrixMath.solveTranspose(M1, M2);

		double[][] expected = { { 1, 0 }, { 0, 1 } };
		Matrix em = new Matrix(expected);

		if (!em.equals(result)) {
			System.out.println(em);
			System.err.println(result);

			Assert.assertTrue(false);
		}
	}
}
