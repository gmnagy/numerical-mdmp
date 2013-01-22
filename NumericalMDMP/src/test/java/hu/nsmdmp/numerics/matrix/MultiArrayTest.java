package hu.nsmdmp.numerics.matrix;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import junit.framework.Assert;

import org.junit.Test;

public class MultiArrayTest {

	@Test
	public void testEqualsTrue() {

		MultiArray<Long> M1 = new MultiArray<Long>(new Long[][] { { 1l, 2l }, { 3l, 4l, 5l } });
		MultiArray<Long> M2 = new MultiArray<Long>(new Long[][] { { 1l, 2l }, { 3l, 4l, 5l } });

		assertTrue("", M1.equals(M2));
	}

	@Test
	public void testEqualsFalse() {

		MultiArray<Long> M1 = new MultiArray<Long>(new Long[][] { { 1l, 2l }, { 3l, 4l, 5l } });
		MultiArray<Long> M2 = new MultiArray<Long>(new Long[][] { { 1l, 2l }, { 3l, 4l, 6l } });

		assertFalse("", M1.equals(M2));
	}

	@Test
	public void test() {

		Long[][] MultiArray = new Long[][] { { 1l, 2l }, { 3l, 4l, 5l } };
		MultiArray<Long> M = new MultiArray<Long>(MultiArray);

		MultiArray<Long> expected = new MultiArray<Long>(new Long[][] { { 1l, 2l }, { 3l, 4l, 5l } });

		assertTrue("", M.equals(expected));

		MultiArray[0][0] = 0l;

		assertTrue("", M.equals(expected));
	}

	@Test
	public void testSetRow() {

		MultiArray<Long> M = new MultiArray<Long>(new Long[][] { { 1l, 2l }, { 3l, 4l, 5l } });
		M.setRow(1, new Long[] { 5l, 5l });

		MultiArray<Long> expected = new MultiArray<Long>(new Long[][] { { 1l, 2l }, { 5l, 5l } });

		Assert.assertEquals(expected, M);
	}

	@Test
	public void testSetValue() {

		MultiArray<Long> M = new MultiArray<Long>(new Long[][] { { 1l, 2l }, { 3l, 4l, 5l } });
		M.set(1, 1, 1l);

		MultiArray<Long> expected = new MultiArray<Long>(new Long[][] { { 1l, 2l }, { 3l, 1l, 5l } });

		Assert.assertEquals(expected, M);
	}
}
