package hu.nsmdmp.mosek;

public class LPSolution {

	double[] x;

	double primalSolution;
	
	int[] basisIndex;

	LPSolution() {
	}

	public double[] getX() {
		return x;
	}

	public double getPrimalSolution() {
		return primalSolution;
	}

	public int[] getBasisIndex() {
		return basisIndex;
	}


}
