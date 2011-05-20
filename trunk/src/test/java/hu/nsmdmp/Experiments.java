package hu.nsmdmp;

import hu.nsmdmp.cvectors.CVector;
import hu.nsmdmp.matrices.Matrix;
import hu.nsmdmp.matrixmath.MatrixMath;
import hu.nsmdmp.mosek.LPSolution;
import hu.nsmdmp.mosek.LinearProgrammingEq;
import hu.nsmdmp.utils.Converters;
import mosek.MosekException;

import org.apfloat.Apfloat;
import org.junit.Test;

public class Experiments {

	@Test
	public void test() throws MosekException {
		double[][] vectorSet = { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 }, { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 },
				{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 } };

		long begin = System.currentTimeMillis();
		Apfloat[][] vSet = Converters.convert(vectorSet);
		int variationNum = MatrixMath.getVariationsNumber(vSet);
		Apfloat[] distr = uniformDistr(variationNum);
		Apfloat[] c = CVector.getStairsCVector(MatrixMath.createVariation(vSet)).getCVectorA();
		System.out.println("c, distr " + (System.currentTimeMillis() - begin) + " ms");

		int maxOrder = 8;
		for (int i = 1; i <= maxOrder; i++) {
			System.out.print("MaxOrder: " + i + "\t");

			begin = System.currentTimeMillis();
			Apfloat[][] matrix = Matrix.getSimpleMatrix(vSet, maxOrder).getMatrix();
			System.out.println("matrix " + (System.currentTimeMillis() - begin) + " ms");

			// A minimization problem 
			System.out.print(" - Minimization: ");
			begin = System.currentTimeMillis();
			LPSolution rMin = optimizeMin(matrix, distr, c);
			System.out.println(rMin.getPrimalSolution());
			System.out.println("min " + (System.currentTimeMillis() - begin) + " ms");
//			System.out.println(MatrixUtils.print(rMin));

			// A maximization problem 
//			System.out.print(" - Maximization: ");
//			begin = System.currentTimeMillis();
//			double[] rMax = optimizeMax(matrix, distr, c);
//			System.out.println("max " + (System.currentTimeMillis() - begin) + " ms");
//			System.out.println(MatrixUtils.print(rMax));
		}
	}

	/**
	 * A minimization problem.
	 * 
	 */
	private LPSolution optimizeMin(Apfloat[][] matrix, Apfloat[] distr, Apfloat[] c) throws MosekException {
		Apfloat[] b = MatrixMath.multiply(matrix, distr);

		return LinearProgrammingEq.optimizeMin(matrix, b, c);
	}

	/**
	 * A maximization problem.
	 * 
	 */
	private LPSolution optimizeMax(Apfloat[][] matrix, Apfloat[] distr, Apfloat[] c) throws MosekException {
		Apfloat[] b = MatrixMath.multiply(matrix, distr);

		return LinearProgrammingEq.optimizeMax(matrix, b, c);
	}

	/**
	 * Create uniform distribution.
	 * 
	 */
	private Apfloat[] uniformDistr(final int n) {
		Apfloat[] b = new Apfloat[n];

		for (int i = 0; i < n; i++) {
			b[i] = MatrixMath.ONE.divide(new Apfloat(n));
		}

		return b;
	}
}
