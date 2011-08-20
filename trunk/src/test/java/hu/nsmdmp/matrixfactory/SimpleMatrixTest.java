package hu.nsmdmp.matrixfactory;

import hu.nsmdmp.matrix.Matrix;
import hu.nsmdmp.matrixfactory.MatrixFactory;
import hu.nsmdmp.utils.Converters;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class SimpleMatrixTest {

	private final int moment;

	private final double[][] vectorSet;

	private final double[][] expectedMatrix;

	public SimpleMatrixTest(int moment, double[][] vectorSet, double[][] expectedMatrix) {
		this.moment = moment;
		this.vectorSet = vectorSet;
		this.expectedMatrix = expectedMatrix;
	}

	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { { 1, input1(), output1() }, { 2, input2(), output2() } };

		return Arrays.asList(data);
	}

	@Test
	public void simpleMatrixTest() {
		Matrix sm = MatrixFactory.getSimpleMatrix(Converters.convert(vectorSet), moment);

		Matrix em = new Matrix(expectedMatrix);

		if (!sm.equals(em)) {
			System.out.println(em);
			System.err.println(sm);

			Assert.assertTrue(false);
		}
	}

	/**
	 * moment = 1
	 */
	private static double[][] input1() {
		return new double[][] { { 0, 1, 2, 3 }, { 0, 1, 2, 3 } };
	}

	/**
	 * input1
	 */
	private static double[][] output1() {
		return new double[][] { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, //
				{ 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3 }, //
				{ 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3 } };
	}

	/**
	 * moment = 2
	 */
	private static double[][] input2() {
		return new double[][] { { 0, 1, 2, 3 }, { 0, 1, 2, 3 } };
	}

	/**
	 * input2
	 */
	private static double[][] output2() {
		return new double[][] { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },// 
				{ 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3 }, //
				{ 0, 1, 4, 9, 0, 1, 4, 9, 0, 1, 4, 9, 0, 1, 4, 9 }, //
				{ 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3 }, //
				{ 0, 0, 0, 0, 0, 1, 2, 3, 0, 2, 4, 6, 0, 3, 6, 9 }, //
				{ 0, 0, 0, 0, 1, 1, 1, 1, 4, 4, 4, 4, 9, 9, 9, 9 } };
	}
}
