package hu.nsmdmp2.tools;

import static hu.nsmdmp2.numerics.matrix.MatrixMath.multiply;
import static hu.nsmdmp2.polynomialmatrixfactory.MonomialMatrix.generateMonomialMatrix;
import static hu.nsmdmp2.tools.SetNormalization.normalize;
import static hu.nsmdmp2.tools.VectorNormalizationWithSet.normailzeByGergo;
import static hu.nsmdmp2.tools.VectorSet.createVectorSet;
import static hu.nsmdmp2.utils.Converters.primitiveToDoubleValue;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import hu.nsmdmp2.numerics.Value;

import org.junit.Test;

public class VectorNormalizationWithSetTest {

	@Test
	public void test() {

		// { { 0, 1, 2 }, { 0, 1, 2 } }
		Value[][] vectorSet = createVectorSet(2, 2, Double.class);

		int maxOrder = 2;

		Value[] V = primitiveToDoubleValue(new double[] { 1, 10, 109, 10, 104, 109 });

		Value[] nV = normailzeByGergo(vectorSet, maxOrder, V);

		Value[] expected = primitiveToDoubleValue(new double[] { 1, 9, 90, 9, 85, 90 });

		assertArrayEquals(expected, nV);
	}

	@Test
	public void test2() {

		// { { 0, 1, 2 }, { 0, 1, 2 } }
		Value[][] vectorSet = createVectorSet(2, 2, Double.class);

		int maxOrder = 2;

		Value[] x = primitiveToDoubleValue(new double[] { 2, 1, 3, 1, 3, 3, 1, 1, 2 });

		// norm(M) * x = nb1
		Value[][] normalizedM = normalize(vectorSet);
		Value[][] nM = generateMonomialMatrix(normalizedM, maxOrder);
		Value[] nb1 = multiply(nM, x);

		// norme(M * x) = nb2
		Value[][] M = generateMonomialMatrix(vectorSet, maxOrder);
		Value[] b2 = multiply(M, x);
		Value[] nb2 = normailzeByGergo(vectorSet, maxOrder, b2);

		assertArrayEquals(nb1, nb2);
	}

	@Test
	public void test3() {

		// { { 0, 1, 2, 3, 4, 5 }, { 0, 1, 2, 3, 4, 5 } }
		Value[][] vectorSet = createVectorSet(2, 5, Double.class);

		int maxOrder = 3;

		Value[] x = primitiveToDoubleValue(new double[] { 2.0, 1.0, 3.0, 1.0, 3.0, 3.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 5.0, 1.0, 7.0, 1.0, 1.0, 1.0, 1.0,
				1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 2.0, 3.0, 3.0, 4.0, 1.0 });

		// norm(M) * x = nb1
		Value[][] normalizedM = normalize(vectorSet);
		Value[][] nM = generateMonomialMatrix(normalizedM, maxOrder);
		Value[] nb1 = multiply(nM, x);

		// norme(M * x) = nb2
		Value[][] M = generateMonomialMatrix(vectorSet, maxOrder);
		Value[] b2 = multiply(M, x);
		Value[] nb2 = normailzeByGergo(vectorSet, maxOrder, b2);

		int i = 0;
		for (Value item : nb1) {
			assertEquals("row = " + i + ": ", item.toDouble(), nb2[i].toDouble(), 0.00001);
			i++;
		}
	}

	@Test
	public void test4() {

		// { { 0, 1, 2, 3, 4 }, { 0, 1, 2, 3, 4 } }
		Value[][] vectorSet = createVectorSet(2, 4, Double.class);

		int maxOrder = 3;

		Value[] x = primitiveToDoubleValue(new double[] { 0.015625, 0.03125, 0.015625, 0.0, 0.0, 0.03125, 0.09375, 0.09375, 0.03125, 0.0, 0.015625, 0.09375,
				0.15625, 0.09375, 0.015625, 0.0, 0.03125, 0.09375, 0.09375, 0.03125, 0.0, 0.0, 0.015625, 0.03125, 0.015625 });

		// norm(M) * x = nb1
		Value[][] normalizedM = normalize(vectorSet);
		Value[][] nM = generateMonomialMatrix(normalizedM, maxOrder);
		Value[] nb1 = multiply(nM, x);

		// norme(M * x) = nb2
		Value[][] M = generateMonomialMatrix(vectorSet, maxOrder);
		Value[] b2 = multiply(M, x);
		Value[] nb2 = normailzeByGergo(vectorSet, maxOrder, b2);

		assertArrayEquals(nb1, nb2);
	}
}
