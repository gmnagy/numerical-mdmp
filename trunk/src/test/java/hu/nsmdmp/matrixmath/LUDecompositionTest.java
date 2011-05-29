package hu.nsmdmp.matrixmath;

import hu.nsmdmp.matrices.IMatrix;
import hu.nsmdmp.matrices.Matrix;
import hu.nsmdmp.matrices.MatrixUtils;

import org.junit.Assert;
import org.junit.Test;

public class LUDecompositionTest {

	/**
	 * A = LU.
	 */
	@Test
	public void test1() {
		double[][] m = { { 4, 3 }, { 6, 3 } };
		IMatrix A = new Matrix(m);

		LUDecomposition lu = new LUDecomposition(A);

		double[][] l = { { 1, 0 }, { 2.0 / 3.0, 1 } };
		IMatrix L = new Matrix(l);
		if (!MatrixUtils.equals(L, lu.getL())) {
			System.out.println(L);
			System.out.println(lu.getL());

			Assert.assertTrue("L: ", false);
		}

		double[][] u = { { 6, 3 }, { 0, 1 } };
		IMatrix U = new Matrix(u);
		if (!MatrixUtils.equals(U, lu.getU())) {
			System.out.println(U);
			System.out.println(lu.getU());

			Assert.assertTrue("U: ", false);
		}
	}
}
