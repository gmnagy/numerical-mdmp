package hu.nsmdmp.mosek;

import org.apfloat.Apfloat;

public class PreciseLPSolution {
	
	int[] basisIndexes;
	
	Apfloat[] x;

	Apfloat objectiveValue;
	
	Apfloat primalNonnegInfeas;
	
	Apfloat dualInfeas;
	
	Apfloat primalEqInfeas;
	
	int primalNonnegInfeasIndex;
	
	int dualInfeasIndex;
	
	int primalEqInfeasIndex;
	

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


	public Apfloat getDualInfeas() {
		return dualInfeas;
	}


	public Apfloat getPrimalEqInfeas() {
		return primalEqInfeas;
	}


	public int getPrimalNonnegInfeasIndex() {
		return primalNonnegInfeasIndex;
	}


	public int getDualInfeasIndex() {
		return dualInfeasIndex;
	}


	public int getPrimalEqInfeasIndex() {
		return primalEqInfeasIndex;
	}

}
