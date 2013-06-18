package hu.nsmdmp2.numerics.matrix;

import static hu.nsmdmp2.utils.Converters.primitiveToDoubleValue;
import static hu.nsmdmp2.utils.Converters.toPrimitiveDoubleArray;
import static org.junit.Assert.assertArrayEquals;
import hu.nsmdmp2.numerics.Value;

import org.junit.Assert;
import org.junit.Test;

public class InverseTest {

	@Test
	public void test1() {

		Value[][] A = primitiveToDoubleValue(new double[][] { { 2, 4 }, { 2, 1 } });

		Value[][] inverse = MatrixMath.inverse(A);

		Value[][] expected = primitiveToDoubleValue(new double[][] { { -1.0 / 6, 2.0 / 3.0 }, { 1.0 / 3.0, -1.0 / 3.0 } });

		int i = 0;
		for (Value[] array : expected) {
			assertArrayEquals("row = " + i + ": ", toPrimitiveDoubleArray(array), toPrimitiveDoubleArray(inverse[i]), 0.0001);
			i++;
		}

	}

	@Test
	public void test2() {

		Value[][] A = primitiveToDoubleValue(new double[][] { { 1.0, 2.0, 3.0 }, { 4.0, 2.0, 2.0 }, { 5.0, 1.0, 7.0 } });

		Value[][] inverse = MatrixMath.inverse(A);

		Value[][] expected = primitiveToDoubleValue(new double[][] { { -(2d / 7d), 11d / 42d, 1d / 21d }, { 3d / 7d, 4d / 21d, -(5d / 21d) },
				{ 1d / 7d, -(3d / 14d), 1d / 7d } });

		int i = 0;
		for (Value[] array : expected) {
			assertArrayEquals("row = " + i + ": ", toPrimitiveDoubleArray(array), toPrimitiveDoubleArray(inverse[i]), 0.0001);
			i++;
		}

	}

	@Test
	public void test3() {

		Value[][] A = primitiveToDoubleValue(new double[][] { { 1.0, 2.0 }, { 3.0, 4.0 }, { 5.0, 6.0 } });

		Value[][] inverse = MatrixMath.inverse(A);

		Value[][] expected = primitiveToDoubleValue(new double[][] { { -(4d / 3d), -(1d / 3d), 2d / 3d }, { 13d / 12d, 1d / 3d, -(5d / 12d) } });

		int i = 0;
		for (Value[] array : expected) {
			assertArrayEquals("row = " + i + ": ", toPrimitiveDoubleArray(array), toPrimitiveDoubleArray(inverse[i]), 0.0001);
			i++;
		}

	}

	@Test
	public void test4() {

		Value[][] A = primitiveToDoubleValue(new double[][] { { 1.0, 2.0, 2.0 }, { 3.0, 1.0, 4.0 } });

		Value[][] inverse = MatrixMath.inverse(A);

		Value[][] expected = primitiveToDoubleValue(new double[][] { { -(1d / 5d), 14d / 65d }, { 3d / 5d, -(17d / 65d) }, { 0d, 2d / 13d } });

		int i = 0;
		for (Value[] array : expected) {
			assertArrayEquals("row = " + i + ": ", toPrimitiveDoubleArray(array), toPrimitiveDoubleArray(inverse[i]), 0.0001);
			i++;
		}

	}

	@Test
	public void test5() {

		Value[][] A = primitiveToDoubleValue(new double[][] { { 1.0, 2.0, 3.0 }, { 4.0, 5.0, 6.0 }, { 7.0, 8.0, 9.0 } });

		try {
			MatrixMath.inverse(A);

		} catch (RuntimeException e) {
			Assert.assertEquals("Matrix is singular.", e.getMessage());

			return;
		}

		Assert.assertTrue("Szingularis matrixot nem lehet invertalni!!! ", false);
	}
}
