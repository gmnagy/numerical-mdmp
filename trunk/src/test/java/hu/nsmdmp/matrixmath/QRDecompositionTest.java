package hu.nsmdmp.matrixmath;

import hu.nsmdmp.matrices.IMatrix;
import hu.nsmdmp.matrices.Matrix;
import hu.nsmdmp.matrices.MatrixUtils;
import hu.nsmdmp.utils.Converters;

import org.junit.Assert;
import org.junit.Test;

public class QRDecompositionTest {

	/**
	 * A = QR.
	 */
	@Test
	public void test() {
		double[][] M = { { 12, -51, 4 }, { 6, 167, -68 }, { -4, 24, -41 } };
		IMatrix A = new Matrix(M);

		QRDecomposition qr = new QRDecomposition(A);

		double[][] R = { { -14, -21, 14 }, { 0, -175, 70 }, { 0, 0, 35 } };
		if (!MatrixUtils.equals(Converters.convert(R), qr.getR().getMatrix())) {
			System.out.println(MatrixUtils.print(R));
			System.out.println(MatrixUtils.print(qr.getR().getMatrix()));

			Assert.assertTrue("R: ", false);
		}

		double[][] Q = { { -6.0 / 7.0, 69.0 / 175.0, -58.0 / 175.0 }, { -3.0 / 7.0, -158.0 / 175.0, 6.0 / 175.0 }, { 2.0 / 7.0, -6.0 / 35.0, -33.0 / 35.0 } };
		if (!MatrixUtils.equals(Converters.convert(Q), qr.getQ().getMatrix())) {
			System.out.println(MatrixUtils.print(Q));
			System.out.println(MatrixUtils.print(qr.getQ().getMatrix()));

			Assert.assertTrue("Q: ", false);
		}
	}
}
