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
import hu.nsmdmp.moments.Moment;
import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.numerics.matrix.Vector;
import hu.nsmdmp.tools.SubSequencesGenerator;
import hu.nsmdmp.utils.IOFile;

import java.io.File;
import java.util.Collection;
import java.util.List;

import org.apfloat.Apfloat;
import org.junit.Test;

public class ContinuousDistributions {

	@Test
	public void testMNG6_5() throws Exception {
		Apfloat[] probabilities = IOFile.read(new File(getClass().getResource("mng21_5").toURI()), Apfloat.class);
		int n = 21;
		int m = 3;
		int dim = 7;
		int l = 3;

		List<Moment<Apfloat>> binomMoms = createBinomialMoments(probabilities, n, m, dim, l);
		Collection<Moment<Apfloat>> powerMoms = convertBinomMomToPowerMom(binomMoms);

		Apfloat[][] vectorSet = SubSequencesGenerator.getSubSequences2(n + 7, l + 1, dim, Apfloat.class);
//		System.out.println(Utils.toString(vectorSet));
//		System.out.println(Utils.toString(normalize(subSequences)));

//		Apfloat[][] vectorSet = createVectorSet(5, 3, Apfloat.class);
//		System.out.println(Utils.toString(vectorSet));

		// normailzed ChebyshevU vector.
		Vector<Apfloat> nChebyUV = createNormChebyshevUVector(m, toVector(powerMoms), vectorSet);
//		System.out.println(nChebyUV.getColumnDimension());

		// ChebyshevU matrix.
		Matrix<Apfloat> chebU = generateApfloatChebyshevUMatrix(normalize(vectorSet), m);
//		System.out.println(chebU.getRowDimension() + "  " + chebU.getColumnDimension());

		Vector<Apfloat> f = discreteVector(getNumberOfVariation(vectorSet), 1, new Apfloat(0), new Apfloat(1));
		System.out.println(f);

		double min = getMinCumProbMatrixElement(chebU, nChebyUV, f);
		double max = getMaxCumProbMatrixElement(chebU, nChebyUV, f);

		System.out.println(String.format("max: %s\tmin: %s", 1 - max, 1 - min));
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
