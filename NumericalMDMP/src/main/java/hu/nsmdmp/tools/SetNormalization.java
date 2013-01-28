package hu.nsmdmp.tools;

import static com.google.common.base.Preconditions.checkNotNull;
import static hu.nsmdmp.numerics.matrix.operations.OperationFactory.selectOperation;
import hu.nsmdmp.numerics.matrix.operations.IOperations;

import java.lang.reflect.Array;

public final class SetNormalization {

	@SuppressWarnings("unchecked")
	public static <T> T[][] normalize(final T[][] set) {
		checkNotNull(set, "The set is NULL.");

		IOperations<T> op = selectOperation(set);

		int row = set.length;

		T[][] normM = (T[][]) Array.newInstance(op.getType(), row, 0);

		for (int i = 0; i < row; i++) {
			T min = set[i][0];
			T max = set[i][0];

			for (int j = 1; j < set[i].length; j++) {
				T x = set[i][j];

				if (op.compareTo(x, min) < 0) {
					min = x;
				}

				if (op.compareTo(x, max) > 0) {
					max = x;
				}
			}

			normM[i] = (T[]) Array.newInstance(op.getType(), set[i].length);

			// (min + max) / 2;
			T mid = op.add(min, max);
			mid = op.divide(mid, op.two());

			for (int j = 0; j < set[i].length; j++) {

				// 2 * (vSet[i][j] - mid)
				T a = op.subtract(set[i][j], mid);
				a = op.multiply(a, op.two());

				// max - min
				T b = op.subtract(max, min);

				// a / b
				normM[i][j] = op.divide(a, b);

			}
		}

		return normM;
	}
}
