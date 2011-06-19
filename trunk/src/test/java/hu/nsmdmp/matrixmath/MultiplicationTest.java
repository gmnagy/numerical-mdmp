package hu.nsmdmp.matrixmath;

import hu.nsmdmp.matrices.Matrix;
import hu.nsmdmp.vectors.Vector;

import org.junit.Assert;
import org.junit.Test;

public class MultiplicationTest {

	@Test
	public void test() {
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
	public void test2() {
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
	public void test3() {
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
}
