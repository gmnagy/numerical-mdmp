package hu.nsmdmp;

import hu.nsmdmp.cvectors.CVector;
import hu.nsmdmp.matrices.Matrix;
import hu.nsmdmp.matrices.MatrixUtils;
import hu.nsmdmp.matrixmath.MatrixMath;
import hu.nsmdmp.mosek.LinearProgrammingEq;
import hu.nsmdmp.utils.Converters;
import mosek.MosekException;

import org.apfloat.Apfloat;
import org.junit.Test;

public class Experiments {

	@Test
	public void test() throws MosekException {
//		double[][] vectorSet = { { 0, 1, 2, 3, 4, 5 }, { 0, 1, 2, 3, 4, 5 }, { 0, 1, 2, 3, 4, 5 } };
		double[][] vectorSet = { { 0, 1, 2, 3, 4 }, { 0, 1, 2, 3, 4 }, { 0, 1, 2, 3, 4 } };

		Apfloat[][] vSet = Converters.convert(vectorSet);
		int variationNum = MatrixMath.getVariationsNumber(vSet);
		Apfloat[] distr = uniformDistr(variationNum);
		Apfloat[] c = CVector.getStairsCVector(MatrixMath.createVariation(vSet)).getCVectorA();

		int maxOrder = 8;
		for (int i = 1; i <= maxOrder; i++) {
			System.out.println("MaxOrder: " + i);

			Apfloat[][] matrix = Matrix.getSimpleMatrix(vSet, maxOrder).getMatrix();

			// A minimization problem 
			System.out.print(" - Minimization:");
			double[] rMin = optimizeMin(matrix, distr, c);
			System.out.println(MatrixUtils.print(rMin));

			// A maximization problem 
			System.out.print(" - Maximization:");
			double[] rMax = optimizeMax(matrix, distr, c);
			System.out.println(MatrixUtils.print(rMax));
		}
	}

	/**
	 * A minimization problem.
	 * 
	 */
	private double[] optimizeMin(Apfloat[][] matrix, Apfloat[] distr, Apfloat[] c) throws MosekException {
		Apfloat[] b = MatrixMath.multiply(matrix, distr);

		return LinearProgrammingEq.optimizeMin(matrix, b, c);
	}

	/**
	 * A maximization problem.
	 * 
	 */
	private double[] optimizeMax(Apfloat[][] matrix, Apfloat[] distr, Apfloat[] c) throws MosekException {
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
