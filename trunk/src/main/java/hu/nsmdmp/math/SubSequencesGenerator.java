package hu.nsmdmp.math;

import java.util.ArrayList;
import java.util.List;

public class SubSequencesGenerator {

	public static List<int[]> getSubSequences(final int n, final int l, final int dim) {
		List<int[]> list = new ArrayList<int[]>();

		int d = 0;
		int j = 0;
		int[] subSequence = null;

		for (int i = 0; i < n; i++) {

			if (i % l == 0 && dim != d) {
				d++;
				j = 0;

				int size = l;
				if (dim == d)
					size = n - i;

				subSequence = new int[size];
				list.add(subSequence);
			}

			subSequence[j] = i;
			j++;
		}

		return list;
	}
}
