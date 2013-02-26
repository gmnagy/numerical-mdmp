package hu.nsmdmp.mosek;

import static hu.nsmdmp.mosek.PreciseLPCalc.optimizeMin;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.numerics.matrix.Vector;

import java.util.Arrays;

import mosek.MosekException;

import org.junit.Test;

public class PreciseLPCalcTest {

	@Test
	public void test() throws MosekException {

		Double m[][] = { { 1.0, 1.0, 1.0, 1.0 }, { 0.0, 1.0, 0.0, 1.0 }, { 0.0, 0.0, 2.0, 2.0 } };
		Matrix<Double> M = new Matrix<Double>(m);

		Double b[] = { 1.0, 0.5, 1.0 };
		Vector<Double> B = new Vector<Double>(b);

		Double c[] = { 0.0, 1.0, 1.0, 1.0 };
		Vector<Double> C = new Vector<Double>(c);

		// A minimization problem 
		PreciseLPSolution<Double> result = optimizeMin(M, B, C);
		Double[] rMin = result.getX();
		System.out.println(Arrays.toString(rMin));

//		 Apfloat half=(new Apfloat(1, Precision.SCALE)).divide(new Apfloat(2, Precision.SCALE));
//		Apfloat expectedMin[] = { half, ApfloatUtils.ZERO, ApfloatUtils.ZERO, half };
		Double half = 1.0 / 2.0;
		Double expectedMin[] = { half, 0.0, 0.0, half };

		assertArrayEquals(expectedMin, rMin);

//		if (!Utils.equals(expectedMin, rMin)) {
//			System.out.println(Utils.print(expectedMin));
//			System.out.println(Utils.print(rMin));
//
//			Assert.assertTrue(false);
//		}

		// a non-degenarate example would be more usefull
		int[] expBasisIndexes = { 3, 4, 6 };
		assertEquals(result.getDualSlackInfeasIndex(), -1);
		assertEquals(result.getPrimalNonnegInfeasIndex(), -1);
		assertEquals(result.getPrimalSlackInfeasIndex(), -1);
		assertTrue(result.getPrimalNonnegInfeas().equals(0.0));
		assertTrue(result.getPrimalSlackInfeas().equals(0.0));
		assertTrue(result.getDualSlackInfeas().equals(0.0));
		assertTrue(result.getObjectiveValue().equals(half));
		//	Assert.assertTrue(Arrays.equals(result.getBasisIndexes(), expBasisIndexes));

//TODO(MNG): half instead of Apfloat(0.5, Precision.SCALE)!!!
		// A maximization problem 
		result = PreciseLPCalc.optimizeMax(M, B, C);
		Double[] rMax = result.getX();
		Double expectedMax[] = { 0.0, 0.5, 0.5, 0.0 };

		assertArrayEquals(expectedMax, rMax);

//		if (!Utils.equals(expectedMax, rMax)) {
//			System.out.println(Utils.print(expectedMax));
//			System.out.println(Utils.print(rMax));
//
//			Assert.assertTrue(false);
//		}

		// a non-degenarate example would be more usefull
		int[] expBasisIndexes2 = { 4, 5, 6 };
		assertEquals(result.getDualSlackInfeasIndex(), -1);
		assertEquals(result.getPrimalNonnegInfeasIndex(), -1);
		assertEquals(result.getPrimalSlackInfeasIndex(), -1);
		assertTrue(result.getPrimalNonnegInfeas().equals(0.0));
		assertTrue(result.getPrimalSlackInfeas().equals(0.0));
		assertTrue(result.getDualSlackInfeas().equals(0.0));
		assertTrue(result.getObjectiveValue().equals(1.0));
		assertTrue(Arrays.equals(result.getBasisIndexes(), expBasisIndexes2));
	}
}
