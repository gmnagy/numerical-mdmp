package hu.nsmdmp.numerics.matrix.math;

import static hu.nsmdmp.numerics.matrix.math.Multiplication.multiply;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.numerics.matrix.Vector;

import org.junit.Test;

public class MultiplicationTest {

	@Test
	public void testMatrixVector1() {

		Matrix<Double> M = new Matrix<Double>(new Double[][] { { 1.0, 2.0, 3.0 }, { 4.0, 5.0, 6.0 }, { 7.0, 8.0, 9.0 }, { 10.0, 11.0, 12.0 } });
		Vector<Double> V = new Vector<Double>(new Double[] { -2.0, 1.0, 0.0 });

		Vector<Double> result = multiply(M, V);

		Vector<Double> expected = new Vector<Double>(new Double[] { 0.0, -3.0, -6.0, -9.0 });

		assertArrayEquals(expected.getArray(), result.getArray());
	}

	@Test
	public void testMatrixVector2() {

		Matrix<Double> M = new Matrix<Double>(new Double[][] { { 1.0, 2.0, 3.0 }, { 4.0, 5.0, 6.0 }, { 7.0, 8.0, 9.0 }, { 10.0, 11.0, 12.0 } });
		Vector<Double> V = new Vector<Double>(new Double[] { -2.0, 1.0, 0.0, 1.0 });

		try {
			multiply(M, V);
		} catch (IllegalArgumentException e) {
			return;
		}

		assertTrue(false);
	}

	@Test
	public void testMatrixVector3() {

		Matrix<Double> M = new Matrix<Double>(new Double[][] { { 1.0, 1.0, 1.0, 1.0 }, { 0.0, 1.0, 0.0, 1.0 }, { 0.0, 0.0, 2.0, 2.0 } });
		Vector<Double> V = new Vector<Double>(new Double[] { 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0 });

		Vector<Double> result = multiply(M, V);

		Vector<Double> expected = new Vector<Double>(new Double[] { 1.0, 2.0 / 4.0, 1.0 });

		assertArrayEquals(expected.getArray(), result.getArray());
	}

	/**
	 * A * B = C
	 * 
	 */
	@Test
	public void testMatrixMatrix1() {

		Matrix<Double> M1 = new Matrix<Double>(new Double[][] { { 1.0, 1.0, 1.0 }, { 2.0, 2.0, 2.0 }, { 3.0, 3.0, 3.0 } });
		Matrix<Double> M2 = new Matrix<Double>(new Double[][] { { 3.0, 3.0, 3.0 }, { 1.0, 1.0, 1.0 }, { 2.0, 2.0, 2.0 } });

		Matrix<Double> result = multiply(M1, M2);

		Double[][] expected = { { 6.0, 6.0, 6.0 }, { 12.0, 12.0, 12.0 }, { 18.0, 18.0, 18.0 } };

		int i = 0;
		for (Double[] array : expected) {
			assertArrayEquals("row = " + i + ": ", array, result.getRow(i));
			i++;
		}
	}

	/**
	 * A * B = C
	 * 
	 */
	@Test
	public void testMatrixMatrix2() {

		Matrix<Double> M1 = new Matrix<Double>(new Double[][] { { 1.0, 1.0, 1.0 }, { 2.0, 2.0, 2.0 }, { 3.0, 3.0, 3.0 } });
		Matrix<Double> M2 = new Matrix<Double>(new Double[][] { { 3.0, 3.0 }, { 1.0, 1.0 }, { 2.0, 2.0 } });

		Matrix<Double> result = multiply(M1, M2);

		Double[][] expected = { { 6.0, 6.0 }, { 12.0, 12.0 }, { 18.0, 18.0 } };

		int i = 0;
		for (Double[] array : expected) {
			assertArrayEquals("row = " + i + ": ", array, result.getRow(i));
			i++;
		}
	}

	/**
	 * A * B = C
	 * 
	 */
	@Test
	public void testMatrixMatrix3() {

		Matrix<Double> M1 = new Matrix<Double>(new Double[][] { { 1.0, 1.0, 1.0 }, { 2.0, 2.0, 2.0 } });
		Matrix<Double> M2 = new Matrix<Double>(new Double[][] { { 3.0, 3.0, 3.0, 3.0 }, { 1.0, 1.0, 1.0, 1.0 }, { 2.0, 2.0, 2.0, 2.0 } });

		Matrix<Double> result = multiply(M1, M2);

		Double[][] expected = { { 6.0, 6.0, 6.0, 6.0 }, { 12.0, 12.0, 12.0, 12.0 } };

		int i = 0;
		for (Double[] array : expected) {
			assertArrayEquals("row = " + i + ": ", array, result.getRow(i));
			i++;
		}
	}
}
