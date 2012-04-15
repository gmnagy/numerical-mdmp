package hu.nsmdmp.cvectors;

import hu.nsmdmp.vector.Vector;

import java.util.List;

import org.apfloat.Apfloat;

public class CVectorFactory {

	public static Vector getStairsCVector(final Apfloat[][] vectorSet, final Apfloat limit) {
		Apfloat[] limits = new Apfloat[vectorSet[0].length];
		for (int i = 0; i < limits.length; i++) {
			limits[i] = limit;
		}

		StairsCVector vector = new StairsCVector(limits);

		return vector.create(vectorSet);
	}

	public static Vector getExpCVector(final List<Apfloat[]> vectorSet) {
		ExpCVector vector = new ExpCVector();

		return vector.create(vectorSet);
	}

	public static Vector getSumProbEx4CVector(final List<Apfloat[]> vectorSet) {
		SumProbEx4CVector vector = new SumProbEx4CVector();

		return vector.create(vectorSet);
	}

	public static Vector getProbSumNotLess15Vector(final List<Apfloat[]> vectorSet) {
		ProbSumNotLess15Vector vector = new ProbSumNotLess15Vector();

		return vector.create(vectorSet);
	}

	public static Vector getCumProbPoisson(final Apfloat[][] vectorSet, final Apfloat[] limits) {
		CumProbPoisson vector = new CumProbPoisson(limits);

		return vector.create(vectorSet);
	}

	public static Vector getCumProbPoisson(final List<Apfloat[]> vectorSet, final Apfloat[] limits) {
		CumProbPoisson vector = new CumProbPoisson(limits);

		return vector.create(vectorSet);
	}

	public static Vector getProbPoisson(final Apfloat[][] vectorSet, final Apfloat[] limits) {
		ProbPoisson vector = new ProbPoisson(limits);

		return vector.create(vectorSet);
	}
}
