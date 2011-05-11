package hu.nsmdmp.matrixmath;

import hu.nsmdmp.matrices.MatrixUtils;
import hu.nsmdmp.utils.Converters;

import org.apfloat.Apfloat;
import org.junit.Assert;
import org.junit.Test;

/**
 * Check normalization of vector set.
 * 
 * @author nzcs
 * 
 */
public class NormalizationTest {

	@Test
	public void normalizationTest1() {
		double[][] vSet = { { 1, 2, 3, 4 }, { 0, 1, 2, 3 } };

		Apfloat[][] normalized = Converters.convert(vSet);
		Normalization.normalize(normalized);

		double[][] expected = { { -1.0, -(1.0 / 3.0), (1.0 / 3.0), 1 }, { -1.0, -(1.0 / 3.0), (1.0 / 3.0), 1 } };

		if (!MatrixUtils.equals(Converters.convert(expected), normalized)) {
			System.out.println(MatrixUtils.print(expected));
			System.out.println(MatrixUtils.print(normalized));

			Assert.assertTrue(false);
		}
	}

	@Test
	public void normalizationTest2() {
		double[][] vSet = { { 0 - 4, 1 - 4, 2 - 4, 3 - 4 }, { 0, 1, 2, 3 } };

		Apfloat[][] normalized = Converters.convert(vSet);
		Normalization.normalize(normalized);

		double[][] expected = { { -1.0, -(1.0 / 3.0), (1.0 / 3.0), 1 }, { -1.0, -(1.0 / 3.0), (1.0 / 3.0), 1 } };

		if (!MatrixUtils.equals(Converters.convert(expected), normalized)) {
			System.out.println(MatrixUtils.print(expected));
			System.out.println(MatrixUtils.print(normalized));

			Assert.assertTrue(false);
		}
	}
}
