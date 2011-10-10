package hu.nsmdmp.moments;

import hu.nsmdmp.utils.Utils;

import java.util.Arrays;

import org.apfloat.Apfloat;

public class BinomialMoment {

	public final int[] alphas;

	public final Apfloat moment;

	public BinomialMoment(final int[] alphas, final Apfloat moment) {
		this.alphas = alphas;
		this.moment = moment;
	}

	@Override
	public String toString() {
		return String.format("BinomialMoment[key: %s; mom: %s]", Utils.arrayToString(alphas, ","), moment);
	}

	@Override
	public boolean equals(final Object obj) {

		if (!(obj instanceof BinomialMoment)) {
			return false;
		}

		BinomialMoment bm = (BinomialMoment) obj;

		if (!Arrays.equals(alphas, bm.alphas)) {
			return false;
		}

		if (!moment.equals(bm.moment)) {
			return false;
		}

		return true;
	}
}
