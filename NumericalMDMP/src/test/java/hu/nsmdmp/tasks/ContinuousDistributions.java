package hu.nsmdmp.tasks;

import static hu.nsmdmp.polynomialmatrixfactory.ChebyshevUMatrix.generateApfloatChebyshevUMatrix;
import static hu.nsmdmp.specialvectors.Discrete.discreteVector;
import static hu.nsmdmp.tasks.TaskUtils.getMaxCumProbMatrixElement;
import static hu.nsmdmp.tasks.TaskUtils.getMinCumProbMatrixElement;
import static hu.nsmdmp.tasks.TaskUtils.normChebyUV;
import static hu.nsmdmp.tools.SetNormalization.normalize;
import static hu.nsmdmp.tools.SetVariationIterator.getNumberOfVariation;
import static hu.nsmdmp.tools.SubSequencesGenerator.getSubSequences2;
import hu.nsmdmp.mosek.LPSolution;
import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.numerics.matrix.Vector;

import org.apfloat.Apfloat;
import org.junit.Test;

public class ContinuousDistributions {

	@Test
	public void test() throws Exception {

		String fileName = "mng16_5";
		int n = 16;
		int m = 3;
		int dim = 5;
		int l = 3;
		double e = 0.0005;

		Apfloat[][] vectorSet = getSubSequences2(n + dim, l + 1, dim, Apfloat.class);

		Vector<Apfloat> normChebyUV = normChebyUV(fileName, n, m, dim, l, vectorSet);
		Matrix<Apfloat> chebU = generateApfloatChebyshevUMatrix(normalize(vectorSet), m);

		Vector<Apfloat> f = discreteVector(getNumberOfVariation(vectorSet), 1, new Apfloat(0), new Apfloat(1));

		LPSolution minLPSolution = getMinCumProbMatrixElement(chebU, normChebyUV, f, e);
		LPSolution maxLPSolution = getMaxCumProbMatrixElement(chebU, normChebyUV, f, e);

		System.out.println(String.format("e:%s    min:%s  -  max:%s", e, minLPSolution.getPrimalSolution(), maxLPSolution.getPrimalSolution()));
	}
}
