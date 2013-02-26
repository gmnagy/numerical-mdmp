package hu.nsmdmp.numerics.matrix.math;

import static hu.nsmdmp.operations.Operations.operation;
import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.numerics.matrix.Vector;
import hu.nsmdmp.operations.IOperation;

import java.lang.reflect.Array;

final class Multiplication {

	/**
	 * Matrix-Vector multiplication.
	 * 
	 */
	static <T> Vector<T> multiply(final Matrix<T> M, final Vector<T> V) {
		IOperation<T> op = operation(M.getValueType());

		int m = M.getRowDimension();
		int n = M.getColumnDimension();

		if (n != V.getColumnDimension()) {
			throw new IllegalArgumentException(String.format("Nem lehet osszeszorozni mert a meret nem megfelelo %d != %d !!!", n, V.getColumnDimension()));
		}

		Vector<T> result = new Vector<T>(m, V.getValueType());

		for (int i = 0; i < m; i++) {

			T x = op.zero();

			for (int j = 0; j < n; j++) {
				T a = op.multiply(M.get(i, j), V.get(j));
				x = op.add(x, a);
			}

			result.set(i, x);
		}

		return result;
	}

	/**
	 * Linear algebraic matrix multiplication, A * B.
	 * 
	 */
	@SuppressWarnings("unchecked")
	static <T> Matrix<T> multiply(final Matrix<T> A, final Matrix<T> B) {
		IOperation<T> op = operation(A.getValueType());

		if (B.getRowDimension() != A.getColumnDimension()) {
			throw new IllegalArgumentException("Matrix inner dimensions must agree.");
		}

		Matrix<T> X = new Matrix<T>(A.getRowDimension(), B.getColumnDimension(), A.getValueType());
		T[] Bcolj = (T[]) Array.newInstance(op.getType(), A.getColumnDimension());

		for (int j = 0; j < B.getColumnDimension(); j++) {
			for (int k = 0; k < A.getColumnDimension(); k++) {
				Bcolj[k] = B.get(k, j);
			}

			for (int i = 0; i < A.getRowDimension(); i++) {
				T[] Arowi = A.getRow(i);
				T s = op.zero();

				for (int k = 0; k < A.getColumnDimension(); k++) {
					s = op.add(s, op.multiply(Arowi[k], Bcolj[k]));
				}

				X.set(i, j, s);
			}
		}

		return X;
	}
}
