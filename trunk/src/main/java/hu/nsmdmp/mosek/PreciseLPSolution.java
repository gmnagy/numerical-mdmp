package hu.nsmdmp.mosek;

import org.apfloat.Apfloat;

public class PreciseLPSolution {
	
	int[] basisIndexes;
	
	Apfloat[] x;

	Apfloat objectiveValue;
	
	Apfloat primalNonnegInfeas;
		
	Apfloat primalSlackInfeas;

	Apfloat dualSlackInfeas;
	
	int primalNonnegInfeasIndex;
	
	int primalSlackInfeasIndex;
		
	int dualSlackInfeasIndex;

	PreciseLPSolution() {
	}


	public int[] getBasisIndexes() {
		return basisIndexes;
	}


	public Apfloat[] getX() {
		return x;
	}


	public Apfloat getObjectiveValue() {
		return objectiveValue;
	}


	public Apfloat getPrimalNonnegInfeas() {
		return primalNonnegInfeas;
	}


	public Apfloat getPrimalSlackInfeas() {
		return primalSlackInfeas;
	}


	public Apfloat getDualSlackInfeas() {
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
