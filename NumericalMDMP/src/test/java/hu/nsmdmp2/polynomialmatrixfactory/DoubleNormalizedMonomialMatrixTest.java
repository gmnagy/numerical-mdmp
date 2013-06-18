package hu.nsmdmp2.polynomialmatrixfactory;

import static hu.nsmdmp2.polynomialmatrixfactory.MonomialMatrix.generateMonomialMatrix;
import static hu.nsmdmp2.tools.SetNormalization.normalize;
import static hu.nsmdmp2.utils.Converters.primitiveToDoubleValue;
import static org.junit.Assert.assertArrayEquals;
import hu.nsmdmp2.numerics.Value;

import org.junit.Test;

public class DoubleNormalizedMonomialMatrixTest {

	@Test
	public void test1() {

		int maxOrder = 1;
		double[][] set = { { 0, 1, 2, 3 }, { 0, 1, 2, 3 } };

		Value[][] normalizedM = normalize(primitiveToDoubleValue(set));
		Value[][] M = generateMonomialMatrix(normalizedM, maxOrder);

		Value[][] expected = primitiveToDoubleValue(new double[][] { //
		{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, //
				{ -1, -1.0 / 3, 1.0 / 3, 1, -1, -1.0 / 3, 1.0 / 3, 1, -1, -1.0 / 3, 1.0 / 3, 1, -1, -1.0 / 3, 1.0 / 3, 1 }, //
				{ -1, -1, -1, -1, -1.0 / 3, -1.0 / 3, -1.0 / 3, -1.0 / 3, 1.0 / 3, 1.0 / 3, 1.0 / 3, 1.0 / 3, 1, 1, 1, 1 } });

		int i = 0;
		for (Value[] array : expected) {
			assertArrayEquals("row = " + i + ": ", array, M[i]);
			i++;
		}
	}

	@Test
	public void test2() {

		int maxOrder = 2;
		double[][] set = { { 0, 1, 2, 3 }, { 0, 1, 2, 3 } };

		Value[][] normalizedM = normalize(primitiveToDoubleValue(set));
		Value[][] M = generateMonomialMatrix(normalizedM, maxOrder);

		Value[][] expected = primitiveToDoubleValue(new double[][] {
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, //
				{ -1, -1.0 / 3.0, 1.0 / 3.0, 1, -1, -1.0 / 3.0, 1.0 / 3.0, 1, -1, -1.0 / 3.0, 1.0 / 3.0, 1.0, -1.0, -1.0 / 3.0, 1.0 / 3.0, 1.0 }, //
				{ 1, 1.0 / 9.0, 1.0 / 9.0, 1, 1, 1.0 / 9.0, 1.0 / 9.0, 1, 1, 1.0 / 9.0, 1.0 / 9.0, 1.0, 1.0, 1.0 / 9.0, 1.0 / 9.0, 1.0 }, //
				{ -1.0, -1.0, -1.0, -1.0, -1.0 / 3.0, -1.0 / 3.0, -1.0 / 3.0, -1.0 / 3.0, 1.0 / 3.0, 1.0 / 3.0, 1.0 / 3.0, 1.0 / 3.0, 1.0, 1.0, 1.0, 1.0 }, //
				{ 1.0, 1.0 / 3.0, -1.0 / 3.0, -1.0, 1.0 / 3.0, 1.0 / 9.0, -1.0 / 9.0, -1.0 / 3.0, -1.0 / 3.0, -1.0 / 9.0, 1.0 / 9.0, 1.0 / 3.0, -1.0,
						-1.0 / 3.0, 1.0 / 3.0, 1.0 }, //
				{ 1.0, 1.0, 1.0, 1.0, 1.0 / 9.0, 1.0 / 9.0, 1.0 / 9.0, 1.0 / 9.0, 1.0 / 9.0, 1.0 / 9.0, 1.0 / 9.0, 1.0 / 9.0, 1, 1, 1, 1 } });

		int i = 0;
		for (Value[] array : expected) {
			assertArrayEquals("row = " + i + ": ", array, M[i]);
			i++;
		}
	}
}
