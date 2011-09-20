package hu.nsmdmp.moments;

import hu.nsmdmp.matrix.MatrixUtils;
import hu.nsmdmp.utils.Converters;
import hu.nsmdmp.utils.IOFile;

import java.io.IOException;

import org.apfloat.Apfloat;
import org.junit.Assert;
import org.junit.Test;

public class UnivariateMomentsTest {

	@Test
	public void testCreateBinomialMoments() throws IOException {
		Apfloat[] probabilities = IOFile.read("prob.txt");

		Apfloat[] binomMoms = UnivariateMoments.createBinomialMoments(probabilities, 6, 3);
		Apfloat[] expected = Converters.convert(new double[] { 1, 1.528, 2.999, 2.082 });

		if (!MatrixUtils.equals(expected, binomMoms)) {
			System.out.println(MatrixUtils.print(expected));
			System.err.println(MatrixUtils.print(binomMoms));

			Assert.assertTrue(false);
		}
	}

	@Test
	public void testCreatePowerMoments() throws IOException {
		Apfloat[] probabilities = IOFile.read("prob.txt");

		Apfloat[] binomMoms = UnivariateMoments.createBinomialMoments(probabilities, 6, 3);
		Apfloat[] powerMoms = UnivariateMoments.convertBinomMomToPowerMom(binomMoms);

		Apfloat[] expected = Converters.convert(new double[] { 1, 1.528, 7.526, 32.014 });

		if (!MatrixUtils.equals(expected, powerMoms)) {
			System.out.println(MatrixUtils.print(expected));
			System.err.println(MatrixUtils.print(powerMoms));

			Assert.assertTrue(false);
		}
	}
}
