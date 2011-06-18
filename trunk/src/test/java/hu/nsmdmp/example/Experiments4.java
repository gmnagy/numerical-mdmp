package hu.nsmdmp.example;


import java.util.Arrays;

import hu.nsmdmp.cvectors.CVector;
import hu.nsmdmp.cvectors.ICVector;
import hu.nsmdmp.distributions.BivPoissDistrVector;
import hu.nsmdmp.distributions.TruncBivPoissArray;
import hu.nsmdmp.matrixmath.MatrixMath;
import hu.nsmdmp.utils.Precision;
import mosek.MosekException;

import org.apfloat.Apfloat;
import org.junit.Test;

public class Experiments4 extends AExperiments {

	@Test
	@Override
	public void run() throws MosekException {
		double[][] vectorSet = new double[2][101];
		for (double i = 0; i <= 100; i++) {
			vectorSet[0][(int) i] = i;
			vectorSet[1][(int) i] = i;
		}

		run(vectorSet);
	}

	@Override
	protected Apfloat[] distribution(int n) {
		Apfloat[] b = new Apfloat[n];

		double[] bivPoissDistrVector=BivPoissDistrVector.getBivPoissDistrVector(TruncBivPoissArray.getTruncBivPoissArray(1, 2, 3, 100, 100));
		System.out.println(Arrays.toString(bivPoissDistrVector));
		for (int i = 0; i < n; i++) {
			b[i] = new Apfloat(bivPoissDistrVector[i] , Precision.SCALE);
		}

		return b;
	}

	@Override
	protected int getMaxOrder() {
		return 8;
	}

	@Override
	protected ICVector getCVector(Apfloat[][] vSet) {
		return CVector.getSumProbEx4CVector(MatrixMath.createVariation(vSet));
	}
}

