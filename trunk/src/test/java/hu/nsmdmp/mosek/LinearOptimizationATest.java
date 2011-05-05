package hu.nsmdmp.mosek;

import hu.nsmdmp.matrices.MatrixUtils;
import hu.nsmdmp.utils.Converters;
import junit.framework.Assert;
import mosek.MosekException;

import org.junit.Test;

public class LinearOptimizationATest {

	@Test
	public void test() throws MosekException {

		double m[][] = { { 2, 1 }, { 2, 3 }, { 3, 1 } };
		double b[] = { 18, 42, 24 };
		double c[] = { 3, 2 };

		double[] r = LinearOptimizationA.optimizeMax(Converters.convert(m), Converters.convert(b), Converters.convert(c));

		double expected[] = { 3, 12 };

		if (!MatrixUtils.equals(expected, r)) {
			System.out.println(MatrixUtils.print(expected));
			System.out.println(MatrixUtils.print(r));

			Assert.assertTrue(false);
		}
	}
}
