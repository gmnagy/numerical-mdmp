package hu.nsmdmp.tools;

import static hu.nsmdmp.tools.SubSequencesGenerator.getSubSequences;
import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SubSequencesGeneratorTest {

	@Test
	public void testSubSequences1() {

		List<int[]> result = getSubSequences(6, 2, 2);

		List<int[]> expected = Arrays.asList(new int[] { 0, 1 }, new int[] { 2, 3, 4, 5 });

		int i = 0;
		for (int[] array : expected) {
			assertArrayEquals("row = " + i + ": ", array, result.get(i));
			i++;
		}
	}

	@Test
	public void testSubSequences2() {

		List<int[]> result = getSubSequences(6, 3, 2);

		List<int[]> expected = Arrays.asList(new int[] { 0, 1, 2 }, new int[] { 3, 4, 5 });

		int i = 0;
		for (int[] array : expected) {
			assertArrayEquals("row = " + i + ": ", array, result.get(i));
			i++;
		}
	}

	@Test
	public void testSubSequences3() {

		List<int[]> result = getSubSequences(6, 4, 2);

		List<int[]> expected = Arrays.asList(new int[] { 0, 1, 2, 3 }, new int[] { 4, 5 });

		int i = 0;
		for (int[] array : expected) {
			assertArrayEquals("row = " + i + ": ", array, result.get(i));
			i++;
		}
	}

	@Test
	public void testSubSequences4() {

		List<int[]> result = getSubSequences(6, 2, 3);

		List<int[]> expected = Arrays.asList(new int[] { 0, 1 }, new int[] { 2, 3 }, new int[] { 4, 5 });

		int i = 0;
		for (int[] array : expected) {
			assertArrayEquals("row = " + i + ": ", array, result.get(i));
			i++;
		}
	}
}
