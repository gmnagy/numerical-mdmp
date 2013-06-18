package hu.nsmdmp2.tasks;

import static hu.nsmdmp2.polynomialmatrixfactory.ChebyshevUMatrix.generateChebyshevUMatrix;
import static hu.nsmdmp2.specialvectors.CumProbPoisson.createCumProbPoisson;
import static hu.nsmdmp2.tools.SetNormalization.normalize;
import static hu.nsmdmp2.tools.SetVariationIterator.generateSetVariation;
import static hu.nsmdmp2.tools.VectorSet.createVectorSet;
import hu.nsmdmp2.mosek.LPSolution;
import hu.nsmdmp2.numerics.Value;
import hu.nsmdmp2.utils.IOFile;

import java.io.File;

import org.apfloat.Apfloat;
import org.junit.Test;

public class Estimations {

	@Test
	public void estimations() throws Exception {

		Value[] powerV = IOFile.read(new File(getClass().getResource("poissonM10").toURI()), Apfloat.class);

		int length = 49;
		int maxOrder = 10;

		Value[] cdf = new Value[] { new Value(new Apfloat(7)), new Value(new Apfloat(8)) };

		// min

		long time = System.currentTimeMillis();

		LPSolution minLPSolution = estimationsMIN(length, maxOrder, powerV, cdf);

		System.out.println(String.format("min: %s\ttime: %s ms", minLPSolution.getPrimalSolution(), (System.currentTimeMillis() - time)));

		// max

		time = System.currentTimeMillis();

		LPSolution maxLPSolution = estimationsMAX(length, maxOrder, powerV, cdf);

		System.out.println(String.format("max: %s\ttime: %s ms", maxLPSolution.getPrimalSolution(), (System.currentTimeMillis() - time)));

	}

	private LPSolution estimationsMIN(int length, int maxOrder, Value[] powerV, Value... cdf) throws Exception {

		Value[][] vectorSet = createVectorSet(2, length, Apfloat.class);

		// normailzed ChebyshevU vector.
		Value[] nChebyUV = TaskUtils.normChebyUV(maxOrder, powerV, vectorSet);

		// ChebyshevU matrix.
		Value[][] chebU = generateChebyshevUMatrix(normalize(vectorSet), maxOrder);

		Value[][] variation = generateSetVariation(vectorSet);
		Value[] f = createCumProbPoisson(variation, cdf);

		return TaskUtils.getMinCumProbMatrixElement(chebU, nChebyUV, f);
	}

	private LPSolution estimationsMAX(int length, int maxOrder, Value[] powerV, Value... cdf) throws Exception {

		Value[][] vectorSet = createVectorSet(2, length, Apfloat.class);

		// normailzed ChebyshevU vector.
		Value[] nChebyUV = TaskUtils.normChebyUV(maxOrder, powerV, vectorSet);

		// ChebyshevU matrix.
		Value[][] chebU = generateChebyshevUMatrix(normalize(vectorSet), maxOrder);

		Value[][] variation = generateSetVariation(vectorSet);
		Value[] f = createCumProbPoisson(variation, cdf);

		return TaskUtils.getMaxCumProbMatrixElement(chebU, nChebyUV, f);
	}
}
