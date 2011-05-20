package hu.nsmdmp.mosek;

public class LPSolution {

	double[] x;

	double primalSolution;

	LPSolution() {
	}

	public double[] getX() {
		return x;
	}

	public double getPrimalSolution() {
		return primalSolution;
	}
}
