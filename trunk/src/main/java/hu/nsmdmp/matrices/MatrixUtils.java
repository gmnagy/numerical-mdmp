package hu.nsmdmp.matrices;

import org.apfloat.Apfloat;
import org.apfloat.spi.RadixConstants;

public final class MatrixUtils {

	public static String print(final Apfloat[][] m) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < m.length; i++) {
			sb.append("{");
			for (int j = 0; j < m[i].length; j++) {
				if (j < m[i].length - 1) {
					sb.append(String.format("%s, ", m[i][j]));
				} else {
					sb.append(String.format("%s", m[i][j]));
				}
			}
			sb.append("}\n");
		}

		return sb.toString();
	}

	public static boolean equals(final Apfloat[][] a, final Apfloat[][] b) {

		if (a.length != b.length) {
			return false;
		}

		for (int i = 0; i < a.length; i++) {
			if (a[i].length != b[i].length) {
				return false;
			}

			for (int j = 0; j < a[i].length; j++) {
				Apfloat e = new Apfloat(a[i][j].doubleValue(), RadixConstants.DOUBLE_PRECISION[10]);
				Apfloat f = new Apfloat(b[i][j].doubleValue(), RadixConstants.DOUBLE_PRECISION[10]);

				if (e.compareTo(f) != 0) {
					System.out.println(String.format("[%d, %d] %s != %s", i, j, e, f));
					return false;
				}
			}
		}

		return true;
	}
}
