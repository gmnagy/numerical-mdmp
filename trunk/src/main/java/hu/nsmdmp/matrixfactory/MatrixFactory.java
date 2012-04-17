package hu.nsmdmp.matrixfactory;

import hu.nsmdmp.matrix.Matrix;
import hu.nsmdmp.matrix.operation.MatrixMath;

import org.apfloat.Apfloat;

public final class MatrixFactory {

	public static Matrix getSimpleMatrix(final Apfloat[][] vectorSet, final int maxOrder) {
		MonomialMatrix matrix = new MonomialMatrix();

		return matrix.create(vectorSet, maxOrder);
	}

	public static Matrix getMonomialMatrix(final Apfloat[][] vectorSet, final int maxOrder) {
		MonomialMatrix matrix = new MonomialMatrix();
		Matrix normalizedMatrix = MatrixMath.normalize(new Matrix(vectorSet));

		return matrix.create(normalizedMatrix.getArray(), maxOrder);
		//return matrix.create(vectorSet, maxOrder);
	}

	public static Matrix getChebyshevTMatrix(final Apfloat[][] vectorSet, final int maxOrder) {
		ChebyshevTMatrix matrix = new ChebyshevTMatrix();
		Matrix normalizedMatrix = MatrixMath.normalize(new Matrix(vectorSet));

		return matrix.create(normalizedMatrix.getArray(), maxOrder);
	}

	public static Matrix getChebyshevUMatrix(final Apfloat[][] vectorSet, final int maxOrder) {
		ChebyshevUMatrix matrix = new ChebyshevUMatrix();
		Matrix normalizedMatrix = MatrixMath.normalize(new Matrix(vectorSet));

		return matrix.create(normalizedMatrix.getArray(), maxOrder);
	}
}
