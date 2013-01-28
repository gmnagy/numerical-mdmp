package hu.nsmdmp.polynomialmatrixfactory;

import static hu.nsmdmp.numerics.matrix.math.MatrixMath.multiply;
import static hu.nsmdmp.polynomialmatrixfactory.ChebyshevTMatrix.generateDoubleChebyshevTMatrix;
import static hu.nsmdmp.polynomialmatrixfactory.MonomialMatrix.generateDoubleMonomialMatrix;
import static hu.nsmdmp.polynomialmatrixfactory.MonomialToChebTMatrix.generateMonomialChebTTransformationMatrix;
import static hu.nsmdmp.tools.SetNormalization.normalize;
import static hu.nsmdmp.utils.Converters.primitiveToDouble;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import hu.nsmdmp.numerics.matrix.Matrix;

import org.junit.Test;

public class DoubleMonomialToChebTMatrixTest {

	@Test
	public void univariate1() {

		int maxOrder = 3;
		double[][] set = { { 0, 1, 2, 3 } };

		Double[][] normalizedM = normalize(primitiveToDouble(set));

		Matrix<Double> monomial = generateDoubleMonomialMatrix(normalizedM, maxOrder);
		Matrix<Double> chebyshev = generateDoubleChebyshevTMatrix(normalizedM, maxOrder);

		Matrix<Double> T = generateMonomialChebTTransformationMatrix(maxOrder, set.length, Double.class);
		System.out.println(T);

		Matrix<Double> newChebyshev = multiply(T, monomial);

		assertEquals(chebyshev, newChebyshev);
	}

	@Test
	public void multivariate1() {

		int maxOrder = 3;
		double[][] set = { { 0, 1, 2 }, { 0, 1, 2 } };

		Double[][] normalizedM = normalize(primitiveToDouble(set));

		Matrix<Double> monomial = generateDoubleMonomialMatrix(normalizedM, maxOrder);
		Matrix<Double> chebyshev = generateDoubleChebyshevTMatrix(normalizedM, maxOrder);

		Matrix<Double> T = generateMonomialChebTTransformationMatrix(maxOrder, set.length, Double.class);

		Matrix<Double> newChebyshev = multiply(T, monomial);
		System.out.println(chebyshev);

		for (int i = 0; i < newChebyshev.getRowDimension(); i++) {
			assertArrayEquals("row = " + i + ": ", newChebyshev.getRow(i), chebyshev.getRow(i));
		}
	}

}
