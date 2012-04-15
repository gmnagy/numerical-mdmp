package hu.nsmdmp.matrixfactory;

import hu.nsmdmp.matrix.Matrix;
import hu.nsmdmp.utils.ApfloatUtils;
import hu.nsmdmp.utils.Converters;

import java.util.Arrays;
import java.util.Collection;

import org.apfloat.Apfloat;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class ChebyshevTMatrixTest {

	private static final Apfloat ZERO = ApfloatUtils.ZERO;
	private static final Apfloat ONE = ApfloatUtils.ONE;
	private static final Apfloat TWO = ApfloatUtils.valueOf(2);
	private static final Apfloat THREE = ApfloatUtils.valueOf(3);
	private static final Apfloat SEVEN = ApfloatUtils.valueOf(7);
	private static final Apfloat NINE = ApfloatUtils.valueOf(9);

	private final int moment;

	private final double[][] vectorSet;

	private final Apfloat[][] expectedMatrix;

	public ChebyshevTMatrixTest(int moment, double[][] vectorSet, Apfloat[][] expectedMatrix) {
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
	private static Apfloat[][] output1() {
		return new Apfloat[][] {
				{ ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE }, //
				{ ONE.negate(), ONE.divide(THREE).negate(), ONE.divide(THREE), ONE, ONE.negate(), ONE.divide(THREE).negate(), ONE.divide(THREE), ONE, ONE.negate(), ONE.divide(THREE).negate(),
						ONE.divide(THREE), ONE, ONE.negate(), ONE.divide(THREE).negate(), ONE.divide(THREE), ONE },
				{ ONE.negate(), ONE.negate(), ONE.negate(), ONE.negate(), ONE.divide(THREE).negate(), ONE.divide(THREE).negate(), ONE.divide(THREE).negate(), ONE.divide(THREE).negate(),
						ONE.divide(THREE), ONE.divide(THREE), ONE.divide(THREE), ONE.divide(THREE), ONE, ONE, ONE, ONE } };
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
	private static Apfloat[][] output2() {
		return new Apfloat[][] {
				{ ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE },
				{ ONE.negate(), ONE.divide(THREE).negate(), ONE.divide(THREE), ONE, ONE.negate(), ONE.divide(THREE).negate(), ONE.divide(THREE), ONE, ONE.negate(), ONE.divide(THREE).negate(),
						ONE.divide(THREE), ONE, ONE.negate(), ONE.divide(THREE).negate(), ONE.divide(THREE), ONE },
				{ ONE, SEVEN.divide(NINE).negate(), SEVEN.divide(NINE).negate(), ONE, ONE, SEVEN.divide(NINE).negate(), SEVEN.divide(NINE).negate(), ONE, ONE, SEVEN.divide(NINE).negate(),
						SEVEN.divide(NINE).negate(), ONE, ONE, SEVEN.divide(NINE).negate(), SEVEN.divide(NINE).negate(), ONE },
				{ ONE.negate(), ONE.negate(), ONE.negate(), ONE.negate(), ONE.divide(THREE).negate(), ONE.divide(THREE).negate(), ONE.divide(THREE).negate(), ONE.divide(THREE).negate(),
						ONE.divide(THREE), ONE.divide(THREE), ONE.divide(THREE), ONE.divide(THREE), ONE, ONE, ONE, ONE },
				{ ONE, ONE.divide(THREE), ONE.divide(THREE).negate(), ONE.negate(), ONE.divide(THREE), ONE.divide(NINE), ONE.divide(NINE).negate(), ONE.divide(THREE).negate(),
						ONE.divide(THREE).negate(), ONE.divide(NINE).negate(), ONE.divide(NINE), ONE.divide(THREE), ONE.negate(), ONE.divide(THREE).negate(), ONE.divide(THREE), ONE },
				{ ONE, ONE, ONE, ONE, SEVEN.divide(NINE).negate(), SEVEN.divide(NINE).negate(), SEVEN.divide(NINE).negate(), SEVEN.divide(NINE).negate(), SEVEN.divide(NINE).negate(),
						SEVEN.divide(NINE).negate(), SEVEN.divide(NINE).negate(), SEVEN.divide(NINE).negate(), ONE, ONE, ONE, ONE } };
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
	private static Apfloat[][] output3() {
		return new Apfloat[][] {
				{ ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE,
						ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE,
						ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE,
						ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE },
				{ ONE.negate(), ONE.divide(TWO).negate(), ZERO, ONE.divide(TWO), ONE, ONE.negate(), ONE.divide(TWO).negate(), ZERO, ONE.divide(TWO), ONE, ONE.negate(), ONE.divide(TWO).negate(), ZERO,
						ONE.divide(TWO), ONE, ONE.negate(), ONE.divide(TWO).negate(), ZERO, ONE.divide(TWO), ONE, ONE.negate(), ONE.divide(TWO).negate(), ZERO, ONE.divide(TWO), ONE, ONE.negate(),
						ONE.divide(TWO).negate(), ZERO, ONE.divide(TWO), ONE, ONE.negate(), ONE.divide(TWO).negate(), ZERO, ONE.divide(TWO), ONE, ONE.negate(), ONE.divide(TWO).negate(), ZERO,
						ONE.divide(TWO), ONE, ONE.negate(), ONE.divide(TWO).negate(), ZERO, ONE.divide(TWO), ONE, ONE.negate(), ONE.divide(TWO).negate(), ZERO, ONE.divide(TWO), ONE, ONE.negate(),
						ONE.divide(TWO).negate(), ZERO, ONE.divide(TWO), ONE, ONE.negate(), ONE.divide(TWO).negate(), ZERO, ONE.divide(TWO), ONE, ONE.negate(), ONE.divide(TWO).negate(), ZERO,
						ONE.divide(TWO), ONE, ONE.negate(), ONE.divide(TWO).negate(), ZERO, ONE.divide(TWO), ONE, ONE.negate(), ONE.divide(TWO).negate(), ZERO, ONE.divide(TWO), ONE, ONE.negate(),
						ONE.divide(TWO).negate(), ZERO, ONE.divide(TWO), ONE, ONE.negate(), ONE.divide(TWO).negate(), ZERO, ONE.divide(TWO), ONE, ONE.negate(), ONE.divide(TWO).negate(), ZERO,
						ONE.divide(TWO), ONE, ONE.negate(), ONE.divide(TWO).negate(), ZERO, ONE.divide(TWO), ONE, ONE.negate(), ONE.divide(TWO).negate(), ZERO, ONE.divide(TWO), ONE, ONE.negate(),
						ONE.divide(TWO).negate(), ZERO, ONE.divide(TWO), ONE, ONE.negate(), ONE.divide(TWO).negate(), ZERO, ONE.divide(TWO), ONE, ONE.negate(), ONE.divide(TWO).negate(), ZERO,
						ONE.divide(TWO), ONE, ONE.negate(), ONE.divide(TWO).negate(), ZERO, ONE.divide(TWO), ONE, ONE.negate(), ONE.divide(TWO).negate(), ZERO, ONE.divide(TWO), ONE },
				{ ONE.negate(), ONE.negate(), ONE.negate(), ONE.negate(), ONE.negate(), ONE.divide(TWO).negate(), ONE.divide(TWO).negate(), ONE.divide(TWO).negate(), ONE.divide(TWO).negate(),
						ONE.divide(TWO).negate(), ZERO, ZERO, ZERO, ZERO, ZERO, ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO), ONE, ONE, ONE, ONE, ONE,
						ONE.negate(), ONE.negate(), ONE.negate(), ONE.negate(), ONE.negate(), ONE.divide(TWO).negate(), ONE.divide(TWO).negate(), ONE.divide(TWO).negate(), ONE.divide(TWO).negate(),
						ONE.divide(TWO).negate(), ZERO, ZERO, ZERO, ZERO, ZERO, ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO), ONE, ONE, ONE, ONE, ONE,
						ONE.negate(), ONE.negate(), ONE.negate(), ONE.negate(), ONE.negate(), ONE.divide(TWO).negate(), ONE.divide(TWO).negate(), ONE.divide(TWO).negate(), ONE.divide(TWO).negate(),
						ONE.divide(TWO).negate(), ZERO, ZERO, ZERO, ZERO, ZERO, ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO), ONE, ONE, ONE, ONE, ONE,
						ONE.negate(), ONE.negate(), ONE.negate(), ONE.negate(), ONE.negate(), ONE.divide(TWO).negate(), ONE.divide(TWO).negate(), ONE.divide(TWO).negate(), ONE.divide(TWO).negate(),
						ONE.divide(TWO).negate(), ZERO, ZERO, ZERO, ZERO, ZERO, ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO), ONE, ONE, ONE, ONE, ONE,
						ONE.negate(), ONE.negate(), ONE.negate(), ONE.negate(), ONE.negate(), ONE.divide(TWO).negate(), ONE.divide(TWO).negate(), ONE.divide(TWO).negate(), ONE.divide(TWO).negate(),
						ONE.divide(TWO).negate(), ZERO, ZERO, ZERO, ZERO, ZERO, ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO), ONE, ONE, ONE, ONE, ONE },
				{ ONE.negate(), ONE.negate(), ONE.negate(), ONE.negate(), ONE.negate(), ONE.negate(), ONE.negate(), ONE.negate(), ONE.negate(), ONE.negate(), ONE.negate(), ONE.negate(), ONE.negate(),
						ONE.negate(), ONE.negate(), ONE.negate(), ONE.negate(), ONE.negate(), ONE.negate(), ONE.negate(), ONE.negate(), ONE.negate(), ONE.negate(), ONE.negate(), ONE.negate(),
						ONE.divide(TWO).negate(), ONE.divide(TWO).negate(), ONE.divide(TWO).negate(), ONE.divide(TWO).negate(), ONE.divide(TWO).negate(), ONE.divide(TWO).negate(),
						ONE.divide(TWO).negate(), ONE.divide(TWO).negate(), ONE.divide(TWO).negate(), ONE.divide(TWO).negate(), ONE.divide(TWO).negate(), ONE.divide(TWO).negate(),
						ONE.divide(TWO).negate(), ONE.divide(TWO).negate(), ONE.divide(TWO).negate(), ONE.divide(TWO).negate(), ONE.divide(TWO).negate(), ONE.divide(TWO).negate(),
						ONE.divide(TWO).negate(), ONE.divide(TWO).negate(), ONE.divide(TWO).negate(), ONE.divide(TWO).negate(), ONE.divide(TWO).negate(), ONE.divide(TWO).negate(),
						ONE.divide(TWO).negate(), ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO, ZERO,
						ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO),
						ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO),
						ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO), ONE.divide(TWO), ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE, ONE,
						ONE, ONE, ONE, ONE, ONE, ONE, ONE } };
	}
}
