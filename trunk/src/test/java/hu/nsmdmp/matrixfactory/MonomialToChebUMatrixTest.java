package hu.nsmdmp.matrixfactory;

import hu.nsmdmp.matrix.Matrix;
import hu.nsmdmp.matrix.operation.MatrixMath;

import org.junit.Assert;
import org.junit.Test;

public class MonomialToChebUMatrixTest {

	@Test
	public void univariate1() {
		Matrix m = new Matrix(new double[][] { { 0, 1, 2, 3 } });
		int maxOrder = 3;

		Matrix monomial = MatrixFactory.getMonomialMatrix(m.getArray(), maxOrder);
		Matrix chebU = MatrixFactory.getChebyshevUMatrix(m.getArray(), maxOrder);
		Matrix T = MonomialToChebUMatrix.getMatrix(maxOrder, m.getRowDimension());

		System.out.println(T);

		Matrix newChebU = MatrixMath.multiply(T, monomial);

		Assert.assertEquals(chebU, newChebU);
	}

	@Test
	public void multivariate1() {
		Matrix m = new Matrix(new double[][] { { 0, 1, 2 }, { 0, 1, 2 } });
		int maxOrder = 3;

		Matrix monomial = MatrixFactory.getMonomialMatrix(m.getArray(), maxOrder);
		Matrix chebU = MatrixFactory.getChebyshevUMatrix(m.getArray(), maxOrder);
		Matrix T = MonomialToChebUMatrix.getMatrix(maxOrder, m.getRowDimension());

		System.out.println(T);

		Matrix newChebU = MatrixMath.multiply(T, monomial);

		Assert.assertEquals(chebU, newChebU);
	}
}
