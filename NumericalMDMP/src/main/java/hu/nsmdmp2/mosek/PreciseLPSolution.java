package hu.nsmdmp2.mosek;

import hu.nsmdmp2.numerics.Value;

public class PreciseLPSolution {

	int[] basisIndexes;

	Value[] x;

	Value objectiveValue;

	Value primalNonnegInfeas;

	Value primalSlackInfeas;

	Value dualSlackInfeas;

	int primalNonnegInfeasIndex;

	int primalSlackInfeasIndex;

	int dualSlackInfeasIndex;

	PreciseLPSolution() {
	}

	public int[] getBasisIndexes() {
		return basisIndexes;
	}

	public Value[] getX() {
		return x;
	}

	public Value getObjectiveValue() {
		return objectiveValue;
	}

	public Value getPrimalNonnegInfeas() {
		return primalNonnegInfeas;
	}

	public Value getPrimalSlackInfeas() {
		return primalSlackInfeas;
	}

	public Value getDualSlackInfeas() {
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
