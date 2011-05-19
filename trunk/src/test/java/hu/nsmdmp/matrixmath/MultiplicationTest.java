package hu.nsmdmp.matrixmath;

import hu.nsmdmp.matrices.MatrixUtils;
import hu.nsmdmp.utils.Converters;

import org.apfloat.Apfloat;
import org.junit.Assert;
import org.junit.Test;

public class MultiplicationTest {

	@Test
	public void test() {
		double[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
		double[] vector = { -2, 1, 0 };

		Apfloat[] result = Multiplication.multiply(Converters.convert(matrix), Converters.convert(vector));

		double[] expected = { 0, -3, -6, -9 };

		if (!MatrixUtils.equals(result, Converters.convert(expected))) {
			System.out.println(MatrixUtils.print(result));
			System.out.println(MatrixUtils.print(expected));

			Assert.assertTrue(false);
		}
	}

	@Test
	public void test2() {
		double[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
		double[] vector = { -2, 1, 0, 1 };

		try {
			Multiplication.multiply(Converters.convert(matrix), Converters.convert(vector));
		} catch (MatrixMathException e) {
			System.out.println(e.getMessage());

			return;
		}

		Assert.assertTrue(false);
	}

	@Test
	public void test3() {
		double[][] matrix = { { 1, 1, 1, 1 }, { 0, 1, 0, 1 }, { 0, 0, 2, 2 } };
		double[] vector = { 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0 };

		Apfloat[] result = Multiplication.multiply(Converters.convert(matrix), Converters.convert(vector));

		double[] expected = { 1, 2.0 / 4.0, 1 };

		if (!MatrixUtils.equals(result, Converters.convert(expected))) {
			System.out.println(MatrixUtils.print(result));
			System.out.println(MatrixUtils.print(expected));

			Assert.assertTrue(false);
		}
	}
}
