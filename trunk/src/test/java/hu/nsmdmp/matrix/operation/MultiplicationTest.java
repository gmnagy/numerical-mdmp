package hu.nsmdmp.matrix.operation;

import hu.nsmdmp.matrix.Matrix;
import hu.nsmdmp.vector.Vector;

import org.junit.Assert;
import org.junit.Test;

public class MultiplicationTest {

	@Test
	public void testMatrixVector1() {
		double[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
		Matrix M = new Matrix(matrix);
		double[] vector = { -2, 1, 0 };
		Vector V = new Vector(vector);

		Vector result = Multiplication.multiply(M, V);

		double[] expected = { 0, -3, -6, -9 };
		Vector ev = new Vector(expected);

		if (!ev.equals(result)) {
			System.out.println(ev);
			System.err.println(result);

			Assert.assertTrue(false);
		}
	}

	@Test
	public void testMatrixVector2() {
		double[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
		Matrix M = new Matrix(matrix);
		double[] vector = { -2, 1, 0, 1 };
		Vector V = new Vector(vector);

		try {
			Multiplication.multiply(M, V);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());

			return;
		}

		Assert.assertTrue(false);
	}

	@Test
	public void testMatrixVector3() {
		double[][] matrix = { { 1, 1, 1, 1 }, { 0, 1, 0, 1 }, { 0, 0, 2, 2 } };
		Matrix M = new Matrix(matrix);
		double[] vector = { 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0 };
		Vector V = new Vector(vector);

		Vector result = Multiplication.multiply(M, V);

		double[] expected = { 1, 2.0 / 4.0, 1 };
		Vector ev = new Vector(expected);

		if (!ev.equals(result)) {
			System.out.println(ev);
			System.err.println(result);

			Assert.assertTrue(false);
		}
	}

	/**
	 * A * B = C
	 * 
	 */
	@Test
	public void testMatrixMatrix1() {
		double[][] matrix1 = { { 1, 1, 1 }, { 2, 2, 2 }, { 3, 3, 3 } };
		Matrix M1 = new Matrix(matrix1);
		double[][] matrix2 = { { 3, 3, 3 }, { 1, 1, 1 }, { 2, 2, 2 } };
		Matrix M2 = new Matrix(matrix2);

		Matrix result = Multiplication.multiply(M1, M2);

		double[][] expected = { { 6, 6, 6 }, { 12, 12, 12 }, { 18, 18, 18 } };
		Matrix em = new Matrix(expected);

		if (!em.equals(result)) {
			System.out.println(em);
			System.err.println(result);

			Assert.assertTrue(false);
		}
	}

	/**
	 * A * B = C
	 * 
	 */
	@Test
	public void testMatrixMatrix2() {
		double[][] matrix1 = { { 1, 1, 1 }, { 2, 2, 2 }, { 3, 3, 3 } };
		Matrix M1 = new Matrix(matrix1);
		double[][] matrix2 = { { 3, 3 }, { 1, 1 }, { 2, 2 } };
		Matrix M2 = new Matrix(matrix2);

		Matrix result = Multiplication.multiply(M1, M2);

		double[][] expected = { { 6, 6 }, { 12, 12 }, { 18, 18 } };
		Matrix em = new Matrix(expected);

		if (!em.equals(result)) {
			System.out.println(em);
			System.err.println(result);

			Assert.assertTrue(false);
		}
	}

	/**
	 * A * B = C
	 * 
	 */
	@Test
	public void testMatrixMatrix3() {
		double[][] matrix1 = { { 1, 1, 1 }, { 2, 2, 2 } };
		Matrix M1 = new Matrix(matrix1);
		double[][] matrix2 = { { 3, 3, 3, 3 }, { 1, 1, 1, 1 }, { 2, 2, 2, 2 } };
		Matrix M2 = new Matrix(matrix2);

		Matrix result = Multiplication.multiply(M1, M2);

		double[][] expected = { { 6, 6, 6, 6 }, { 12, 12, 12, 12 } };
		Matrix em = new Matrix(expected);

		if (!em.equals(result)) {
			System.out.println(em);
			System.err.println(result);

			Assert.assertTrue(false);
		}
	}
}
