package hu.nsmdmp.matrixforms;

import org.apfloat.Apfloat;

public final class MatrixFactory {

	public static SimpleMatrix createSimpleMatrix(final Apfloat[][] vectorSet, final int moment) {
		SimpleMatrix matrix = new SimpleMatrix();
		matrix.create(vectorSet, moment);

		return matrix;
	}

	public static NormalizedMatrix createNormalizedMatrix(final Apfloat[][] vectorSet, final int moment) {
		NormalizedMatrix matrix = new NormalizedMatrix();
		matrix.create(vectorSet, moment);

		return matrix;
	}

	public static ChebyshevTMatrix createChebyshevTMatrix(final Apfloat[][] vectorSet, final int moment) {
		ChebyshevTMatrix matrix = new ChebyshevTMatrix();
		matrix.create(vectorSet, moment);

		return matrix;
	}

	public static ChebyshevUMatrix createChebyshevUMatrix(final Apfloat[][] vectorSet, final int moment) {
		ChebyshevUMatrix matrix = new ChebyshevUMatrix();
		matrix.create(vectorSet, moment);

		return matrix;
	}
}
