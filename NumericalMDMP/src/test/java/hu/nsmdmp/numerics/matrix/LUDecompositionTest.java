package hu.nsmdmp.numerics.matrix;

import static junit.framework.Assert.assertEquals;
import hu.nsmdmp.numerics.matrix.LUDecomposition;
import hu.nsmdmp.numerics.matrix.Matrix;

import org.junit.Test;

public class LUDecompositionTest {

	/**
	 * A = LU.
	 */
	@Test
	public void test1() {
		Double[][] m = { { 4.0, 3.0 }, { 6.0, 3.0 } };
		Matrix<Double> A = new Matrix<Double>(m);

		LUDecomposition<Double> lu = new LUDecomposition<Double>(A);

		Matrix<Double> L = new Matrix<Double>(new Double[][] { { 1.0, 0.0 }, { 2.0 / 3.0, 1.0 } });

		assertEquals(L, lu.getL());

		Double[][] u = { { 6.0, 3.0 }, { 0.0, 1.0 } };
		Matrix<Double> U = new Matrix<Double>(u);

		assertEquals(U, lu.getU());
	}
}
