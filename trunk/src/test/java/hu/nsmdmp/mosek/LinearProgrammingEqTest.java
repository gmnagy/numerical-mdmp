package hu.nsmdmp.mosek;

import hu.nsmdmp.matrices.MatrixUtils;
import hu.nsmdmp.utils.Converters;
import mosek.MosekException;

import org.junit.Assert;
import org.junit.Test;

public class LinearProgrammingEqTest {

	@Test
	public void test() throws MosekException {

		double m[][] = { { 1, 1, 1, 1 }, { 0, 1, 0, 1 }, { 0, 0, 2, 2 } };
		double b[] = { 1, 0.5, 1 };
		double c[] = { 0, 1, 1, 1 };

		// A minimization problem 
		double[] rMin = LinearProgrammingEq.optimizeMin(Converters.convert(m), Converters.convert(b), Converters.convert(c));

		double expectedMin[] = { 0.5, 0, 0, 0.5 };

		if (!MatrixUtils.equals(expectedMin, rMin)) {
			System.out.println(MatrixUtils.print(expectedMin));
			System.out.println(MatrixUtils.print(rMin));

			Assert.assertTrue(false);
		}

		// A maximization problem 
		double[] rMax = LinearProgrammingEq.optimizeMax(Converters.convert(m), Converters.convert(b), Converters.convert(c));

		double expectedMax[] = { 0, 0.5, 0.5, 0 };

		if (!MatrixUtils.equals(expectedMax, rMax)) {
			System.out.println(MatrixUtils.print(expectedMax));
			System.out.println(MatrixUtils.print(rMax));

			Assert.assertTrue(false);
		}
	}
}
