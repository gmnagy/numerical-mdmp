package hu.nsmdmp2.tools;

import static hu.nsmdmp2.tools.Math.numberOfCombination;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FactorialTest {

	@Test
	public void test1() {
		assertEquals(120, numberOfCombination(10, 3));
	}
}
