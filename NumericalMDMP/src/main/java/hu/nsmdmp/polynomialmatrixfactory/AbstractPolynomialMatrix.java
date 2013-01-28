package hu.nsmdmp.polynomialmatrixfactory;

import static hu.nsmdmp.numerics.matrix.operations.OperationFactory.selectOperation;
import static hu.nsmdmp.tools.TotalOrder.generateTotalOrderOfMomentMembers;
import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.numerics.matrix.operations.IOperations;
import hu.nsmdmp.tools.SetVariationIterator;

import java.util.List;

abstract class AbstractPolynomialMatrix<T> {

	Matrix<T> create(final T[][] set, final int maxOrder) {

		int size = set.length;
		SetVariationIterator<T> itV = new SetVariationIterator<T>(set);
		List<int[]> exponents = generateTotalOrderOfMomentMembers(maxOrder, size);

		int row = exponents.size();
		int column = itV.numberOfVariation;
		Matrix<T> M = new Matrix<T>(row, column);

		int j = 0;
		while (itV.hasNext()) {
			T[] variation = itV.next();

			for (int i = 0; i < row; i++)
				M.set(i, j, getMatrixElement(exponents.get(i), variation));

			j++;
		}

		return M;
	}

	protected abstract T getPolynomialValue(final int n, final T value);

	/**
	 * Az <tt>exponents</tt> halmaz k.-ik tagjabol n-ed foku polinomot allitunk elo, majd a
	 * <tt>variation</tt> k.-ik tagjat a valtozo helyere behelyetesitjuk. A kapott ertekeket
	 * osszeszorozuk.
	 * 
	 */
	private T getMatrixElement(final int[] exponents, final T[] variation) {

		IOperations<T> operations = selectOperation(variation);

		int s = variation.length;
		T element = operations.one();

		for (int k = 0; k < s; k++) {
			element = operations.multiply(element, getPolynomialValue(exponents[k], variation[k]));
		}

		return element;
	}
}
