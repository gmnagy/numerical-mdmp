package hu.nsmdmp.mosek;

import org.apfloat.Apfloat;

public class PreciseLPSolution {
	Apfloat[] x;

	Apfloat primalSolution;
	
	Apfloat primalInfeas;
	
	Apfloat dualInfeas;
	

	PreciseLPSolution() {
	}


	public Apfloat[] getX() {
		return x;
	}


	public Apfloat getPrimalSolution() {
		return primalSolution;
	}


	public Apfloat getPrimalInfeas() {
		return primalInfeas;
	}


	public Apfloat getDualInfeas() {
		return dualInfeas;
	}

}
