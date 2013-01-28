package hu.nsmdmp.mosek;


public class PreciseLPSolution<T> {

	int[] basisIndexes;

	T[] x;

	T objectiveValue;

	T primalNonnegInfeas;

	T primalSlackInfeas;

	T dualSlackInfeas;

	int primalNonnegInfeasIndex;

	int primalSlackInfeasIndex;

	int dualSlackInfeasIndex;

	PreciseLPSolution() {
	}

	public int[] getBasisIndexes() {
		return basisIndexes;
	}

	public T[] getX() {
		return x;
	}

	public T getObjectiveValue() {
		return objectiveValue;
	}

	public T getPrimalNonnegInfeas() {
		return primalNonnegInfeas;
	}

	public T getPrimalSlackInfeas() {
		return primalSlackInfeas;
	}

	public T getDualSlackInfeas() {
		return dualSlackInfeas;
	}

	public int getPrimalNonnegInfeasIndex() {
		return primalNonnegInfeasIndex;
	}

	public int getPrimalSlackInfeasIndex() {
		return primalSlackInfeasIndex;
	}

	public int getDualSlackInfeasIndex() {
		return dualSlackInfeasIndex;
	}
}
