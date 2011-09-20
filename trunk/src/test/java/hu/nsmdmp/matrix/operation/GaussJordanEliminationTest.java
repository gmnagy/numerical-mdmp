package hu.nsmdmp.matrix.operation;

import hu.nsmdmp.ApfloatUtils;
import hu.nsmdmp.matrix.Matrix;
import hu.nsmdmp.vector.Vector;

import org.junit.Assert;
import org.junit.Test;

public class GaussJordanEliminationTest {

	/**
	 * 3-by-3 nonsingular system.
	 * 
	 */
	@Test
	public void test1() {
		Matrix A = new Matrix(new double[][] { { 0, 1, 1 }, { 2, 4, -2 }, { 0, 3, 15 } });
		Vector b = new Vector(new double[] { 4, 2, 36 });

		GaussJordanElimination gaussian = new GaussJordanElimination(A, b);
		Vector x = gaussian.getSolution();

		Vector bb = MatrixMath.multiply(A, x);

		if (!b.equals(bb)) {
			System.out.println(b);
			System.err.println(bb);

			Assert.assertTrue(false);
		}
	}

	/**
	 * 3-by-3 nonsingular system
	 * 
	 */
	@Test
	public void test2() {
		Matrix A = new Matrix(new double[][] { { 1, -3, 1 }, { 2, -8, 8 }, { -6, 3, -15 } });
		Vector b = new Vector(new double[] { 4, -2, 9 });

		GaussJordanElimination gaussian = new GaussJordanElimination(A, b);
		Vector x = gaussian.getSolution();

		Vector bb = MatrixMath.multiply(A, x);

		if (!b.equals(bb)) {
			System.out.println(b);
			System.err.println(bb);

			Assert.assertTrue(false);
		}
	}

//	/**
//	 * 5-by-5 singluar: infinitely many solutions
//	 */
//	@Test
//	public void test3() {
//		Matrix A = new Matrix(new double[][] { { 2, -3, -1, 2, 3 }, { 4, -4, -1, 4, 11 }, { 2, -5, -2, 2, -1 }, { 0, 2, 1, 0, 4 }, { -4, 6, 0, 0, 7 } });
//		Vector b = new Vector(new double[] { 4, 4, 9, -5, 5 });
//
//		GaussJordanElimination gaussian = new GaussJordanElimination(A, b);
//		Vector x = gaussian.getSolution();
//		System.out.println(x);
//
//		
//		Vector bb = MatrixMath.multiply(A, x);
//	}

	/**
	 * 3-by-3 singluar: infinitely many solutions
	 * 
	 */
	@Test
	public void test4() {
		Matrix A = new Matrix(new double[][] { { 1, -1, 2 }, { 4, 4, -2 }, { -2, 2, -4 } });
		Vector b = new Vector(new double[] { -3, 1, 6 });

		GaussJordanElimination gaussian = new GaussJordanElimination(A, b);
		Vector x = gaussian.getSolution();
		System.out.println(x);

		// legyen 0, ahol vetelen sok megoldas van
		x.set(2, ApfloatUtils.ZERO);

		Vector bb = MatrixMath.multiply(A, x);

		if (!b.equals(bb)) {
			System.out.println(b);
			System.err.println(bb);

			Assert.assertTrue(false);
		}
	}

	/**
	 * 3-by-3 singular: no solutions
	 * 
	 */
	@Test
	public void test5() {
		Matrix A = new Matrix(new double[][] { { 2, -1, 1 }, { 3, 2, -4 }, { -6, 3, -3 } });
		Vector b = new Vector(new double[] { 1, 4, 2 });

		GaussJordanElimination gaussian = new GaussJordanElimination(A, b);

		try {
			Vector x = gaussian.getSolution();
			System.out.println(x);
		} catch (RuntimeException e) {
			Assert.assertEquals("Matrix is singular.", e.getMessage());

			return;
		}

		Assert.assertTrue(false);
	}

}
