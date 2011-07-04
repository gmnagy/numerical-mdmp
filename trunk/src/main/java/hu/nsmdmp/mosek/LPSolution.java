package hu.nsmdmp.mosek;

public class LPSolution {

	double[] x;

	double primalSolution;
	
	int[] basisIndexes;

	LPSolution() {
	}

	public double[] getX() {
		return x;
	}

	public double getPrimalSolution() {
		return primalSolution;
	}

	public int[] getBasisIndexes() {
		return basisIndexes;
	}


}
