package hu.nsmdmp.cvectors;

import org.apfloat.Apfloat;

public class CVector {

	public static ICVector getStairsCVector(final Apfloat[][] variations) {
		StairsCVector c = new StairsCVector();
		c.create(variations);

		return c;
	}
	
	public static ICVector getExpCVector(final Apfloat[][] variations) {
		ExpCVector c = new ExpCVector();
		c.create(variations);

		return c;
	}

	public static ICVector getSumProbEx4CVector(final Apfloat[][] variations) {
		SumProbEx4CVector c = new SumProbEx4CVector();
		c.create(variations);

		return c;
	}

}
