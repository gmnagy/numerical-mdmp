package hu.nsmdmp.numerics.matrix.math;

import static com.google.common.base.Preconditions.checkNotNull;
import static hu.nsmdmp.numerics.matrix.operations.OperationFactory.selectOperation;
import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.numerics.matrix.Vector;
import hu.nsmdmp.numerics.matrix.operations.IOperations;

public final class MatrixMath {

	/**
	 * Matrix-Vector multiplication.
	 * 
	 */
	public static <T> Vector<T> multiply(final Matrix<T> A, final Vector<T> V) {
		return Multiplication.multiply(A, V);
	}

	/**
	 * Linear algebraic matrix multiplication, A * B.
	 * 
	 */
	public static <T> Matrix<T> multiply(final Matrix<T> A, final Matrix<T> B) {
		return Multiplication.multiply(A, B);
	}

	/**
	 * @return An m-by-n matrix with ones on the diagonal and zeros elsewhere.
	 */
	public static <T> Matrix<T> identity(int row, int column, final Class<T> type) {
		checkNotNull(type, "The type of matrix is NULL.");

		IOperations<T> op = selectOperation(type);

		Matrix<T> A = new Matrix<T>(row, column);

		for (int i = 0; i < row; i++)
			for (int j = 0; j < column; j++)
				A.set(i, j, i == j ? op.one() : op.zero());

		return A;
	}
}
