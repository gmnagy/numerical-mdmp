package hu.nsmdmp2.numerics.matrix;

import static hu.nsmdmp2.utils.Converters.primitiveToDoubleValue;
import static hu.nsmdmp2.utils.Converters.toPrimitiveDoubleArray;
import static org.junit.Assert.assertArrayEquals;
import hu.nsmdmp2.numerics.Value;

import org.junit.Test;

public class LUDecompositionTest {

	/**
	 * A = LU.
	 */
	@Test
	public void test1() {
		Value[][] A = primitiveToDoubleValue(new double[][] { { 4.0, 3.0 }, { 6.0, 3.0 } });

		LUDecomposition lu = new LUDecomposition(A);

		Value[][] L = primitiveToDoubleValue(new double[][] { { 1.0, 0.0 }, { 2.0 / 3.0, 1.0 } });

		int i = 0;
		for (Value[] array : L) {
			assertArrayEquals("row = " + i + ": ", toPrimitiveDoubleArray(array), toPrimitiveDoubleArray(lu.getL()[i]), 0.0001);
			i++;
		}

		Value[][] U = primitiveToDoubleValue(new double[][] { { 6.0, 3.0 }, { 0.0, 1.0 } });

		i = 0;
		for (Value[] array : U) {
			assertArrayEquals("row = " + i + ": ", toPrimitiveDoubleArray(array), toPrimitiveDoubleArray(lu.getU()[i]), 0.0001);
			i++;
		}
	}
}
