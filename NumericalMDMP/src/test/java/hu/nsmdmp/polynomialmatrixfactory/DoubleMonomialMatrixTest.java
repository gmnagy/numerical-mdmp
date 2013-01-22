package hu.nsmdmp.polynomialmatrixfactory;

import static hu.nsmdmp.polynomialmatrixfactory.MonomialMatrix.generateDoubleMonomialMatrix;
import static org.junit.Assert.assertArrayEquals;
import hu.nsmdmp.numerics.matrix.Matrix;

import org.junit.Test;

public class DoubleMonomialMatrixTest {

	@Test
	public void test() {

		int maxOrder = 1;
		Double[][] set = { { 0.0, 1.0, 2.0, 3.0 }, { 0.0, 1.0, 2.0, 3.0 } };

		Matrix<Double> M = generateDoubleMonomialMatrix(set, maxOrder);

		Double[][] expected = { //
				{ 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 }, //
				{ 0.0, 1.0, 2.0, 3.0, 0.0, 1.0, 2.0, 3.0, 0.0, 1.0, 2.0, 3.0, 0.0, 1.0, 2.0, 3.0 }, //
				{ 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 2.0, 2.0, 2.0, 2.0, 3.0, 3.0, 3.0, 3.0 } };

		int i = 0;
		for (Double[] array : expected) {
			assertArrayEquals("row = " + i + ": ", array, M.getRow(i));
			i++;
		}
	}

	@Test
	public void test2() {

		int maxOrder = 2;
		Double[][] set = { { 0.0, 1.0, 2.0, 3.0 }, { 0.0, 1.0, 2.0, 3.0 } };

		Matrix<Double> M = generateDoubleMonomialMatrix(set, maxOrder);

		Double[][] expected = { //
				{ 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 }, //
				{ 0.0, 1.0, 2.0, 3.0, 0.0, 1.0, 2.0, 3.0, 0.0, 1.0, 2.0, 3.0, 0.0, 1.0, 2.0, 3.0 }, //
				{ 0.0, 1.0, 4.0, 9.0, 0.0, 1.0, 4.0, 9.0, 0.0, 1.0, 4.0, 9.0, 0.0, 1.0, 4.0, 9.0 }, //
				{ 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 2.0, 2.0, 2.0, 2.0, 3.0, 3.0, 3.0, 3.0 }, //
				{ 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 2.0, 3.0, 0.0, 2.0, 4.0, 6.0, 0.0, 3.0, 6.0, 9.0 }, //
				{ 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 4.0, 4.0, 4.0, 4.0, 9.0, 9.0, 9.0, 9.0 } };

		int i = 0;
		for (Double[] array : expected) {
			assertArrayEquals("row = " + i + ": ", array, M.getRow(i));
			i++;
		}
	}
}
