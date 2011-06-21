package hu.nsmdmp.cvectors;

import hu.nsmdmp.matrices.MatrixUtils;
import hu.nsmdmp.utils.Converters;
import hu.nsmdmp.vectors.Vector;

import org.apfloat.Apfloat;
import org.junit.Assert;
import org.junit.Test;

public class ExpCVectorTest {

	/**
	 * f(z1,z2) = Exp[z1/50 + z2/50 + z1z2/10000].
	 */
	@Test
	public void test() {
		double[][] m = { { 1, 2, 3 }, { 0, 1, 2 } };
		Apfloat[][] variation = MatrixUtils.createVariation(Converters.convert(m));

		Vector cVector = CVectorFactory.getExpCVector(variation);

		double[] expected = { Math.exp(1d / 50d), Math.exp(2d / 50d), Math.exp(3d / 50d), Math.exp(2d / 50d + 1d / 10000d), Math.exp(3d / 50d + 2d / 10000d), Math.exp(4d / 50d + 3d / 10000d),
				Math.exp(3d / 50d + 2d / 10000d), Math.exp(4d / 50d + 4d / 10000d), Math.exp(5d / 50d + 6d / 10000d) };
		Vector ev = new Vector(expected);

		if (!ev.equals(cVector)) {
			System.out.println(ev);
			System.err.println(cVector);

			Assert.assertTrue(false);
		}
	}
}
