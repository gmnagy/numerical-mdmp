package hu.nsmdmp.tasks;

import hu.nsmdmp.ApfloatUtils;

import org.apfloat.Apfloat;

public class TaskUtils {

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
