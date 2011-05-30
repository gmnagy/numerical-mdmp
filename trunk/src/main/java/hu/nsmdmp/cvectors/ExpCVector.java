package hu.nsmdmp.cvectors;

import hu.nsmdmp.utils.Precision;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

public class ExpCVector extends AbstractCVector {

	/**
	 * f(z1,z2) = Exp[z1/50 + z2/50 + z1z2/10000].
	 */
	@Override
	Apfloat function(final Apfloat[] variation) {
		if (variation.length != 2) {
			throw new RuntimeException("ExpCVector: z1,z2 kell!");
		}

		Apfloat z1 = variation[0];
		Apfloat z2 = variation[1];

		// (z1+z2)/50
		Apfloat a = z1.add(z2).divide(new Apfloat(50d, Precision.SCALE));
		// (z1*z2)/10000
		Apfloat b = z1.multiply(z2).divide(new Apfloat(10000d, Precision.SCALE));

		return ApfloatMath.exp(a.add(b));
	}
}
