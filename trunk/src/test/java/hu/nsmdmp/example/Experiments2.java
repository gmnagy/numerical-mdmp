package hu.nsmdmp.example;

import hu.nsmdmp.ApfloatUtils;
import hu.nsmdmp.cvectors.CVector;
import hu.nsmdmp.cvectors.ICVector;
import hu.nsmdmp.matrices.MatrixUtils;
import mosek.MosekException;

import org.apfloat.Apfloat;
import org.junit.Test;

public class Experiments2 extends AExperiments {

	@Test
	@Override
	public void run() throws MosekException {
		double[][] vectorSet = { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 }, { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 },
				{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 } };

		run(vectorSet);
	}

	@Override
	protected Apfloat[] distribution(final int n) {
		Apfloat[] b = new Apfloat[n];

		for (int i = 0; i < n; i++) {
			b[i] = ApfloatUtils.ONE.divide(new Apfloat(n));
		}

		return b;
	}

	@Override
	protected int getMaxOrder() {
		return 6;
	}

	@Override
	protected ICVector getCVector(final Apfloat[][] vSet) {
		return CVector.getStairsCVector(MatrixUtils.createVariation(vSet));
	}
}
