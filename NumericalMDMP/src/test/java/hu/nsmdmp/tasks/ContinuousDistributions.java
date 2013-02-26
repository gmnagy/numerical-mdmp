package hu.nsmdmp.tasks;

import static hu.nsmdmp.moments.MultivariateMoments.convertBinomMomToPowerMom;
import static hu.nsmdmp.moments.MultivariateMoments.createBinomialMoments;
import static hu.nsmdmp.specialvectors.Discrete.discreteVector;
import static hu.nsmdmp.tasks.TaskUtils.getMaxCumProbMatrixElement;
import static hu.nsmdmp.tools.SetNormalization.normalize;
import static hu.nsmdmp.tools.SetVariationIterator.getNumberOfVariation;
import hu.nsmdmp.moments.Moment;
import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.numerics.matrix.Vector;
import hu.nsmdmp.polynomialmatrixfactory.ChebyshevUMatrix;
import hu.nsmdmp.tools.SubSequencesGenerator;
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
		Apfloat[] probabilities = IOFile.read(new File(getClass().getResource("mng11_5").toURI()), Apfloat.class);
		int n = 11;
		int m = 3;
		int dim = 3;
		int l = 3;

		List<Moment<Apfloat>> binomMoms = createBinomialMoments(probabilities, n, m, dim, l);
		Collection<Moment<Apfloat>> powerMoms = convertBinomMomToPowerMom(binomMoms);

		Apfloat[][] vectorSet = SubSequencesGenerator.getSubSequences2(n + 3, l + 1, dim, Apfloat.class);
		System.out.println(Utils.toString(vectorSet));
//		System.out.println(Utils.toString(normalize(subSequences)));

//		Apfloat[][] vectorSet = createVectorSet(5, 3, Apfloat.class);
//		System.out.println(Utils.toString(vectorSet));

		// normailzed ChebyshevU vector.
		Vector<Apfloat> nChebyUV = TaskUtils.createNormChebyshevUVector(m, toVector(powerMoms), vectorSet);
//		Vector<Apfloat> nChebyTV = TaskUtils.createNormChebyshevTVector(m, toVector(powerMoms), vectorSet);
//		System.out.println(nChebyUV.getColumnDimension());

		// ChebyshevU matrix.
		Matrix<Apfloat> chebU = ChebyshevUMatrix.generateApfloatChebyshevUMatrix(normalize(vectorSet), m);
//		Matrix<Apfloat> chebT = ChebyshevTMatrix.generateApfloatChebyshevTMatrix(normalize(vectorSet), m);
//		System.out.println(chebU.getRowDimension() + "  " + chebU.getColumnDimension());

		Vector<Apfloat> f = discreteVector(getNumberOfVariation(vectorSet), 1, new Apfloat(0), new Apfloat(1));
//		System.out.println(f);

		double minU = TaskUtils.getMinCumProbMatrixElement(chebU, nChebyUV, f);
		double maxU = getMaxCumProbMatrixElement(chebU, nChebyUV, f);
//		System.out.println(String.format("maxU: %s\tminU: %s", 1 - maxU, 1 - minU));

//		double minT = getMinCumProbMatrixElement(chebT, nChebyTV, f);
//		double maxT = getMaxCumProbMatrixElement(chebT, nChebyTV, f);
//		System.out.println(String.format("maxT: %s\tminT: %s", 1 - maxT, 1 - minT));
	}

	Vector<Apfloat> toVector(Collection<Moment<Apfloat>> moments) {

		Vector<Apfloat> v = new Vector<Apfloat>(moments.size(), Apfloat.class);

		int i = 0;
		for (Moment<Apfloat> moment : moments) {
			v.set(i, moment.moment);

			i++;
		}

		return v;
	}
}
