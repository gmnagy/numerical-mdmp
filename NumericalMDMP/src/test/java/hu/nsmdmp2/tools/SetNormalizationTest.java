package hu.nsmdmp2.tools;

import static hu.nsmdmp2.tools.SetNormalization.normalize;
import static hu.nsmdmp2.utils.Converters.primitiveToDoubleValue;
import static org.junit.Assert.assertArrayEquals;
import hu.nsmdmp2.numerics.Value;

import org.junit.Test;

public class SetNormalizationTest {

	@Test
	public void test1() {

		double[][] set = { { 1, 2, 3, 4 }, { 0, 1, 2, 3 } };
		Value[][] normalizedM = normalize(primitiveToDoubleValue(set));

		double[][] expected = { { -1.0, -(1.0 / 3.0), (1.0 / 3.0), 1.0 }, { -1.0, -(1.0 / 3.0), (1.0 / 3.0), 1.0 } };

		int i = 0;
		for (Value[] array : primitiveToDoubleValue(expected)) {
			assertArrayEquals("row = " + i + ": ", array, normalizedM[i]);
			i++;
		}
	}

	@Test
	public void test2() {

		double[][] set = { { 0 - 4, 1 - 4, 2 - 4, 3 - 4 }, { 0, 1, 2, 3 } };
		Value[][] normalizedM = normalize(primitiveToDoubleValue(set));

		double[][] expected = { { -1.0, -(1.0 / 3.0), (1.0 / 3.0), 1.0 }, { -1.0, -(1.0 / 3.0), (1.0 / 3.0), 1.0 } };

		int i = 0;
		for (Value[] array : primitiveToDoubleValue(expected)) {
			assertArrayEquals("row = " + i + ": ", array, normalizedM[i]);
			i++;
		}
	}

	@Test
	public void test3() {

		double[][] set = { { 0, 1, 2, 3 }, { 0, 1, 2, 3 } };
		Value[][] normalizedM = normalize(primitiveToDoubleValue(set));

		double[][] expected = { { -1.0, -(1.0 / 3.0), (1.0 / 3.0), 1.0 }, { -1.0, -(1.0 / 3.0), (1.0 / 3.0), 1.0 } };

		int i = 0;
		for (Value[] array : primitiveToDoubleValue(expected)) {
			assertArrayEquals("row = " + i + ": ", array, normalizedM[i]);
			i++;
		}
	}

	@Test
	public void test4() {

		double[][] set = { { 0, 1, 2 }, { 0, 1, 2, 3 } };
		Value[][] normalizedM = normalize(primitiveToDoubleValue(set));

		double[][] expected = { { -1.0, 0.0, 1.0 }, { -1.0, -(1.0 / 3.0), (1.0 / 3.0), 1.0 } };

		int i = 0;
		for (Value[] array : primitiveToDoubleValue(expected)) {
			assertArrayEquals("row = " + i + ": ", array, normalizedM[i]);
			i++;
		}
	}
}
