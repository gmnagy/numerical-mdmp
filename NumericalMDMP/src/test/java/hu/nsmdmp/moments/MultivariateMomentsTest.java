package hu.nsmdmp.moments;

import static hu.nsmdmp.moments.MultivariateMoments.createBinomialMoments;
import static org.junit.Assert.assertArrayEquals;
import hu.nsmdmp.utils.IOFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MultivariateMomentsTest {

	/**
	 * n=4. m=2. Dimensional = 1.
	 * 
	 */
	@Test
	public void testCreateBinomialMoments1() throws IOException {
		Double[] probabilities = IOFile.read("prob.txt", Double.class);

		List<Moment<Double>> binomMoms = createBinomialMoments(probabilities, 4, 2, 1, 2);

		List<Moment<Double>> expected = new ArrayList<Moment<Double>>();
		expected.add(new Moment<Double>(new int[] { 0 }, 1.0));
		expected.add(new Moment<Double>(new int[] { 1 }, 1.059));
		expected.add(new Moment<Double>(new int[] { 2 }, 1.271));

		assertArrayEquals(expected.toArray(), binomMoms.toArray());
	}
}
