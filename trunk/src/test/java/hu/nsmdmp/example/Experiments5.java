package hu.nsmdmp.example;

import hu.nsmdmp.ApfloatUtils;
import hu.nsmdmp.cvectors.CVector;
import hu.nsmdmp.cvectors.ICVector;
import hu.nsmdmp.matrices.MatrixUtils;
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
	protected Apfloat[] distribution(int n) {
		Apfloat[] b = new Apfloat[n];

		for (int i = 0; i < n; i++) {
			b[i] = ApfloatUtils.ONE.divide(new Apfloat(n));
		}

		return b;
	}

	@Override
	protected int getMaxOrder() {
		return 8;
	}

	@Override
	protected ICVector getCVector(Apfloat[][] vSet) {
		return CVector.getExpCVector(MatrixUtils.createVariation(vSet));
	}
}
