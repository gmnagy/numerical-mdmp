package hu.nsmdmp.distribution;

import org.apfloat.Apfloat;

abstract class AbstractDistribution {

	private Apfloat[] distributionA;

	private double[] distributionD;

	void create(final Apfloat[][] variations) {
		int n = variations.length;

		distributionA = new Apfloat[n];
		distributionD = new double[n];

		for (int i = 0; i < n; i++) {
			Apfloat d = getDistributionItem(variations[i]);
			distributionA[i] = d;
			distributionD[i] = d.doubleValue();
		}
	}

	abstract Apfloat getDistributionItem(final Apfloat[] variation);

	public Apfloat[] getDistributionA() {
		return distributionA;
	}

	public double[] getDistributionD() {
		return distributionD;
	}
}
