package hu.nsmdmp.matrices;

import hu.nsmdmp.utils.Converters;

import org.junit.Assert;
import org.junit.Test;

/**
 * Check variation of vector set.
 * 
 * @author nzcs
 * 
 */
public class VariationTest {

	@Test
	public void variationTest1() {
		double[][] vSet = { { 1, 2, 3, 4 }, { 0, 1, 2, 3 } };

		double[][] actual = getVariations(vSet);
		double[][] expected = { { 1, 0 }, { 2, 0 }, { 3, 0 }, { 4, 0 }, { 1, 1 }, { 2, 1 }, { 3, 1 }, { 4, 1 }, { 1, 2 }, { 2, 2 }, { 3, 2 }, { 4, 2 }, { 1, 3 }, { 2, 3 }, { 3, 3 }, { 4, 3 } };

		if (!MatrixUtils.equals(expected, actual)) {
			System.out.println(MatrixUtils.print(expected));
			System.out.println(MatrixUtils.print(actual));

			Assert.assertTrue(false);
		}
	}

	@Test
	public void variationTest2() {
		double[][] vSet = { { 0 - 4, 1 - 4, 2 - 4, 3 - 4 }, { 0, 1, 2, 3 } };

		double[][] actual = getVariations(vSet);
		double[][] expected = { { -4, 0 }, { -3, 0 }, { -2, 0 }, { -1, 0 }, { -4, 1 }, { -3, 1 }, { -2, 1 }, { -1, 1 }, { -4, 2 }, { -3, 2 }, { -2, 2 }, { -1, 2 }, { -4, 3 }, { -3, 3 }, { -2, 3 },
				{ -1, 3 } };

		if (!MatrixUtils.equals(expected, actual)) {
			System.out.println(MatrixUtils.print(expected));
			System.out.println(MatrixUtils.print(actual));

			Assert.assertTrue(false);
		}
	}

	private double[][] getVariations(final double[][] vSet) {
		return Converters.convert(Matrix.getSimpleMatrix(Converters.convert(vSet), 1).getVariations());
	}
}
