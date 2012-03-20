package hu.nsmdmp.example;

import hu.nsmdmp.math.Variation;
import hu.nsmdmp.matrix.Matrix;
import hu.nsmdmp.matrix.operation.MatrixMath;
import hu.nsmdmp.matrixfactory.MatrixFactory;
import hu.nsmdmp.mosek.LPSolution;
import hu.nsmdmp.mosek.LinearProgrammingEq;
import hu.nsmdmp.mosek.PreciseLPCalc;
import hu.nsmdmp.mosek.PreciseLPSolution;
import hu.nsmdmp.utils.Converters;
import hu.nsmdmp.vector.Vector;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import mosek.MosekException;

import org.apfloat.Apfloat;

abstract class AExperiments {

	public abstract void run() throws MosekException;

	protected abstract int getMaxOrder();

	protected abstract Vector getCVector(final Apfloat[][] vSet);

	protected void run(double[][] vectorSet) throws MosekException {
		long starttime = System.currentTimeMillis();
		Apfloat[][] vSet = Converters.convert(vectorSet);
		int variationNum = Variation.getPieceVariation(vSet);

		// distribution
		Vector distr = distribution(variationNum);

		// coefficient vector
		Vector c = getCVector(vSet);

		// normalized vector set
		Matrix normVSet = MatrixMath.normalize(new Matrix(vectorSet));

		System.out.println("AExperiments disrt, obj, var vectors prepare time: " + (System.currentTimeMillis() - starttime));
		int maxOrder = getMaxOrder();
		for (int i = 1; i <= maxOrder; i++) {
			System.out.println("MaxOrder: " + i);

			//IMatrix normM = MatrixFactory.getMonomialMatrix(normVSet.getMatrix(), i);
			//printMinMaxPrimalSolution(normM, distr, c, "MonomialMatrix: ");
			System.out.println("ChebyshevTMatrix: ");
			starttime = System.currentTimeMillis();
			Matrix chebTM = MatrixFactory.getChebyshevTMatrix(normVSet.getArray(), i);
			Vector b = MatrixMath.multiply(chebTM, distr);
			System.out.println("Matrix and RHS prepare time: " + (System.currentTimeMillis() - starttime));
			printMinMaxPrimalSolution(chebTM, b, c, "ChebyshevTMatrix: ");

			System.out.println("ChebyshevUMatrix: ");
			starttime = System.currentTimeMillis();
			Matrix chebUM = MatrixFactory.getChebyshevUMatrix(normVSet.getArray(), i);
			b = MatrixMath.multiply(chebUM, distr);
			System.out.println("Matrix and RHS prepare time: " + (System.currentTimeMillis() - starttime));
			printMinMaxPrimalSolution(chebUM, b, c, "ChebyshevUMatrix: ");

			System.out.println();
		}

	}

	/**
	 * Create distribution.
	 * 
	 */
	protected abstract Vector distribution(final int n);

	protected void printMinMaxPrimalSolution(Matrix matrix, Vector b, Vector c, String prefix) throws MosekException {

		NumberFormat formatter = new DecimalFormat();
		formatter = new DecimalFormat("0.#################E0");

		long starttime = System.currentTimeMillis();
		LPSolution min = LinearProgrammingEq.optimizeMin(matrix, b, c);
		System.out.println(String.format("%s \tmin: %s \ttime: %s", "mosek: ", formatter.format(min.getPrimalSolution()), (System.currentTimeMillis() - starttime)));

		starttime = System.currentTimeMillis();
		PreciseLPSolution precMin = PreciseLPCalc.optimizeMin(matrix, b, c);
		System.out.println(String.format("%s \tmin: %s \ttime: %s", "precise: ", formatter.format(precMin.getObjectiveValue().doubleValue()), (System.currentTimeMillis() - starttime)));
		System.out.println(String.format(	"%s nonneg: %s,\tslack: %s",
											"infeasibility: ",
											formatter.format(precMin.getPrimalNonnegInfeas().doubleValue()),
											formatter.format(precMin.getPrimalSlackInfeas().doubleValue())));
		System.out.println(String.format("%s slack: %s", "dual infeas: ", formatter.format(precMin.getDualSlackInfeas().doubleValue())));

		starttime = System.currentTimeMillis();
		LPSolution max = LinearProgrammingEq.optimizeMax(matrix, b, c);
		System.out.println(String.format("%s \tmax: %s \ttime: %s", "mosek: ", formatter.format(max.getPrimalSolution()), (System.currentTimeMillis() - starttime)));

		starttime = System.currentTimeMillis();
		PreciseLPSolution precMax = PreciseLPCalc.optimizeMax(matrix, b, c);
		System.out.println(String.format("%s \tmax: %s \ttime: %s", "precise: ", formatter.format(precMax.getObjectiveValue().doubleValue()), (System.currentTimeMillis() - starttime)));
		System.out.println(String.format(	"%s nonneg: %s,\tslack: %s",
											"infeasibility: ",
											formatter.format(precMax.getPrimalNonnegInfeas().doubleValue()),
											formatter.format(precMax.getPrimalSlackInfeas().doubleValue())));
		System.out.println(String.format("%s slack: %s", "dual infeas: ", formatter.format(precMax.getDualSlackInfeas().doubleValue())));

	}
}
