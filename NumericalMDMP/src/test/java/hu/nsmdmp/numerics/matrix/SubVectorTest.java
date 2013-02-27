package hu.nsmdmp.numerics.matrix;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

public class SubVectorTest {

	@Test
	public void test1() {
		Double[] m = { 1.0, 2.0, 2.0, 3.0, 1.0, 4.0, 5.0, 6.0, 7.0 };
		Vector<Double> A = new Vector<Double>(m);

		Vector<Double> resultA = A.getSubVector(2, 5);

		Double[] expected = { 2.0, 3.0, 1.0, 4.0 };
		Vector<Double> expectedA = new Vector<Double>(expected);

		assertEquals(expectedA, resultA);
	}
}
