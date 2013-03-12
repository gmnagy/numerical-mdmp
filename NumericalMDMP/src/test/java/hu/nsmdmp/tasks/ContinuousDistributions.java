package hu.nsmdmp.tasks;

import static hu.nsmdmp.polynomialmatrixfactory.ChebyshevUMatrix.generateApfloatChebyshevUMatrix;
import static hu.nsmdmp.specialvectors.Discrete.discreteVector;
import static hu.nsmdmp.tasks.TaskUtils.getMaxCumProbMatrixElement;
import static hu.nsmdmp.tasks.TaskUtils.getMinCumProbMatrixElement;
import static hu.nsmdmp.tasks.TaskUtils.normChebyUV;
import static hu.nsmdmp.tools.SetNormalization.normalize;
import static hu.nsmdmp.tools.SetVariationIterator.getNumberOfVariation;
import static hu.nsmdmp.tools.SubSequencesGenerator.getSubSequences2;
import static hu.nsmdmp.tasks.TaskUtils.*;
import static hu.nsmdmp.polynomialmatrixfactory.MonomialMatrix.generateApfloatMonomialMatrix;


import java.util.Arrays;

import hu.nsmdmp.mosek.LPSolution;
import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.numerics.matrix.Vector;

import org.apfloat.Apfloat;
import org.junit.Test;

public class ContinuousDistributions {

	String fileName = "mng31_5";
	int n = 31;
	int m = 3;
	int dim = 1;
	int l = 31;
	double e = 0;

	
	@Test
	public void test() throws Exception {


		Apfloat[][] vectorSet = getSubSequences2(n + dim, l + 1, dim, Apfloat.class);

		for(int i=0; i<dim; i++){
			System.out.println(Arrays.toString(vectorSet[i]));
		}

		Vector<Apfloat> normChebyUV = normChebyUV(fileName, n, m, dim, l, vectorSet);
		Matrix<Apfloat> chebU = generateApfloatChebyshevUMatrix(normalize(vectorSet), m);

		Vector<Apfloat> f = discreteVector(getNumberOfVariation(vectorSet), 1, new Apfloat(0), new Apfloat(1));

		LPSolution minLPSolution = getMinCumProbMatrixElement(chebU, normChebyUV, f, e);
		LPSolution maxLPSolution = getMaxCumProbMatrixElement(chebU, normChebyUV, f, e);

		//System.out.println(String.format("e:%s    min:%s  -  max:%s", e, 1-maxLPSolution.getPrimalSolution(), 1-minLPSolution.getPrimalSolution()));
		System.out.println(String.format("test   e:%s    min:%s  -  max:%s", e, minLPSolution.getPrimalSolution(), maxLPSolution.getPrimalSolution()));
		System.out.println(  Arrays.toString(minLPSolution.getBasisIndexes()) + Arrays.toString(maxLPSolution.getBasisIndexes()));
	}
	
	
	
	@Test
	public void test2() throws Exception {


		Apfloat[][] vectorSet = getSubSequences2(n + dim, l + 1, dim, Apfloat.class);

		for(int i=0; i<dim; i++){
			System.out.println(Arrays.toString(vectorSet[i]));
		}

		Vector<Apfloat> normChebyUV = createPowerMoments(fileName, n, m, dim, l);
		Matrix<Apfloat> chebU = generateApfloatMonomialMatrix(vectorSet, m);

		Vector<Apfloat> f = discreteVector(getNumberOfVariation(vectorSet), 1, new Apfloat(0), new Apfloat(1));

		LPSolution minLPSolution = getMinCumProbMatrixElement(chebU, normChebyUV, f, e);
		LPSolution maxLPSolution = getMaxCumProbMatrixElement(chebU, normChebyUV, f, e);

		//System.out.println(String.format("e:%s    min:%s  -  max:%s", e, 1-maxLPSolution.getPrimalSolution(), 1-minLPSolution.getPrimalSolution()));
		System.out.println(String.format("test2   e:%s    min:%s  -  max:%s", e, minLPSolution.getPrimalSolution(), maxLPSolution.getPrimalSolution()));
		System.out.println(  Arrays.toString(minLPSolution.getBasisIndexes()) + Arrays.toString(maxLPSolution.getBasisIndexes()));

	}
}

