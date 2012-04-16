package hu.nsmdmp.tasks;

import hu.nsmdmp.cvectors.CVectorFactory;
import hu.nsmdmp.math.Variation;
import hu.nsmdmp.matrix.Matrix;
import hu.nsmdmp.matrix.operation.MatrixMath;
import hu.nsmdmp.matrixfactory.MatrixFactory;
import hu.nsmdmp.matrixfactory.MonomialToChebUMatrix;
import hu.nsmdmp.mosek.LPSolution;
import hu.nsmdmp.mosek.LinearProgrammingEq;
import hu.nsmdmp.utils.ApfloatUtils;
import hu.nsmdmp.utils.IOFile;
import hu.nsmdmp.utils.Utils;
import hu.nsmdmp.vector.Vector;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import mosek.MosekException;

import org.apfloat.Apfloat;
import org.junit.Test;

public class DiscreteMultivariateDistributionsTask {

	@Test
	public void discreteMultivariateDistributions() throws IOException, URISyntaxException, MosekException {
		URL url = getClass().getResource("binomialM2");
		Vector powerB = new Vector(IOFile.read(new File(url.toURI())));

		Apfloat[][] vectorSet = TaskUtils.createVectorSet(2, 5);

		int maxOrder = 2;
		Matrix T = MonomialToChebUMatrix.getMatrix(maxOrder, vectorSet.length);

		Vector cheby2B = MatrixMath.multiply(T, powerB);
//		System.out.println("b: " + cheby2B);

		// f(z1,z2) = 1 if z1<=x and z2<=y, 0 otherwise. 
		List<Apfloat[]> variation = Variation.createVariation(vectorSet);
		Vector f = CVectorFactory.getCumProbPoisson(variation, new Apfloat[] { ApfloatUtils.valueOf(1), ApfloatUtils.valueOf(1) });
//		System.out.println("f: " + f);

		Matrix chebU = MatrixFactory.getChebyshevUMatrix(vectorSet, maxOrder);
//		System.out.println(chebU);

		//LPSolution min = LinearProgrammingEq.optimizeMin(chebU, cheby2B, f);
		LPSolution min = LinearProgrammingEq.optimizeMin(MatrixFactory.getMonomialMatrix(vectorSet, maxOrder), powerB, f);

		System.out.println(Utils.print(min.getX()));
		System.out.println("c(min): " + getC(min.getX(), f));
		System.out.println("t(min): " + min.getPrimalSolution());

//		LPSolution max = LinearProgrammingEq.optimizeMax(chebU, cheby2B, f);
//		System.out.println("c(max): " + getC(max.getX()));
//		System.out.println("t(max): " + max.getObjectiveValue());
	}

	private Apfloat getC(double[] X, Vector f) {
		Apfloat c = ApfloatUtils.ZERO;
		for (int i = 0; i < X.length; i++) {
			if (X[i] != 0) {
//				System.out.println(X[i] + "  " + f.get(i));
			}

			Apfloat x = ApfloatUtils.valueOf(X[i]);

			c = c.add(x.multiply(f.get(i)));
		}

		return c;
	}
}
