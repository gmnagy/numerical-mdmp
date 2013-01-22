package hu.nsmdmp.tools;

import static hu.nsmdmp.tools.SetVariationIterator.generateSetVariation;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class SetVariationTest {

	@Test
	public void test1() {

		Long[][] set = { { 1l, 2l, 3l, 4l }, { 0l, 1l, 2l, 3l } };

		Long[][] result = generateSetVariation(set);

		Long[][] expected = { //
				{ 1l, 0l }, { 2l, 0l }, { 3l, 0l }, { 4l, 0l }, //
				{ 1l, 1l }, { 2l, 1l }, { 3l, 1l }, { 4l, 1l }, //
				{ 1l, 2l }, { 2l, 2l }, { 3l, 2l }, { 4l, 2l }, //
				{ 1l, 3l }, { 2l, 3l }, { 3l, 3l }, { 4l, 3l } };

		int i = 0;
		for (Long[] array : expected) {
			assertArrayEquals("row = " + i + ": ", array, result[i]);
			i++;
		}
	}

	@Test
	public void test2() {

		Long[][] set = { { 1l, 2l, 3l }, { 1l, 2l }, { 5l }, { 6l, 7l } };

		Long[][] result = generateSetVariation(set);

		Long[][] expected = { //
				{ 1l, 1l, 5l, 6l }, { 2l, 1l, 5l, 6l }, { 3l, 1l, 5l, 6l }, //
				{ 1l, 2l, 5l, 6l }, { 2l, 2l, 5l, 6l }, { 3l, 2l, 5l, 6l }, //
				{ 1l, 1l, 5l, 7l }, { 2l, 1l, 5l, 7l }, { 3l, 1l, 5l, 7l }, //
				{ 1l, 2l, 5l, 7l }, { 2l, 2l, 5l, 7l }, { 3l, 2l, 5l, 7l } };

		int i = 0;
		for (Long[] array : expected) {
			assertArrayEquals("row = " + i + ": ", array, result[i]);
			i++;
		}
	}
}
