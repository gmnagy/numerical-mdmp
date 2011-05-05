package hu.nsmdmp.distribution;

import hu.nsmdmp.matrices.Matrix;
import hu.nsmdmp.matrices.MatrixUtils;
import hu.nsmdmp.matrices.SimpleMatrix;
import hu.nsmdmp.utils.Converters;

import org.junit.Assert;
import org.junit.Test;

public class UniformTest {

	@Test
	public void test() {
		double[][] m = { { 0, 1, 2 }, { 0, 1, 2 }, { 0, 1, 2 } };
		SimpleMatrix sm = Matrix.getSimpleMatrix(Converters.convert(m), 3);

		Uniform uniform = Distribution.uniformDistribution(sm.getVariations());

		double[] expected = { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };

		if (!MatrixUtils.equals(expected, uniform.getDistributionD())) {
			System.out.println(MatrixUtils.print(expected));
			System.out.println(MatrixUtils.print(uniform.getDistributionD()));

			Assert.assertTrue(false);
		}
	}
}
