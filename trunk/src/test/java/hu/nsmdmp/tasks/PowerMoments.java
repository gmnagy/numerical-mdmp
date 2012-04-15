package hu.nsmdmp.tasks;

import hu.nsmdmp.cvectors.CVectorFactory;
import hu.nsmdmp.distributions.BivPoissDistrVector;
import hu.nsmdmp.distributions.TruncBivPoissArray;
import hu.nsmdmp.math.Variation;
import hu.nsmdmp.matrix.Matrix;
import hu.nsmdmp.matrix.operation.MatrixMath;
import hu.nsmdmp.matrixfactory.MatrixFactory;
import hu.nsmdmp.utils.ApfloatUtils;
import hu.nsmdmp.vector.Vector;

import java.util.List;

import mosek.MosekException;

import org.apfloat.Apfloat;
import org.junit.Test;

public class PowerMoments {

	@Test
	public void discreteMultivariateDistributions1() throws MosekException {
		int moment = 2;
		int dim = 2;
		int setNumber = 19;

		Apfloat[][] vectorSet = TaskUtils.createVectorSet(dim, setNumber);
		List<Apfloat[]> variation = Variation.createVariation(vectorSet);

		// the matrix
		Matrix monomial = MatrixFactory.getMonomialMatrix(vectorSet, moment);
//		Matrix chebU = MatrixFactory.getChebyshevTMatrix(vectorSet, moment);
		// f(z1,z2) = 1 if z1<=x and z2<=y, 0 otherwise. 
		// x=1, y=1
		Vector f = CVectorFactory.getCumProbPoisson(variation, new Apfloat[] { ApfloatUtils.ONE, ApfloatUtils.ONE });
		// b vector
		Vector b = MatrixMath.multiply(monomial, distribution(variation.size()));
		System.out.println(b);

//		PreciseLPSolution result = PreciseLPCalc.optimizeMin(chebU, b, f);

//		System.out.println(Utils.print(result.getX()));

//		Apfloat sum = 
//		for(Apfloat a: result.getX()) {
//			
//		}
	}

//	private Vector distribution(int n) {
//		Vector D = new Vector(n);
//
//		for (int i = 0; i < n; i++) {
//			D.set(i, ApfloatUtils.ONE.divide(new Apfloat(n)));
//		}
//
//		return D;
//	}

	protected Vector distribution(int n) {
		Vector D = new Vector(n);

		double[] bivPoissDistrVector = BivPoissDistrVector.getBivPoissDistrVector(TruncBivPoissArray.getTruncBivPoissArray(3, 4, 5, 19, 19));
//		System.out.println(Arrays.toString(bivPoissDistrVector));
		for (int i = 0; i < n; i++) {
			D.set(i, ApfloatUtils.valueOf(bivPoissDistrVector[i]));
		}

		return D;
	}
}
