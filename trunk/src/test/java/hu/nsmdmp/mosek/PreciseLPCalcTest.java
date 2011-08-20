package hu.nsmdmp.mosek;

import java.util.Arrays;

import hu.nsmdmp.ApfloatUtils;
import hu.nsmdmp.matrix.Matrix;
import hu.nsmdmp.matrix.MatrixUtils;
import hu.nsmdmp.utils.Precision;
import hu.nsmdmp.vector.Vector;
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
		
		// a non-degenarate example would be more usefull
		int[] expBasisIndexes={3,4,6};
		Assert.assertEquals(result.getDualSlackInfeasIndex(),-1);
		Assert.assertEquals(result.getPrimalNonnegInfeasIndex(),-1);
		Assert.assertEquals(result.getPrimalSlackInfeasIndex(),-1);
		Assert.assertTrue(result.getPrimalNonnegInfeas().equals(ApfloatUtils.ZERO));
		Assert.assertTrue(result.getPrimalSlackInfeas().equals(ApfloatUtils.ZERO));
		Assert.assertTrue(result.getDualSlackInfeas().equals(ApfloatUtils.ZERO));
		Assert.assertTrue(result.getObjectiveValue().equals(new Apfloat(0.5,Precision.SCALE)));
		Assert.assertTrue(Arrays.equals(result.getBasisIndexes(), expBasisIndexes));
		//System.out.println(Arrays.toString(result.basisIndexes));
		

		// A maximization problem 
		result = PreciseLPCalc.optimizeMax(M, B, C);
		Apfloat[] rMax=result.getX();
		Apfloat expectedMax[] = { ApfloatUtils.ZERO, new Apfloat(0.5, Precision.SCALE), new Apfloat(0.5, Precision.SCALE), ApfloatUtils.ZERO};

		if (!MatrixUtils.equals(expectedMax, rMax)) {
			System.out.println(MatrixUtils.print(expectedMax));
			System.out.println(MatrixUtils.print(rMax));

			Assert.assertTrue(false);
		}
		
		// a non-degenarate example would be more usefull
		int[] expBasisIndexes2={4,5,6};
		Assert.assertEquals(result.getDualSlackInfeasIndex(),-1);
		Assert.assertEquals(result.getPrimalNonnegInfeasIndex(),-1);
		Assert.assertEquals(result.getPrimalSlackInfeasIndex(),-1);
		Assert.assertTrue(result.getPrimalNonnegInfeas().equals(ApfloatUtils.ZERO));
		Assert.assertTrue(result.getPrimalSlackInfeas().equals(ApfloatUtils.ZERO));
		Assert.assertTrue(result.getDualSlackInfeas().equals(ApfloatUtils.ZERO));
		Assert.assertTrue(result.getObjectiveValue().equals(new Apfloat(1.0,Precision.SCALE)));
		Assert.assertTrue(Arrays.equals(result.getBasisIndexes(), expBasisIndexes2));
		//System.out.println(Arrays.toString(result.basisIndexes));
		
	}
	
}
