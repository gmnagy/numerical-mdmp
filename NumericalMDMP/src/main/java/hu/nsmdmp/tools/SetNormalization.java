package hu.nsmdmp.tools;

import static com.google.common.base.Preconditions.checkNotNull;
import static hu.nsmdmp.numerics.OperationFactory.selectOperation;
import hu.nsmdmp.numerics.IOperations;

import java.lang.reflect.Array;

public final class SetNormalization {

	@SuppressWarnings("unchecked")
	public static <T> T[][] normalize(final T[][] set, final Class<T> type) {
		checkNotNull(set, "The set is NULL.");

		IOperations<T> operations = selectOperation(type);

		int row = set.length;

		T[][] normM = (T[][]) Array.newInstance(type, row, 0);

		for (int i = 0; i < row; i++) {
			T min = set[i][0];
			T max = set[i][0];

			for (int j = 1; j < set[i].length; j++) {
				T x = set[i][j];

				if (operations.compareTo(x, min) < 0) {
					min = x;
				}

				if (operations.compareTo(x, max) > 0) {
					max = x;
				}
			}

			normM[i] = (T[]) Array.newInstance(type, set[i].length);

			// (min + max) / 2;
			T mid = operations.add(min, max);
			mid = operations.divide(mid, operations.two());

			for (int j = 0; j < set[i].length; j++) {

				// 2 * (vSet[i][j] - mid)
				T a = operations.subtract(set[i][j], mid);
				a = operations.multiply(a, operations.two());

				// max - min
				T b = operations.subtract(max, min);

				// a / b
				normM[i][j] = operations.divide(a, b);

			}
		}

		return normM;
	}
}
