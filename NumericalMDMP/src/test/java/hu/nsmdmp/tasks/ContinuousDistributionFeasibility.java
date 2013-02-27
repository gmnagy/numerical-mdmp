package hu.nsmdmp.tasks;

import static hu.nsmdmp.moments.MultivariateMoments.convertBinomMomToPowerMom;
import static hu.nsmdmp.moments.MultivariateMoments.createBinomialMoments;
import static hu.nsmdmp.polynomialmatrixfactory.ChebyshevUMatrix.generateApfloatChebyshevUMatrix;
import static hu.nsmdmp.tasks.TaskUtils.getMaxCumProbMatrixElement;
import static hu.nsmdmp.tasks.TaskUtils.getMinCumProbMatrixElement;
import static hu.nsmdmp.tasks.TaskUtils.toVector;
import static hu.nsmdmp.tools.SetNormalization.normalize;
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

public class ContinuousDistributionFeasibility {

	@Test
	public void test() throws Exception {

		String fileName = "mng16_5";
		int n = 16;
		int m = 3;
		int dim = 5;
		int l = 3;

		Apfloat[][] vectorSet = SubSequencesGenerator.getSubSequences2(n + dim, l + 1, dim, Apfloat.class);

		Vector<Apfloat> normChebyUV = normChebyUV(fileName, n, m, dim, l, vectorSet);
		Matrix<Apfloat> chebU = generateApfloatChebyshevUMatrix(normalize(vectorSet), m);
//		System.out.println(chebU);
//		System.out.println();

		for (int i = 0; i < chebU.getRowDimension() - 1; i++) {

			Matrix<Apfloat> sub = chebU.getSubMatrix(0, i);
//			System.out.println(sub);
			Vector<Apfloat> v = normChebyUV.getSubVector(0, i);
//			System.out.println(v);
			Vector<Apfloat> f = new Vector<Apfloat>(chebU.getRow(i + 1));
//			System.out.println(f);

			double min = getMinCumProbMatrixElement(sub, v, f);
			double max = getMaxCumProbMatrixElement(sub, v, f);

			System.out.println(String.format("i:%s    min:%s  -  expected:%s  -  max:%s", i, min, normChebyUV.get(i+1), max));
		}

	}

	private Vector<Apfloat> normChebyUV(String fileName, int n, int m, int dim, int l, Apfloat[][] vectorSet) throws Exception {
		Apfloat[] probabilities = IOFile.read(new File(getClass().getResource(fileName).toURI()), Apfloat.class);

		List<Moment<Apfloat>> binomMoms = createBinomialMoments(probabilities, n, m, dim, l);
		Collection<Moment<Apfloat>> powerMoms = convertBinomMomToPowerMom(binomMoms);

		return TaskUtils.createNormChebyshevUVector(m, toVector(powerMoms), vectorSet);
	}

}
