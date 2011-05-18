package hu.nsmdmp.matrices;

import hu.nsmdmp.matrixmath.MatrixMath;

import org.apfloat.Apfloat;

public class NormalizedMatrix extends SimpleMatrix {

	NormalizedMatrix() {
	}

	@Override
	void create(final Apfloat[][] vectorSet, final int maxOrder) {

		MatrixMath.normalize(vectorSet);

		super.create(vectorSet, maxOrder);
	}
}
