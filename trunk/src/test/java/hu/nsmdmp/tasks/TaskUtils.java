package hu.nsmdmp.tasks;

import hu.nsmdmp.utils.ApfloatUtils;

import org.apfloat.Apfloat;

public class TaskUtils {

	/**
	 * n=2, m=3 => {{0, 1, 2}, {0, 1, 2}}
	 */
	public static Apfloat[][] createVectorSet(final int n, final int m) {
		Apfloat[][] vectorSet = new Apfloat[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				vectorSet[i][j] = ApfloatUtils.valueOf(j);
			}
		}

		return vectorSet;
	}
}
