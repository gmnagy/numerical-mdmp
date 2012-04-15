package hu.nsmdmp.vector;

import hu.nsmdmp.utils.ApfloatUtils;
import hu.nsmdmp.utils.Utils;

import org.apfloat.Apfloat;

public class Vector implements IVector {

	/**
	 * Array for internal storage of elements.
	 * 
	 */
	protected Apfloat[] V;

	/**
	 * Column dimension.
	 * 
	 */
	protected int n;

	/**
	 * Construct a empty vector.
	 * 
	 * @param n
	 *            Number of colums.
	 */
	public Vector(final int n) {
		this.n = n;
		V = new Apfloat[n];
	}

	/**
	 * Construct a matrix from a 1-D array.
	 * 
	 * @param A
	 *            One-dimensional array of {@link Apfloat}.
	 */
	public Vector(final Apfloat[] V) {
		n = V.length;
		this.V = new Apfloat[n];

		for (int i = 0; i < n; i++) {
			this.V[i] = V[i];
		}
	}

	/**
	 * Construct a matrix from a 1-D array.
	 * 
	 * @param A
	 *            One-dimensional array of {@link Apfloat}.
	 */
	public Vector(final double[] V) {
		this.n = V.length;
		this.V = new Apfloat[n];

		for (int i = 0; i < n; i++) {
			this.V[i] = ApfloatUtils.valueOf(V[i]);
		}
	}

	/* (non-Javadoc)
	 * @see hu.nsmdmp.vectors.IVector#getVector()
	 */
	@Override
	public Apfloat[] getArray() {
		return V;
	}

	/* (non-Javadoc)
	 * @see hu.nsmdmp.vectors.IVector#get(int)
	 */
	public Apfloat get(final int i) {
		return V[i];
	}

	/* (non-Javadoc)
	 * @see hu.nsmdmp.vectors.IVector#set(int, org.apfloat.Apfloat)
	 */
	public void set(final int i, final Apfloat s) {
		V[i] = s;
	}

	/* (non-Javadoc)
	 * @see hu.nsmdmp.vectors.IVector#getColumnDimension()
	 */
	@Override
	public int getColumnDimension() {
		return n;
	}

	/* (non-Javadoc)
	 * @see hu.nsmdmp.vectors.IVector#getArrayCopy()
	 */
	@Override
	public Apfloat[] getArrayCopy() {
		Apfloat[] V = new Apfloat[n];

		for (int i = 0; i < n; i++) {
			V[i] = this.V[i];
		}

		return V;
	}

	/* (non-Javadoc)
	 * @see hu.nsmdmp.vectors.IVector#copy()
	 */
	@Override
	public Vector copy() {
		Vector X = new Vector(n);
		Apfloat[] C = X.getArray();

		for (int i = 0; i < n; i++) {
			C[i] = this.V[i];
		}

		return X;
	}

	/**
	 * Clone the {@link Vector} object.
	 * 
	 */
	public Object clone() {
		return copy();
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof Vector)) {
			return false;
		}

		return Utils.equals(getArray(), ((Vector) obj).getArray());
	}

	@Override
	public String toString() {
		return Utils.print(V);
	}
}
