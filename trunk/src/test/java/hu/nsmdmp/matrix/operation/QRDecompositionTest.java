package hu.nsmdmp.matrix.operation;

import hu.nsmdmp.ApfloatUtils;
import hu.nsmdmp.matrix.Matrix;

import org.apfloat.Apfloat;
import org.junit.Assert;
import org.junit.Test;

public class QRDecompositionTest {

	/**
	 * A = QR.
	 */
	@Test
	public void test() {
		double[][] m = { { 12, -51, 4 }, { 6, 167, -68 }, { -4, 24, -41 } };
		Matrix A = new Matrix(m);

		QRDecomposition qr = new QRDecomposition(A);

		double[][] r = { { -14, -21, 14 }, { 0, -175, 70 }, { 0, 0, 35 } };
		Matrix R = new Matrix(r);
		if (!R.equals(qr.getR())) {
			System.out.println(R);
			System.out.println(qr.getR());

			Assert.assertTrue("R: ", false);
		}

		// { { -6.0 / 7.0, 69.0 / 175.0, -58.0 / 175.0 }, { -3.0 / 7.0, -158.0 / 175.0, 6.0 / 175.0 }, { 2.0 / 7.0, -6.0 / 35.0, -33.0 / 35.0 } }
		Apfloat[][] q = {
				{ ApfloatUtils.valueOf(-6).divide(ApfloatUtils.valueOf(7)), ApfloatUtils.valueOf(69).divide(ApfloatUtils.valueOf(175)), ApfloatUtils.valueOf(-58).divide(ApfloatUtils.valueOf(175)) }, //
				{ ApfloatUtils.valueOf(-3).divide(ApfloatUtils.valueOf(7)), ApfloatUtils.valueOf(-158).divide(ApfloatUtils.valueOf(175)), ApfloatUtils.valueOf(6).divide(ApfloatUtils.valueOf(175)) }, //
				{ ApfloatUtils.valueOf(2).divide(ApfloatUtils.valueOf(7)), ApfloatUtils.valueOf(-6).divide(ApfloatUtils.valueOf(35)), ApfloatUtils.valueOf(-33).divide(ApfloatUtils.valueOf(35)) } };
		Matrix Q = new Matrix(q);
		if (!Q.equals(qr.getQ())) {
			System.out.println(Q);
			System.out.println(qr.getQ());

			Assert.assertTrue("Q: ", false);
		}
	}
}
