package hu.nsmdmp.mosek;

import static hu.nsmdmp.operations.Operations.operation;
import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.numerics.matrix.Vector;
import hu.nsmdmp.numerics.matrix.math.MatrixMath;
import hu.nsmdmp.operations.IOperation;

import java.lang.reflect.Array;

import mosek.MosekException;

public class PreciseLPCalc {

	//PreciseLPSolution result;
	public static LPSolution solverResult;

	PreciseLPCalc() {
	}

	public static <T> PreciseLPSolution<T> optimizeMin(final Matrix<T> matrix, final Vector<T> b, final Vector<T> c) throws MosekException {
		PreciseLPSolution<T> result;
		solverResult = LinearProgrammingEq.optimizeMin(matrix, b, c);
		result = calcResult(solverResult.basisIndexes, matrix, b, c, -1);

		return result;
	}

	/**
	 * Maximalizalasa.
	 * 
	 */
	public static <T> PreciseLPSolution<T> optimizeMax(final Matrix<T> matrix, final Vector<T> b, final Vector<T> c) throws MosekException {
		PreciseLPSolution<T> result;
		solverResult = LinearProgrammingEq.optimizeMax(matrix, b, c);
		result = calcResult(solverResult.basisIndexes, matrix, b, c, 1);

		return result;
	}

	/*
	 * final int sense is -1 in case of min problem
	 */
	@SuppressWarnings("unchecked")
	private static <T> PreciseLPSolution<T> calcResult(final int[] basisIndexes, final Matrix<T> matrix, final Vector<T> b, final Vector<T> c, final int sense) {

		IOperation<T> op = operation(matrix.getValueType());

		PreciseLPSolution<T> result = new PreciseLPSolution<T>();
		//result.objectiveValue????
		result.basisIndexes = basisIndexes;
		result.dualSlackInfeasIndex = -1;
		result.primalNonnegInfeasIndex = -1;
		result.primalSlackInfeasIndex = -1;
		result.primalNonnegInfeas = op.zero();
		result.primalSlackInfeas = op.zero();
		result.dualSlackInfeas = op.zero();

		/* basis matrix and obj. coefficients */
		Matrix<T> basisMatrix = new Matrix<T>(matrix.getRowDimension(), matrix.getRowDimension(), op.getType());
		Vector<T> cBasis = new Vector<T>(matrix.getRowDimension(), op.getType());

		for (int j = 0; j < matrix.getRowDimension(); j++) {
			if (basisIndexes[j] < matrix.getRowDimension()) {
				for (int i = 0; i < matrix.getRowDimension(); i++) {
					basisMatrix.set(i, j, op.zero());
				}
				basisMatrix.set(basisIndexes[j], j, op.one());
				cBasis.set(j, op.zero());
			} else {
				for (int i = 0; i < matrix.getRowDimension(); i++) {
					basisMatrix.set(i, j, matrix.get(i, basisIndexes[j] - matrix.getRowDimension()));
				}
				cBasis.set(j, c.get(basisIndexes[j] - matrix.getRowDimension()));
			}

		}

		/* primal infeasibilities */
		Vector<T> xBasis = MatrixMath.multiply(basisMatrix.inverse(), b);
		result.objectiveValue = op.zero();
		for (int i = 0; i < matrix.getRowDimension(); i++) {
			result.objectiveValue = op.add(result.objectiveValue, op.multiply(cBasis.get(i), xBasis.get(i)));
		}

		result.x = (T[]) Array.newInstance(op.getType(), matrix.getColumnDimension());
		for (int j = 0; j < matrix.getColumnDimension(); j++) {
			result.x[j] = op.zero();
		}

		for (int j = 0; j < matrix.getRowDimension(); j++) {
			if (basisIndexes[j] < matrix.getRowDimension()) {

				T abs = xBasis.get(j);
				if (op.compareTo(abs, op.zero()) < 0) {
					abs = op.negate(abs);
				}

				if (op.compareTo(abs, result.primalSlackInfeas) > 0) {
					result.primalSlackInfeas = abs;
					result.primalSlackInfeasIndex = basisIndexes[j];
				}
			} else {
				result.x[basisIndexes[j] - matrix.getRowDimension()] = xBasis.get(j);
				if (op.compareTo(xBasis.get(j), result.primalNonnegInfeas) < 0) {
					result.primalNonnegInfeas = xBasis.get(j);
					result.primalNonnegInfeasIndex = basisIndexes[j] - matrix.getRowDimension();
				}
			}

		}

		/* dual feasibilities */
		Vector<T> yDual = MatrixMath.multiply(basisMatrix.inverse().transpose(), cBasis);
		Vector<T> slackDual = MatrixMath.multiply(matrix.transpose(), yDual);
		for (int j = 0; j < matrix.getColumnDimension(); j++) {
			if (sense == 1) {
				//if ((slackDual.get(j).subtract(c.get(j))).compareTo(result.dualSlackInfeas) < 0) {
				if (op.compareTo(op.subtract(slackDual.get(j), c.get(j)), result.dualSlackInfeas) < 0) {
					result.dualSlackInfeas = op.subtract(slackDual.get(j), c.get(j));
					result.dualSlackInfeasIndex = j;
				}
			} else {
				if (op.compareTo(op.subtract(slackDual.get(j), c.get(j)), result.dualSlackInfeas) > 0) {
					result.dualSlackInfeas = op.subtract(slackDual.get(j), c.get(j));
					result.dualSlackInfeasIndex = j;
				}
			}
		}

		return result;
	}
}
