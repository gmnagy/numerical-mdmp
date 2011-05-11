package hu.nsmdmp.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class ExponentsTest {

	private final int maxOrder;

	private final int size;

	private final List<int[]> expected;

	public ExponentsTest(final int maxOrder, final int size, final List<int[]> expected) {
		this.maxOrder = maxOrder;
		this.size = size;
		this.expected = expected;
	}

	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { { 2, 2, result1() }, { 2, 3, result2() }, { 5, 3, result3() }, { 1, 2, result4() } };

		return Arrays.asList(data);
	}

	@Test
	public void test() {
		List<int[]> exps = Exponents.getExponents(maxOrder, size);

		Assert.assertTrue(Exponents.equals(exps, expected));
	}

	/**
	 * maxOrder = 2, size = 2
	 */
	private static List<int[]> result1() {
		int[][] e = { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 0, 1 }, { 1, 1 }, { 0, 2 } };

		return Arrays.asList(e);
	}

	/**
	 * maxOrder = 2, size = 3
	 */
	private static List<int[]> result2() {
		int[][] e = { { 0, 0, 0 }, { 1, 0, 0 }, { 2, 0, 0 }, { 0, 1, 0 }, { 1, 1, 0 }, { 0, 2, 0 }, { 0, 0, 1 }, { 1, 0, 1 }, { 0, 1, 1 }, { 0, 0, 2 } };

		return Arrays.asList(e);
	}

	/**
	 * maxOrder = 5, size = 3
	 */
	private static List<int[]> result3() {
		int[][] e = { { 0, 0, 0 }, { 1, 0, 0 }, { 2, 0, 0 }, { 3, 0, 0 }, { 4, 0, 0 }, { 5, 0, 0 }, { 0, 1, 0 }, { 1, 1, 0 }, { 2, 1, 0 }, { 3, 1, 0 }, { 4, 1, 0 }, { 0, 2, 0 }, { 1, 2, 0 },
				{ 2, 2, 0 }, { 3, 2, 0 }, { 0, 3, 0 }, { 1, 3, 0 }, { 2, 3, 0 }, { 0, 4, 0 }, { 1, 4, 0 }, { 0, 5, 0 }, { 0, 0, 1 }, { 1, 0, 1 }, { 2, 0, 1 }, { 3, 0, 1 }, { 4, 0, 1 }, { 0, 1, 1 },
				{ 1, 1, 1 }, { 2, 1, 1 }, { 3, 1, 1 }, { 0, 2, 1 }, { 1, 2, 1 }, { 2, 2, 1 }, { 0, 3, 1 }, { 1, 3, 1 }, { 0, 4, 1 }, { 0, 0, 2 }, { 1, 0, 2 }, { 2, 0, 2 }, { 3, 0, 2 }, { 0, 1, 2 },
				{ 1, 1, 2 }, { 2, 1, 2 }, { 0, 2, 2 }, { 1, 2, 2 }, { 0, 3, 2 }, { 0, 0, 3 }, { 1, 0, 3 }, { 2, 0, 3 }, { 0, 1, 3 }, { 1, 1, 3 }, { 0, 2, 3 }, { 0, 0, 4 }, { 1, 0, 4 }, { 0, 1, 4 },
				{ 0, 0, 5 } };

		return Arrays.asList(e);
	}

	/**
	 * maxOrder = 1, size = 2
	 */
	private static List<int[]> result4() {
		int[][] e = { { 0, 0 }, { 1, 0 }, { 0, 1 } };

		return Arrays.asList(e);
	}
}
