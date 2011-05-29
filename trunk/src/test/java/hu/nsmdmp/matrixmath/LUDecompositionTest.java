package hu.nsmdmp.matrixmath;

import hu.nsmdmp.matrices.IMatrix;
import hu.nsmdmp.matrices.Matrix;
import hu.nsmdmp.matrices.MatrixUtils;
import hu.nsmdmp.utils.Converters;

import org.junit.Assert;
import org.junit.Test;

public class LUDecompositionTest {

	/**
	 * A = LU.
	 */
	@Test
	public void test1() {
		double[][] M = { { 4, 3 }, { 6, 3 } };
		IMatrix A = new Matrix(M);

		LUDecomposition lu = new LUDecomposition(A);

		double[][] L = { { 1, 0 }, { 2.0 / 3.0, 1 } };
		if (!MatrixUtils.equals(Converters.convert(L), lu.getL().getMatrix())) {
			System.out.println(MatrixUtils.print(L));
			System.out.println(MatrixUtils.print(lu.getL().getMatrix()));

			Assert.assertTrue("L: ", false);
		}

		double[][] U = { { 6, 3 }, { 0, 1 } };
		if (!MatrixUtils.equals(Converters.convert(U), lu.getU().getMatrix())) {
			System.out.println(MatrixUtils.print(U));
			System.out.println(MatrixUtils.print(lu.getU().getMatrix()));

			Assert.assertTrue("U: ", false);
		}
	}
}
