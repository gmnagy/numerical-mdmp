package hu.nsmdmp2.numerics.matrix;

import static com.google.common.base.Preconditions.checkNotNull;
import hu.nsmdmp2.numerics.Value;

final class Multiplication {

	/**
	 * Matrix-Vector multiplication.
	 * 
	 */
	static Value[] multiply(final Value[][] M, final Value[] V) {
		checkNotNull(M);
		checkNotNull(V);

		Class<? extends Comparable<?>> valueType = V[0].getType();

		int m = M.length;
		int n = M[0].length;

		if (n != V.length) {
			throw new IllegalArgumentException(String.format("Nem lehet osszeszorozni mert a meret nem megfelelo %d != %d !!!", n, V.length));
		}

		Value[] result = new Value[m];

		for (int i = 0; i < m; i++) {

			Value x = Value.zero(valueType);

			for (int j = 0; j < n; j++) {
				Value a = M[i][j].multiply(V[j]);
				x = x.add(a);
			}

			result[i] = x;
		}

		return result;
	}

	/**
	 * Linear algebraic matrix multiplication, A * B.
	 * 
	 */
	static Value[][] multiply(final Value[][] A, final Value[][] B) {
		checkNotNull(A);
		checkNotNull(B);

		Class<? extends Comparable<?>> valueType = A[0][0].getType();

		if (B.length != A[0].length) {
			throw new IllegalArgumentException("Matrix inner dimensions must agree.");
		}

		Value[][] X = new Value[A.length][B[0].length];
		Value[] Bcolj = new Value[A[0].length];

		for (int j = 0; j < B[0].length; j++) {
			for (int k = 0; k < A[0].length; k++) {
				Bcolj[k] = B[k][j];
			}

			for (int i = 0; i < A.length; i++) {
				Value[] Arowi = A[i];
				Value s = Value.zero(valueType);

				for (int k = 0; k < A[0].length; k++) {
					s = s.add(Arowi[k].multiply(Bcolj[k]));
				}

				X[i][j] = s;
			}
		}

		return X;
	}
}
