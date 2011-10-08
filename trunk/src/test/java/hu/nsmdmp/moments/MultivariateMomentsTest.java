package hu.nsmdmp.moments;

import hu.nsmdmp.matrix.MatrixUtils;
import hu.nsmdmp.utils.Converters;
import hu.nsmdmp.utils.IOFile;

import java.io.IOException;

import org.apfloat.Apfloat;
import org.junit.Assert;
import org.junit.Test;

public class MultivariateMomentsTest {

	/**
	 * n=4. m=2. Dimensional = 1.
	 * 
	 */
	@Test
	public void testCreateBinomialMoments1() throws IOException {
		Apfloat[] probabilities = IOFile.read("prob.txt");

		Apfloat[] binomMoms = MultivariateMoments.createBinomialMoments(probabilities, 4, 2, 1);
		Apfloat[] expected = Converters.convert(new double[] { 1, 1.059, 1.271 });

		if (!MatrixUtils.equals(expected, binomMoms)) {
			System.out.println(MatrixUtils.print(expected));
			System.err.println(MatrixUtils.print(binomMoms));

			Assert.assertTrue(false);
		}
	}

	/**
	 * n=4. m=2. Dimensional = 2.
	 * 
	 */
	@Test
	public void testCreateBinomialMoments2() throws IOException {
		Apfloat[] probabilities = IOFile.read("prob.txt");

		Apfloat[] binomMoms = MultivariateMoments.createBinomialMoments(probabilities, 4, 2, 2);
		Apfloat[] expected = Converters.convert(new double[] { 1, 0.597, 0.224, 0.462, 0.897, 0.15 });

		if (!MatrixUtils.equals(expected, binomMoms)) {
			System.out.println(MatrixUtils.print(expected));
			System.err.println(MatrixUtils.print(binomMoms));

			Assert.assertTrue(false);
		}
	}

	/**
	 * n=6. m=3. Dimensional = 2.
	 * 
	 */
	@Test
	public void testCreateBinomialMoments3() throws IOException {
		Apfloat[] probabilities = IOFile.read("prob.txt");

		Apfloat[] binomMoms = MultivariateMoments.createBinomialMoments(probabilities, 6, 3, 2);
		Apfloat[] expected = Converters.convert(new double[] { 1, 0.754, 0.62, 0.157, 0.774, 1.922, 0.986, 0.457, 0.849, 0.09 });

		if (!MatrixUtils.equals(expected, binomMoms)) {
			System.out.println(MatrixUtils.print(expected));
			System.err.println(MatrixUtils.print(binomMoms));

			Assert.assertTrue(false);
		}
	}
}
