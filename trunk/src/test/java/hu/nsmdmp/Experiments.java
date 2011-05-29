package hu.nsmdmp;

import hu.nsmdmp.cvectors.CVector;
import hu.nsmdmp.matrices.IMatrix;
import hu.nsmdmp.matrices.Matrix;
import hu.nsmdmp.matrices.MatrixFactory;
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

		Apfloat[][] vSet = Converters.convert(vectorSet);
		int variationNum = MatrixMath.getVariationsNumber(vSet);

		// distribution
		Apfloat[] distr = uniformDistr(variationNum);

		// coefficient vector
		Apfloat[] c = CVector.getStairsCVector(MatrixMath.createVariation(vSet)).getCVectorA();

		// normalized vector set
		IMatrix normVSet = MatrixMath.normalize(new Matrix(vectorSet));

		int maxOrder = 6;
		for (int i = 1; i <= maxOrder; i++) {
			System.out.println("MaxOrder: " + i);

			IMatrix normM = MatrixFactory.getMonomialMatrix(normVSet.getMatrix(), i);
			printMinMaxPrimalSolution(normM, distr, c, "MonomialMatrix: ");

			IMatrix chebTM = MatrixFactory.getChebyshevTMatrix(normVSet.getMatrix(), i);
			printMinMaxPrimalSolution(chebTM, distr, c, "ChebyshevTMatrix: ");

			IMatrix chebUM = MatrixFactory.getChebyshevUMatrix(normVSet.getMatrix(), i);
			printMinMaxPrimalSolution(chebUM, distr, c, "ChebyshevUMatrix: ");

			System.out.println();
		}

	}

	private void printMinMaxPrimalSolution(IMatrix matrix, Apfloat[] distr, Apfloat[] c, String prefix) throws MosekException {
		Apfloat[] b = MatrixMath.multiply(matrix, distr);

		LPSolution min = LinearProgrammingEq.optimizeMin(matrix, b, c);
		LPSolution max = LinearProgrammingEq.optimizeMax(matrix, b, c);

		System.out.println(String.format("%s min: %s,\tmax: %s", prefix, min.getPrimalSolution(), max.getPrimalSolution()));
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
