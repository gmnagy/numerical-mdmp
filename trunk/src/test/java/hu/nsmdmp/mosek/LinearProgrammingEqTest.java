package hu.nsmdmp.mosek;

import hu.nsmdmp.matrix.Matrix;
import hu.nsmdmp.utils.Utils;
import hu.nsmdmp.vector.Vector;
import mosek.MosekException;

import org.junit.Assert;
import org.junit.Test;

public class LinearProgrammingEqTest {

	@Test
	public void test() throws MosekException {

		double m[][] = { { 1, 1, 1, 1 }, { 0, 1, 0, 1 }, { 0, 0, 2, 2 } };
		Matrix M = new Matrix(m);
		double b[] = { 1, 0.5, 1 };
		Vector B = new Vector(b);
		double c[] = { 0, 1, 1, 1 };
		Vector C = new Vector(c);

		// A minimization problem 
		double[] rMin = LinearProgrammingEq.optimizeMin(M, B, C).getX();

		double expectedMin[] = { 0.5, 0, 0, 0.5 };

		if (!Utils.equals(expectedMin, rMin)) {
			System.out.println(Utils.print(expectedMin));
			System.out.println(Utils.print(rMin));

			Assert.assertTrue(false);
		}

		// A maximization problem 
		double[] rMax = LinearProgrammingEq.optimizeMax(M, B, C).getX();

		double expectedMax[] = { 0, 0.5, 0.5, 0 };

		if (!Utils.equals(expectedMax, rMax)) {
			System.out.println(Utils.print(expectedMax));
			System.out.println(Utils.print(rMax));

			Assert.assertTrue(false);
		}
	}
}
