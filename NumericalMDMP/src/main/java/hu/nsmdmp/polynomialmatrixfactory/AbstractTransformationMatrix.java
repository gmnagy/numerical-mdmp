package hu.nsmdmp.polynomialmatrixfactory;

import static hu.nsmdmp.numerics.matrix.operations.OperationFactory.selectOperation;
import static hu.nsmdmp.tools.TotalOrder.generateTotalOrderOfMomentMembers;
import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.numerics.matrix.operations.IOperations;
import hu.nsmdmp.polynomialmatrixfactory.cachedpolynomials.AbstractCachedPolynomials;

import java.util.List;

import org.opensourcephysics.numerics.Polynomial;

public class AbstractTransformationMatrix<T> {

	protected final IOperations<T> operations;
	private final AbstractCachedPolynomials cachedPolynomials;

	protected AbstractTransformationMatrix(AbstractCachedPolynomials cachedPolynomials, final Class<T> type) {
		this.cachedPolynomials = cachedPolynomials;
		this.operations = selectOperation(type);
	}

	protected Matrix<T> getTransformationMatrix(final int maxOrder, final int dim) {
		List<int[]> exponentsList = generateTotalOrderOfMomentMembers(maxOrder, dim);

		Matrix<T> M = new Matrix<T>(exponentsList.size(), exponentsList.size());

		// rows
		int i = 0;
		for (int[] exponents : exponentsList) {

			int j = 0; // polynoms 
			for (int exp : exponents) {
				Polynomial polynomial = cachedPolynomials.getPolynomial(exp);

				// iterate columns
				int k = 0;
				for (int[] items : exponentsList) {
					T c = operations.valueOf(polynomial.coefficient(items[j]));

					T x = M.get(i, k);
					if (null == x) {
						x = operations.one();
					}

					x = operations.multiply(x, c);
					M.set(i, k, x);

					k++;
				}

				j++;
			}

			i++;
		}

		return M;
	}
}
