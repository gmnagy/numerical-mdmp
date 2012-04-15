package hu.nsmdmp.example;

import hu.nsmdmp.cvectors.CVectorFactory;
import hu.nsmdmp.math.Variation;
import hu.nsmdmp.utils.ApfloatUtils;
import hu.nsmdmp.vector.Vector;
import mosek.MosekException;

import org.apfloat.Apfloat;
import org.junit.Test;

public class Experiments5 extends AExperiments {

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
	protected Vector distribution(int n) {
		Vector D = new Vector(n);

		for (int i = 0; i < n; i++) {
			D.set(i, ApfloatUtils.ONE.divide(new Apfloat(n)));
		}

		return D;
	}

	@Override
	protected int getMaxOrder() {
		return 2;
	}

	@Override
	protected Vector getCVector(Apfloat[][] vSet) {
		return CVectorFactory.getExpCVector(Variation.createVariation(vSet));
	}
}
