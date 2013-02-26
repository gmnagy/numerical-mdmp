package hu.nsmdmp.numerics.matrix;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class QRDecompositionTest {

	/**
	 * A = QR.
	 */
	@Test
	public void test() {
		Double[][] m = { { 12.0, -51.0, 4.0 }, { 6.0, 167.0, -68.0 }, { -4.0, 24.0, -41.0 } };
		Matrix<Double> A = new Matrix<Double>(m);

		QRDecomposition<Double> qr = new QRDecomposition<Double>(A);

		Double[][] r = { { -14.0, -21.0, 14.0 }, { 0.0, -175.0, 70.0 }, { 0.0, 0.0, 35.0 } };
		Matrix<Double> R = new Matrix<Double>(r);

		int i = 0;
		for (double[] array : R.toDoubleMultiArray()) {
			assertArrayEquals("row = " + i + ": ", array, qr.getR().toDoubleMultiArray()[i], 0.000001);
			i++;
		}

		Double[][] q = { { -6.0 / 7.0, 69.0 / 175.0, -58.0 / 175.0 }, { -3.0 / 7.0, -158.0 / 175.0, 6.0 / 175.0 }, { 2.0 / 7.0, -6.0 / 35.0, -33.0 / 35.0 } };
		Matrix<Double> Q = new Matrix<Double>(q);

		i = 0;
		for (double[] array : Q.toDoubleMultiArray()) {
			assertArrayEquals("row = " + i + ": ", array, qr.getQ().toDoubleMultiArray()[i], 0.000001);
			i++;
		}
	}
}
