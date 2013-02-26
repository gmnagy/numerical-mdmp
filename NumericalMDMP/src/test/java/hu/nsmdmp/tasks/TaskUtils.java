package hu.nsmdmp.tasks;

import static hu.nsmdmp.numerics.matrix.math.MatrixMath.multiply;
import static hu.nsmdmp.polynomialmatrixfactory.MonomialToChebTMatrix.generateMonomialChebTTransformationMatrix;
import static hu.nsmdmp.polynomialmatrixfactory.MonomialToChebUMatrix.generateMonomialChebUTransformationMatrix;
import static hu.nsmdmp.tools.VectorNormalizationWithSet.normailzeByGergo;
import hu.nsmdmp.mosek.LPSolution;
import hu.nsmdmp.mosek.LinearProgrammingEq;
import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.numerics.matrix.Vector;
import mosek.MosekException;

public class TaskUtils {

	/**
	 * Normailzed ChebyshevU vector.
	 */
	public static <T> Vector<T> createNormChebyshevUVector(final int maxOrder, final Vector<T> powerV, final T[][] vectorSet) {

		// normailzed power vector
		Vector<T> nPowerV = normailzeByGergo(vectorSet, maxOrder, powerV);

		// Monomial ChebyshevU transormation matrix.
		Matrix<T> T = generateMonomialChebUTransformationMatrix(maxOrder, vectorSet.length, powerV.getValueType());

		return multiply(T, nPowerV);
	}

	/**
	 * Normailzed ChebyshevT vector.
	 */
	public static <T> Vector<T> createNormChebyshevTVector(final int maxOrder, final Vector<T> powerV, final T[][] vectorSet) {

		// normailzed power vector
		Vector<T> nPowerV = normailzeByGergo(vectorSet, maxOrder, powerV);

		// Monomial ChebyshevU transormation matrix.
		Matrix<T> T = generateMonomialChebTTransformationMatrix(maxOrder, vectorSet.length, powerV.getValueType());

		return multiply(T, nPowerV);
	}

	/**
	 * Optimize Min.
	 */
	public static <T> double getMinCumProbMatrixElement(final Matrix<T> matrix, final Vector<T> vector, final Vector<T> f) throws MosekException {
		LPSolution min = LinearProgrammingEq.optimizeMin(matrix, vector, f);

		return min.getPrimalSolution();
	}

	/**
	 * Optimize Max.
	 */
	public static <T> double getMaxCumProbMatrixElement(final Matrix<T> matrix, final Vector<T> vector, Vector<T> f) throws MosekException {
		LPSolution max = LinearProgrammingEq.optimizeMax(matrix, vector, f);

		return max.getPrimalSolution();
	}
}
