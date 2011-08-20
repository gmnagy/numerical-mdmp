package hu.nsmdmp.matrix.operation;

import hu.nsmdmp.ApfloatUtils;
import hu.nsmdmp.matrix.Matrix;
import hu.nsmdmp.vector.Vector;

import org.apfloat.Apfloat;

public final class Multiplication {

	/**
	 * Matrix-Vector multiplication.
	 * 
	 * @param A
	 *            {@link Matrix}
	 * @param V
	 *            {@link Vector}
	 * @return A * V
	 * @exception IllegalArgumentException
	 */
	static Vector multiply(final Matrix A, final Vector V) {
		int m = A.getRowDimension();
		int n = A.getColumnDimension();

		Vector result = new Vector(m);

		for (int i = 0; i < m; i++) {

			if (n != V.getColumnDimension()) {
				throw new IllegalArgumentException(String.format("Nem lehet osszeszorozni mert a meret nem megfelelo %d != %d !!!", n, V.getColumnDimension()));
			}

			Apfloat x = ApfloatUtils.ZERO;

			for (int j = 0; j < n; j++) {
				Apfloat a = A.get(i, j).multiply(V.get(j));
				x = x.add(a);
			}

			result.set(i, x);
		}

		return result;
	}

	/**
	 * Linear algebraic matrix multiplication, A * B.
	 * 
	 * @param A
	 *            one {@link Matrix}
	 * @param B
	 *            another {@link Matrix}
	 * 
	 * @return Matrix product, A * B
	 * @exception IllegalArgumentException
	 *                Matrix inner dimensions must agree.
	 */
	public static Matrix multiply(final Matrix A, final Matrix B) {
		if (B.getRowDimension() != A.getColumnDimension()) {
			throw new IllegalArgumentException("Matrix inner dimensions must agree.");
		}

		Matrix X = new Matrix(A.getRowDimension(), B.getColumnDimension());
		Apfloat[][] C = X.getArray();
		Apfloat[] Bcolj = new Apfloat[A.getColumnDimension()];

		for (int j = 0; j < B.getColumnDimension(); j++) {
			for (int k = 0; k < A.getColumnDimension(); k++) {
				Bcolj[k] = B.get(k, j);
			}

			for (int i = 0; i < A.getRowDimension(); i++) {
				Apfloat[] Arowi = A.getRow(i);
				Apfloat s = ApfloatUtils.ZERO;

				for (int k = 0; k < A.getColumnDimension(); k++) {
					s = s.add(Arowi[k].multiply(Bcolj[k]));
				}

				C[i][j] = s;
			}
		}
		return X;
	}
}
