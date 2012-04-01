package hu.nsmdmp.matrixfactory;

import hu.nsmdmp.matrix.Matrix;
import hu.nsmdmp.matrix.operation.MatrixMath;

import org.junit.Assert;
import org.junit.Test;

public class MonomialToChebTMatrixTest {

	@Test
	public void univariate1() {
		Matrix m = new Matrix(new double[][] { { 0, 1, 2, 3 } });
		int moment = 3;

		Matrix monomial = MatrixFactory.getMonomialMatrix(m.getArray(), moment);
		Matrix chebT = MatrixFactory.getChebyshevTMatrix(m.getArray(), moment);
		Matrix T = MonomialToChebTMatrix.getMatrix(moment, m.getRowDimension());

		System.out.println(T);

		Matrix newChebT = MatrixMath.multiply(T, monomial);

		Assert.assertEquals(chebT, newChebT);
	}

	@Test
	public void multivariate1() {
		Matrix m = new Matrix(new double[][] { { 0, 1, 2 }, { 0, 1, 2 } });
		int moment = 3;

		Matrix monomial = MatrixFactory.getMonomialMatrix(m.getArray(), moment);
		Matrix chebT = MatrixFactory.getChebyshevTMatrix(m.getArray(), moment);
		Matrix T = MonomialToChebTMatrix.getMatrix(moment, m.getRowDimension());

		System.out.println(T);

		Matrix newChebT = MatrixMath.multiply(T, monomial);

		Assert.assertEquals(chebT, newChebT);
	}
}
