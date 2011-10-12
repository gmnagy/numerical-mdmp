package hu.nsmdmp.moments;

import hu.nsmdmp.utils.Converters;

import java.util.Arrays;

import org.apfloat.Apfloat;

public class Moment {

	public final int[] alphas;

	public final Apfloat moment;

	public Moment(final int[] alphas, final Apfloat moment) {
		this.alphas = alphas;
		this.moment = moment;
	}

	@Override
	public String toString() {
		return String.format("Moment[key: %s; mom: %s]", Converters.arrayToString(alphas, ","), moment);
	}

	@Override
	public boolean equals(final Object obj) {

		if (!(obj instanceof Moment)) {
			return false;
		}

		Moment bm = (Moment) obj;

		if (!Arrays.equals(alphas, bm.alphas)) {
			return false;
		}

		if (!moment.equals(bm.moment)) {
			return false;
		}

		return true;
	}
}
