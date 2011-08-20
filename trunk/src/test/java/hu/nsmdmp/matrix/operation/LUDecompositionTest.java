package hu.nsmdmp.matrix.operation;

import hu.nsmdmp.matrix.Matrix;
import hu.nsmdmp.matrix.operation.LUDecomposition;

import org.junit.Assert;
import org.junit.Test;

public class LUDecompositionTest {

	/**
	 * A = LU.
	 */
	@Test
	public void test1() {
		double[][] m = { { 4, 3 }, { 6, 3 } };
		Matrix A = new Matrix(m);

		LUDecomposition lu = new LUDecomposition(A);

		double[][] l = { { 1, 0 }, { 2.0 / 3.0, 1 } };
		Matrix L = new Matrix(l);
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
}
