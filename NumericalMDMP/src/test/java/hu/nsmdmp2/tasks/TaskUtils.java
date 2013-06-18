package hu.nsmdmp2.tasks;

import static hu.nsmdmp2.numerics.matrix.MatrixMath.multiply;
import static hu.nsmdmp2.polynomialmatrixfactory.MonomialToChebTMatrix.generateMonomialChebTTransformationMatrix;
import static hu.nsmdmp2.polynomialmatrixfactory.MonomialToChebUMatrix.generateMonomialChebUTransformationMatrix;
import static hu.nsmdmp2.tools.VectorNormalizationWithSet.normailzeByGergo;
import hu.nsmdmp2.mosek.LPSolution;
import hu.nsmdmp2.mosek.LinearProgrammingEq;
import hu.nsmdmp2.mosek.LinearProgrammingIneq;
import hu.nsmdmp2.mosek.PreciseLPCalc;
import hu.nsmdmp2.mosek.PreciseLPSolution;
import hu.nsmdmp2.numerics.Value;
import hu.nsmdmp2.numerics.matrix.SparseMatrix;
import mosek.MosekException;

public class TaskUtils {

	// public static Value[] normChebyUV(String fileName, int n, int m, int dim,
	// int l, Value[][] vectorSet) throws Exception {
	//
	// Value[] powerMomentV = createPowerMoments(fileName, n, m, dim, l);
	//
	// return normChebyUV(m, powerMomentV, vectorSet);
	// }

	public static Value[] normChebyUV(final int maxOrder, final Value[] powerV, final Value[][] vectorSet) {

		Class<? extends Comparable<?>> valueType = vectorSet[0][0].getType();

		Value[] normPowerMomentV = normailzeByGergo(vectorSet, maxOrder, powerV);

		Value[][] M = generateMonomialChebUTransformationMatrix(maxOrder, vectorSet.length, valueType);

		return multiply(M, normPowerMomentV);
	}

	// public static Value[] normChebyTV(String fileName, int n, int m, int dim,
	// int l, Value[][] vectorSet) throws Exception {
	//
	// Class<? extends Comparable<?>> valueType = vectorSet[0][0].getType();
	//
	// Value[] powerMomentV = createPowerMoments(fileName, n, m, dim, l);
	// Value[] normPowerMomentV = normailzeByGergo(vectorSet, m, powerMomentV);
	//
	// Value[][] M = generateMonomialChebTTransformationMatrix(m,
	// vectorSet.length, normPowerMomentV.getValueType());
	//
	// return MatrixMath.multiply(M, normPowerMomentV);
	// }

	public static Value[] normChebyTV(final int maxOrder, final Value[] powerV, final Value[][] vectorSet) {

		Class<? extends Comparable<?>> valueType = vectorSet[0][0].getType();

		Value[] normPowerMomentV = normailzeByGergo(vectorSet, maxOrder, powerV);
		Value[][] M = generateMonomialChebTTransformationMatrix(maxOrder, vectorSet.length, valueType);

		return multiply(M, normPowerMomentV);
	}

	// public static Value[] createPowerMoments(String fileName, int n, int m,
	// int dim, int l) throws Exception {
	// Apfloat[] probabilities = IOFile.read(new
	// File(TaskUtils.class.getResource(fileName).toURI()), Apfloat.class);
	//
	// List<Moment<Apfloat>> binomMoms = createBinomialMoments(probabilities, n,
	// m, dim, l);
	// Collection<Moment<Apfloat>> powerMoms =
	// convertBinomMomToPowerMom(binomMoms);
	//
	// return toVector(powerMoms);
	// }

	public static LPSolution getMinCumProbMatrixElement(final Value[][] M, final Value[] V, final Value[] f) throws MosekException {
		return LinearProgrammingEq.optimizeMin(M, V, f);
	}

	public static LPSolution getMinCumProbMatrixElement(final SparseMatrix SM, final Value[] V, final Value[] f) throws MosekException {
		return LinearProgrammingEq.optimizeMin(SM, V, f);
	}

	public static LPSolution getMaxCumProbMatrixElement(final Value[][] M, final Value[] V, Value[] f) throws MosekException {
		return LinearProgrammingEq.optimizeMax(M, V, f);
	}

	public static LPSolution getMaxCumProbMatrixElement(final SparseMatrix SM, final Value[] V, Value[] f) throws MosekException {
		return LinearProgrammingEq.optimizeMax(SM, V, f);
	}

	public static PreciseLPSolution getPrecMinCumProbMatrixElement(final Value[][] M, final Value[] V, final Value[] f) throws MosekException {
		return PreciseLPCalc.optimizeMin(M, V, f);
	}

	public static PreciseLPSolution getPrecMaxCumProbMatrixElement(final Value[][] M, final Value[] V, Value[] f) throws MosekException {
		return PreciseLPCalc.optimizeMax(M, V, f);
	}

	public static LPSolution getMinCumProbMatrixElement(final Value[][] M, final Value[] V, final Value[] f, final double e) throws MosekException {
		return LinearProgrammingIneq.optimizeMin(M, V, f, e);
	}

	public static LPSolution getMaxCumProbMatrixElement(final Value[][] M, final Value[] V, Value[] f, final double e) throws MosekException {
		return LinearProgrammingIneq.optimizeMax(M, V, f, e);
	}

	// public static Vector<Apfloat> toVector(Collection<Moment<Apfloat>>
	// moments) {
	//
	// Vector<Apfloat> v = new Vector<Apfloat>(moments.size(), Apfloat.class);
	//
	// int i = 0;
	// for (Moment<Apfloat> moment : moments) {
	// v.set(i, moment.moment);
	//
	// i++;
	// }
	//
	// return v;
	// }
}
