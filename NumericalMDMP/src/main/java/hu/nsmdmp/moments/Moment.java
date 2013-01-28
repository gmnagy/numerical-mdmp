package hu.nsmdmp.moments;

import java.util.Arrays;

public class Moment<T> {

	public final int[] alphas;

	public final T moment;

	public Moment(final int[] alphas, final T moment) {
		this.alphas = alphas;
		this.moment = moment;
	}

	@Override
	public String toString() {
		return String.format("Moment[key: %s; mom: %s]", Arrays.toString(alphas), moment);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(final Object obj) {

		if (!(obj instanceof Moment)) {
			return false;
		}

		Moment<T> bm = (Moment<T>) obj;

		if (!Arrays.equals(alphas, bm.alphas)) {
			return false;
		}

		if (!moment.equals(bm.moment)) {
			return false;
		}

		return true;
	}
}
