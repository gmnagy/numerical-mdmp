package hu.nsmdmp.vector;


import org.apfloat.Apfloat;

public interface IVector {

	/**
	 * Get the internal one-dimensional array.
	 * 
	 * @return Pointer to the one-dimensional array of vector elements.
	 */
	Apfloat[] getArray();

	/**
	 * Get a single element.
	 * 
	 * @param i
	 *            Column index.
	 * @return V(i)
	 * @exception ArrayIndexOutOfBoundsException
	 */
	Apfloat get(final int i);

	/**
	 * Set a single element.
	 * 
	 * @param i
	 *            Column index.
	 * @param s
	 *            V(i).
	 * @exception ArrayIndexOutOfBoundsException
	 */
	void set(final int i, final Apfloat s);

	/**
	 * Copy the internal one-dimensional array.
	 * 
	 * @return one-dimensional array copy of matrix elements.
	 */
	Apfloat[] getArrayCopy();

	/**
	 * Get column dimension.
	 * 
	 * @return n, the number of columns.
	 */
	int getColumnDimension();

	/**
	 * Make a deep copy of a vector.
	 * 
	 */
	Vector copy();
}
