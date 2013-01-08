package hu.nsmdmp.tasks;

import hu.nsmdmp.cvectors.CVectorFactory;
import hu.nsmdmp.math.Variation;
import hu.nsmdmp.matrix.Matrix;
import hu.nsmdmp.matrix.operation.MatrixMath;
import hu.nsmdmp.matrix.operation.PowerMomentsNormalization;
import hu.nsmdmp.matrixfactory.MatrixFactory;
import hu.nsmdmp.matrixfactory.MonomialToChebUMatrix;
import hu.nsmdmp.mosek.LPSolution;
import hu.nsmdmp.mosek.LinearProgrammingEq;
import hu.nsmdmp.utils.ApfloatUtils;
import hu.nsmdmp.utils.IOFile;
import hu.nsmdmp.vector.Vector;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import mosek.MosekException;

import org.apfloat.Apfloat;
import org.junit.Test;

public class DiscreteMultivariateDistributionsTask {

	@Test
	public void discreteMultivariateDistributions() throws IOException, URISyntaxException, MosekException {
		Vector powerV = new Vector(IOFile.read(new File(getClass().getResource("poissonM10").toURI())));

		getMinMaxCumProbMatrix(10, 100, powerV);
	}

//	@Test
//	public void discreteMultivariateDistributions2() throws IOException, URISyntaxException, MosekException {
//		int maxOrder = 5;
//		Vector powerV = new Vector(IOFile.read(new File(getClass().getResource("binomialM5").toURI())));
//
//		Apfloat[][] vectorSet = TaskUtils.createVectorSet(2, 100);
//
//		// normailzed ChebyshevU vector.
//		Vector nChebyUV = createNormChebyshevUVector(maxOrder, powerV, vectorSet);
//
//		// ChebyshevU matrix.
//		Matrix chebU = MatrixFactory.getChebyshevUMatrix(vectorSet, maxOrder);
//
//		List<Apfloat[]> variation = Variation.createVariation(vectorSet);
//		Vector f = CVectorFactory.getCumProbPoisson(variation, new Apfloat[] { ApfloatUtils.valueOf(0), ApfloatUtils.valueOf(0) });
//
//		double min = getMinCumProbMatrixElement(chebU, nChebyUV, f);
//		double max = getMaxCumProbMatrixElement(chebU, nChebyUV, f);
//
//		System.out.println(String.format("max: %s\tmin: %s", max, min));
//	}

	/**
	 * Min, Max CumProbMatrix.
	 */
	private void getMinMaxCumProbMatrix(final int maxOrder, final int length, final Vector powerV) throws MosekException, IOException, URISyntaxException {
		Apfloat[][] vectorSet = TaskUtils.createVectorSet(2, length);

		// normailzed ChebyshevU vector.
		Vector nChebyUV = createNormChebyshevUVector(maxOrder, powerV, vectorSet);

		// ChebyshevU matrix.
		Matrix chebU = MatrixFactory.getChebyshevUMatrix(vectorSet, maxOrder);

		for (int i = 0; i <= length; i++) {
			for (int j = 0; j <= length; j++) {

//				if (i * length + j <= 7416) {
//					System.out.println(String.format("[%s, %s] =>skip", i, j));
//					continue;
//				}
//				if (i == 74 && j == 17) {
//					IOFile.append("minPoissonM10", j == 0 ? null : "\t", j == length, -1);
//					IOFile.append("maxPoissonM10", j == 0 ? null : "\t", j == length, -1);
//					System.out.println(String.format("[%s, %s] => max: %s\tmin: %s", i, j, -1, -1));
//					continue;
//				}

				// f(z1,z2) = 1 if z1<=x and z2<=y, 0 otherwise. 
				List<Apfloat[]> variation = Variation.createVariation(vectorSet);
				Vector f = CVectorFactory.getCumProbPoisson(variation, new Apfloat[] { ApfloatUtils.valueOf(i), ApfloatUtils.valueOf(j) });

				double min = getMinCumProbMatrixElement(chebU, nChebyUV, f);
				double max = getMaxCumProbMatrixElement(chebU, nChebyUV, f);

//				IOFile.append("minPoissonM10", j == 0 ? null : "\t", j == length, min);
//				IOFile.append("maxPoissonM10", j == 0 ? null : "\t", j == length, max);

				System.out.println(String.format("[%s, %s] => max: %s\tmin: %s", i, j, max, min));
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		return;
	}

	/**
	 * Normailzed ChebyshevU vector.
	 */
	private Vector createNormChebyshevUVector(final int maxOrder, final Vector powerV, final Apfloat[][] vectorSet) {
		// normailzed power vector
		Vector nPowerV = PowerMomentsNormalization.normailze(vectorSet, maxOrder, powerV);

		// Monomial ChebyshevU transormation matrix.
		Matrix T = MonomialToChebUMatrix.getMatrix(maxOrder, vectorSet.length);

		return MatrixMath.multiply(T, nPowerV);
	}

	/**
	 * Optimize Min.
	 */
	private double getMinCumProbMatrixElement(final Matrix chebU, final Vector nChebyUV, final Vector f) throws MosekException {
		try {
			LPSolution max = LinearProgrammingEq.optimizeMin(chebU, nChebyUV, f);

			return max.getPrimalSolution();
		} catch (MosekException e) {
			return -1;
		}
	}

	/**
	 * Optimize Max.
	 */
	private double getMaxCumProbMatrixElement(final Matrix chebU, final Vector nChebyUV, Vector f) throws MosekException {
		try {
			LPSolution max = LinearProgrammingEq.optimizeMax(chebU, nChebyUV, f);

			return max.getPrimalSolution();
		} catch (MosekException e) {
			return -1;
		}
	}
}
