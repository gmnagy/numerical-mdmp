package hu.nsmdmp2.tasks;

import static hu.nsmdmp2.polynomialmatrixfactory.ChebyshevTMatrix.generateChebyshevTMatrix;
import static hu.nsmdmp2.polynomialmatrixfactory.ChebyshevUMatrix.generateChebyshevUMatrix;
import static hu.nsmdmp2.specialvectors.CumProbPoisson.createCumProbPoisson;
import static hu.nsmdmp2.tools.SetNormalization.normalize;
import static hu.nsmdmp2.tools.SetVariationIterator.generateSetVariation;
import static hu.nsmdmp2.tools.VectorSet.createVectorSet;
import static org.junit.Assert.assertEquals;
import hu.nsmdmp2.mosek.LPSolution;
import hu.nsmdmp2.numerics.Value;
import hu.nsmdmp2.utils.IOFile;

import java.io.File;

import org.apfloat.Apfloat;
import org.junit.Test;

public class DiscreteMultivariateDistributionsTest {

	@Test
	public void discreteMultivariateDistributions() throws Exception {
		Value[] powerV = IOFile.read(new File(getClass().getResource("binomialM5").toURI()), Apfloat.class);

		int length = 100;
		int maxOrder = 5;

		Value[][] vectorSet = createVectorSet(2, length, Apfloat.class);

		// normailzed ChebyshevU vector.
		Value[] nChebyUV = TaskUtils.normChebyUV(maxOrder, powerV, vectorSet);

		// ChebyshevU matrix.
		Value[][] chebU = generateChebyshevUMatrix(normalize(vectorSet), maxOrder);

		Value[][] variation = generateSetVariation(vectorSet);
		Value[] f = createCumProbPoisson(variation, new Value(new Apfloat(8)), new Value(new Apfloat(8)));

		LPSolution minLPSolution = TaskUtils.getMinCumProbMatrixElement(chebU, nChebyUV, f);
		LPSolution maxLPSolution = TaskUtils.getMaxCumProbMatrixElement(chebU, nChebyUV, f);

		System.out.println(String.format("max: %s\tmin: %s", maxLPSolution.getPrimalSolution(), minLPSolution.getPrimalSolution()));

		assertEquals(0.00338248781146195, minLPSolution.getPrimalSolution(), 0.000000000001);
		assertEquals(0.512441887045137, maxLPSolution.getPrimalSolution(), 0.000000000001);
	}

	/**
	 * Nincs valos eredmeny!!!!!!!
	 */
	@Test
	public void discreteMultivariateDistributionsWithChebT() throws Exception {
		Value[] powerV = IOFile.read(new File(getClass().getResource("binomialM5").toURI()), Apfloat.class);

		int length = 100;
		int maxOrder = 5;

		Value[][] vectorSet = createVectorSet(2, length, Apfloat.class);

		// normailzed ChebyshevT vector.
		Value[] nChebyTV = TaskUtils.normChebyTV(maxOrder, powerV, vectorSet);

		// ChebyshevU matrix.
		Value[][] chebT = generateChebyshevTMatrix(normalize(vectorSet), maxOrder);

		Value[][] variation = generateSetVariation(vectorSet);
		Value[] f = createCumProbPoisson(variation, new Value(new Apfloat(8)), new Value(new Apfloat(8)));

		LPSolution minLPSolution = TaskUtils.getMinCumProbMatrixElement(chebT, nChebyTV, f);
		LPSolution maxLPSolution = TaskUtils.getMaxCumProbMatrixElement(chebT, nChebyTV, f);

		System.out.println(String.format("max: %s\tmin: %s", maxLPSolution.getPrimalSolution(), minLPSolution.getPrimalSolution()));

		assertEquals(0.00338248781146195, minLPSolution.getPrimalSolution(), 0.000001);
		assertEquals(0.512441887045137, maxLPSolution.getPrimalSolution(), 0.001);
	}

}
