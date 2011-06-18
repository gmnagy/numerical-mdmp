package hu.nsmdmp.cvectors;

import hu.nsmdmp.matrixmath.MatrixMath;
import hu.nsmdmp.utils.Precision;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;


public class SumProbEx4CVector extends AbstractCVector {
	
	/**
	 * f(z1,z2) = 0 if z1+z2<6, 1 otherwise .
	 */
	@Override
	Apfloat function(final Apfloat[] variation) {
		if (variation.length != 2) {
			throw new RuntimeException("SumProbEx4CVector: z1,z2 kell!");
		}

		Apfloat z1 = variation[0];
		Apfloat z2 = variation[1];

		// (z1+z2)/50
		//Apfloat a = z1.add(z2).divide(new Apfloat(50d, Precision.SCALE));
		// (z1*z2)/10000
		//Apfloat b = z1.multiply(z2).divide(new Apfloat(10000d, Precision.SCALE));
		if (z1.add(z2).doubleValue()<6) return MatrixMath.ZERO;
		else return MatrixMath.ONE;
	}

	
}
