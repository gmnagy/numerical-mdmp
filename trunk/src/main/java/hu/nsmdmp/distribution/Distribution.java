package hu.nsmdmp.distribution;

import org.apfloat.Apfloat;

public final class Distribution {

	public static Uniform uniformDistribution(final Apfloat[][] variations) {
		Uniform uniform = new Uniform();
		uniform.create(variations);

		return uniform;
	}
}
