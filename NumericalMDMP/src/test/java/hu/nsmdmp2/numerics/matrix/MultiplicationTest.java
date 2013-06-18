package hu.nsmdmp2.numerics.matrix;

import static hu.nsmdmp2.numerics.matrix.Multiplication.multiply;
import static hu.nsmdmp2.utils.Converters.primitiveToDoubleValue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import hu.nsmdmp2.numerics.Value;

import org.junit.Test;

public class MultiplicationTest {

	@Test
	public void testMatrixVector1() {

		Value[][] M = primitiveToDoubleValue(new double[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } });
		Value[] V = primitiveToDoubleValue(new double[] { -2, 1, 0 });

		Value[] result = multiply(M, V);

		Value[] expected = primitiveToDoubleValue(new double[] { 0, -3, -6, -9 });

		assertArrayEquals(expected, result);
	}

	@Test
	public void testMatrixVector2() {

		Value[][] M = primitiveToDoubleValue(new double[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } });
		Value[] V = primitiveToDoubleValue(new double[] { -2, 1, 0, 1 });

		try {
			multiply(M, V);
		} catch (IllegalArgumentException e) {
			return;
		}

		assertTrue(false);
	}

	@Test
	public void testMatrixVector3() {

		Value[][] M = primitiveToDoubleValue(new double[][] { { 1, 1, 1, 1 }, { 0, 1, 0, 1 }, { 0, 0, 2, 2 } });
		Value[] V = primitiveToDoubleValue(new double[] { 1.0 / 4, 1.0 / 4, 1.0 / 4, 1.0 / 4 });

		Value[] result = multiply(M, V);

		Value[] expected = primitiveToDoubleValue(new double[] { 1, 2.0 / 4, 1 });

		assertArrayEquals(expected, result);
	}

	/**
	 * A * B = C
	 * 
	 */
	@Test
	public void testMatrixMatrix1() {

		Value[][] A = primitiveToDoubleValue(new double[][] { { 1.0, 1.0, 1.0 }, { 2.0, 2.0, 2.0 }, { 3.0, 3.0, 3.0 } });
		Value[][] B = primitiveToDoubleValue(new double[][] { { 3.0, 3.0, 3.0 }, { 1.0, 1.0, 1.0 }, { 2.0, 2.0, 2.0 } });

		Value[][] result = multiply(A, B);

		Value[][] expected = primitiveToDoubleValue(new double[][] { { 6, 6, 6 }, { 12, 12, 12 }, { 18, 18, 18 } });

		int i = 0;
		for (Value[] array : expected) {
			assertArrayEquals("row = " + i + ": ", array, result[i]);
			i++;
		}
	}

	/**
	 * A * B = C
	 * 
	 */
	@Test
	public void testMatrixMatrix2() {

		Value[][] A = primitiveToDoubleValue(new double[][] { { 1.0, 1.0, 1.0 }, { 2.0, 2.0, 2.0 }, { 3.0, 3.0, 3.0 } });
		Value[][] B = primitiveToDoubleValue(new double[][] { { 3.0, 3.0 }, { 1.0, 1.0 }, { 2.0, 2.0 } });

		Value[][] result = multiply(A, B);

		Value[][] expected = primitiveToDoubleValue(new double[][] { { 6, 6 }, { 12, 12 }, { 18, 18 } });

		int i = 0;
		for (Value[] array : expected) {
			assertArrayEquals("row = " + i + ": ", array, result[i]);
			i++;
		}
	}

	/**
	 * A * B = C
	 * 
	 */
	@Test
	public void testMatrixMatrix3() {

		Value[][] A = primitiveToDoubleValue(new double[][] { { 1.0, 1.0, 1.0 }, { 2.0, 2.0, 2.0 } });
		Value[][] B = primitiveToDoubleValue(new double[][] { { 3.0, 3.0, 3.0, 3.0 }, { 1.0, 1.0, 1.0, 1.0 }, { 2.0, 2.0, 2.0, 2.0 } });

		Value[][] result = multiply(A, B);

		Value[][] expected = primitiveToDoubleValue(new double[][] { { 6, 6, 6, 6 }, { 12, 12, 12, 12 } });

		int i = 0;
		for (Value[] array : expected) {
			assertArrayEquals("row = " + i + ": ", array, result[i]);
			i++;
		}
	}
}
