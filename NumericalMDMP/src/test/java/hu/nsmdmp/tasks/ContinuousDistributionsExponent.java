package hu.nsmdmp.tasks;

import static hu.nsmdmp.moments.MultivariateMoments.convertBinomMomToPowerMom;
import static hu.nsmdmp.moments.MultivariateMoments.createBinomialMoments;
import static hu.nsmdmp.polynomialmatrixfactory.ChebyshevUMatrix.generateApfloatChebyshevUMatrix;
import static hu.nsmdmp.specialvectors.Discrete.discreteVector;
import static hu.nsmdmp.tasks.TaskUtils.getMaxCumProbMatrixElement;
import static hu.nsmdmp.tasks.TaskUtils.getMinCumProbMatrixElement;
import static hu.nsmdmp.tasks.TaskUtils.getPrecMaxCumProbMatrixElement;
import static hu.nsmdmp.tasks.TaskUtils.getPrecMinCumProbMatrixElement;
import static hu.nsmdmp.tools.SetNormalization.normalize;
import static hu.nsmdmp.tools.SetVariationIterator.getNumberOfVariation;
import static hu.nsmdmp.tools.SubSequencesGenerator.getSubSequences2;
import hu.nsmdmp.moments.Moment;
import hu.nsmdmp.mosek.LPSolution;
import hu.nsmdmp.mosek.PreciseLPSolution;
import hu.nsmdmp.mosek.PreciseLPCalc;
import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.numerics.matrix.Vector;
import hu.nsmdmp.utils.IOFile;

import java.io.File;
import java.util.Arrays;
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
		int dim = 2;
		int l = 11;

		System.out.println("start-vectorset");
		int i = 1;
		Apfloat[][] vectorSet = getSubSequences2(n + dim, l + i, dim, Apfloat.class);
		System.out.println("start-chebU");
		Matrix<Apfloat> chebU = generateApfloatChebyshevUMatrix(normalize(vectorSet), m);
		System.out.println("start-discrete vector");
		Vector<Apfloat> f = discreteVector(getNumberOfVariation(vectorSet), i, new Apfloat(0), new Apfloat(i));

		String[][] result = new String[pArray.length][6];
		System.out.println(pArray.length);

		int ind = 0;
		for (Apfloat p : pArray) {
			System.out.println(ind);

			System.out.println("start-power");
			Vector<Apfloat> powerMomentV = createPowerMoments(p, expArray, n, m, dim, l);
			System.out.println("normChebyUV");
			Vector<Apfloat> normChebyUV = TaskUtils.normChebyUV(m, powerMomentV, vectorSet);

			System.out.println("start-min");
			long startTime = System.currentTimeMillis();
			LPSolution minLPSolution = getMinCumProbMatrixElement(chebU, normChebyUV, f);
			System.out.println("Running time-min:" + (System.currentTimeMillis() - startTime));

			result[ind][0] = String.valueOf(minLPSolution.getPrimalSolution());
			result[ind][1] = String.valueOf(minLPSolution.getSolutionStatus());
			result[ind][2] = String.valueOf((System.currentTimeMillis() - startTime));

			System.out.println("start-minPrec");
			startTime = System.currentTimeMillis();
			PreciseLPSolution<Apfloat> minPrecLPSolution = getPrecMinCumProbMatrixElement(chebU, normChebyUV, f);
			System.out.println("Running time-min:" + (System.currentTimeMillis() - startTime));

			
			System.out.println("start-max");
			startTime = System.currentTimeMillis();
			LPSolution maxLPSolution = getMaxCumProbMatrixElement(chebU, normChebyUV, f);
			System.out.println("Running time-max:" + (System.currentTimeMillis() - startTime));
			
			result[ind][3] = String.valueOf(maxLPSolution.getPrimalSolution());
			result[ind][4] = String.valueOf(maxLPSolution.getSolutionStatus());
			result[ind][5] = String.valueOf((System.currentTimeMillis() - startTime));
			
			System.out.println("start-maxPrec");
			startTime = System.currentTimeMillis();
			PreciseLPSolution<Apfloat> maxPrecLPSolution = getPrecMaxCumProbMatrixElement(chebU, normChebyUV, f);
			System.out.println("Running time-min:" + (System.currentTimeMillis() - startTime));

			
			System.out.println(String.format("min:%s  -  max:%s", minLPSolution.getPrimalSolution(), maxLPSolution.getPrimalSolution()));
			System.out.println(String.format("Prec_min:%s  -  Prec_max:%s", minPrecLPSolution.getObjectiveValue(), maxPrecLPSolution.getObjectiveValue()));
			System.out.println(String.format("nonneg_min:%s  -  nonneg_max:%s",
					minPrecLPSolution.getPrimalNonnegInfeas().doubleValue(), maxPrecLPSolution.getPrimalNonnegInfeas().doubleValue()));
			System.out.println(String.format("prslack_min:%s  -  prslack_max:%s", 
					minPrecLPSolution.getPrimalSlackInfeas().doubleValue(), maxPrecLPSolution.getPrimalSlackInfeas().doubleValue()));
			System.out.println(String.format("dualslack_min:%s  -  dslack_max:%s", 
					minPrecLPSolution.getDualSlackInfeas().doubleValue(), maxPrecLPSolution.getDualSlackInfeas().doubleValue()));
			//System.out.println(  Arrays.toString(minLPSolution.getBasisIndexes()) + Arrays.toString(maxLPSolution.getBasisIndexes()));



			ind++;
		}

		IOFile.write("resultCDE", "\t", result);
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
