package hu.nsmdmp.mosek;

import hu.nsmdmp.matrices.IMatrix;
import hu.nsmdmp.matrices.Matrix;
import hu.nsmdmp.matrices.MatrixUtils;

import org.junit.Assert;
import org.junit.Test;

public class SparseMatrixTest {

	@Test
	public void test() {
		double m[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 0, 7, 0 }, { 8, 9, 0 } };
		IMatrix M = new Matrix(m);

		SparseMatrix sm = new SparseMatrix(M);

		double expectedAval[][] = { { 1, 4, 8 }, { 2, 5, 7, 9 }, { 3, 6 } };

		if (!MatrixUtils.equals(expectedAval, sm.aval)) {
			System.out.println(MatrixUtils.print(expectedAval));
			System.out.println(MatrixUtils.print(sm.aval));

			Assert.assertTrue(false);
		}

		int expectedAsub[][] = { { 0, 1, 3 }, { 0, 1, 2, 3 }, { 0, 1 } };

		if (!MatrixUtils.equals(expectedAsub, sm.asub)) {
			System.out.println(MatrixUtils.print(expectedAsub));
			System.out.println(MatrixUtils.print(sm.asub));

			Assert.assertTrue(false);
		}
	}
}
