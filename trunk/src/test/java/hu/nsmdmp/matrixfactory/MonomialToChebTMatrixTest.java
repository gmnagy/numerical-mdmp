package hu.nsmdmp.matrixfactory;

import hu.nsmdmp.matrix.Matrix;
import hu.nsmdmp.matrix.operation.MatrixMath;

import org.junit.Assert;
import org.junit.Test;

public class MonomialToChebTMatrixTest {

	@Test
	public void univariate1() {
		Matrix m = new Matrix(new double[][] { { 0, 1, 2, 3 } });
		int maxOrder = 3;

		Matrix monomial = MatrixFactory.getMonomialMatrix(m.getArray(), maxOrder);
		Matrix chebT = MatrixFactory.getChebyshevTMatrix(m.getArray(), maxOrder);
		Matrix T = MonomialToChebTMatrix.getMatrix(maxOrder, m.getRowDimension());

		System.out.println(T);

		Matrix newChebT = MatrixMath.multiply(T, monomial);

		Assert.assertEquals(chebT, newChebT);
	}

	@Test
	public void multivariate1() {
		Matrix m = new Matrix(new double[][] { { 0, 1, 2 }, { 0, 1, 2 } });
		int maxOrder = 3;

		Matrix monomial = MatrixFactory.getMonomialMatrix(m.getArray(), maxOrder);
		Matrix chebT = MatrixFactory.getChebyshevTMatrix(m.getArray(), maxOrder);
		Matrix T = MonomialToChebTMatrix.getMatrix(maxOrder, m.getRowDimension());

		System.out.println(T);

		Matrix newChebT = MatrixMath.multiply(T, monomial);

		Assert.assertEquals(chebT, newChebT);
	}
}
