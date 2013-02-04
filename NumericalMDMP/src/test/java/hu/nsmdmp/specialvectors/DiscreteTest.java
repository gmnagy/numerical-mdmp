package hu.nsmdmp.specialvectors;

import static hu.nsmdmp.specialvectors.Discrete.discreteVector;
import static org.junit.Assert.assertArrayEquals;
import hu.nsmdmp.numerics.matrix.Vector;

import org.junit.Test;

public class DiscreteTest {

	@Test
	public void test() {

		Vector<Double> v = discreteVector(5, 2, 0.0, 1.0);

		assertArrayEquals(new Double[] { 0.0, 0.0, 1.0, 1.0, 1.0 }, v.getArray());
	}
}
