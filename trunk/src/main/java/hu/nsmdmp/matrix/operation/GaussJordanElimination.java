package hu.nsmdmp.matrix.operation;

import hu.nsmdmp.ApfloatUtils;
import hu.nsmdmp.matrix.Matrix;
import hu.nsmdmp.vector.Vector;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

public class GaussJordanElimination {

	// N-by-N system
	private final int N;

	// N-by-N+1 augmented matrix
	private Matrix aug;

	// Gauss-Jordan elimination with partial pivoting
	GaussJordanElimination(final Matrix A, final Vector b) {
		N = b.getColumnDimension();

		// build augmented matrix
		aug = new Matrix(N, N + N + 1);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				aug.set(i, j, A.get(i, j));
			}
		}

		// only need if you want to find certificate of infeasibility (or compute inverse)
		for (int i = 0; i < N; i++) {
			for (int j = N; j < N + N; j++) {
				aug.set(i, j, (i + N == j) ? ApfloatUtils.ONE : ApfloatUtils.ZERO);
			}
		}

		for (int i = 0; i < N; i++) {
			aug.set(i, N + N, b.get(i));
		}

		solve();
	}

	private void solve() {

		// Gauss-Jordan elimination
		for (int p = 0; p < N; p++) {

			// find pivot row using partial pivoting
			int max = p;
			for (int i = p + 1; i < N; i++) {
				// greater than
				if (ApfloatMath.abs(aug.get(i, p)).compareTo(ApfloatMath.abs(aug.get(max, p))) == 1) {
					max = i;
				}
			}

			// exchange row p with row max
			swap(p, max);

			// singular or nearly singular
			if (aug.get(p, p).signum() == 0) {
				continue;
				// throw new RuntimeException("Matrix is singular or nearly singular");
			}

			// pivot
			pivot(p, p);
		}
	}

	// swap row1 and row2
	private void swap(int row1, int row2) {
		Apfloat[] temp = aug.getRow(row1);
		aug.setRow(row1, aug.getRow(row2));
		aug.setRow(row2, temp);
	}

	// pivot on entry (p, q) using Gauss-Jordan elimination
	private void pivot(int p, int q) {

		// everything but row p and column q
		for (int i = 0; i < N; i++) {
			Apfloat alpha = aug.get(i, q).divide(aug.get(p, q));

			for (int j = 0; j <= N + N; j++) {
				if (i != p && j != q) {
					aug.set(i, j, aug.get(i, j).subtract(alpha.multiply(aug.get(p, j))));
				}
			}
		}

		// zero out column q
		for (int i = 0; i < N; i++) {
			if (i != p) {
				aug.set(i, q, ApfloatUtils.ZERO);
			}
		}

		// scale row p (ok to go from q+1 to N, but do this for consistency with simplex pivot)
		for (int j = 0; j <= N + N; j++) {
			if (j != q) {
				aug.set(p, j, aug.get(p, j).divide(aug.get(p, q)));
			}
		}

		aug.set(p, q, ApfloatUtils.ONE);
	}

	/**
	 * Extract solution to Ax = b.
	 * 
	 * @return <code>null</code> if matrix is singular.
	 */
	private Vector primal() {
		Vector x = new Vector(N);

		for (int i = 0; i < N; i++) {
			if (aug.get(i, i).signum() != 0) {
				x.set(i, aug.get(i, N + N).divide(aug.get(i, i)));
			} else if (aug.get(i, N + N).signum() != 0) {
				return null;
			}
		}

		return x;
	}

	private Vector dual() {
		Vector y = new Vector(N);

		for (int i = 0; i < N; i++) {
			if (aug.get(i, i).signum() == 0 && aug.get(i, N + N).signum() != 0) {
				for (int j = 0; j < N; j++) {
					y.set(j, aug.get(i, N + j));
				}

				return y;
			}
		}

		return null;
	}

	/**
	 * Does the system have a solution?
	 * 
	 */
	private boolean isFeasible() {
		return primal() != null;
	}

	Vector getSolution() {
		if (isFeasible()) {
			return primal();
		}

		throw new RuntimeException("Matrix is singular.");
//		return dual();
	}
}
