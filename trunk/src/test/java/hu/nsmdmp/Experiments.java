package hu.nsmdmp;

import hu.nsmdmp.cvectors.CVector;
import hu.nsmdmp.cvectors.StairsCVector;
import hu.nsmdmp.matrices.Matrix;
import hu.nsmdmp.matrices.MatrixUtils;
import hu.nsmdmp.matrices.NormalizedMatrix;
import hu.nsmdmp.matrixmath.MatrixMath;
import hu.nsmdmp.mosek.LinearProgrammingEq;
import hu.nsmdmp.utils.Converters;
import mosek.MosekException;

import org.apfloat.Apfloat;
import org.junit.Test;

public class Experiments {

	@Test
	public void test() throws MosekException {
		double[][] vectorSet = { { 0, 1, 2, 3 }, { 0, 1, 2, 3 } };

		Apfloat[][] vSet = Converters.convert(vectorSet);
		int variationNum = MatrixMath.getVariationsNumber(vSet);
		Apfloat[] b = getBVector(variationNum);

		int maxOrder = 1;
		NormalizedMatrix normalized = Matrix.getNormalizedMatrix(vSet, maxOrder);
//		ChebyshevTMatrix chebyshevT = Matrix.getChebyshevTMatrix(vSet, maxOrder);
//		ChebyshevUMatrix chebyshevU = Matrix.getChebyshevUMatrix(vSet, maxOrder);

		StairsCVector c = CVector.getStairsCVector(normalized.getVariations());

		System.out.println(MatrixUtils.print(normalized.getMatrix()));
		System.out.println(MatrixUtils.print(b));
		System.out.println(MatrixUtils.print(c.getCVectorA()));

		LinearProgrammingEq.optimizeMin(normalized.getMatrix(), b, c.getCVectorA());
//		LinearProgrammingEq.optimizeMin(chebyshevT.getMatrix(), b, c.getCVectorA());
//		LinearProgrammingEq.optimizeMin(chebyshevU.getMatrix(), b, c.getCVectorA());
	}

	private Apfloat[] getBVector(final int n) {
		Apfloat[] b = new Apfloat[n];

		for (int i = 0; i < n; i++) {
			b[i] = MatrixMath.ONE.divide(new Apfloat(n));
		}

		return b;
	}
}
