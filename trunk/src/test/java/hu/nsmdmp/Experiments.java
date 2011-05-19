package hu.nsmdmp;

import hu.nsmdmp.cvectors.CVector;
import hu.nsmdmp.cvectors.ICVector;
import hu.nsmdmp.matrices.IMatrix;
import hu.nsmdmp.matrices.Matrix;
import hu.nsmdmp.matrices.MatrixUtils;
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
		IMatrix normalized = Matrix.getNormalizedMatrix(vSet, maxOrder);
//		ChebyshevTMatrix chebyshevT = Matrix.getChebyshevTMatrix(vSet, maxOrder);
//		ChebyshevUMatrix chebyshevU = Matrix.getChebyshevUMatrix(vSet, maxOrder);

		ICVector c = CVector.getStairsCVector(normalized.getVariations());
		Apfloat[] cc = c.getCVectorA();
		cc[0] = MatrixMath.ZERO;
		System.out.println(MatrixUtils.print(cc));

		LinearProgrammingEq.optimizeMin(normalized.getMatrix(), b, cc);
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
