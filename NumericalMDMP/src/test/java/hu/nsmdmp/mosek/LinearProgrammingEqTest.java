package hu.nsmdmp.mosek;

import static hu.nsmdmp.mosek.LinearProgrammingEq.optimizeMax;
import static hu.nsmdmp.mosek.LinearProgrammingEq.optimizeMin;
import static org.junit.Assert.assertArrayEquals;
import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.numerics.matrix.Vector;
import mosek.MosekException;

import org.junit.Test;

public class LinearProgrammingEqTest {

	@Test
	public void test() throws MosekException {

		Double m[][] = { { 1.0, 1.0, 1.0, 1.0 }, { 0.0, 1.0, 0.0, 1.0 }, { 0.0, 0.0, 2.0, 2.0 } };
		Matrix<Double> M = new Matrix<Double>(m);

		Double b[] = { 1.0, 0.5, 1.0 };
		Vector<Double> B = new Vector<Double>(b);

		Double c[] = { 0.0, 1.0, 1.0, 1.0 };
		Vector<Double> C = new Vector<Double>(c);

		// A minimization problem 
		double[] rMin = optimizeMin(M, B, C).getX();

		double expectedMin[] = { 0.5, 0, 0, 0.5 };

		assertArrayEquals(expectedMin, rMin, 0.000001);

//		if (!Utils.equals(expectedMin, rMin)) {
//			System.out.println(Utils.print(expectedMin));
//			System.out.println(Utils.print(rMin));
//
//			Assert.assertTrue(false);
//		}

		// A maximization problem 
		double[] rMax = optimizeMax(M, B, C).getX();

		double expectedMax[] = { 0, 0.5, 0.5, 0 };

		assertArrayEquals(expectedMax, rMax, 0.000001);

//		if (!Utils.equals(expectedMax, rMax)) {
//			System.out.println(Utils.print(expectedMax));
//			System.out.println(Utils.print(rMax));
//
//			Assert.assertTrue(false);
//		}
	}
}
