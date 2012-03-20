package hu.nsmdmp.matrix.operation;

import hu.nsmdmp.ApfloatUtils;
import hu.nsmdmp.matrix.Matrix;

import org.apfloat.Apfloat;
import org.junit.Assert;
import org.junit.Test;

public class LUDecompositionTest {

	private static final Apfloat ZERO = ApfloatUtils.ZERO;
	private static final Apfloat ONE = ApfloatUtils.ONE;
	private static final Apfloat TWO = ApfloatUtils.valueOf(2);
	private static final Apfloat THREE = ApfloatUtils.valueOf(3);

	/**
	 * A = LU.
	 */
	@Test
	public void test1() {
		double[][] m = { { 4, 3 }, { 6, 3 } };
		Matrix A = new Matrix(m);

		LUDecomposition lu = new LUDecomposition(A);

		// { { 1, 2 }, { 2.0 / 3.0, 1 } }
		Matrix L = new Matrix(new Apfloat[][] { { ONE, ZERO }, { TWO.divide(THREE), ONE } });
		if (!L.equals(lu.getL())) {
			System.out.println(L);
			System.out.println(lu.getL());

			Assert.assertTrue("L: ", false);
		}

		double[][] u = { { 6, 3 }, { 0, 1 } };
		Matrix U = new Matrix(u);
		if (!U.equals(lu.getU())) {
			System.out.println(U);
			System.out.println(lu.getU());

			Assert.assertTrue("U: ", false);
		}
	}

	@Test
	public void test2() {
		Matrix A = new Matrix(new double[][] { { 0, -1, 2 }, { 4, 11, 2 }, { 0, 0, 0 } });

		LUDecomposition lu = new LUDecomposition(A);

		System.out.println(lu.getU());
	}
}
