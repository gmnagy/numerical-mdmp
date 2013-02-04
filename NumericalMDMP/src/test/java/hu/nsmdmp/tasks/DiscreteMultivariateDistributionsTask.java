package hu.nsmdmp.tasks;

import static hu.nsmdmp.polynomialmatrixfactory.ChebyshevUMatrix.generateDoubleChebyshevUMatrix;
import static hu.nsmdmp.specialvectors.CumProbPoisson.createCumProbPoisson;
import static hu.nsmdmp.tasks.TaskUtils.createNormChebyshevUVector;
import static hu.nsmdmp.tasks.TaskUtils.getMaxCumProbMatrixElement;
import static hu.nsmdmp.tasks.TaskUtils.getMinCumProbMatrixElement;
import static hu.nsmdmp.tools.SetNormalization.normalize;
import static hu.nsmdmp.tools.SetVariationIterator.generateSetVariation;
import static hu.nsmdmp.tools.VectorSet.createVectorSet;
import static org.junit.Assert.assertEquals;
import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.numerics.matrix.Vector;
import hu.nsmdmp.utils.IOFile;

import java.io.File;

import org.apfloat.Apfloat;
import org.junit.Test;

public class DiscreteMultivariateDistributionsTask {

	@Test
	public void discreteMultivariateDistributions() throws Exception {
		Vector<Apfloat> powerV = new Vector<Apfloat>(IOFile.read(new File(getClass().getResource("binomialM5").toURI()), Apfloat.class));

		int length = 100;
		int maxOrder = 5;

		Apfloat[][] vectorSet = createVectorSet(2, length, Apfloat.class);

		// normailzed ChebyshevU vector.
		Vector<Apfloat> nChebyUV = createNormChebyshevUVector(maxOrder, powerV, vectorSet);

		// ChebyshevU matrix.
		Matrix<Apfloat> chebU = generateDoubleChebyshevUMatrix(normalize(vectorSet), maxOrder);

		Apfloat[][] variation = generateSetVariation(vectorSet);
		Vector<Apfloat> f = createCumProbPoisson(variation, new Apfloat(8), new Apfloat(8));

		double min = getMinCumProbMatrixElement(chebU, nChebyUV, f);
		double max = getMaxCumProbMatrixElement(chebU, nChebyUV, f);

		System.out.println(String.format("max: %s\tmin: %s", max, min));

		assertEquals(0.00338248781146195, min, 0.000000000000001);
		assertEquals(0.512441887045137, max, 0.000000000000001);
	}

}
