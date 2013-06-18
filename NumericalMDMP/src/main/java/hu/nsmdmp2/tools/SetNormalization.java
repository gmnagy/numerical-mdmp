package hu.nsmdmp2.tools;

import static com.google.common.base.Preconditions.checkNotNull;
import hu.nsmdmp2.numerics.Value;

public final class SetNormalization {

	public static  Value[][] normalize(final Value[][] set) {
		checkNotNull(set, "The set is NULL.");
		checkNotNull(set[0][0], "The set is NULL.");

		int row = set.length;

		Value[][] normM = new Value[row][0];

		for (int i = 0; i < row; i++) {
			Value min = set[i][0];
			Value max = set[i][0];

			for (int j = 1; j < set[i].length; j++) {
				Value x = set[i][j];

				if (x.compareTo(min) < 0) {
					min = x;
				}

				if (x.compareTo(max) > 0) {
					max = x;
				}
			}

			normM[i] = new Value[set[i].length];

			// (min + max) / 2;
			Value mid = min.add(max);
			mid = mid.divide(2);

			for (int j = 0; j < set[i].length; j++) {

				// 2 * (vSet[i][j] - mid)
				Value a = set[i][j].subtract(mid);
				a = a.multiply(2);

				// max - min
				Value b = max.subtract(min);

				// a / b
				normM[i][j] = a.divide(b);
			}
		}

		return normM;
	}
}
