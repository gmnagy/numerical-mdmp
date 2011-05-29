package hu.nsmdmp.matrixmath;

import hu.nsmdmp.matrices.IMatrix;
import hu.nsmdmp.matrices.Matrix;
import hu.nsmdmp.matrices.MatrixUtils;

import org.junit.Assert;
import org.junit.Test;

public class QRDecompositionTest {

	/**
	 * A = QR.
	 */
	@Test
	public void test() {
		double[][] m = { { 12, -51, 4 }, { 6, 167, -68 }, { -4, 24, -41 } };
		IMatrix A = new Matrix(m);

		QRDecomposition qr = new QRDecomposition(A);

		double[][] r = { { -14, -21, 14 }, { 0, -175, 70 }, { 0, 0, 35 } };
		IMatrix R = new Matrix(r);
		if (!MatrixUtils.equals(R, qr.getR())) {
			System.out.println(R);
			System.out.println(qr.getR());

			Assert.assertTrue("R: ", false);
		}

		double[][] q = { { -6.0 / 7.0, 69.0 / 175.0, -58.0 / 175.0 }, { -3.0 / 7.0, -158.0 / 175.0, 6.0 / 175.0 }, { 2.0 / 7.0, -6.0 / 35.0, -33.0 / 35.0 } };
		IMatrix Q = new Matrix(q);
		if (!MatrixUtils.equals(Q, qr.getQ())) {
			System.out.println(Q);
			System.out.println(qr.getQ());

			Assert.assertTrue("Q: ", false);
		}
	}
}
