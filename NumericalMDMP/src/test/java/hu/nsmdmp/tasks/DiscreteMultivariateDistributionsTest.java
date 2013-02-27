package hu.nsmdmp.tasks;

import static hu.nsmdmp.polynomialmatrixfactory.ChebyshevTMatrix.generateApfloatChebyshevTMatrix;
import static hu.nsmdmp.polynomialmatrixfactory.ChebyshevUMatrix.generateApfloatChebyshevUMatrix;
import static hu.nsmdmp.specialvectors.CumProbPoisson.createCumProbPoisson;
import static hu.nsmdmp.tasks.TaskUtils.getMaxCumProbMatrixElement;
import static hu.nsmdmp.tasks.TaskUtils.getMinCumProbMatrixElement;
import static hu.nsmdmp.tasks.TaskUtils.normChebyTV;
import static hu.nsmdmp.tasks.TaskUtils.normChebyUV;
import static hu.nsmdmp.tools.SetNormalization.normalize;
import static hu.nsmdmp.tools.SetVariationIterator.generateSetVariation;
import static hu.nsmdmp.tools.VectorSet.createVectorSet;
import static org.junit.Assert.assertEquals;
import hu.nsmdmp.mosek.LPSolution;
import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.numerics.matrix.Vector;
import hu.nsmdmp.utils.IOFile;

import java.io.File;

import org.apfloat.Apfloat;
import org.junit.Test;

public class DiscreteMultivariateDistributionsTest {

	@Test
	public void discreteMultivariateDistributions() throws Exception {
		Vector<Apfloat> powerV = new Vector<Apfloat>(IOFile.read(new File(getClass().getResource("binomialM5").toURI()), Apfloat.class));

		int length = 100;
		int maxOrder = 5;

		Apfloat[][] vectorSet = createVectorSet(2, length, Apfloat.class);

		// normailzed ChebyshevU vector.
		Vector<Apfloat> nChebyUV = normChebyUV(maxOrder, powerV, vectorSet);

		// ChebyshevU matrix.
		Matrix<Apfloat> chebU = generateApfloatChebyshevUMatrix(normalize(vectorSet), maxOrder);

		Apfloat[][] variation = generateSetVariation(vectorSet);
		Vector<Apfloat> f = createCumProbPoisson(variation, new Apfloat(8), new Apfloat(8));

		LPSolution minLPSolution = TaskUtils.getMinCumProbMatrixElement(chebU, nChebyUV, f);
		LPSolution maxLPSolution = TaskUtils.getMaxCumProbMatrixElement(chebU, nChebyUV, f);

		System.out.println(String.format("max: %s\tmin: %s", maxLPSolution.getPrimalSolution(), minLPSolution.getPrimalSolution()));

		assertEquals(0.00338248781146195, minLPSolution.getPrimalSolution(), 0.00000000000001);
		assertEquals(0.512441887045137, maxLPSolution.getPrimalSolution(), 0.00000000000001);
	}

	/**
	 * Nincs valos eredmeny!!!!!!!
	 */
	@Test
	public void discreteMultivariateDistributionsWithChebT() throws Exception {
		Vector<Apfloat> powerV = new Vector<Apfloat>(IOFile.read(new File(getClass().getResource("binomialM5").toURI()), Apfloat.class));

		int length = 100;
		int maxOrder = 5;

		Apfloat[][] vectorSet = createVectorSet(2, length, Apfloat.class);

		// normailzed ChebyshevT vector.
		Vector<Apfloat> nChebyTV = normChebyTV(maxOrder, powerV, vectorSet);

		// ChebyshevU matrix.
		Matrix<Apfloat> chebT = generateApfloatChebyshevTMatrix(normalize(vectorSet), maxOrder);

		Apfloat[][] variation = generateSetVariation(vectorSet);
		Vector<Apfloat> f = createCumProbPoisson(variation, new Apfloat(8), new Apfloat(8));

		LPSolution minLPSolution = getMinCumProbMatrixElement(chebT, nChebyTV, f);
		LPSolution maxLPSolution = getMaxCumProbMatrixElement(chebT, nChebyTV, f);

		System.out.println(String.format("max: %s\tmin: %s", maxLPSolution.getPrimalSolution(), minLPSolution.getPrimalSolution()));

		assertEquals(0.00338248781146195, minLPSolution.getPrimalSolution(), 0.000001);
		assertEquals(0.512441887045137, maxLPSolution.getPrimalSolution(), 0.001);
	}

}
