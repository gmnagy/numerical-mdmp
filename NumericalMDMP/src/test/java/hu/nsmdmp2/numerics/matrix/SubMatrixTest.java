package hu.nsmdmp2.numerics.matrix;

import static hu.nsmdmp2.numerics.matrix.MatrixMath.getSubMatrix;
import static hu.nsmdmp2.numerics.matrix.MatrixMath.transpose;
import static hu.nsmdmp2.utils.Converters.primitiveToDoubleValue;
import static hu.nsmdmp2.utils.Converters.toPrimitiveDoubleArray;
import static org.junit.Assert.assertArrayEquals;
import hu.nsmdmp2.numerics.Value;

import org.junit.Test;

public class SubMatrixTest {

	@Test
	public void test1() {
		Value[][] A = primitiveToDoubleValue(new double[][] { { 1.0, 2.0, 2.0 }, { 3.0, 1.0, 4.0 }, { 5.0, 6.0, 7.0 } });

		int[] r = { 0, 2 };
		Value[][] result = MatrixMath.getSubMatrix(A, r, 0, 2);

		Value[][] expected = primitiveToDoubleValue(new double[][] { { 1.0, 2.0, 2.0 }, { 5.0, 6.0, 7.0 } });

		int i = 0;
		for (Value[] array : expected) {
			assertArrayEquals("row = " + i + ": ", toPrimitiveDoubleArray(array), toPrimitiveDoubleArray(result[i]), 0.0001);
			i++;
		}
	}

	@Test
	public void test2() {

		Value[][] A = primitiveToDoubleValue(new double[][] { { 1.0, 2.0, 2.0 }, { 3.0, 1.0, 4.0 }, { 5.0, 6.0, 7.0 } });

		int[] r = { 0, 2 };
		Value[][] result = transpose(getSubMatrix(transpose(A), r, 0, 2));

		Value[][] expected = primitiveToDoubleValue(new double[][] { { 1.0, 2.0 }, { 3.0, 4.0 }, { 5.0, 7.0 } });

		int i = 0;
		for (Value[] array : expected) {
			assertArrayEquals("row = " + i + ": ", toPrimitiveDoubleArray(array), toPrimitiveDoubleArray(result[i]), 0.0001);
			i++;
		}

	}

}
