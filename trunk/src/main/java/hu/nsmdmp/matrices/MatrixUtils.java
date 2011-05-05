package hu.nsmdmp.matrices;

import org.apfloat.Apfloat;
import org.apfloat.spi.RadixConstants;

public final class MatrixUtils {

	private static final double e = 0.00000000000001;

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

	public static String print(final Apfloat[] v) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");

		for (int i = 0; i < v.length; i++) {
			if (i < v.length - 1) {
				sb.append(String.format("%s, ", v[i]));
			} else {
				sb.append(String.format("%s", v[i]));
			}
		}

		sb.append("}");

		return sb.toString();
	}

	public static String print(final double[][] m) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < m.length; i++) {
			sb.append("{");
			for (int j = 0; j < m[i].length; j++) {
				if (j < m[i].length - 1) {
					sb.append(String.format("%s, ", m[i][j]));
				} else {
					sb.append(String.format("%s}\n", m[i][j]));
				}
			}
		}

		return sb.toString();
	}

	public static String print(final int[][] m) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < m.length; i++) {
			sb.append("{");
			for (int j = 0; j < m[i].length; j++) {
				if (j < m[i].length - 1) {
					sb.append(String.format("%s, ", m[i][j]));
				} else {
					sb.append(String.format("%s}\n", m[i][j]));
				}
			}
		}

		return sb.toString();
	}

	public static String print(final double[] v) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");

		for (int i = 0; i < v.length; i++) {
			if (i < v.length - 1) {
				sb.append(String.format("%s, ", v[i]));
			} else {
				sb.append(String.format("%s", v[i]));
			}
		}

		sb.append("}");

		return sb.toString();
	}

	public static String print(final int[] v) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");

		for (int i = 0; i < v.length; i++) {
			if (i < v.length - 1) {
				sb.append(String.format("%s, ", v[i]));
			} else {
				sb.append(String.format("%s", v[i]));
			}
		}

		sb.append("}");

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

	public static boolean equals(final Apfloat[] a, final Apfloat[] b) {

		if (a.length != b.length) {
			return false;
		}

		for (int i = 0; i < a.length; i++) {
			Apfloat e = new Apfloat(a[i].doubleValue(), RadixConstants.DOUBLE_PRECISION[10]);
			Apfloat f = new Apfloat(b[i].doubleValue(), RadixConstants.DOUBLE_PRECISION[10]);

			if (e.compareTo(f) != 0) {
				System.out.println(String.format("%s != %s", e, f));

				return false;
			}
		}

		return true;
	}

	public static boolean equals(final double[][] a, final double[][] b) {

		if (a.length != b.length) {
			return false;
		}

		for (int i = 0; i < a.length; i++) {
			if (a[i].length != b[i].length) {
				return false;
			}

			for (int j = 0; j < a[i].length; j++) {
				if (Math.abs(a[i][j] - b[i][j]) > e) {
					System.out.println(String.format("[%d, %d] %s != %s", i, j, a[i][j], b[i][j]));
					return false;
				}
			}
		}

		return true;
	}

	public static boolean equals(final int[][] a, final int[][] b) {
		if (a.length != b.length) {
			return false;
		}

		for (int i = 0; i < a.length; i++) {
			if (a[i].length != b[i].length) {
				return false;
			}

			for (int j = 0; j < a[i].length; j++) {
				if (a[i][j] != b[i][j]) {
					System.out.println(String.format("[%d, %d] %s != %s", i, j, a[i][j], b[i][j]));
					return false;
				}
			}
		}

		return true;
	}

	public static boolean equals(final double[] a, final double[] b) {

		if (a.length != b.length) {
			return false;
		}

		for (int i = 0; i < a.length; i++) {
			if (Math.abs(a[i] - b[i]) > e) {
				System.out.println(String.format("%s != %s", a[i], b[i]));

				return false;
			}
		}

		return true;
	}
}
