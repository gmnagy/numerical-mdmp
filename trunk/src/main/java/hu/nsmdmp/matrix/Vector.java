package hu.nsmdmp.matrix;

import org.apfloat.Apfloat;

public class Vector extends Matrix {

	/**
	 * Construct a empty vector.
	 * 
	 * @param n
	 *            Number of colums.
	 */
	public Vector(final int n) {
		super(1, n);
	}

	/**
	 * Construct a vector from a 1-D array.
	 * 
	 * @param A
	 *            One-dimensional array of {@link Apfloat}.
	 */
	public Vector(final Apfloat[] A) {
		super(new Apfloat[][] { A });
	}

	/**
	 * Construct a vector from a 1-D array.
	 * 
	 * @param A
	 *            One-dimensional array of doubles.
	 */
	public Vector(final double[] A) {
		super(new double[][] { A });
	}
}
