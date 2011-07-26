package hu.nsmdmp.cvectors;

import hu.nsmdmp.ApfloatUtils;
import hu.nsmdmp.utils.Precision;

import org.apfloat.Apfloat;

class ProbSumNotLess15Vector extends AbstractCVector{

Apfloat limit=new Apfloat(15.0, Precision.SCALE);

	
	@Override
	Apfloat function(final Apfloat[] variation) {
		int n = variation.length;

		Apfloat sum=ApfloatUtils.ZERO;
		for (int i = 0; i < n; i++) {
			sum=sum.add(variation[i]);
			}
			if (sum.compareTo(limit) < 0) {
				return ApfloatUtils.ZERO;

		}

		return ApfloatUtils.ONE;
	}
	
}


