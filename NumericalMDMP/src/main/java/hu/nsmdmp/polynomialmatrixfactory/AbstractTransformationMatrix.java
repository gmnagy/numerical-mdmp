package hu.nsmdmp.polynomialmatrixfactory;

import static hu.nsmdmp.operations.Operations.operation;
import static hu.nsmdmp.tools.TotalOrder.generateTotalOrderOfMomentMembers;
import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.operations.IOperation;
import hu.nsmdmp.polynomialmatrixfactory.cachedpolynomials.AbstractCachedPolynomials;

import java.util.List;

import org.opensourcephysics.numerics.Polynomial;

public class AbstractTransformationMatrix<T> {

	protected final IOperation<T> op;
	private final AbstractCachedPolynomials cachedPolynomials;

	protected AbstractTransformationMatrix(AbstractCachedPolynomials cachedPolynomials, final Class<T> type) {
		this.cachedPolynomials = cachedPolynomials;
		this.op = operation(type);
	}

	protected Matrix<T> getTransformationMatrix(final int maxOrder, final int dim) {
		List<int[]> exponentsList = generateTotalOrderOfMomentMembers(maxOrder, dim);

		Matrix<T> M = new Matrix<T>(exponentsList.size(), exponentsList.size(), op.getType());

		// rows
		int i = 0;
		for (int[] exponents : exponentsList) {

			int j = 0; // polynoms 
			for (int exp : exponents) {
				Polynomial polynomial = cachedPolynomials.getPolynomial(exp);

				// iterate columns
				int k = 0;
				for (int[] items : exponentsList) {
					T c = op.valueOf(polynomial.coefficient(items[j]));

					T x = M.get(i, k);
					if (null == x) {
						x = op.one();
					}

					x = op.multiply(x, c);
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
