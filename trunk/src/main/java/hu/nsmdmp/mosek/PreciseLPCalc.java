package hu.nsmdmp.mosek;

import org.apfloat.Apfloat;

import hu.nsmdmp.ApfloatUtils;
import hu.nsmdmp.matrices.Matrix;
import hu.nsmdmp.matrixmath.MatrixMath;
import hu.nsmdmp.vectors.Vector;
import mosek.MosekException;

public class PreciseLPCalc {
	//PreciseLPSolution result;
	public static LPSolution solverResult;
	PreciseLPCalc( ) {
	}
	
	public static PreciseLPSolution optimizeMin(final Matrix matrix, final Vector b, final Vector c) throws MosekException {
		PreciseLPSolution result;
		solverResult=LinearProgrammingEq.optimizeMin(matrix, b, c);
		result=calcResult(solverResult.basisIndexes,matrix, b, c, -1);
		return result;
	}

	/**
	 * Maximalizalasa.
	 * 
	 */
	public static PreciseLPSolution optimizeMax(final Matrix matrix, final Vector b, final Vector c) throws MosekException {
		PreciseLPSolution result;
		solverResult=LinearProgrammingEq.optimizeMax(matrix, b, c);
		result=calcResult(solverResult.basisIndexes,matrix, b, c, 1);
		return result;
	}

	/*
	 * final int sense is -1 in case of min problem
	 */
	private static PreciseLPSolution calcResult(final int[] basisIndexes, final Matrix matrix, final Vector b, final Vector c, final int sense){
		PreciseLPSolution result=new PreciseLPSolution();
		//result.objectiveValue????
		result.basisIndexes=basisIndexes;
		result.dualSlackInfeasIndex=-1;
		result.primalNonnegInfeasIndex=-1;
		result.primalSlackInfeasIndex=-1;	
		result.primalNonnegInfeas=ApfloatUtils.ZERO;
		result.primalSlackInfeas=ApfloatUtils.ZERO;
		result.dualSlackInfeas=ApfloatUtils.ZERO;
		
		
		/* basis matrix and obj. coefficients */
		Matrix basisMatrix=new Matrix(matrix.getRowDimension(),matrix.getRowDimension());
		Vector cBasis=new Vector(matrix.getRowDimension());
		
		for(int j=0; j<matrix.getRowDimension(); j++){
			if (basisIndexes[j]<matrix.getRowDimension()){
				for(int i=0; i<matrix.getRowDimension(); i++){
					basisMatrix.set(i, j, ApfloatUtils.ZERO);
				}
			basisMatrix.set(basisIndexes[j], j, ApfloatUtils.ONE);	
			cBasis.set(j, ApfloatUtils.ZERO);
			}
			else{				
				for(int i=0; i<matrix.getRowDimension(); i++){
					basisMatrix.set(i, j, matrix.get(i, basisIndexes[j]-matrix.getRowDimension()));
				}
				cBasis.set(j, c.get(basisIndexes[j]-matrix.getRowDimension()));
			}
			
		}
		
		/* primal infeasibilities */
		Vector xBasis=MatrixMath.multiply(basisMatrix.inverse(),b);
		result.objectiveValue=ApfloatUtils.ZERO;
		for(int i=0; i<matrix.getRowDimension(); i++){
			result.objectiveValue=result.objectiveValue.add(cBasis.get(i).multiply(xBasis.get(i)));
		}
		
		result.x=new Apfloat[matrix.getColumnDimension()];
		for (int j=0; j<matrix.getColumnDimension(); j++){
			result.x[j]=ApfloatUtils.ZERO;
		}
			
		
		
		for(int j=0; j<matrix.getRowDimension(); j++){			
			if (basisIndexes[j]<matrix.getRowDimension()){
	
				Apfloat abs=xBasis.get(j);
				if (abs.compareTo(ApfloatUtils.ZERO)<0){
					abs=abs.negate();
				}
				
				if(abs.compareTo(result.primalSlackInfeas)>0){
					result.primalSlackInfeas=abs;
					result.primalSlackInfeasIndex=basisIndexes[j];					
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

		/* dual feasibilities */
		Vector yDual=MatrixMath.multiply(basisMatrix.inverse().transpose(),cBasis);
		Vector slackDual=MatrixMath.multiply(matrix.transpose(),yDual);
		for(int j=0; j<matrix.getColumnDimension(); j++){
			if(sense==1){
				if((slackDual.get(j).subtract(c.get(j))).compareTo(result.dualSlackInfeas)<0){
					result.dualSlackInfeas=(slackDual.get(j).subtract(c.get(j)));
					result.dualSlackInfeasIndex=j;
				}
			}else{
				if((slackDual.get(j).subtract(c.get(j))).compareTo(result.dualSlackInfeas)>0){
					result.dualSlackInfeas=(slackDual.get(j).subtract(c.get(j)));
					result.dualSlackInfeasIndex=j;	
				}
			}
		}
		
		
		
		
			
			
		
		return result;
	}
	
}
