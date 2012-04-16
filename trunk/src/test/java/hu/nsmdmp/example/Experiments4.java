package hu.nsmdmp.example;

import hu.nsmdmp.cvectors.CVectorFactory;
import hu.nsmdmp.distributions.BivPoissDistrVector;
import hu.nsmdmp.distributions.TruncBivPoissArray;
import hu.nsmdmp.math.Variation;
import hu.nsmdmp.utils.ApfloatUtils;
import hu.nsmdmp.vector.Vector;
import mosek.MosekException;

import org.apfloat.Apfloat;
import org.junit.Test;

public class Experiments4 extends AExperiments {

	@Test
	@Override
	public void run() throws MosekException {
		long starttime = System.currentTimeMillis();
		double[][] vectorSet = new double[2][101];
		for (double i = 0; i <= 100; i++) {
			vectorSet[0][(int) i] = i;
			vectorSet[1][(int) i] = i;
		}

		System.out.println("Experiments 4 Vectorset prepare time: " + (System.currentTimeMillis() - starttime));
		run(vectorSet);
	}

	@Override
	protected Vector distribution(int n) {
		Vector D = new Vector(n);

		double[] bivPoissDistrVector = BivPoissDistrVector.getBivPoissDistrVector(TruncBivPoissArray.getTruncBivPoissArray(1, 2, 3, 100, 100));
		//System.out.println(Arrays.toString(bivPoissDistrVector));
		for (int i = 0; i < n; i++) {
			D.set(i, ApfloatUtils.valueOf(bivPoissDistrVector[i]));
		}

		return D;
	}

	@Override
	protected int getMaxOrder() {
		return 4;
	}

	@Override
	protected Vector getCVector(Apfloat[][] vSet) {
		return CVectorFactory.getSumProbEx4CVector(Variation.createVariation(vSet));
	}
}
