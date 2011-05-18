package hu.nsmdmp.matrices;

import org.apfloat.Apfloat;

public final class Matrix {

	public static SimpleMatrix getSimpleMatrix(final Apfloat[][] vectorSet, final int maxOrder) {
		SimpleMatrix matrix = new SimpleMatrix();
		matrix.create(vectorSet, maxOrder);

		return matrix;
	}

	public static NormalizedMatrix getNormalizedMatrix(final Apfloat[][] vectorSet, final int maxOrder) {
		NormalizedMatrix matrix = new NormalizedMatrix();
		matrix.create(vectorSet, maxOrder);

		return matrix;
	}

	public static ChebyshevTMatrix getChebyshevTMatrix(final Apfloat[][] vectorSet, final int maxOrder) {
		ChebyshevTMatrix matrix = new ChebyshevTMatrix();
		matrix.create(vectorSet, maxOrder);

		return matrix;
	}

	public static ChebyshevUMatrix getChebyshevUMatrix(final Apfloat[][] vectorSet, final int maxOrder) {
		ChebyshevUMatrix matrix = new ChebyshevUMatrix();
		matrix.create(vectorSet, maxOrder);

		return matrix;
	}
}
