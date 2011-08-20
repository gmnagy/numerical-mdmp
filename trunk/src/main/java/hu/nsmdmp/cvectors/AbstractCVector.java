package hu.nsmdmp.cvectors;

import hu.nsmdmp.vector.Vector;

import org.apfloat.Apfloat;

abstract class AbstractCVector {

	Vector create(final Apfloat[][] variations) {
		int n = variations.length;
		Vector C = new Vector(n);

		for (int i = 0; i < n; i++) {
			Apfloat d = function(variations[i]);
			C.set(i, d);
		}

		return C;
	}

	abstract Apfloat function(final Apfloat[] variation);
}
