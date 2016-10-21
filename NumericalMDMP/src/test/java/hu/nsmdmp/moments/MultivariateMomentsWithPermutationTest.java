package hu.nsmdmp.moments;

import static hu.nsmdmp.moments.MultivariateMoments.createBinomialMoments;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import hu.nsmdmp.utils.IOFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class MultivariateMomentsWithPermutationTest {

	/**
	 * n=4. m=2. Dimensional = 1.
	 * 
	 */
	@Test
	public void testCreateBinomialMoments1() throws IOException {
		Double[] probabilities = IOFile.read("prob.txt", Double.class);

		List<Moment<Double>> binomMoms = createBinomialMoments(probabilities, 4, new int[] { 2, 1, 0, 3 }, 2, 1, 2);
//		List<Moment<Double>> binomMoms = createBinomialMoments(probabilities, 4, new int[] { 1, 0, 2, 3 }, 2, 1, 2);

		List<Moment<Double>> expected = new ArrayList<Moment<Double>>();
		expected.add(new Moment<Double>(new int[] { 0 }, 1.0));
		expected.add(new Moment<Double>(new int[] { 1 }, 1.059));
		expected.add(new Moment<Double>(new int[] { 2 }, 1.271));

		assertArrayEquals(expected.toArray(), binomMoms.toArray());
	}

	/**
	 * n=4. m=2. Dimensional = 2.
	 * 
	 */
	@Test
	public void testCreateBinomialMoments2a() throws IOException {
		Double[] probabilities = IOFile.read("prob.txt", Double.class);

		List<Moment<Double>> binomMoms = createBinomialMoments(probabilities, 4, 2, 2, 2);

		List<Moment<Double>> expected = new ArrayList<Moment<Double>>();
		expected.add(new Moment<Double>(new int[] { 0, 0 }, 1.0));
		expected.add(new Moment<Double>(new int[] { 1, 0 }, 0.597));
		expected.add(new Moment<Double>(new int[] { 2, 0 }, 0.224));
		expected.add(new Moment<Double>(new int[] { 0, 1 }, 0.462));
		expected.add(new Moment<Double>(new int[] { 1, 1 }, 0.897));
		expected.add(new Moment<Double>(new int[] { 0, 2 }, 0.15));

		int i = 0;
		for (Moment<Double> moment : expected) {
			assertEquals("row = " + i + ": ", moment.moment, binomMoms.get(i).moment, 0.00001);
			i++;
		}
	}

	/**
	 * n=4. m=2. Dimensional = 2.
	 * 
	 */
	@Test
	public void testCreateBinomialMoments2b() throws IOException {
		Double[] probabilities = IOFile.read("prob.txt", Double.class);

		List<Moment<Double>> binomMoms = createBinomialMoments(probabilities, 4, new int[] { 2, 1, 0, 3 }, 2, 2, 2);

		List<Moment<Double>> expected = new ArrayList<Moment<Double>>();
		expected.add(new Moment<Double>(new int[] { 0, 0 }, 1.0));
		expected.add(new Moment<Double>(new int[] { 1, 0 }, 0.436));
		expected.add(new Moment<Double>(new int[] { 2, 0 }, 0.246));
		expected.add(new Moment<Double>(new int[] { 0, 1 }, 0.623));
		expected.add(new Moment<Double>(new int[] { 1, 1 }, 0.864));
		expected.add(new Moment<Double>(new int[] { 0, 2 }, 0.161));

		int i = 0;
		for (Moment<Double> moment : expected) {
			assertEquals("row = " + i + ": ", moment.moment, binomMoms.get(i).moment, 0.00001);
			i++;
		}
	}

	/**
	 * n=6. m=3. Dimensional = 2.
	 * 
	 */
	@Test
	public void testCreateBinomialMoments3() throws IOException {
		Double[] probabilities = IOFile.read("prob.txt", Double.class);

		List<Moment<Double>> binomMoms = createBinomialMoments(probabilities, 6, 3, 2, 3);

		List<Moment<Double>> expected = new ArrayList<Moment<Double>>();
		expected.add(new Moment<Double>(new int[] { 0, 0 }, 1.0));
		expected.add(new Moment<Double>(new int[] { 1, 0 }, 0.754));
		expected.add(new Moment<Double>(new int[] { 2, 0 }, 0.62));
		expected.add(new Moment<Double>(new int[] { 3, 0 }, 0.157));
		expected.add(new Moment<Double>(new int[] { 0, 1 }, 0.774));
		expected.add(new Moment<Double>(new int[] { 1, 1 }, 1.922));
		expected.add(new Moment<Double>(new int[] { 2, 1 }, 0.986));
		expected.add(new Moment<Double>(new int[] { 0, 2 }, 0.457));
		expected.add(new Moment<Double>(new int[] { 1, 2 }, 0.849));
		expected.add(new Moment<Double>(new int[] { 0, 3 }, 0.09));

		int i = 0;
		for (Moment<Double> moment : expected) {
			assertEquals("row = " + i + ": ", moment.moment, binomMoms.get(i).moment, 0.00001);
			i++;
		}
	}

	/**
	 * n=6. m=2. Dimensional = 2.
	 * 
	 */
	@Test
	public void testCreateBinomialMoments4() throws IOException {
		Double[] probabilities = IOFile.read("prob.txt", Double.class);

		List<Moment<Double>> binomMoms = createBinomialMoments(probabilities, 6, 2, 2, 2);

		List<Moment<Double>> expected = new ArrayList<Moment<Double>>();
		expected.add(new Moment<Double>(new int[] { 0, 0 }, 1.0));
		expected.add(new Moment<Double>(new int[] { 1, 0 }, 0.597));
		expected.add(new Moment<Double>(new int[] { 2, 0 }, 0.161));
		expected.add(new Moment<Double>(new int[] { 0, 1 }, 0.931));
		expected.add(new Moment<Double>(new int[] { 1, 1 }, 1.695));
		expected.add(new Moment<Double>(new int[] { 0, 2 }, 1.143));

		int i = 0;
		for (Moment<Double> moment : expected) {
			assertEquals("row = " + i + ": ", moment.moment, binomMoms.get(i).moment, 0.00001);
			i++;
		}
	}

	/**
	 * n=6. m=3. Dimensional = 2.
	 * 
	 */
	@Test
	public void testCreatePowerMoments1() throws IOException {
		Double[] probabilities = IOFile.read("prob.txt", Double.class);

		List<Moment<Double>> binomMoms = createBinomialMoments(probabilities, 6, 3, 2, 3);
		Collection<Moment<Double>> powerMoms = MultivariateMoments.convertBinomMomToPowerMom(binomMoms);

		List<Moment<Double>> expected = new ArrayList<Moment<Double>>();
		expected.add(new Moment<Double>(new int[] { 0, 0 }, 1.0));
		expected.add(new Moment<Double>(new int[] { 1, 0 }, 0.754));
		expected.add(new Moment<Double>(new int[] { 2, 0 }, 1.994));
		expected.add(new Moment<Double>(new int[] { 3, 0 }, 5.416));
		expected.add(new Moment<Double>(new int[] { 0, 1 }, 0.774));
		expected.add(new Moment<Double>(new int[] { 1, 1 }, 1.922));
		expected.add(new Moment<Double>(new int[] { 2, 1 }, 3.894));
		expected.add(new Moment<Double>(new int[] { 0, 2 }, 1.688));
		expected.add(new Moment<Double>(new int[] { 1, 2 }, 3.62));
		expected.add(new Moment<Double>(new int[] { 0, 3 }, 4.056));

		Iterator<Moment<Double>> it = powerMoms.iterator();
		int i = 0;
		for (Moment<Double> moment : expected) {
			assertEquals("row = " + i + ": ", moment.moment, it.next().moment, 0.00001);
			i++;
		}
	}
}
