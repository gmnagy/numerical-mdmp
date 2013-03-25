package hu.nsmdmp.tasks;

import static hu.nsmdmp.moments.MultivariateMoments.convertBinomMomToPowerMom;
import static hu.nsmdmp.moments.MultivariateMoments.createBinomialMoments;
import static hu.nsmdmp.polynomialmatrixfactory.ChebyshevUMatrix.generateApfloatChebyshevUMatrix;
import static hu.nsmdmp.specialvectors.Discrete.discreteVector;
import static hu.nsmdmp.tasks.TaskUtils.getMaxCumProbMatrixElement;
import static hu.nsmdmp.tasks.TaskUtils.getMinCumProbMatrixElement;
import static hu.nsmdmp.tools.SetNormalization.normalize;
import static hu.nsmdmp.tools.SetVariationIterator.getNumberOfVariation;
import static hu.nsmdmp.tools.SubSequencesGenerator.getSubSequences2;
import hu.nsmdmp.moments.Moment;
import hu.nsmdmp.mosek.LPSolution;
import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.numerics.matrix.Vector;
import hu.nsmdmp.utils.IOFile;

import java.io.File;
import java.util.Collection;
import java.util.List;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;
import org.junit.Test;

public class ContinuousDistributionsExponent {

	@Test
	public void runChebyshevU() throws Exception {

		String exponentFile = "exponent";
		String pFile = "p";

		Double[] expArray = IOFile.read(new File(ContinuousDistributionsExponent.class.getResource(exponentFile).toURI()), Double.class);
		Apfloat[] pArray = IOFile.read(new File(ContinuousDistributionsExponent.class.getResource(pFile).toURI()), Apfloat.class);

		int n = 23; //?
		int m = 3;
		int dim = 7;
		int l = 3;

		Apfloat[][] vectorSet = getSubSequences2(n + dim, l + 1, dim, Apfloat.class);
		Matrix<Apfloat> chebU = generateApfloatChebyshevUMatrix(normalize(vectorSet), m);
		Vector<Apfloat> f = discreteVector(getNumberOfVariation(vectorSet), 1, new Apfloat(0), new Apfloat(1));

		for (Apfloat p : pArray) {

			Vector<Apfloat> powerMomentV = createPowerMoments(p, expArray, n, m, dim, l);
			Vector<Apfloat> normChebyUV = TaskUtils.normChebyUV(m, powerMomentV, vectorSet);

//			LPSolution minLPSolution = getMinCumProbMatrixElement(chebU, normChebyUV, f);
//			LPSolution maxLPSolution = getMaxCumProbMatrixElement(chebU, normChebyUV, f);
//
//			System.out.println(String.format("min:%s  -  max:%s", minLPSolution.getPrimalSolution(), maxLPSolution.getPrimalSolution()));
		}

	}

	private static Vector<Apfloat> createPowerMoments(Apfloat p, Double[] expArray, int n, int m, int dim, int l) throws Exception {

		Apfloat[] probabilities = new Apfloat[expArray.length];
		int i = 0;
		for (double exp : expArray) {
			probabilities[i] = ApfloatMath.pow(p, (long) exp);
			i++;
		}

		List<Moment<Apfloat>> binomMoms = createBinomialMoments(probabilities, n, m, dim, l);
		Collection<Moment<Apfloat>> powerMoms = convertBinomMomToPowerMom(binomMoms);

		return TaskUtils.toVector(powerMoms);
	}
}
