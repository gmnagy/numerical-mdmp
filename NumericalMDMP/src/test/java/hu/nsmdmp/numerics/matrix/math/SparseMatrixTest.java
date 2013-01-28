package hu.nsmdmp.numerics.matrix.math;

import static org.junit.Assert.assertArrayEquals;
import hu.nsmdmp.numerics.matrix.Matrix;

import org.junit.Test;

public class SparseMatrixTest {

	@Test
	public void test() {
		Double m[][] = { { 1.0, 2.0, 3.0 }, { 4.0, 5.0, 6.0 }, { 0.0, 7.0, 0.0 }, { 8.0, 9.0, 0.0 } };
		Matrix<Double> M = new Matrix<Double>(m);

		SparseMatrix sm = new SparseMatrix(M);

		double expectedAval[][] = { { 1, 4, 8 }, { 2, 5, 7, 9 }, { 3, 6 } };

		int i = 0;
		for (double[] array : expectedAval) {
			assertArrayEquals("row = " + i + ": ", array, sm.aval[i], 0);
			i++;
		}

		int expectedAsub[][] = { { 0, 1, 3 }, { 0, 1, 2, 3 }, { 0, 1 } };

		i = 0;
		for (int[] array : expectedAsub) {
			assertArrayEquals("row = " + i + ": ", array, sm.asub[i]);
			i++;
		}
	}
}
