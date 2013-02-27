package hu.nsmdmp.tasks;

import static hu.nsmdmp.polynomialmatrixfactory.ChebyshevUMatrix.generateApfloatChebyshevUMatrix;
import static hu.nsmdmp.tasks.TaskUtils.getMaxCumProbMatrixElement;
import static hu.nsmdmp.tasks.TaskUtils.getMinCumProbMatrixElement;
import static hu.nsmdmp.tasks.TaskUtils.normChebyUV;
import static hu.nsmdmp.tools.SetNormalization.normalize;
import static hu.nsmdmp.tools.SubSequencesGenerator.getSubSequences2;
import hu.nsmdmp.mosek.LPSolution;
import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.numerics.matrix.Vector;
import mosek.Env;

import org.apfloat.Apfloat;
import org.junit.Test;

public class ContinuousDistributionFeasibility {

	@Test
	public void test() throws Exception {

		String fileName = "mng16_5";
		int n = 16;
		int m = 3;
		int dim = 5;
		int l = 3;

		Apfloat[][] vectorSet = getSubSequences2(n + dim, l + 1, dim, Apfloat.class);

		Vector<Apfloat> normChebyUV = normChebyUV(fileName, n, m, dim, l, vectorSet);
		Matrix<Apfloat> chebU = generateApfloatChebyshevUMatrix(normalize(vectorSet), m);

		for (int i = 0; i < chebU.getRowDimension() - 1; i++) {

			Matrix<Apfloat> sub = chebU.getSubMatrix(0, i);
			Vector<Apfloat> v = normChebyUV.getSubVector(0, i);
			Vector<Apfloat> f = new Vector<Apfloat>(chebU.getRow(i + 1));

			LPSolution minLPSolution = getMinCumProbMatrixElement(sub, v, f);
			LPSolution maxLPSolution = getMaxCumProbMatrixElement(sub, v, f);

			System.out.println(String.format("i:%s    min:%s  -  expected:%s  -  max:%s", i, minLPSolution.getPrimalSolution(), normChebyUV.get(i + 1), maxLPSolution.getPrimalSolution()));

			if (minLPSolution.getSolutionStatus() != Env.solsta.optimal || maxLPSolution.getSolutionStatus() != Env.solsta.optimal)
				break;
		}

	}
}
