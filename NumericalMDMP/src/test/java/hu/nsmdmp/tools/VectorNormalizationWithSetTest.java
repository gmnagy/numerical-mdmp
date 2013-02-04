package hu.nsmdmp.tools;

import static hu.nsmdmp.numerics.matrix.math.MatrixMath.multiply;
import static hu.nsmdmp.polynomialmatrixfactory.MonomialMatrix.generateDoubleMonomialMatrix;
import static hu.nsmdmp.tools.SetNormalization.normalize;
import static hu.nsmdmp.tools.VectorNormalizationWithSet.normailzeByGergo;
import static hu.nsmdmp.tools.VectorSet.createVectorSet;
import static org.junit.Assert.assertEquals;
import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.numerics.matrix.Vector;

import org.junit.Assert;
import org.junit.Test;

public class VectorNormalizationWithSetTest {

	@Test
	public void test() {

		// { { 0, 1, 2 }, { 0, 1, 2 } }
		Double[][] vectorSet = createVectorSet(2, 2, Double.class);

		int maxOrder = 2;

		Vector<Double> V = new Vector<Double>(new Double[] { 1.0, 10.0, 109.0, 10.0, 104.5, 109.0 });

		Vector<Double> nV = normailzeByGergo(vectorSet, maxOrder, V);

		Vector<Double> expected = new Vector<Double>(new Double[] { 1.0, 9.0, 90.0, 9.0, 85.5, 90.0 });

		assertEquals(expected, nV);
	}

	@Test
	public void test2() {

		// { { 0, 1, 2 }, { 0, 1, 2 } }
		Double[][] vectorSet = createVectorSet(2, 2, Double.class);

		int maxOrder = 2;

		Vector<Double> x = new Vector<Double>(new Double[] { 2.0, 1.0, 3.0, 1.0, 3.0, 3.0, 1.0, 1.0, 2.0 });

		// norm(M) * x = nb1
		Double[][] normalizedM = normalize(vectorSet);
		Matrix<Double> nM = generateDoubleMonomialMatrix(normalizedM, maxOrder);
		Vector<Double> nb1 = multiply(nM, x);

		// norme(M * x) = nb2
		Matrix<Double> M = generateDoubleMonomialMatrix(vectorSet, maxOrder);
		Vector<Double> b2 = multiply(M, x);
		Vector<Double> nb2 = normailzeByGergo(vectorSet, maxOrder, b2);

		Assert.assertEquals(nb1, nb2);
	}

	@Test
	public void test3() {

		// { { 0, 1, 2, 3, 4, 5 }, { 0, 1, 2, 3, 4, 5 } }
		Double[][] vectorSet = createVectorSet(2, 5, Double.class);

		int maxOrder = 3;

		Vector<Double> x = new Vector<Double>(new Double[] { 2.0, 1.0, 3.0, 1.0, 3.0, 3.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 5.0, 1.0, 7.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				1.0, 1.0, 1.0, 2.0, 2.0, 3.0, 3.0, 4.0, 1.0 });

		// norm(M) * x = nb1
		Double[][] normalizedM = normalize(vectorSet);
		Matrix<Double> nM = generateDoubleMonomialMatrix(normalizedM, maxOrder);
		Vector<Double> nb1 = multiply(nM, x);

		// norme(M * x) = nb2
		Matrix<Double> M = generateDoubleMonomialMatrix(vectorSet, maxOrder);
		Vector<Double> b2 = multiply(M, x);
		Vector<Double> nb2 = normailzeByGergo(vectorSet, maxOrder, b2);

		int i = 0;
		for (Double item : nb1.getDoubleArray()) {
			assertEquals("row = " + i + ": ", item, nb2.getDoubleArray()[i], 0.00001);
			i++;
		}
	}

	@Test
	public void test4() {
		// { { 0, 1, 2, 3, 4 }, { 0, 1, 2, 3, 4 } }
		Double[][] vectorSet = createVectorSet(2, 4, Double.class);

		int maxOrder = 3;

		Vector<Double> x = new Vector<Double>(new Double[] { 0.015625, 0.03125, 0.015625, 0.0, 0.0, 0.03125, 0.09375, 0.09375, 0.03125, 0.0, 0.015625, 0.09375, 0.15625, 0.09375, 0.015625, 0.0,
				0.03125, 0.09375, 0.09375, 0.03125, 0.0, 0.0, 0.015625, 0.03125, 0.015625 });

		// norm(M) * x = nb1
		Double[][] normalizedM = normalize(vectorSet);
		Matrix<Double> nM = generateDoubleMonomialMatrix(normalizedM, maxOrder);
		Vector<Double> nb1 = multiply(nM, x);

		// norme(M * x) = nb2
		Matrix<Double> M = generateDoubleMonomialMatrix(vectorSet, maxOrder);
		Vector<Double> b2 = multiply(M, x);
		Vector<Double> nb2 = normailzeByGergo(vectorSet, maxOrder, b2);

		Assert.assertEquals("", nb1, nb2);
	}
}
