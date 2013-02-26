package hu.nsmdmp.moments;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Ignore;
import org.junit.Test;

public class ArrayCombinationTest {

	@Ignore
	@Test
	public void testCombinations1() {

		String[] result = MultivariateMoments.combinations(new int[] { 11, 22 }, 1, new int[] { 11, 22 });

		assertArrayEquals(new String[] { "11", "22" }, result);
	}

	@Ignore
	@Test
	public void testCombinations2() {

		String[] result = MultivariateMoments.combinations(new int[] { 11, 22 }, 2, new int[] { 11, 22 });

		assertArrayEquals(new String[] { "1122" }, result);
	}
}
