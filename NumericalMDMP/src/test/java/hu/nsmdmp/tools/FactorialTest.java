package hu.nsmdmp.tools;

import static hu.nsmdmp.tools.Math.numberOfCombination;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FactorialTest {

	@Test
	public void test1() {
		assertEquals(120, numberOfCombination(10, 3));
	}
}
