package hu.nsmdmp.cvectors;

import hu.nsmdmp.matrixfactory.Variation;
import hu.nsmdmp.utils.Converters;
import hu.nsmdmp.vector.Vector;

import org.apfloat.Apfloat;
import org.junit.Assert;
import org.junit.Test;

public class StairsCVectorTest {

	@Test
	public void test() {
		double[][] m = { { 0, 1, 2 }, { 0, 1, 2 }, { 0, 1, 2 } };
		Apfloat[][] variation = Variation.createVariation(Converters.convert(m));

		Vector cVector = CVectorFactory.getStairsCVector(variation);

		double[] expected = { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
		Vector ev = new Vector(expected);

		if (!ev.equals(cVector)) {
			System.out.println(ev);
			System.err.println(cVector);

			Assert.assertTrue(false);
		}
	}

	@Test
	public void test2() {
		double[][] m = { { 0, 1, 2, 3 }, { 1, 2, 3, 4 } };
		Apfloat[][] variation = Variation.createVariation(Converters.convert(m));

		Vector cVector = CVectorFactory.getStairsCVector(variation);

		double[] expected = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
		Vector ev = new Vector(expected);

		if (!ev.equals(cVector)) {
			System.out.println(ev);
			System.err.println(cVector);

			Assert.assertTrue(false);
		}
	}
}
