package hu.nsmdmp.polynomialmatrixfactory;

import static hu.nsmdmp.operations.Operations.operation;
import static hu.nsmdmp.tools.TotalOrder.generateTotalOrderOfMomentMembers;
import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.operations.IOperation;
import hu.nsmdmp.tools.SetVariationIterator;

import java.util.List;

abstract class AbstractPolynomialMatrix<T> {

	protected final Class<T> valueType;
	protected final IOperation<T> op;

	protected AbstractPolynomialMatrix(final Class<T> valueType) {
		this.valueType = valueType;
		this.op = operation(valueType);
	}

	Matrix<T> create(final T[][] set, final int maxOrder) {

		int size = set.length;
		SetVariationIterator<T> itV = new SetVariationIterator<T>(set);
		List<int[]> exponents = generateTotalOrderOfMomentMembers(maxOrder, size);

		int row = exponents.size();
		int column = itV.numberOfVariation;
		Matrix<T> M = new Matrix<T>(row, column, valueType);

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

		int s = variation.length;
		T element = op.one();

		for (int k = 0; k < s; k++) {
			element = op.multiply(element, getPolynomialValue(exponents[k], variation[k]));
		}

		return element;
	}
}
