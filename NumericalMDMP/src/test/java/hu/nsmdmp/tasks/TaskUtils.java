package hu.nsmdmp.tasks;

import static hu.nsmdmp.numerics.matrix.math.MatrixMath.multiply;
import static hu.nsmdmp.numerics.matrix.operations.OperationFactory.selectOperation;
import static hu.nsmdmp.polynomialmatrixfactory.MonomialToChebUMatrix.generateMonomialChebUTransformationMatrix;
import static hu.nsmdmp.tools.VectorNormalizationWithSet.normailzeByGergo;
import hu.nsmdmp.mosek.LPSolution;
import hu.nsmdmp.mosek.LinearProgrammingEq;
import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.numerics.matrix.Vector;
import hu.nsmdmp.numerics.matrix.operations.IOperations;
import mosek.MosekException;

public class TaskUtils {

	/**
	 * Normailzed ChebyshevU vector.
	 */
	public static <T> Vector<T> createNormChebyshevUVector(final int maxOrder, final Vector<T> powerV, final T[][] vectorSet) {
		IOperations<T> op = selectOperation(powerV.getElementType());

		// normailzed power vector
		Vector<T> nPowerV = normailzeByGergo(vectorSet, maxOrder, powerV);

		// Monomial ChebyshevU transormation matrix.
		Matrix<T> T = generateMonomialChebUTransformationMatrix(maxOrder, vectorSet.length, op.getType());

		return multiply(T, nPowerV);
	}

	/**
	 * Optimize Min.
	 */
	public static <T> double getMinCumProbMatrixElement(final Matrix<T> chebU, final Vector<T> nChebyUV, final Vector<T> f) throws MosekException {
		LPSolution max = LinearProgrammingEq.optimizeMin(chebU, nChebyUV, f);

		return max.getPrimalSolution();
	}

	/**
	 * Optimize Max.
	 */
	public static <T> double getMaxCumProbMatrixElement(final Matrix<T> chebU, final Vector<T> nChebyUV, Vector<T> f) throws MosekException {
		LPSolution max = LinearProgrammingEq.optimizeMax(chebU, nChebyUV, f);

		return max.getPrimalSolution();
	}
}
