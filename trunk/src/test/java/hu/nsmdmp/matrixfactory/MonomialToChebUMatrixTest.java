package hu.nsmdmp.matrixfactory;

import hu.nsmdmp.matrix.Matrix;
import hu.nsmdmp.matrix.operation.MatrixMath;

import org.junit.Assert;
import org.junit.Test;

public class MonomialToChebUMatrixTest {

	@Test
	public void univariate1() {
		Matrix m = new Matrix(new double[][] { { 0, 1, 2, 3 } });
		int moment = 3;

		Matrix monomial = MatrixFactory.getMonomialMatrix(m.getArray(), moment);
		Matrix chebU = MatrixFactory.getChebyshevUMatrix(m.getArray(), moment);
		Matrix T = MonomialToChebUMatrix.getMatrix(moment, m.getRowDimension());

		System.out.println(T);

		Matrix newChebU = MatrixMath.multiply(T, monomial);

		Assert.assertEquals(chebU, newChebU);
	}

	@Test
	public void multivariate1() {
		Matrix m = new Matrix(new double[][] { { 0, 1, 2 }, { 0, 1, 2 } });
		int moment = 3;

		Matrix monomial = MatrixFactory.getMonomialMatrix(m.getArray(), moment);
		Matrix chebU = MatrixFactory.getChebyshevUMatrix(m.getArray(), moment);
		Matrix T = MonomialToChebUMatrix.getMatrix(moment, m.getRowDimension());

		System.out.println(T);

		Matrix newChebU = MatrixMath.multiply(T, monomial);

		Assert.assertEquals(chebU, newChebU);
	}
}
