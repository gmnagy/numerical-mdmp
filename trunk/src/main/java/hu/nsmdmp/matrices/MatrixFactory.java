package hu.nsmdmp.matrices;

import org.apfloat.Apfloat;

public final class MatrixFactory {

	public static IMatrix getMonomialMatrix(final Apfloat[][] vectorSet, final int maxOrder) {
		MonomialMatrix matrix = new MonomialMatrix();
		matrix.create(vectorSet, maxOrder);

		return matrix;
	}

	public static IMatrix getChebyshevTMatrix(final Apfloat[][] vectorSet, final int maxOrder) {
		ChebyshevTMatrix matrix = new ChebyshevTMatrix();
		matrix.create(vectorSet, maxOrder);

		return matrix;
	}

	public static IMatrix getChebyshevUMatrix(final Apfloat[][] vectorSet, final int maxOrder) {
		ChebyshevUMatrix matrix = new ChebyshevUMatrix();
		matrix.create(vectorSet, maxOrder);

		return matrix;
	}
}
