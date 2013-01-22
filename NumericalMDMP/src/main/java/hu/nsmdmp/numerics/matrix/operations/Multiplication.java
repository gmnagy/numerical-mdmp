package hu.nsmdmp.numerics.matrix.operations;

import static hu.nsmdmp.numerics.OperationFactory.selectOperation;
import hu.nsmdmp.numerics.IOperations;
import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.numerics.matrix.Vector;

import java.lang.reflect.Array;

final class Multiplication {

	/**
	 * Matrix-Vector multiplication.
	 * 
	 */
	static <T> Vector<T> multiply(final Matrix<T> A, final Vector<T> V, final Class<T> type) {
		IOperations<T> operations = selectOperation(type);

		int m = A.getRowDimension();
		int n = A.getColumnDimension();

		Vector<T> result = new Vector<T>(m);

		for (int i = 0; i < m; i++) {

			if (n != V.getColumnDimension()) {
				throw new IllegalArgumentException(String.format("Nem lehet osszeszorozni mert a meret nem megfelelo %d != %d !!!", n, V.getColumnDimension()));
			}

			T x = operations.zero();

			for (int j = 0; j < n; j++) {
				T a = operations.multiply(A.get(i, j), V.get(j));
				x = operations.add(x, a);
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
	static <T> Matrix<T> multiply(final Matrix<T> A, final Matrix<T> B, final Class<T> type) {
		IOperations<T> operations = selectOperation(type);

		if (B.getRowDimension() != A.getColumnDimension()) {
			throw new IllegalArgumentException("Matrix inner dimensions must agree.");
		}

		Matrix<T> X = new Matrix<T>(A.getRowDimension(), B.getColumnDimension());
		T[][] C = X.getArray();
		T[] Bcolj = (T[]) Array.newInstance(type, A.getColumnDimension());

		for (int j = 0; j < B.getColumnDimension(); j++) {
			for (int k = 0; k < A.getColumnDimension(); k++) {
				Bcolj[k] = B.get(k, j);
			}

			for (int i = 0; i < A.getRowDimension(); i++) {
				T[] Arowi = A.getRow(i);
				T s = operations.zero();

				for (int k = 0; k < A.getColumnDimension(); k++) {
					s = operations.add(s, operations.multiply(Arowi[k], Bcolj[k]));
				}

				C[i][j] = s;
			}
		}

		return X;
	}
}
