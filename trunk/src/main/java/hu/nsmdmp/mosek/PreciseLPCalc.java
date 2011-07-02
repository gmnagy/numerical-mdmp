package hu.nsmdmp.mosek;

import hu.nsmdmp.matrices.Matrix;
import hu.nsmdmp.utils.Converters;
import hu.nsmdmp.vectors.Vector;
import mosek.Env;
import mosek.MosekException;

public class PreciseLPCalc {
	//PreciseLPSolution result;
	LPSolution result;
	static LPSolution solverResult;
	PreciseLPCalc( ) {
	}
	
	public static PreciseLPSolution optimizeMin(final Matrix matrix, final Vector b, final Vector c) throws MosekException {
		solverResult=LinearProgrammingEq.optimizeMin(matrix, b, c);
		PreciseLPSolution result=new PreciseLPSolution();
		//result=calcResult(solverResult);
		return result;
	}

	/**
	 * Maximalizalasa.
	 * 
	 */
	public static PreciseLPSolution optimizeMax(final Matrix matrix, final Vector b, final Vector c) throws MosekException {
		solverResult=LinearProgrammingEq.optimizeMin(matrix, b, c);
		PreciseLPSolution result=new PreciseLPSolution();
		//result=calcResult(solverResult);
		return result;
	}

	private static PreciseLPSolution calcResult(PreciseLPSolution solverResult){
		
		
		return solverResult;
	}
	
}
