package hu.nsmdmp2.tools;

import static hu.nsmdmp2.tools.SetVariationIterator.generateSetVariation;
import static hu.nsmdmp2.utils.Converters.primitiveToDoubleValue;
import static org.junit.Assert.assertArrayEquals;
import hu.nsmdmp2.numerics.Value;

import org.junit.Test;

public class SetVariationTest {

	@Test
	public void test1() {

		Value[][] set = primitiveToDoubleValue(new double[][] { { 1, 2, 3, 4 }, { 0, 1, 2, 3 } });

		Value[][] result = generateSetVariation(set);

		Value[][] expected = primitiveToDoubleValue(new double[][] { //
		{ 1, 0 }, { 2, 0 }, { 3, 0 }, { 4, 0 }, //
				{ 1, 1 }, { 2, 1 }, { 3, 1 }, { 4, 1 }, //
				{ 1, 2 }, { 2, 2 }, { 3, 2 }, { 4, 2 }, //
				{ 1, 3 }, { 2, 3 }, { 3, 3 }, { 4, 3 } });

		int i = 0;
		for (Value[] array : expected) {
			assertArrayEquals("row = " + i + ": ", array, result[i]);
			i++;
		}
	}

	@Test
	public void test2() {

		Value[][] set = primitiveToDoubleValue(new double[][] { { 1, 2, 3 }, { 1, 2 }, { 5 }, { 6, 7 } });

		Value[][] result = generateSetVariation(set);

		Value[][] expected = primitiveToDoubleValue(new double[][] { //
		{ 1, 1, 5, 6 }, { 2, 1, 5, 6 }, { 3, 1, 5, 6 }, //
				{ 1, 2, 5, 6 }, { 2, 2, 5, 6 }, { 3, 2, 5, 6 }, //
				{ 1, 1, 5, 7 }, { 2, 1, 5, 7 }, { 3, 1, 5, 7 }, //
				{ 1, 2, 5, 7 }, { 2, 2, 5, 7 }, { 3, 2, 5, 7 } });

		int i = 0;
		for (Value[] array : expected) {
			assertArrayEquals("row = " + i + ": ", array, result[i]);
			i++;
		}
	}
}
