package hu.nsmdmp.matrix.operation;

import hu.nsmdmp.matrix.Matrix;
import hu.nsmdmp.matrixfactory.MatrixFactory;
import hu.nsmdmp.tasks.TaskUtils;
import hu.nsmdmp.vector.Vector;

import org.apfloat.Apfloat;
import org.junit.Assert;
import org.junit.Test;

public class PowerMomentsNormalizationTest {

	@Test
	public void test() {
		// { { 0, 1, 2 }, { 0, 1, 2 } }
		Apfloat[][] vectorSet = TaskUtils.createVectorSet(2, 2);

		int maxOrder = 2;

		Vector V = new Vector(new double[] { 1, 10, 109, 10, 104.5, 109 });

		Vector nV = PowerMomentsNormalization.normailze(vectorSet, maxOrder, V);

		Vector expected = new Vector(new double[] { 1, 9, 90, 9, 85.5, 90 });
		Assert.assertEquals("", expected, nV);
	}

	@Test
	public void test2() {
		// { { 0, 1, 2 }, { 0, 1, 2 } }
		Apfloat[][] vectorSet = TaskUtils.createVectorSet(2, 2);

		int maxOrder = 2;

		Vector x = new Vector(new double[] { 2, 1, 3, 1, 3, 3, 1, 1, 2 });

		// norm(M) * x = nb1
		Matrix nM = MatrixFactory.getMonomialMatrix(vectorSet, maxOrder);
		Vector nb1 = MatrixMath.multiply(nM, x);

		// norme(M * x) = nb2
		Matrix M = MatrixFactory.getSimpleMatrix(vectorSet, maxOrder);
		Vector b2 = MatrixMath.multiply(M, x);
		Vector nb2 = PowerMomentsNormalization.normailze(vectorSet, maxOrder, b2);

		Assert.assertEquals("", nb1, nb2);
	}

	@Test
	public void test3() {
		// { { 0, 1, 2, 3, 4, 5 }, { 0, 1, 2, 3, 4, 5 } }
		Apfloat[][] vectorSet = TaskUtils.createVectorSet(2, 5);

		int maxOrder = 3;

		Vector x = new Vector(new double[] { 2, 1, 3, 1, 3, 3, 1, 1, 2, 1, 1, 1, 1, 5, 1, 7, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 3, 3, 4, 1 });

		// norm(M) * x = nb1
		Matrix nM = MatrixFactory.getMonomialMatrix(vectorSet, maxOrder);
		Vector nb1 = MatrixMath.multiply(nM, x);

		// norme(M * x) = nb2
		Matrix M = MatrixFactory.getSimpleMatrix(vectorSet, maxOrder);
		Vector b2 = MatrixMath.multiply(M, x);
		Vector nb2 = PowerMomentsNormalization.normailze(vectorSet, maxOrder, b2);

		System.out.println(nb1);
		System.out.println(nb2);
		Assert.assertEquals("", nb1, nb2);
	}

	@Test
	public void test4() {
		// { { 0, 1, 2, 3, 4 }, { 0, 1, 2, 3, 4 } }
		Apfloat[][] vectorSet = TaskUtils.createVectorSet(2, 4);

		int maxOrder = 3;

		Vector x = new Vector(new double[] { 0.015625, 0.03125, 0.015625, 0, 0, 0.03125, 0.09375, 0.09375, 0.03125, 0, 0.015625, 0.09375, 0.15625, 0.09375, 0.015625, 0, 0.03125, 0.09375, 0.09375,
				0.03125, 0, 0, 0.015625, 0.03125, 0.015625 });

		// norm(M) * x = nb1
		Matrix nM = MatrixFactory.getMonomialMatrix(vectorSet, maxOrder);
		Vector nb1 = MatrixMath.multiply(nM, x);

		// norme(M * x) = nb2
		Matrix M = MatrixFactory.getSimpleMatrix(vectorSet, maxOrder);
		Vector b2 = MatrixMath.multiply(M, x);
		Vector nb2 = PowerMomentsNormalization.normailze(vectorSet, maxOrder, b2);

		System.out.println(nb1);
		System.out.println(nb2);
		System.out.println(b2);
		Assert.assertEquals("", nb1, nb2);
	}
}
