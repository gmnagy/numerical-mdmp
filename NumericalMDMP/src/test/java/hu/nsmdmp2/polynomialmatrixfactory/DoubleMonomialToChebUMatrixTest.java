package hu.nsmdmp2.polynomialmatrixfactory;

import static hu.nsmdmp2.numerics.matrix.MatrixMath.multiply;
import static hu.nsmdmp2.polynomialmatrixfactory.ChebyshevUMatrix.generateChebyshevUMatrix;
import static hu.nsmdmp2.polynomialmatrixfactory.MonomialMatrix.generateMonomialMatrix;
import static hu.nsmdmp2.polynomialmatrixfactory.MonomialToChebUMatrix.generateMonomialChebUTransformationMatrix;
import static hu.nsmdmp2.tools.SetNormalization.normalize;
import static hu.nsmdmp2.utils.Converters.primitiveToDoubleValue;
import static org.junit.Assert.assertArrayEquals;
import hu.nsmdmp2.numerics.Value;

import org.junit.Test;

public class DoubleMonomialToChebUMatrixTest {

	@Test
	public void univariate1() {

		int maxOrder = 3;
		double[][] set = { { 0, 1, 2, 3 } };

		Value[][] normalizedM = normalize(primitiveToDoubleValue(set));

		Value[][] monomial = generateMonomialMatrix(normalizedM, maxOrder);
		Value[][] chebyshev = generateChebyshevUMatrix(normalizedM, maxOrder);

		Value[][] T = generateMonomialChebUTransformationMatrix(maxOrder, set.length, Double.class);

		Value[][] newChebyshev = multiply(T, monomial);

		int i = 0;
		for (Value[] array : chebyshev) {
			assertArrayEquals("row = " + i + ": ", array, newChebyshev[i]);
			i++;
		}
	}

	@Test
	public void multivariate1() {

		int maxOrder = 3;
		double[][] set = { { 0, 1, 2 }, { 0, 1, 2 } };

		Value[][] normalizedM = normalize(primitiveToDoubleValue(set));

		Value[][] monomial = generateMonomialMatrix(normalizedM, maxOrder);
		Value[][] chebyshev = generateChebyshevUMatrix(normalizedM, maxOrder);

		Value[][] T = generateMonomialChebUTransformationMatrix(maxOrder, set.length, Double.class);

		Value[][] newChebyshev = multiply(T, monomial);

		int i = 0;
		for (Value[] array : chebyshev) {
			assertArrayEquals("row = " + i + ": ", array, newChebyshev[i]);
			i++;
		}
	}
}
