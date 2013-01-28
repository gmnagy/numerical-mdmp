package hu.nsmdmp.moments;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class ArrayCombinationTest {

	@Test
	public void testCombinations1() {

		String[] result = MultivariateMoments.combinations(new int[] { 11, 22 }, 1);

		assertArrayEquals(new String[] { "11", "22" }, result);
	}

	@Test
	public void testCombinations2() {

		String[] result = MultivariateMoments.combinations(new int[] { 11, 22 }, 2);

		assertArrayEquals(new String[] { "1122" }, result);
	}
}
