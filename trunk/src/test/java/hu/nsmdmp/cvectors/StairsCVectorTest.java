package hu.nsmdmp.cvectors;

import hu.nsmdmp.matrices.MatrixUtils;
import hu.nsmdmp.utils.Converters;

import org.apfloat.Apfloat;
import org.junit.Assert;
import org.junit.Test;

public class StairsCVectorTest {

	@Test
	public void test() {
		double[][] m = { { 0, 1, 2 }, { 0, 1, 2 }, { 0, 1, 2 } };
		Apfloat[][] variation = MatrixUtils.createVariation(Converters.convert(m));

		ICVector cVector = CVector.getStairsCVector(variation);

		double[] expected = { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };

		if (!MatrixUtils.equals(expected, cVector.getCVectorD())) {
			System.out.println(MatrixUtils.print(expected));
			System.out.println(MatrixUtils.print(cVector.getCVectorD()));

			Assert.assertTrue(false);
		}
	}

	@Test
	public void test2() {
		double[][] m = { { 0, 1, 2, 3 }, { 1, 2, 3, 4 } };
		Apfloat[][] variation = MatrixUtils.createVariation(Converters.convert(m));

		ICVector cVector = CVector.getStairsCVector(variation);

		double[] expected = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };

		if (!MatrixUtils.equals(expected, cVector.getCVectorD())) {
			System.out.println(MatrixUtils.print(expected));
			System.out.println(MatrixUtils.print(cVector.getCVectorD()));

			Assert.assertTrue(false);
		}
	}
}
