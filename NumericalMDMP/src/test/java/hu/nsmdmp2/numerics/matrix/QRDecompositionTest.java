package hu.nsmdmp2.numerics.matrix;

import static hu.nsmdmp2.utils.Converters.primitiveToDoubleValue;
import static hu.nsmdmp2.utils.Converters.toPrimitiveDoubleArray;
import static org.junit.Assert.assertArrayEquals;
import hu.nsmdmp2.numerics.Value;

import org.junit.Test;

public class QRDecompositionTest {

	/**
	 * A = QR.
	 */
	@Test
	public void test() {

		Value[][] A = primitiveToDoubleValue(new double[][] { { 12.0, -51.0, 4.0 }, { 6.0, 167.0, -68.0 }, { -4.0, 24.0, -41.0 } });

		QRDecomposition qr = new QRDecomposition(A);

		Value[][] R = primitiveToDoubleValue(new double[][] { { -14.0, -21.0, 14.0 }, { 0.0, -175.0, 70.0 }, { 0.0, 0.0, 35.0 } });

		int i = 0;
		for (Value[] array : R) {
			assertArrayEquals("row = " + i + ": ", toPrimitiveDoubleArray(array), toPrimitiveDoubleArray(qr.getR()[i]), 0.0001);
			i++;
		}

		Value[][] Q = primitiveToDoubleValue(new double[][] { { -6.0 / 7.0, 69.0 / 175.0, -58.0 / 175.0 }, { -3.0 / 7.0, -158.0 / 175.0, 6.0 / 175.0 },
				{ 2.0 / 7.0, -6.0 / 35.0, -33.0 / 35.0 } });

		i = 0;
		for (Value[] array : Q) {
			assertArrayEquals("row = " + i + ": ", toPrimitiveDoubleArray(array), toPrimitiveDoubleArray(qr.getQ()[i]), 0.0001);
			i++;
		}

	}
}
