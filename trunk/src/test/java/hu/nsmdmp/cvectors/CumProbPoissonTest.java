package hu.nsmdmp.cvectors;

import hu.nsmdmp.ApfloatUtils;
import hu.nsmdmp.math.Variation;
import hu.nsmdmp.utils.Converters;
import hu.nsmdmp.vector.Vector;

import org.apfloat.Apfloat;
import org.junit.Assert;
import org.junit.Test;

public class CumProbPoissonTest {

	@Test
	public void test1() {
		double[][] m = { { 0, 1, 2, 3 }, { 1, 2, 3, 4 } };
		Apfloat[][] variation = Converters.convert(Variation.createVariation(Converters.convert(m)));

		Vector cVector = CVectorFactory.getCumProbPoisson(variation, new Apfloat[] { ApfloatUtils.TWO, ApfloatUtils.THREE });

		double[] expected = { 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0 };
		Vector ev = new Vector(expected);

		if (!ev.equals(cVector)) {
			System.out.println(ev);
			System.err.println(cVector);

			Assert.assertTrue(false);
		}
	}
}
