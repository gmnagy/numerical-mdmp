package hu.nsmdmp.cvectors;

import hu.nsmdmp.vectors.Vector;

import org.apfloat.Apfloat;

public class CVectorFactory {

	public static Vector getStairsCVector(final Apfloat[][] vectorSet) {
		StairsCVector vector = new StairsCVector();

		return vector.create(vectorSet);
	}

	public static Vector getExpCVector(final Apfloat[][] vectorSet) {
		ExpCVector vector = new ExpCVector();

		return vector.create(vectorSet);
	}

	public static Vector getSumProbEx4CVector(final Apfloat[][] vectorSet) {
		SumProbEx4CVector vector = new SumProbEx4CVector();

		return vector.create(vectorSet);
	}
}
