package hu.nsmdmp2.polynomialmatrixfactory;

import static hu.nsmdmp2.polynomialmatrixfactory.MonomialMatrix.generateMonomialMatrix;
import static hu.nsmdmp2.utils.Converters.primitiveToDoubleValue;
import static org.junit.Assert.assertArrayEquals;
import hu.nsmdmp2.numerics.Value;

import org.junit.Test;

public class DoubleMonomialMatrixTest {

	@Test
	public void test() {

		int maxOrder = 1;
		double[][] set = { { 0, 1, 2, 3 }, { 0, 1, 2, 3 } };

		Value[][] M = generateMonomialMatrix(primitiveToDoubleValue(set), maxOrder);

		Value[][] expected = primitiveToDoubleValue(new double[][] { //
		{ 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 }, //
				{ 0.0, 1.0, 2.0, 3.0, 0.0, 1.0, 2.0, 3.0, 0.0, 1.0, 2.0, 3.0, 0.0, 1.0, 2.0, 3.0 }, //
				{ 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 2.0, 2.0, 2.0, 2.0, 3.0, 3.0, 3.0, 3.0 } });

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

		Value[][] M = generateMonomialMatrix(primitiveToDoubleValue(set), maxOrder);

		Value[][] expected = primitiveToDoubleValue(new double[][] { //
		{ 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 }, //
				{ 0.0, 1.0, 2.0, 3.0, 0.0, 1.0, 2.0, 3.0, 0.0, 1.0, 2.0, 3.0, 0.0, 1.0, 2.0, 3.0 }, //
				{ 0.0, 1.0, 4.0, 9.0, 0.0, 1.0, 4.0, 9.0, 0.0, 1.0, 4.0, 9.0, 0.0, 1.0, 4.0, 9.0 }, //
				{ 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 2.0, 2.0, 2.0, 2.0, 3.0, 3.0, 3.0, 3.0 }, //
				{ 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 2.0, 3.0, 0.0, 2.0, 4.0, 6.0, 0.0, 3.0, 6.0, 9.0 }, //
				{ 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 4.0, 4.0, 4.0, 4.0, 9.0, 9.0, 9.0, 9.0 } });

		int i = 0;
		for (Value[] array : expected) {
			assertArrayEquals("row = " + i + ": ", array, M[i]);
			i++;
		}
	}
}
