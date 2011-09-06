package hu.nsmdmp.matrix.operation;

import hu.nsmdmp.matrix.Matrix;

import org.junit.Assert;
import org.junit.Test;

public class ConverterMatrixTest {

	/**
	 * T*A=B. Square matrix!
	 */
	@Test
	public void testConverterMatrix1() {
		Matrix A = new Matrix(new double[][] { { 2, 4 }, { 2, 1 } });
		Matrix B = new Matrix(new double[][] { { 2, 4 }, { 2, 1 } });

		Matrix T = MatrixMath.getTransformationMatrix(A, B);

		Matrix em = new Matrix(new double[][] { { 1, 0 }, { 0, 1 } });

		if (!em.equals(T)) {
			System.out.println(em);
			System.err.println(T);

			Assert.assertTrue(false);
		}
	}

	/**
	 * T*A=B. Rectangle matrix!
	 */
	@Test
	public void testConverterMatrix2() {
		Matrix A = new Matrix(new double[][] { { 0, -1, 2 }, { 4, 1, 2 } });
		Matrix B = new Matrix(new double[][] { { 8, 1, 6 }, { 12, -2, 16 } });

		Matrix T = MatrixMath.getTransformationMatrix(A, B);

		Matrix em = new Matrix(new double[][] { { 1, 2 }, { 5, 3 } });

		if (!em.equals(T)) {
			System.out.println(em);
			System.err.println(T);

			Assert.assertTrue(false);
		}
	}

	/**
	 * T*A=B. Rectangle matrix!
	 */
	@Test
	public void testConverterMatrix3() {
		Matrix A = new Matrix(new double[][] { { 0, 4 }, { -1, 1 }, { 2, 2 } });
		Matrix B = new Matrix(new double[][] { { 8, 12 }, { 1, -2 }, { 6, 16 } });

		Matrix T = MatrixMath.getTransformationMatrix(A, B);

		Matrix em = new Matrix(new double[][] { { 5, -8, 0 }, { -1.0 / 4.0, -1, 0 }, { 9.0 / 2.0, -4, 1 } });

		if (!em.equals(T)) {
			System.out.println(em);
			System.err.println(T);

			Assert.assertTrue(false);
		}
	}
}
