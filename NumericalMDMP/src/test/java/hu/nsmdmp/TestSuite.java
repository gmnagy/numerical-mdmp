package hu.nsmdmp;

import hu.nsmdmp.mosek.LinearProgrammingEqTest;
import hu.nsmdmp.mosek.PreciseLPCalcTest;
import hu.nsmdmp.numerics.matrix.GetSubMatrixTest;
import hu.nsmdmp.numerics.matrix.LUDecompositionTest;
import hu.nsmdmp.numerics.matrix.MultiArrayTest;
import hu.nsmdmp.numerics.matrix.QRDecompositionTest;
import hu.nsmdmp.numerics.matrix.math.MultiplicationTest;
import hu.nsmdmp.numerics.matrix.math.SparseMatrixTest;
import hu.nsmdmp.polynomialmatrixfactory.DoubleChebyshevTMatrixTest;
import hu.nsmdmp.polynomialmatrixfactory.DoubleMonomialMatrixTest;
import hu.nsmdmp.polynomialmatrixfactory.DoubleMonomialToChebTMatrixTest;
import hu.nsmdmp.polynomialmatrixfactory.DoubleNormalizedMonomialMatrixTest;
import hu.nsmdmp.tools.SetNormalizationTest;
import hu.nsmdmp.tools.SetVariationTest;
import hu.nsmdmp.tools.TotalOrderTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	
	// tools
	SetNormalizationTest.class, SetVariationTest.class, TotalOrderTest.class,
	
	// polynomialmatrixfactory
	DoubleChebyshevTMatrixTest.class, DoubleMonomialMatrixTest.class, DoubleMonomialToChebTMatrixTest.class, 
	DoubleNormalizedMonomialMatrixTest.class,
	
	// matrix
	MultiArrayTest.class, MultiplicationTest.class, SparseMatrixTest.class, GetSubMatrixTest.class, 
	LUDecompositionTest.class, QRDecompositionTest.class,
	
	// mosek
	LinearProgrammingEqTest.class, PreciseLPCalcTest.class
	
	})
public class TestSuite {

}
