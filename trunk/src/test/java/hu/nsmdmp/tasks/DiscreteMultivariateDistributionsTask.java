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
		Vector powerV = new Vector(IOFile.read(new File(getClass().getResource("binomialM5").toURI())));

		MinMaxMatrix results = getMinMaxCumProbMatrix(5, 100, powerV);

		IOFile.write("minbinomialM5", "\t", results.min);
		IOFile.write("maxbinomialM5", "\t", results.max);
	}

	/**
	 * Min, Max CumProbMatrix.
	 */
	private MinMaxMatrix getMinMaxCumProbMatrix(final int maxOrder, final int length, final Vector powerV) throws MosekException, IOException, URISyntaxException {
		Apfloat[][] vectorSet = TaskUtils.createVectorSet(2, length);

		// normailzed ChebyshevU vector.
		Vector nChebyUV = createNormChebyshevUVector(maxOrder, powerV, vectorSet);

		// ChebyshevU matrix.
		Matrix chebU = MatrixFactory.getChebyshevUMatrix(vectorSet, maxOrder);

		MinMaxMatrix results = new MinMaxMatrix(length + 1, length + 1);

		for (int i = 0; i <= length; i++) {
			for (int j = 0; j <= length; j++) {

				// f(z1,z2) = 1 if z1<=x and z2<=y, 0 otherwise. 
				List<Apfloat[]> variation = Variation.createVariation(vectorSet);
				Vector f = CVectorFactory.getCumProbPoisson(variation, new Apfloat[] { ApfloatUtils.valueOf(i), ApfloatUtils.valueOf(j) });

				results.min[i][j] = getMinCumProbMatrixElement(chebU, nChebyUV, f);
				results.max[i][j] = getMaxCumProbMatrixElement(chebU, nChebyUV, f);
				System.out.println(String.format("[%s, %s] => max: %s\tmin: %s", i, j, results.max[i][j], results.min[i][j]));
			}
		}

		return results;
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
		LPSolution max = LinearProgrammingEq.optimizeMin(chebU, nChebyUV, f);

		return max.getPrimalSolution();
	}

	/**
	 * Optimize Max.
	 */
	private double getMaxCumProbMatrixElement(final Matrix chebU, final Vector nChebyUV, Vector f) throws MosekException {
		LPSolution max = LinearProgrammingEq.optimizeMax(chebU, nChebyUV, f);

		return max.getPrimalSolution();
	}

//	@Test
//	public void test() {
//		Apfloat[][] vectorSet = TaskUtils.createVectorSet(2, 4);
//		System.out.println(Utils.print(vectorSet));
//
//		Matrix M = MatrixFactory.getSimpleMatrix(vectorSet, 4);
//		System.out.println(M);
//
//		Vector P = new Vector(new double[] { 0.015625, 0.03125, 0.015625, 0, 0, 0.03125, 0.09375, 0.09375, 0.03125, 0, 0.015625, 0.09375, 0.15625, 0.09375, 0.015625, 0, 0.03125, 0.09375, 0.09375,
//				0.03125, 0, 0, 0.015625, 0.03125, 0.015625 });
//
//		Vector R = MatrixMath.multiply(M, P);
//		System.out.println(R);
//	}
//
//	@Test
//	public void test1() throws IOException, URISyntaxException {
////		double[][] vs = new double[][] { { -1, -0.5, 0, 0.5, 1 }, { -1, -0.5, 0, 0.5, 1 } };
////		Apfloat[][] vectorSet = Converters.convert(vs);
//		Apfloat[][] vectorSet = TaskUtils.createVectorSet(2, 4);
//
//		Matrix M = MatrixFactory.getSimpleMatrix(vectorSet, 4);
////		System.out.println(M);
//
//		Vector P = new Vector(new double[] { 0.015625, 0.03125, 0.015625, 0, 0, 0.03125, 0.09375, 0.09375, 0.03125, 0, 0.015625, 0.09375, 0.15625, 0.09375, 0.015625, 0, 0.03125, 0.09375, 0.09375,
//				0.03125, 0, 0, 0.015625, 0.03125, 0.015625 });
//
//		Vector R = MatrixMath.multiply(M, P);
//		System.out.println(R);
//
//		int maxOrder = 4;
//		URL url = getClass().getResource("binomialM4test");
//		Vector powerB = new Vector(IOFile.read(new File(url.toURI())));
//		System.out.println(powerB);
//
//		Vector V = PowerMomentsNormalization.normailze(vectorSet, maxOrder, powerB);
//		System.out.println(V);
//	}

	private class MinMaxMatrix {
		private double[][] min;
		private double[][] max;

		MinMaxMatrix(int rowNum, int colNum) {
			this.min = new double[rowNum][colNum];
			this.max = new double[rowNum][colNum];
		}
	}
}
