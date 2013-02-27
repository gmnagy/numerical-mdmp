package hu.nsmdmp.tasks;

import static hu.nsmdmp.moments.MultivariateMoments.convertBinomMomToPowerMom;
import static hu.nsmdmp.moments.MultivariateMoments.createBinomialMoments;
import static hu.nsmdmp.numerics.matrix.math.MatrixMath.multiply;
import static hu.nsmdmp.polynomialmatrixfactory.MonomialToChebTMatrix.generateMonomialChebTTransformationMatrix;
import static hu.nsmdmp.polynomialmatrixfactory.MonomialToChebUMatrix.generateMonomialChebUTransformationMatrix;
import static hu.nsmdmp.tools.VectorNormalizationWithSet.normailzeByGergo;
import hu.nsmdmp.moments.Moment;
import hu.nsmdmp.mosek.LPSolution;
import hu.nsmdmp.mosek.LinearProgrammingEq;
import hu.nsmdmp.mosek.LinearProgrammingIneq;
import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.numerics.matrix.Vector;
import hu.nsmdmp.utils.IOFile;

import java.io.File;
import java.util.Collection;
import java.util.List;

import mosek.MosekException;

import org.apfloat.Apfloat;

public class TaskUtils {

	public static Vector<Apfloat> normChebyUV(String fileName, int n, int m, int dim, int l, Apfloat[][] vectorSet) throws Exception {

		Vector<Apfloat> powerMomentV = createPowerMoments(fileName, n, m, dim, l);

		return normChebyUV(m, powerMomentV, vectorSet);
	}

	public static Vector<Apfloat> normChebyUV(final int maxOrder, final Vector<Apfloat> powerV, final Apfloat[][] vectorSet) {

		Vector<Apfloat> normPowerMomentV = normailzeByGergo(vectorSet, maxOrder, powerV);
		Matrix<Apfloat> M = generateMonomialChebUTransformationMatrix(maxOrder, vectorSet.length, normPowerMomentV.getValueType());

		return multiply(M, normPowerMomentV);
	}

	public static Vector<Apfloat> normChebyTV(String fileName, int n, int m, int dim, int l, Apfloat[][] vectorSet) throws Exception {

		Vector<Apfloat> powerMomentV = createPowerMoments(fileName, n, m, dim, l);
		Vector<Apfloat> normPowerMomentV = normailzeByGergo(vectorSet, m, powerMomentV);

		Matrix<Apfloat> M = generateMonomialChebTTransformationMatrix(m, vectorSet.length, normPowerMomentV.getValueType());

		return multiply(M, normPowerMomentV);
	}

	public static Vector<Apfloat> normChebyTV(final int maxOrder, final Vector<Apfloat> powerV, final Apfloat[][] vectorSet) {

		Vector<Apfloat> normPowerMomentV = normailzeByGergo(vectorSet, maxOrder, powerV);
		Matrix<Apfloat> M = generateMonomialChebTTransformationMatrix(maxOrder, vectorSet.length, normPowerMomentV.getValueType());

		return multiply(M, normPowerMomentV);
	}

	public static Vector<Apfloat> createPowerMoments(String fileName, int n, int m, int dim, int l) throws Exception {
		Apfloat[] probabilities = IOFile.read(new File(TaskUtils.class.getResource(fileName).toURI()), Apfloat.class);

		List<Moment<Apfloat>> binomMoms = createBinomialMoments(probabilities, n, m, dim, l);
		Collection<Moment<Apfloat>> powerMoms = convertBinomMomToPowerMom(binomMoms);

		return toVector(powerMoms);
	}

	public static <T> LPSolution getMinCumProbMatrixElement(final Matrix<T> matrix, final Vector<T> vector, final Vector<T> f) throws MosekException {
		return LinearProgrammingEq.optimizeMin(matrix, vector, f);
	}

	public static <T> LPSolution getMaxCumProbMatrixElement(final Matrix<T> matrix, final Vector<T> vector, Vector<T> f) throws MosekException {
		return LinearProgrammingEq.optimizeMax(matrix, vector, f);
	}

	public static <T> LPSolution getMinCumProbMatrixElement(final Matrix<T> matrix, final Vector<T> vector, final Vector<T> f, final double e) throws MosekException {
		return LinearProgrammingIneq.optimizeMin(matrix, vector, f, e);
	}

	public static <T> LPSolution getMaxCumProbMatrixElement(final Matrix<T> matrix, final Vector<T> vector, Vector<T> f, final double e) throws MosekException {
		return LinearProgrammingIneq.optimizeMax(matrix, vector, f, e);
	}

	public static Vector<Apfloat> toVector(Collection<Moment<Apfloat>> moments) {

		Vector<Apfloat> v = new Vector<Apfloat>(moments.size(), Apfloat.class);

		int i = 0;
		for (Moment<Apfloat> moment : moments) {
			v.set(i, moment.moment);

			i++;
		}

		return v;
	}
}
