package hu.nsmdmp.cvectors;

import hu.nsmdmp.vector.Vector;

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

	public static Vector getProbSumNotLess15Vector(final Apfloat[][] vectorSet) {
		ProbSumNotLess15Vector vector = new ProbSumNotLess15Vector();

		return vector.create(vectorSet);
	}


}
