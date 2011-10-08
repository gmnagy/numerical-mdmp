package hu.nsmdmp.matrix.operation;

import hu.nsmdmp.ApfloatUtils;
import hu.nsmdmp.math.Variation;
import hu.nsmdmp.matrix.Matrix;
import hu.nsmdmp.matrixfactory.MatrixFactory;
import hu.nsmdmp.utils.Converters;
import hu.nsmdmp.vector.Vector;

import org.apfloat.Apfloat;
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

	@Test
	public void testConverterMatrix1b() {
		Matrix A = new Matrix(new double[][] { { 1, 1, 1 }, { -1, 0, 1 }, { -1, -1, -1 } });
		Matrix B = new Matrix(new double[][] { { 1, 1, 1 }, { -1, 0, 1 }, { -1, -1, -1 } });

		Matrix T = MatrixMath.getTransformationMatrix(A, B);
		System.out.println(T);

		Matrix em = new Matrix(new double[][] { { 1, 0, 0 }, { 0, 1, 0 }, { -1, 0, 0 } });

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

	@Test
	public void testConverterMatrix4() {
		Matrix A = new Matrix(new double[][] { { 0, -1, 2 }, { 4, 1, 2 } });
		Matrix B = new Matrix(new double[][] { { 8, 1, 6 }, { 12, -2, 16 } });

		Matrix T = MatrixMath.getTransformationMatrix(A, B);

		Vector x = new Vector(new double[] { 1, 2, 2 });

		Vector a = MatrixMath.multiply(A, x);
		Vector b = MatrixMath.multiply(B, x);
		Vector bb = MatrixMath.multiply(T, a);

		if (!b.equals(bb)) {
			System.out.println(b);
			System.err.println(bb);

			Assert.assertTrue(false);
		}
	}

	@Test
	public void testConverterMatrix5() {
//		Matrix A = new Matrix(new double[][] { { 1, 1, 1, 1 }, { -1, 0, 1, -1 }, { -1, -1, -1, 0 } });
//		Matrix B = new Matrix(new double[][] { { 1, 1, 1, 1 }, { -1, 0, 1, -1 }, { -1, -1, -1, 0 } });
		Matrix A = new Matrix(new double[][] { { 1, 1, 1 }, { -1, 0, -1 }, { -1, -1, 0 } });
		Matrix B = new Matrix(new double[][] { { 1, 1, 1 }, { -1, 0, -1 }, { -1, -1, 0 } });

		Matrix T = MatrixMath.getTransformationMatrix(A, B);
		System.out.println(T);

		Vector x = new Vector(new double[] { 1, 2, 2, 2 });

		Vector a = MatrixMath.multiply(A, x);
		System.out.println("a :" + a);
		Vector b = MatrixMath.multiply(B, x);
		System.out.println("b :" + b);
		Vector bb = MatrixMath.multiply(T, a);
		System.out.println("b':" + bb);

//		if (!b.equals(bb)) {
//			System.out.println(b);
//			System.err.println(bb);
//
//			Assert.assertTrue(false);
//		}
	}

	@Test
	public void testMonToCheb1() {
		int maxOrder = 1;
		Apfloat[][] vectorSet = Converters.convert(new double[][] { { 0, 1, 2 }, { 0, 1, 2 } });

		Matrix mon = MatrixFactory.getMonomialMatrix(vectorSet, maxOrder);
		System.out.println(mon);
		System.out.println();
		Matrix cheb1 = MatrixFactory.getChebyshevTMatrix(vectorSet, maxOrder);
		System.out.println(cheb1);

		Matrix T = MatrixMath.getTransformationMatrix(mon, cheb1);
		System.out.println(T);
		System.out.println();

		Matrix C = MatrixMath.multiply(T, mon);
		System.out.println(C);

		int variationNum = Variation.getVariationsNumber(vectorSet);
		Vector x = distribution(variationNum);

		Vector bMon = MatrixMath.multiply(mon, x);
		System.out.println(bMon);

		Vector bCheb1 = MatrixMath.multiply(cheb1, x);
		System.out.println(bCheb1);

		Vector bCheb1_1 = MatrixMath.multiply(T, bMon);
		System.out.println(bCheb1_1);
	}

	private Vector distribution(final int n) {
		Vector D = new Vector(n);

		for (int i = 0; i < n; i++) {
			D.set(i, ApfloatUtils.ONE);
		}

		return D;
	}
}
