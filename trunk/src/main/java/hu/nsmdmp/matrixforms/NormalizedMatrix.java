package hu.nsmdmp.matrixforms;

import hu.nsmdmp.utils.MatrixOperations;

import org.apfloat.Apfloat;

public class NormalizedMatrix extends SimpleMatrix {

	NormalizedMatrix() {
	}

	@Override
	void create(final Apfloat[][] vectorSet, final int moment) {

		MatrixOperations.normalize(vectorSet);

		super.create(vectorSet, moment);
	}
}
