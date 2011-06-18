package hu.nsmdmp.example;

import hu.nsmdmp.cvectors.ICVector;
import hu.nsmdmp.matrices.IMatrix;
import hu.nsmdmp.matrices.Matrix;
import hu.nsmdmp.matrices.MatrixFactory;
import hu.nsmdmp.matrixmath.MatrixMath;
import hu.nsmdmp.mosek.LPSolution;
import hu.nsmdmp.mosek.LinearProgrammingEq;
import hu.nsmdmp.utils.Converters;
import mosek.MosekException;

import org.apfloat.Apfloat;

abstract class AExperiments {

	public abstract void run() throws MosekException;

	protected abstract int getMaxOrder();

	protected abstract ICVector getCVector(final Apfloat[][] vSet);

	protected void run(double[][] vectorSet) throws MosekException {

		Apfloat[][] vSet = Converters.convert(vectorSet);
		int variationNum = MatrixMath.getVariationsNumber(vSet);

		// distribution
		Apfloat[] distr = distribution(variationNum);

		// coefficient vector
		Apfloat[] c = getCVector(vSet).getCVectorA();

		// normalized vector set
		IMatrix normVSet = MatrixMath.normalize(new Matrix(vectorSet));

		int maxOrder = getMaxOrder();
		for (int i = 1; i <= maxOrder; i++) {
			System.out.println("MaxOrder: " + i);

			//IMatrix normM = MatrixFactory.getMonomialMatrix(normVSet.getMatrix(), i);
			//printMinMaxPrimalSolution(normM, distr, c, "MonomialMatrix: ");

			IMatrix chebTM = MatrixFactory.getChebyshevTMatrix(normVSet.getMatrix(), i);
			printMinMaxPrimalSolution(chebTM, distr, c, "ChebyshevTMatrix: ");

			IMatrix chebUM = MatrixFactory.getChebyshevUMatrix(normVSet.getMatrix(), i);
			printMinMaxPrimalSolution(chebUM, distr, c, "ChebyshevUMatrix: ");

			System.out.println();
		}

	}

	/**
	 * Create distribution.
	 * 
	 */
	protected abstract Apfloat[] distribution(final int n);

	protected void printMinMaxPrimalSolution(IMatrix matrix, Apfloat[] distr, Apfloat[] c, String prefix) throws MosekException {
		Apfloat[] b = MatrixMath.multiply(matrix, distr);

		LPSolution min = LinearProgrammingEq.optimizeMin(matrix, b, c);
		LPSolution max = LinearProgrammingEq.optimizeMax(matrix, b, c);

		System.out.println(String.format("%s min: %s,\tmax: %s", prefix, min.getPrimalSolution(), max.getPrimalSolution()));
	}
}
