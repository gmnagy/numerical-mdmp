package hu.nsmdmp.specialvectors;

import static hu.nsmdmp.specialvectors.CumProbPoisson.createCumProbPoisson;
import static hu.nsmdmp.tools.SetVariationIterator.generateSetVariation;
import static org.junit.Assert.assertArrayEquals;
import hu.nsmdmp.numerics.matrix.Vector;

import org.junit.Test;

public class CumProbPoissonTest {

	@Test
	public void test1() {

		Double[][] set = { { 0.0, 1.0, 2.0, 3.0 }, { 1.0, 2.0, 3.0, 4.0 } };
		Double[][] variation = generateSetVariation(set);

		Vector<Double> cVector = createCumProbPoisson(variation, new Double[] { 2.0, 3.0 });

		double[] expected = { 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0 };

		assertArrayEquals(expected, cVector.getDoubleArray(), 0.0001);
	}
}
