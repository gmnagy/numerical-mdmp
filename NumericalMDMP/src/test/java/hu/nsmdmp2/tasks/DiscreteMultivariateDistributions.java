package hu.nsmdmp2.tasks;

import static hu.nsmdmp2.polynomialmatrixfactory.ChebyshevUMatrix.generateChebyshevUMatrix;
import static hu.nsmdmp2.specialvectors.CumProbPoisson.createCumProbPoisson;
import static hu.nsmdmp2.tools.SetNormalization.normalize;
import static hu.nsmdmp2.tools.SetVariationIterator.generateSetVariation;
import static hu.nsmdmp2.tools.VectorSet.createVectorSet;
import hu.nsmdmp2.mosek.LPSolution;
import hu.nsmdmp2.numerics.Value;
import hu.nsmdmp2.numerics.matrix.SparseMatrix;
import hu.nsmdmp2.utils.IOFile;

import java.io.File;

import org.apfloat.Apfloat;
import org.junit.Test;

public class DiscreteMultivariateDistributions {

	@Test
	public void distributions() throws Exception {

		Value[] powerV = IOFile.read(new File(getClass().getResource("poissonM10").toURI()), Apfloat.class);
		// getMinMaxCumProbMatrix(10, 49, powerV).write("poissonM10_49");
		// getMinMaxCumProbMatrix(10, 100, powerV).write("poissonM10_100");
		getMinMaxCumProbMatrix(10, 100, powerV);
	}

	/**
	 * Min, Max CumProbMatrix.
	 */
	private Result getMinMaxCumProbMatrix(final int maxOrder, final int length, final Value[] powerV) throws Exception {

		Value[][] vectorSet = createVectorSet(2, length, Apfloat.class);
		Value[][] variation = generateSetVariation(vectorSet);

		// normailzed ChebyshevU vector.
		Value[] nChebyUV = TaskUtils.normChebyUV(maxOrder, powerV, vectorSet);

		// ChebyshevU matrix.
		Value[][] chebU = generateChebyshevUMatrix(normalize(vectorSet), maxOrder);
		SparseMatrix smChebU = new SparseMatrix(chebU);

		Double[][] resultMIN = new Double[length + 1][length + 1];
		Double[][] resultMAX = new Double[length + 1][length + 1];

		for (int i = 0; i <= length; i++) {
			for (int j = 0; j <= length; j++) {

				if (i * length + j <= 6862) {
					System.out.println(String.format("[%s, %s] =>skip", i, j));
					continue;
				} else if (i == 68 && j == 63) {
					resultMIN[i][j] = -1D;
					resultMAX[i][j] = -1D;
					IOFile.append("minpoissonM10_100", j == 0 ? null : "\t", j == length, resultMIN[i][j]);
					IOFile.append("maxpoissonM10_100", j == 0 ? null : "\t", j == length, resultMAX[i][j]);
					continue;
				}

				Value[] f = createCumProbPoisson(variation, new Value(new Apfloat(i)), new Value(new Apfloat(j)));

				try {
					LPSolution minLPSolution = TaskUtils.getMinCumProbMatrixElement(smChebU, nChebyUV, f);
					resultMIN[i][j] = minLPSolution.getPrimalSolution();
				} catch (Exception a) {
					resultMIN[i][j] = -1D;
				}

				try {
					LPSolution maxLPSolution = TaskUtils.getMaxCumProbMatrixElement(smChebU, nChebyUV, f);
					resultMAX[i][j] = maxLPSolution.getPrimalSolution();
				} catch (Exception e) {
					resultMAX[i][j] = -1D;
				}

				System.out.println(String.format("[%s, %s] => min: %s\tmax: %s", i, j, resultMIN[i][j], resultMAX[i][j]));

				IOFile.append("minpoissonM10_100", j == 0 ? null : "\t", j == length, resultMIN[i][j]);
				IOFile.append("maxpoissonM10_100", j == 0 ? null : "\t", j == length, resultMAX[i][j]);
			}
		}

		return new Result(resultMIN, resultMAX);
	}

	private class Result {
		final Double[][] resultMIN;
		final Double[][] resultMAX;

		Result(Double[][] resultMIN, Double[][] resultMAX) {
			this.resultMIN = resultMIN;
			this.resultMAX = resultMAX;
		}

		void write(String filename) throws Exception {
			IOFile.write("min" + filename, "\t", resultMIN);
			IOFile.write("max" + filename, "\t", resultMAX);
		}
	}
}
