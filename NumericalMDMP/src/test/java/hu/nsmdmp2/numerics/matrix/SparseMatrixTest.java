package hu.nsmdmp2.numerics.matrix;

import static hu.nsmdmp2.utils.Converters.primitiveToDoubleValue;
import static org.junit.Assert.assertArrayEquals;
import hu.nsmdmp2.numerics.Value;

import org.junit.Test;

public class SparseMatrixTest {

	@Test
	public void test() {
		Value[][] M = primitiveToDoubleValue(new double[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 0, 7, 0 }, { 8, 9, 0 } });

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
