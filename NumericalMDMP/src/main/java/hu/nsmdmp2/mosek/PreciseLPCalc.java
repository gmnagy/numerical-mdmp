package hu.nsmdmp2.mosek;

import static hu.nsmdmp2.numerics.matrix.MatrixMath.inverse;
import static hu.nsmdmp2.numerics.matrix.MatrixMath.multiply;
import static hu.nsmdmp2.numerics.matrix.MatrixMath.transpose;
import hu.nsmdmp2.numerics.Value;
import mosek.MosekException;

public class PreciseLPCalc {

	// PreciseLPSolution result;
	public static LPSolution solverResult;

	PreciseLPCalc() {
	}

	public static PreciseLPSolution optimizeMin(final Value[][] matrix, final Value[] b, final Value[] c) throws MosekException {
		PreciseLPSolution result;
		solverResult = LinearProgrammingEq.optimizeMin(matrix, b, c);
		result = calcResult(solverResult.basisIndexes, matrix, b, c, -1);

		return result;
	}

	/**
	 * Maximalizalasa.
	 * 
	 */
	public static PreciseLPSolution optimizeMax(final Value[][] matrix, final Value[] b, final Value[] c) throws MosekException {
		PreciseLPSolution result;
		solverResult = LinearProgrammingEq.optimizeMax(matrix, b, c);
		result = calcResult(solverResult.basisIndexes, matrix, b, c, 1);

		return result;
	}

	/*
	 * final int sense is -1 in case of min problem
	 */
	private static PreciseLPSolution calcResult(final int[] basisIndexes, final Value[][] M, final Value[] b, final Value[] c, final int sense) {

		Class<? extends Comparable<?>> valueType = b[0].getType();

		PreciseLPSolution result = new PreciseLPSolution();
		// result.objectiveValue????
		result.basisIndexes = basisIndexes;
		result.dualSlackInfeasIndex = -1;
		result.primalNonnegInfeasIndex = -1;
		result.primalSlackInfeasIndex = -1;
		result.primalNonnegInfeas = Value.zero(valueType);
		result.primalSlackInfeas = Value.zero(valueType);
		result.dualSlackInfeas = Value.zero(valueType);

		int row = M.length;
		int col = M[0].length;

		/* basis matrix and obj. coefficients */
		Value[][] basisMatrix = new Value[row][row];
		Value[] cBasis = new Value[row];

		for (int j = 0; j < row; j++) {
			if (basisIndexes[j] < row) {
				for (int i = 0; i < row; i++) {
					basisMatrix[i][j] = Value.zero(valueType);
				}
				basisMatrix[basisIndexes[j]][j] = Value.one(valueType);
				cBasis[j] = Value.zero(valueType);
			} else {
				for (int i = 0; i < row; i++) {
					basisMatrix[i][j] = M[i][basisIndexes[j] - row];
				}
				cBasis[j] = c[basisIndexes[j] - row];
			}

		}

		/* primal infeasibilities */
		Value[] xBasis = multiply(inverse(basisMatrix), b);
		result.objectiveValue = Value.zero(valueType);
		for (int i = 0; i < row; i++) {
			result.objectiveValue = result.objectiveValue.add(cBasis[i].multiply(xBasis[i]));
		}

		result.x = new Value[col];
		for (int j = 0; j < col; j++) {
			result.x[j] = Value.zero(valueType);
		}

		for (int j = 0; j < row; j++) {
			if (basisIndexes[j] < row) {

				Value abs = xBasis[j];
				if (abs.compareTo(Value.zero(valueType)) < 0) {
					abs = abs.negate();
				}

				if (abs.compareTo(result.primalSlackInfeas) > 0) {
					result.primalSlackInfeas = abs;
					result.primalSlackInfeasIndex = basisIndexes[j];
				}
			} else {
				result.x[basisIndexes[j] - row] = xBasis[j];
				if (xBasis[j].compareTo(result.primalNonnegInfeas) < 0) {
					result.primalNonnegInfeas = xBasis[j];
					result.primalNonnegInfeasIndex = basisIndexes[j] - row;
				}
			}

		}

		/* dual feasibilities */
		Value[] yDual = multiply(transpose(inverse(basisMatrix)), cBasis);
		Value[] slackDual = multiply(transpose(M), yDual);
		for (int j = 0; j < col; j++) {
			if (sense == 1) {
				// if
				// ((slackDual.get(j).subtract(c.get(j))).compareTo(result.dualSlackInfeas)
				// < 0) {
				if (slackDual[j].subtract(c[j]).compareTo(result.dualSlackInfeas) < 0) {
					result.dualSlackInfeas = slackDual[j].subtract(c[j]);
					result.dualSlackInfeasIndex = j;
				}
			} else {
				if (slackDual[j].subtract(c[j]).compareTo(result.dualSlackInfeas) > 0) {
					result.dualSlackInfeas = slackDual[j].subtract(c[j]);
					result.dualSlackInfeasIndex = j;
				}
			}
		}

		return result;
	}
}
