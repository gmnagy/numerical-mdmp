package hu.nsmdmp2.numerics.matrix;

import static com.google.common.base.Preconditions.checkNotNull;
import hu.nsmdmp2.numerics.Value;

public class MatrixMath {

	/**
	 * Matrix-Vector multiplication.
	 * 
	 */
	public static Value[] multiply(Value[][] A, Value[] V) {
		return Multiplication.multiply(A, V);
	}

	/**
	 * Linear algebraic matrix multiplication, A * B.
	 * 
	 */
	public static Value[][] multiply(Value[][] A, Value[][] B) {
		return Multiplication.multiply(A, B);
	}

	/**
	 * @return An m-by-n matrix with ones on the diagonal and zeros elsewhere.
	 */
	public static Value[][] identity(int row, int column, Class<? extends Comparable<?>> type) {
		checkNotNull(type, "The type of matrix is NULL.");

		Value[][] A = new Value[row][column];

		for (int i = 0; i < row; i++)
			for (int j = 0; j < column; j++)
				A[i][j] = i == j ? Value.one(type) : Value.zero(type);

		return A;
	}

	public static Value[][] transpose(Value[][] M) {
		int row = M.length;
		int col = M[0].length;

		Value[][] X = new Value[col][row];

		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++)
				X[j][i] = M[i][j];

		return X;
	}

	public static Value[][] getSubMatrix(Value[][] M, int[] r, int i0, int i1) {
		Value[][] X = new Value[r.length][i1 - i0 + 1];

		for (int i = 0; i < r.length; i++)
			for (int j = i0; j <= i1; j++)
				X[i][j - i0] = M[r[i]][j];

		return X;
	}

	public static Value[][] getSubMatrix(Value[][] M, int i0, int i1, int j0, int j1) {
		Value[][] X = new Value[i1 - i0 + 1][j1 - j0 + 1];

		for (int i = i0; i <= i1; i++)
			for (int j = j0; j <= j1; j++)
				X[i - i0][j - j0] = M[i][j];

		return X;
	}

	/**
	 * Matrix inverse or pseudoinverse.
	 * 
	 * @return inverse(A) if A is square, pseudoinverse otherwise.
	 */
	public static Value[][] inverse(Value[][] M) {
		int row = M.length;
		int col = M[0].length;
		Class<? extends Comparable<?>> valueType = M[0][0].getType();

		Value[][] identity = MatrixMath.identity(row, row, valueType);

		if (isSquare(M)) {
			return new LUDecomposition(M).solve(identity);
		} else if (row > col) {
			return new QRDecomposition(M).solve(identity);
		} else {
			Value[][] transposed = transpose(M);
			return transpose(new QRDecomposition(transposed).solve(identity(col, col, valueType)));
		}
	}

	public static boolean isSquare(Value[][] M) {
		return M.length == M[0].length;
	}

	public static Value[][] clone(Value[][] M) {
		int row = M.length;
		int col = M[0].length;

		Value[][] copy = new Value[row][col];

		for (int i = 0; i < row; i++) {
			System.arraycopy(M[i], 0, copy[i], 0, M[i].length);
		}

		return copy;
	}
}
