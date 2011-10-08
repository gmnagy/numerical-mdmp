package hu.nsmdmp.cvectors;

import hu.nsmdmp.vector.Vector;

import java.util.List;

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

	Vector create(final List<Apfloat[]> variations) {
		int n = variations.size();
		Vector C = new Vector(n);

		int i = 0;
		for (Apfloat[] variation : variations) {
			Apfloat d = function(variation);
			C.set(i, d);

			i++;
		}

		return C;
	}

	abstract Apfloat function(final Apfloat[] variation);
}
