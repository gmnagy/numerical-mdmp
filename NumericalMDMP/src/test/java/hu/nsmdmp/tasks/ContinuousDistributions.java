package hu.nsmdmp.tasks;

import static hu.nsmdmp.moments.MultivariateMoments.convertBinomMomToPowerMom;
import static hu.nsmdmp.moments.MultivariateMoments.createBinomialMoments;
import static hu.nsmdmp.polynomialmatrixfactory.ChebyshevUMatrix.generateApfloatChebyshevUMatrix;
import static hu.nsmdmp.specialvectors.Discrete.discreteVector;
import static hu.nsmdmp.tasks.TaskUtils.createNormChebyshevUVector;
import static hu.nsmdmp.tasks.TaskUtils.getMaxCumProbMatrixElement;
import static hu.nsmdmp.tasks.TaskUtils.getMinCumProbMatrixElement;
import static hu.nsmdmp.tools.SetNormalization.normalize;
import static hu.nsmdmp.tools.SetVariationIterator.getNumberOfVariation;
import static hu.nsmdmp.tools.SubSequencesGenerator.getSubSequences3;
import hu.nsmdmp.moments.Moment;
import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.numerics.matrix.Vector;
import hu.nsmdmp.utils.IOFile;
import hu.nsmdmp.utils.Utils;

import java.io.File;
import java.util.Collection;
import java.util.List;

import org.apfloat.Apfloat;
import org.junit.Test;

public class ContinuousDistributions {

	@Test
	public void testMNG6_5() throws Exception {
		Apfloat[] probabilities = IOFile.read(new File(getClass().getResource("mng6_5").toURI()), Apfloat.class);
		int n = 6;
		int m = 2;
		int dim = 2;
		int l = 3;

		List<Moment<Apfloat>> binomMoms = createBinomialMoments(probabilities, n, m, dim, l);
		Collection<Moment<Apfloat>> powerMoms = convertBinomMomToPowerMom(binomMoms);

		Apfloat[][] subSequences = getSubSequences3(n, l, dim, Apfloat.class);
		System.out.println(Utils.toString(subSequences));
		System.out.println(Utils.toString(normalize(subSequences)));

		// normailzed ChebyshevU vector.
		Vector<Apfloat> nChebyUV = createNormChebyshevUVector(m, toVector(powerMoms), normalize(subSequences));
		System.out.println(nChebyUV);

		// ChebyshevU matrix.
		Matrix<Apfloat> chebU = generateApfloatChebyshevUMatrix(normalize(subSequences), m);
		System.out.println(chebU);

		Vector<Apfloat> f = discreteVector(getNumberOfVariation(subSequences), 1, new Apfloat(0), new Apfloat(1));
		System.out.println(f);

		double min = getMinCumProbMatrixElement(chebU, nChebyUV, f);
		double max = getMaxCumProbMatrixElement(chebU, nChebyUV, f);

		System.out.println(String.format("max: %s\tmin: %s", max, min));
	}

	<T> Vector<T> toVector(Collection<Moment<T>> moments) {

		Vector<T> v = new Vector<T>(moments.size());

		int i = 0;
		for (Moment<T> moment : moments) {
			v.set(i, moment.moment);

			i++;
		}

		return v;
	}
}
