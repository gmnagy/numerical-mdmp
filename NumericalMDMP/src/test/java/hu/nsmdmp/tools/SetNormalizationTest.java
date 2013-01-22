package hu.nsmdmp.tools;

import static hu.nsmdmp.tools.SetNormalization.normalize;
import static hu.nsmdmp.utils.Converters.primitiveToDouble;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class SetNormalizationTest {

	@Test
	public void test1() {

		double[][] set = { { 1, 2, 3, 4 }, { 0, 1, 2, 3 } };

		Double[][] normalizedM = normalize(primitiveToDouble(set), Double.class);

		Double[][] expected = { { -1.0, -(1.0 / 3.0), (1.0 / 3.0), 1.0 }, { -1.0, -(1.0 / 3.0), (1.0 / 3.0), 1.0 } };

		int i = 0;
		for (Double[] array : expected) {
			assertArrayEquals("row = " + i + ": ", array, normalizedM[i]);
			i++;
		}
	}

	@Test
	public void test2() {

		double[][] set = { { 0 - 4, 1 - 4, 2 - 4, 3 - 4 }, { 0, 1, 2, 3 } };

		Double[][] normalizedM = normalize(primitiveToDouble(set), Double.class);

		Double[][] expected = { { -1.0, -(1.0 / 3.0), (1.0 / 3.0), 1.0 }, { -1.0, -(1.0 / 3.0), (1.0 / 3.0), 1.0 } };

		int i = 0;
		for (Double[] array : expected) {
			assertArrayEquals("row = " + i + ": ", array, normalizedM[i]);
			i++;
		}
	}

	@Test
	public void test3() {

		double[][] set = { { 0, 1, 2, 3 }, { 0, 1, 2, 3 } };

		Double[][] normalizedM = normalize(primitiveToDouble(set), Double.class);

		Double[][] expected = { { -1.0, -(1.0 / 3.0), (1.0 / 3.0), 1.0 }, { -1.0, -(1.0 / 3.0), (1.0 / 3.0), 1.0 } };

		int i = 0;
		for (Double[] array : expected) {
			assertArrayEquals("row = " + i + ": ", array, normalizedM[i]);
			i++;
		}
	}

	@Test
	public void test4() {

		double[][] set = { { 0, 1, 2 }, { 0, 1, 2, 3 } };

		Double[][] normalizedM = normalize(primitiveToDouble(set), Double.class);

		Double[][] expected = { { -1.0, 0.0, 1.0 }, { -1.0, -(1.0 / 3.0), (1.0 / 3.0), 1.0 } };

		int i = 0;
		for (Double[] array : expected) {
			assertArrayEquals("row = " + i + ": ", array, normalizedM[i]);
			i++;
		}
	}
}
