package hu.nsmdmp.moments;

import java.util.List;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.google.common.collect.Lists;

final class SubSet {

	final List<Integer> set = Lists.newArrayList();

	SubSet() {
	}

	SubSet(int... items) {
		for (int item : items)
			set.add(item);
	}

	SubSet(SubSet... items) {
		for (SubSet item : items)
			set.addAll(item.set);
	}

	void add(int item) {
		set.add(item);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(set).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SubSet))
			return false;

		SubSet that = (SubSet) obj;

		return set.equals(that.set);
	}

	@Override
	public String toString() {
		return set.toString();
	}

}
