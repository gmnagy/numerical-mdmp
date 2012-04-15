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
		URL url = getClass().getResource("m2");
		Vector powerB = new Vector(IOFile.read(new File(url.toURI())));

		Apfloat[][] vectorSet = TaskUtils.createVectorSet(2, 3);

		int maxOrder = 2;
		Matrix T = MonomialToChebUMatrix.getMatrix(maxOrder, vectorSet.length);

		Vector cheby2B = MatrixMath.multiply(T, powerB);
		System.out.println("b: " + cheby2B);

		// f(z1,z2) = 1 if z1<=x and z2<=y, 0 otherwise. 
		List<Apfloat[]> variation = Variation.createVariation(vectorSet);
		Vector f = CVectorFactory.getCumProbPoisson(variation, new Apfloat[] { ApfloatUtils.valueOf(1), ApfloatUtils.valueOf(1) });
		System.out.println("f: " + f);

		Matrix chebU = MatrixFactory.getChebyshevUMatrix(vectorSet, maxOrder);
		System.out.println(chebU);

		LPSolution min = LinearProgrammingEq.optimizeMin(chebU, cheby2B, f);
//		PreciseLPSolution min = PreciseLPCalc.optimizeMin(chebU, cheby2B, f);
//		PreciseLPSolution max = PreciseLPCalc.optimizeMax(chebU, cheby2B, f);

		System.out.println(Utils.print(min.getX()));
		System.out.println(min.getPrimalSolution());
	}

	@Test
	public void test() {
		Apfloat[][] vectorSet = TaskUtils.createVectorSet(2, 3);
		Matrix chebU = MatrixFactory.getChebyshevUMatrix(vectorSet, 3);

		Vector x = new Vector(new double[] { 34.916666666666664, 0.0, 8.916666666666668, 0.0, 0.0, 0.0, 1.4166666666666679, 16.0, 34.416666666666686 });
		Vector b = MatrixMath.multiply(chebU, x);

		System.out.println(b);
	}
}
