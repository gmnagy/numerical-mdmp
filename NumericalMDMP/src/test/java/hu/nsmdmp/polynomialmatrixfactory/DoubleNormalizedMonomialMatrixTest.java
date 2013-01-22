package hu.nsmdmp.polynomialmatrixfactory;

import static hu.nsmdmp.polynomialmatrixfactory.MonomialMatrix.generateDoubleMonomialMatrix;
import static hu.nsmdmp.tools.SetNormalization.normalize;
import static hu.nsmdmp.utils.Converters.primitiveToDouble;
import static org.junit.Assert.assertArrayEquals;
import hu.nsmdmp.numerics.matrix.Matrix;

import org.junit.Test;

public class DoubleNormalizedMonomialMatrixTest {

	@Test
	public void test1() {

		int maxOrder = 1;
		double[][] set = { { 0, 1, 2, 3 }, { 0, 1, 2, 3 } };

		Double[][] normalizedM = normalize(primitiveToDouble(set), Double.class);
		Matrix<Double> M = generateDoubleMonomialMatrix(normalizedM, maxOrder);

		Double[][] expected = { //
		{ 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 }, //
				{ -1.0, -1.0 / 3.0, 1.0 / 3.0, 1.0, -1.0, -1.0 / 3.0, 1.0 / 3.0, 1.0, -1.0, -1.0 / 3.0, 1.0 / 3.0, 1.0, -1.0, -1.0 / 3.0, 1.0 / 3.0, 1.0 }, //
				{ -1.0, -1.0, -1.0, -1.0, -1.0 / 3.0, -1.0 / 3.0, -1.0 / 3.0, -1.0 / 3.0, 1.0 / 3.0, 1.0 / 3.0, 1.0 / 3.0, 1.0 / 3.0, 1.0, 1.0, 1.0, 1.0 } };

		int i = 0;
		for (Double[] array : expected) {
			assertArrayEquals("row = " + i + ": ", array, M.getRow(i));
			i++;
		}
	}

	@Test
	public void test2() {

		int maxOrder = 2;
		double[][] set = { { 0, 1, 2, 3 }, { 0, 1, 2, 3 } };

		Double[][] normalizedM = normalize(primitiveToDouble(set), Double.class);
		Matrix<Double> M = generateDoubleMonomialMatrix(normalizedM, maxOrder);

		Double[][] expected = { { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 }, //
				{ -1.0, -1.0 / 3.0, 1.0 / 3.0, 1.0, -1.0, -1.0 / 3.0, 1.0 / 3.0, 1.0, -1.0, -1.0 / 3.0, 1.0 / 3.0, 1.0, -1.0, -1.0 / 3.0, 1.0 / 3.0, 1.0 }, //
				{ 1.0, 1.0 / 9.0, 1.0 / 9.0, 1.0, 1.0, 1.0 / 9.0, 1.0 / 9.0, 1.0, 1.0, 1.0 / 9.0, 1.0 / 9.0, 1.0, 1.0, 1.0 / 9.0, 1.0 / 9.0, 1.0 }, //
				{ -1.0, -1.0, -1.0, -1.0, -1.0 / 3.0, -1.0 / 3.0, -1.0 / 3.0, -1.0 / 3.0, 1.0 / 3.0, 1.0 / 3.0, 1.0 / 3.0, 1.0 / 3.0, 1.0, 1.0, 1.0, 1.0 }, //
				{ 1.0, 1.0 / 3.0, -1.0 / 3.0, -1.0, 1.0 / 3.0, 1.0 / 9.0, -1.0 / 9.0, -1.0 / 3.0, -1.0 / 3.0, -1.0 / 9.0, 1.0 / 9.0, 1.0 / 3.0, -1.0, -1.0 / 3.0, 1.0 / 3.0, 1.0 }, //
				{ 1.0, 1.0, 1.0, 1.0, 1.0 / 9.0, 1.0 / 9.0, 1.0 / 9.0, 1.0 / 9.0, 1.0 / 9.0, 1.0 / 9.0, 1.0 / 9.0, 1.0 / 9.0, 1.0, 1.0, 1.0, 1.0 } };

		int i = 0;
		for (Double[] array : expected) {
			assertArrayEquals("row = " + i + ": ", array, M.getRow(i));
			i++;
		}
	}
}
