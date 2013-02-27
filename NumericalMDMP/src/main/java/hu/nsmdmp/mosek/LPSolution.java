package hu.nsmdmp.mosek;

import mosek.Env;

public class LPSolution {

	final double[] x;

	final double primalSolution;

	final int[] basisIndexes;

	final Env.solsta solutionStatus;

	LPSolution(double[] x, double primalSolution, int[] basisIndexes, Env.solsta solutionStatus) {
		this.x = x;
		this.primalSolution = primalSolution;
		this.basisIndexes = basisIndexes;
		this.solutionStatus = solutionStatus;
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

	public Env.solsta getSolutionStatus() {
		return solutionStatus;
	}
}
