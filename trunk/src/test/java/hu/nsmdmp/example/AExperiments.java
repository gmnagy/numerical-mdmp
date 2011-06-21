package hu.nsmdmp.example;

import hu.nsmdmp.matrices.Matrix;
import hu.nsmdmp.matrices.MatrixFactory;
import hu.nsmdmp.matrices.MatrixUtils;
import hu.nsmdmp.matrixmath.MatrixMath;
import hu.nsmdmp.mosek.LPSolution;
import hu.nsmdmp.mosek.LinearProgrammingEq;
import hu.nsmdmp.utils.Converters;
import hu.nsmdmp.vectors.Vector;
import mosek.MosekException;

import org.apfloat.Apfloat;

abstract class AExperiments {

	public abstract void run() throws MosekException;

	protected abstract int getMaxOrder();

	protected abstract Vector getCVector(final Apfloat[][] vSet);

	protected void run(double[][] vectorSet) throws MosekException {

		Apfloat[][] vSet = Converters.convert(vectorSet);
		int variationNum = MatrixUtils.getVariationsNumber(vSet);

		// distribution
		Vector distr = distribution(variationNum);

		// coefficient vector
		Vector c = getCVector(vSet);

		// normalized vector set
		Matrix normVSet = MatrixMath.normalize(new Matrix(vectorSet));

		int maxOrder = getMaxOrder();
		for (int i = 1; i <= maxOrder; i++) {
			System.out.println("MaxOrder: " + i);

			//IMatrix normM = MatrixFactory.getMonomialMatrix(normVSet.getMatrix(), i);
			//printMinMaxPrimalSolution(normM, distr, c, "MonomialMatrix: ");

			Matrix chebTM = MatrixFactory.getChebyshevTMatrix(normVSet.getArray(), i);
			printMinMaxPrimalSolution(chebTM, distr, c, "ChebyshevTMatrix: ");

			Matrix chebUM = MatrixFactory.getChebyshevUMatrix(normVSet.getArray(), i);
			printMinMaxPrimalSolution(chebUM, distr, c, "ChebyshevUMatrix: ");

			System.out.println();
		}

	}

	/**
	 * Create distribution.
	 * 
	 */
	protected abstract Vector distribution(final int n);

	protected void printMinMaxPrimalSolution(Matrix matrix, Vector distr, Vector c, String prefix) throws MosekException {
		Vector b = MatrixMath.multiply(matrix, distr);

		LPSolution min = LinearProgrammingEq.optimizeMin(matrix, b, c);
		LPSolution max = LinearProgrammingEq.optimizeMax(matrix, b, c);

		System.out.println(String.format("%s min: %s,\tmax: %s", prefix, min.getPrimalSolution(), max.getPrimalSolution()));
	}
}
