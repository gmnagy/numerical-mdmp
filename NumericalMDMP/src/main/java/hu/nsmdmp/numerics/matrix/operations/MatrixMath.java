package hu.nsmdmp.numerics.matrix.operations;

import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.numerics.matrix.Vector;

public final class MatrixMath {

	/**
	 * Matrix-Vector multiplication.
	 * 
	 */
	public static <T> Vector<T> multiply(final Matrix<T> A, final Vector<T> V, final Class<T> type) {
		return Multiplication.multiply(A, V, type);
	}

	/**
	 * Linear algebraic matrix multiplication, A * B.
	 * 
	 */
	public static <T> Matrix<T> multiply(final Matrix<T> A, final Matrix<T> B, final Class<T> type) {
		return Multiplication.multiply(A, B, type);
	}
}
