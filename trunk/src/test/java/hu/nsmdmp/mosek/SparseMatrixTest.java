package hu.nsmdmp.mosek;

import hu.nsmdmp.matrix.Matrix;
import hu.nsmdmp.utils.Utils;

import org.junit.Assert;
import org.junit.Test;

public class SparseMatrixTest {

	@Test
	public void test() {
		double m[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 0, 7, 0 }, { 8, 9, 0 } };
		Matrix M = new Matrix(m);

		SparseMatrix sm = new SparseMatrix(M);

		double expectedAval[][] = { { 1, 4, 8 }, { 2, 5, 7, 9 }, { 3, 6 } };

		if (!Utils.equals(expectedAval, sm.aval)) {
			System.out.println(Utils.print(expectedAval));
			System.out.println(Utils.print(sm.aval));

			Assert.assertTrue(false);
		}

		int expectedAsub[][] = { { 0, 1, 3 }, { 0, 1, 2, 3 }, { 0, 1 } };

		if (!Utils.equals(expectedAsub, sm.asub)) {
			System.out.println(Utils.print(expectedAsub));
			System.out.println(Utils.print(sm.asub));

			Assert.assertTrue(false);
		}
	}
}
