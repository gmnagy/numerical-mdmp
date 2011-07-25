package hu.nsmdmp.mosek;

import hu.nsmdmp.ApfloatUtils;
import hu.nsmdmp.matrices.Matrix;
import hu.nsmdmp.matrices.MatrixUtils;
import hu.nsmdmp.utils.Precision;
import hu.nsmdmp.vectors.Vector;
import mosek.MosekException;

import org.apfloat.Apfloat;
import org.junit.Assert;
import org.junit.Test;

public class PreciseLPCalcTest {

	@Test
	public void test() throws MosekException {

		double m[][] = { { 1, 1, 1, 1 }, { 0, 1, 0, 1 }, { 0, 0, 2, 2 } };
		Matrix M = new Matrix(m);
		double b[] = { 1, 0.5, 1 };
		Vector B = new Vector(b);
		double c[] = { 0, 1, 1, 1 };
		Vector C = new Vector(c);

		// A minimization problem 
		 PreciseLPSolution result = PreciseLPCalc.optimizeMin(M, B, C);
		 Apfloat[] rMin=result.getX();

		Apfloat expectedMin[] = { new Apfloat(0.5, Precision.SCALE), ApfloatUtils.ZERO, ApfloatUtils.ZERO, new Apfloat(0.5, Precision.SCALE) };

		if (!MatrixUtils.equals(expectedMin, rMin)) {
			System.out.println(MatrixUtils.print(expectedMin));
			System.out.println(MatrixUtils.print(rMin));

			Assert.assertTrue(false);
		}
		
		//result.basisIndexes=basisIndexes;
		//Assert.assertEquals(result.getDualSlackInfeasIndex(),-1);
		//Assert.assertEquals(result.getPrimalNonnegInfeasIndex(),-1);
		//Assert.assertEquals(result.getPrimalSlackInfeasIndex(),-1);
		//Assert.assertTrue(result.primalNonnegInfeas.equals(ApfloatUtils.ZERO));
		//Assert.assertTrue(result.primalSlackInfeas.equals(ApfloatUtils.ZERO));
		//Assert.assertTrue(result.dualSlackInfeas.equals(ApfloatUtils.ZERO));

		// A maximization problem 
		Apfloat[] rMax = PreciseLPCalc.optimizeMax(M, B, C).getX();

		Apfloat expectedMax[] = { ApfloatUtils.ZERO, new Apfloat(0.5, Precision.SCALE), new Apfloat(0.5, Precision.SCALE), ApfloatUtils.ZERO};

		if (!MatrixUtils.equals(expectedMax, rMax)) {
			System.out.println(MatrixUtils.print(expectedMax));
			System.out.println(MatrixUtils.print(rMax));

			Assert.assertTrue(false);
		}
	}
	
}
