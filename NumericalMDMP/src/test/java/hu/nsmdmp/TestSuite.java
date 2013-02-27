package hu.nsmdmp;

import hu.nsmdmp.moments.ArrayCombinationTest;
import hu.nsmdmp.moments.MultivariateMomentsTest;
import hu.nsmdmp.moments.MultivariateMomentsWithPermutationTest;
import hu.nsmdmp.mosek.LinearProgrammingEqTest;
import hu.nsmdmp.mosek.PreciseLPCalcTest;
import hu.nsmdmp.numerics.matrix.SubMatrixTest;
import hu.nsmdmp.numerics.matrix.InverseTest;
import hu.nsmdmp.numerics.matrix.LUDecompositionTest;
import hu.nsmdmp.numerics.matrix.MultiArrayTest;
import hu.nsmdmp.numerics.matrix.QRDecompositionTest;
import hu.nsmdmp.numerics.matrix.SubVectorTest;
import hu.nsmdmp.numerics.matrix.math.MultiplicationTest;
import hu.nsmdmp.numerics.matrix.math.SparseMatrixTest;
import hu.nsmdmp.polynomialmatrixfactory.DoubleChebyshevTMatrixTest;
import hu.nsmdmp.polynomialmatrixfactory.DoubleMonomialMatrixTest;
import hu.nsmdmp.polynomialmatrixfactory.DoubleMonomialToChebTMatrixTest;
import hu.nsmdmp.polynomialmatrixfactory.DoubleMonomialToChebUMatrixTest;
import hu.nsmdmp.polynomialmatrixfactory.DoubleNormalizedMonomialMatrixTest;
import hu.nsmdmp.specialvectors.CumProbPoissonTest;
import hu.nsmdmp.specialvectors.DiscreteTest;
import hu.nsmdmp.tools.FactorialTest;
import hu.nsmdmp.tools.SetNormalizationTest;
import hu.nsmdmp.tools.SetVariationTest;
import hu.nsmdmp.tools.SubSequencesGeneratorTest;
import hu.nsmdmp.tools.TotalOrderTest;
import hu.nsmdmp.tools.VectorNormalizationWithSetTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	
	// tools
	SetNormalizationTest.class, SetVariationTest.class, TotalOrderTest.class, SubSequencesGeneratorTest.class,
	VectorNormalizationWithSetTest.class, FactorialTest.class,
	
	// polynomialmatrixfactory
	DoubleChebyshevTMatrixTest.class, DoubleMonomialMatrixTest.class, DoubleMonomialToChebTMatrixTest.class, 
	DoubleNormalizedMonomialMatrixTest.class, DoubleMonomialToChebUMatrixTest.class,
	
	// matrix
	MultiArrayTest.class, MultiplicationTest.class, SparseMatrixTest.class, SubMatrixTest.class, 
	LUDecompositionTest.class, QRDecompositionTest.class, InverseTest.class, SubVectorTest.class,
	
	// mosek
	LinearProgrammingEqTest.class, PreciseLPCalcTest.class,
	
	// moments
	ArrayCombinationTest.class, MultivariateMomentsTest.class, MultivariateMomentsWithPermutationTest.class,
	
	// specialvectors
	CumProbPoissonTest.class, DiscreteTest.class
	
	})
public class TestSuite {

}
