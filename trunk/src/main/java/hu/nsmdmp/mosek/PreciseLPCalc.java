package hu.nsmdmp.mosek;

import org.apfloat.Apfloat;

import hu.nsmdmp.ApfloatUtils;
import hu.nsmdmp.matrices.Matrix;
import hu.nsmdmp.matrixmath.MatrixMath;
import hu.nsmdmp.utils.Converters;
import hu.nsmdmp.vectors.Vector;
import mosek.Env;
import mosek.MosekException;

public class PreciseLPCalc {
	//PreciseLPSolution result;
	public static LPSolution solverResult;
	PreciseLPCalc( ) {
	}
	
	public static PreciseLPSolution optimizeMin(final Matrix matrix, final Vector b, final Vector c) throws MosekException {
		PreciseLPSolution result;
		solverResult=LinearProgrammingEq.optimizeMin(matrix, b, c);
		result=calcResult(solverResult.basisIndexes,matrix, b, c, 1);
		return result;
	}

	/**
	 * Maximalizalasa.
	 * 
	 */
	public static PreciseLPSolution optimizeMax(final Matrix matrix, final Vector b, final Vector c) throws MosekException {
		PreciseLPSolution result;
		solverResult=LinearProgrammingEq.optimizeMax(matrix, b, c);
		result=calcResult(solverResult.basisIndexes,matrix, b, c, -1);
		return result;
	}

	/*
	 * final int sense is -1 in case of max problem
	 */
	private static PreciseLPSolution calcResult(final int[] basisIndexes, final Matrix matrix, final Vector b, final Vector c, final int sense){
		PreciseLPSolution result=new PreciseLPSolution();
		result.basisIndexes=basisIndexes;
		result.dualInfeasIndex=-1;
		result.primalEqInfeasIndex=-1;
		result.primalNonnegInfeasIndex=-1;	
		result.primalNonnegInfeas=ApfloatUtils.ZERO;
		result.dualInfeas=ApfloatUtils.ZERO;
		result.primalEqInfeas=ApfloatUtils.ZERO;
		
		
		
		Matrix basisMatrix=new Matrix(matrix.getRowDimension(),matrix.getRowDimension());
		for(int j=0; j<matrix.getRowDimension(); j++){
			if (basisIndexes[j]<matrix.getRowDimension()){
				for(int i=0; i<matrix.getRowDimension(); i++){
					basisMatrix.set(i, j, ApfloatUtils.ZERO);
				}
			basisMatrix.set(basisIndexes[j], j, ApfloatUtils.ONE);	
			}
			else{				
				for(int i=0; i<matrix.getRowDimension(); i++){
					basisMatrix.set(i, j, matrix.get(i, basisIndexes[j]-matrix.getRowDimension()));
				}

			}
			
		}
		
		/* primal infeasibilities */
		Vector xBasis=MatrixMath.multiply(basisMatrix.inverse(),b);
		
		
		result.x=new Apfloat[matrix.getColumnDimension()];
		for(int j=0; j<matrix.getRowDimension(); j++){
			if (basisIndexes[j]<matrix.getRowDimension()){
	
				Apfloat abs=xBasis.get(j);
				if (abs.compareTo(ApfloatUtils.ZERO)<0){
					abs=abs.negate();
				}
				
				if(abs.compareTo(result.primalEqInfeas)>0){
					result.primalEqInfeas=abs;
					result.primalEqInfeasIndex=basisIndexes[j];					
				}			
			}
			else{				
				result.x[basisIndexes[j]-matrix.getRowDimension()]=xBasis.get(j);
				if(xBasis.get(j).compareTo(result.primalNonnegInfeas)<0){
					result.primalNonnegInfeas=xBasis.get(j);
					result.primalNonnegInfeasIndex=basisIndexes[j]-matrix.getRowDimension();
				}
			}
			
		}

		
		
		
			
			
		
		return result;
	}
	
}
