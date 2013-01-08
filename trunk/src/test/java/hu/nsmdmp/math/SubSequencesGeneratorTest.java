package hu.nsmdmp.math;

import static hu.nsmdmp.math.SubSequencesGenerator.getSubSequences;
import static junit.framework.Assert.assertTrue;
import hu.nsmdmp.utils.Utils;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SubSequencesGeneratorTest {

	@Test
	public void testSubSequences1() {

		List<int[]> result = getSubSequences(6, 2, 2);
		System.out.println(Utils.print(result));

		List<int[]> expected = Arrays.asList(new int[] { 0, 1 }, new int[] { 2, 3, 4, 5 });

		assertTrue(String.format("Expected: %s, Result %s", Utils.print(expected), Utils.print(result)), Utils.equals(result, expected));
	}

	@Test
	public void testSubSequences2() {

		List<int[]> result = getSubSequences(6, 3, 2);
		System.out.println(Utils.print(result));

		List<int[]> expected = Arrays.asList(new int[] { 0, 1, 2 }, new int[] { 3, 4, 5 });

		assertTrue(String.format("Expected: %s, Result %s", Utils.print(expected), Utils.print(result)), Utils.equals(result, expected));
	}

	@Test
	public void testSubSequences3() {

		List<int[]> result = getSubSequences(6, 4, 2);
		System.out.println(Utils.print(result));

		List<int[]> expected = Arrays.asList(new int[] { 0, 1, 2, 3 }, new int[] { 4, 5 });

		assertTrue(String.format("Expected: %s, Result %s", Utils.print(expected), Utils.print(result)), Utils.equals(result, expected));
	}

	@Test
	public void testSubSequences4() {

		List<int[]> result = getSubSequences(6, 2, 3);
		System.out.println(Utils.print(result));

		List<int[]> expected = Arrays.asList(new int[] { 0, 1 }, new int[] { 2, 3 }, new int[] { 4, 5 });

		assertTrue(String.format("Expected: %s, Result %s", Utils.print(expected), Utils.print(result)), Utils.equals(result, expected));
	}
}
