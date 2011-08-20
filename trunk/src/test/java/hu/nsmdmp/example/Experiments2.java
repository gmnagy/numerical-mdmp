package hu.nsmdmp.example;

import hu.nsmdmp.ApfloatUtils;
import hu.nsmdmp.cvectors.CVectorFactory;
import hu.nsmdmp.matrixfactory.Variation;
import hu.nsmdmp.vector.Vector;
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
	protected Vector distribution(final int n) {
		Vector D = new Vector(n);

		for (int i = 0; i < n; i++) {
			D.set(i, ApfloatUtils.ONE.divide(new Apfloat(n)));
		}

		return D;
	}

	@Override
	protected int getMaxOrder() {
		return 4;
	}

	@Override
	protected Vector getCVector(final Apfloat[][] vSet) {
		//return CVectorFactory.getStairsCVector(MatrixUtils.createVariation(vSet));
		return CVectorFactory.getProbSumNotLess15Vector(Variation.createVariation(vSet));
	}
}
