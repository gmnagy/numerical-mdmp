package hu.nsmdmp.numerics.matrix;

import static junit.framework.Assert.assertEquals;
import hu.nsmdmp.numerics.matrix.math.MatrixMath;

import org.junit.Test;

public class PreAppendMatrixTest {

	@Test
	public void test1() {
		Double[][] m = { { 1.0, 2.0, 2.0 }, { 3.0, 1.0, 4.0 }, { 5.0, 6.0, 7.0 } };
		Matrix<Double> A = new Matrix<Double>(m);
		Matrix<Double> identity = MatrixMath.identity(3,3, A.valueType);
		
		Matrix<Double> resultA = A.getColPrepend(identity);

		Double[][] expected = { {1.0, 0.0, 0.0,  1.0, 2.0, 2.0 }, { 0.0, 1.0, 0.0, 3.0, 1.0, 4.0 }, {0.0, 0.0, 1.0, 5.0, 6.0, 7.0 } };
		Matrix<Double> expectedA = new Matrix<Double>(expected);

		assertEquals(expectedA, resultA);
	}



}
