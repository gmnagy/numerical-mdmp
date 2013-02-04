package hu.nsmdmp.mosek;

import static hu.nsmdmp.mosek.LinearProgrammingEq.optimizeMax;
import static hu.nsmdmp.mosek.LinearProgrammingEq.optimizeMin;
import static hu.nsmdmp.utils.Converters.toApfloat;
import static org.junit.Assert.assertArrayEquals;
import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.numerics.matrix.Vector;
import mosek.MosekException;

import org.apfloat.Apfloat;
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

		// A maximization problem 
		double[] rMax = optimizeMax(M, B, C).getX();

		double expectedMax[] = { 0, 0.5, 0.5, 0 };

		assertArrayEquals(expectedMax, rMax, 0.000001);
	}

	@Test
	public void test2() throws MosekException {

		double m[][] = { { 1.0, 1.0, 1.0, 1.0 }, { 0.0, 1.0, 0.0, 1.0 }, { 0.0, 0.0, 2.0, 2.0 } };
		Matrix<Apfloat> M = new Matrix<Apfloat>(toApfloat(m));

		double b[] = { 1.0, 0.5, 1.0 };
		Vector<Apfloat> B = new Vector<Apfloat>(toApfloat(b));

		double c[] = { 0.0, 1.0, 1.0, 1.0 };
		Vector<Apfloat> C = new Vector<Apfloat>(toApfloat(c));

		// A minimization problem 
		double[] rMin = optimizeMin(M, B, C).getX();

		double expectedMin[] = { 0.5, 0, 0, 0.5 };

		assertArrayEquals(expectedMin, rMin, 0.000001);

		// A maximization problem 
		double[] rMax = optimizeMax(M, B, C).getX();

		double expectedMax[] = { 0, 0.5, 0.5, 0 };

		assertArrayEquals(expectedMax, rMax, 0.000001);
	}
}
