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
public class ChebyshevTMatrixTest {

	private final int moment;

	private final double[][] vectorSet;

	private final double[][] expectedMatrix;

	public ChebyshevTMatrixTest(int moment, double[][] vectorSet, double[][] expectedMatrix) {
		this.moment = moment;
		this.vectorSet = vectorSet;
		this.expectedMatrix = expectedMatrix;
	}

	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { { 1, input1(), output1() }, { 2, input2(), output2() }, { 1, input3(), output3() } };

		return Arrays.asList(data);
	}

	@Test
	public void simpleMatrixTest() {
		Matrix chebT = MatrixFactory.getChebyshevTMatrix(Converters.convert(vectorSet), moment);

		Matrix em = new Matrix(expectedMatrix);

		if (!chebT.equals(em)) {
			System.out.println(em);
			System.err.println(chebT);

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
		return new double[][] {
				{ 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 }, //
				{ -1.0, -(1.0 / 3), 1.0 / 3, 1.0, -1.0, -(1.0 / 3), 1.0 / 3, 1.0, -1.0, -(1.0 / 3), 1.0 / 3, 1.0, -1.0, -(1.0 / 3), 1.0 / 3, 1.0 },
				{ -1.0, -1.0, -1.0, -1.0, -(1.0 / 3), -(1.0 / 3), -(1.0 / 3), -(1.0 / 3), 1.0 / 3, 1.0 / 3, 1.0 / 3, 1.0 / 3, 1.0, 1.0, 1.0, 1.0 } };
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
		return new double[][] { { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 },
				{ -1.0, -(1.0 / 3.0), 1.0 / 3.0, 1.0, -1.0, -(1.0 / 3.0), 1.0 / 3.0, 1.0, -1.0, -(1.0 / 3.0), 1.0 / 3.0, 1.0, -1.0, -(1.0 / 3.0), 1.0 / 3.0, 1.0 },
				{ 1.0, -(7.0 / 9.0), -(7.0 / 9.0), 1.0, 1.0, -(7.0 / 9.0), -(7.0 / 9.0), 1.0, 1.0, -(7.0 / 9.0), -(7.0 / 9.0), 1.0, 1.0, -(7.0 / 9.0), -(7.0 / 9.0), 1.0 },
				{ -1.0, -1.0, -1.0, -1.0, -(1.0 / 3.0), -(1.0 / 3.0), -(1.0 / 3.0), -(1.0 / 3.0), 1.0 / 3.0, 1.0 / 3.0, 1.0 / 3.0, 1.0 / 3.0, 1.0, 1.0, 1.0, 1.0 },
				{ 1.0, 1.0 / 3.0, -(1.0 / 3.0), -1.0, 1.0 / 3.0, 1.0 / 9.0, -(1.0 / 9.0), -(1.0 / 3.0), -(1.0 / 3.0), -(1.0 / 9.0), 1.0 / 9.0, 1.0 / 3.0, -1.0, -(1.0 / 3.0), 1.0 / 3.0, 1.0 },
				{ 1.0, 1.0, 1.0, 1.0, -(7.0 / 9.0), -(7.0 / 9.0), -(7.0 / 9.0), -(7.0 / 9.0), -(7.0 / 9.0), -(7.0 / 9.0), -(7.0 / 9.0), -(7.0 / 9.0), 1.0, 1.0, 1.0, 1.0 } };
	}

	/**
	 * moment = 1
	 */
	private static double[][] input3() {
		return new double[][] { { 0, 1, 2, 3, 4 }, { 0, 1, 2, 3, 4 }, { 0, 1, 2, 3, 4 } };
	}

	/**
	 * input3
	 */
	private static double[][] output3() {
		return new double[][] {
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
						1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
						1, 1, 1, 1, 1, 1, 1 },
				{ -1, -(1.0 / 2.0), 0, 1.0 / 2.0, 1, -1, -(1.0 / 2.0), 0, 1.0 / 2.0, 1, -1, -(1.0 / 2.0), 0, 1.0 / 2.0, 1, -1, -(1.0 / 2.0), 0, 1.0 / 2.0, 1, -1, -(1.0 / 2.0), 0, 1.0 / 2.0, 1, -1,
						-(1.0 / 2.0), 0, 1.0 / 2.0, 1, -1, -(1.0 / 2.0), 0, 1.0 / 2.0, 1, -1, -(1.0 / 2.0), 0, 1.0 / 2.0, 1, -1, -(1.0 / 2.0), 0, 1.0 / 2.0, 1, -1, -(1.0 / 2.0), 0, 1.0 / 2.0, 1, -1,
						-(1.0 / 2.0), 0, 1.0 / 2.0, 1, -1, -(1.0 / 2.0), 0, 1.0 / 2.0, 1, -1, -(1.0 / 2.0), 0, 1.0 / 2.0, 1, -1, -(1.0 / 2.0), 0, 1.0 / 2.0, 1, -1, -(1.0 / 2.0), 0, 1.0 / 2.0, 1, -1,
						-(1.0 / 2.0), 0, 1.0 / 2.0, 1, -1, -(1.0 / 2.0), 0, 1.0 / 2.0, 1, -1, -(1.0 / 2.0), 0, 1.0 / 2.0, 1, -1, -(1.0 / 2.0), 0, 1.0 / 2.0, 1, -1, -(1.0 / 2.0), 0, 1.0 / 2.0, 1, -1,
						-(1.0 / 2.0), 0, 1.0 / 2.0, 1, -1, -(1.0 / 2.0), 0, 1.0 / 2.0, 1, -1, -(1.0 / 2.0), 0, 1.0 / 2.0, 1, -1, -(1.0 / 2.0), 0, 1.0 / 2.0, 1, -1, -(1.0 / 2.0), 0, 1.0 / 2.0, 1 },
				{ -1, -1, -1, -1, -1, -(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0), 0, 0, 0, 0, 0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1, 1, 1, 1, 1, -1,
						-1, -1, -1, -1, -(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0), 0, 0, 0, 0, 0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1, 1, 1, 1, 1, -1,
						-1, -1, -1, -1, -(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0), 0, 0, 0, 0, 0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1, 1, 1, 1, 1, -1,
						-1, -1, -1, -1, -(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0), 0, 0, 0, 0, 0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1, 1, 1, 1, 1, -1,
						-1, -1, -1, -1, -(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0), 0, 0, 0, 0, 0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1, 1, 1, 1, 1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0),
						-(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0),
						-(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0), -(1.0 / 2.0), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						0, 0, 0, 0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0,
						1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1.0 / 2.0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
						1, 1, 1, 1, 1, 1, 1 } };
	}
}