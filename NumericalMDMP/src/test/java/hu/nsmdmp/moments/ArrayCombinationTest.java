package hu.nsmdmp.moments;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class ArrayCombinationTest {

	@Test
	public void testCombinations1() {

		SubSet[] result = MultivariateMoments.combinations(new int[] { 0, 1 }, 1, new int[] { 0, 1 });

		assertEquals(2, result.length);
		assertArrayEquals(new SubSet[] { new SubSet(0), new SubSet(1) }, result);
	}

	@Test
	public void testCombinations2() {

		SubSet[] result = MultivariateMoments.combinations(new int[] { 0, 1, 3 }, 2, new int[] { 0, 1, 3 });

		assertEquals(3, result.length);
		assertArrayEquals(new SubSet[] { new SubSet(0, 1), new SubSet(0, 3), new SubSet(1, 3) }, result);
	}
}
