package hu.nsmdmp.tasks;

import hu.nsmdmp.moments.Moment;
import hu.nsmdmp.moments.MultivariateMoments;
import hu.nsmdmp.utils.IOFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apfloat.Apfloat;
import org.junit.Test;

public class ContinuousDistributions {

	private static final String PATH = "results/mngteszt/";

	@Test
	public void testMNG6_5() throws IOException {
		Apfloat[] probabilities = IOFile.read(PATH + "mng6_5");

		//  n=6. m=3. Dimensional = 2.
		List<Moment> binomMoms = MultivariateMoments.createBinomialMoments(probabilities, 6, 3, 2, 3);
		Collection<Moment> powerMoms = MultivariateMoments.convertBinomMomToPowerMom(binomMoms);

		for (Moment moment : powerMoms) {
			System.out.println(moment);
		}
	}
}
