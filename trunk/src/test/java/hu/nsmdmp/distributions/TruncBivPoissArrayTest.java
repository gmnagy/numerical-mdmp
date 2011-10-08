package hu.nsmdmp.distributions;

import junit.framework.Assert;

import org.junit.Test;

public class TruncBivPoissArrayTest {

	@Test
	public void testGetTruncBivPoissArray() {
		Assert.assertEquals(0.00247875, TruncBivPoissArray.getTruncBivPoissArray(1, 2, 3, 4, 5)[0][0],0.00001);
		Assert.assertEquals(0.0091975, TruncBivPoissArray.getTruncBivPoissArray(1, 2, 3, 4, 5)[0][5],0.00001);
		Assert.assertEquals(0.00261688, TruncBivPoissArray.getTruncBivPoissArray(1, 2, 3, 4, 5)[4][0],0.00001);
		Assert.assertEquals(0.175374, TruncBivPoissArray.getTruncBivPoissArray(1, 2, 3, 4, 5)[4][5],0.00001);
		Assert.assertEquals(0.0450307, TruncBivPoissArray.getTruncBivPoissArray(1, 2, 3, 4, 5)[3][3],0.00001);
		Assert.assertEquals(5, TruncBivPoissArray.getTruncBivPoissArray(1, 2, 3, 4, 5).length,0.00001);
		Assert.assertEquals(6, TruncBivPoissArray.getTruncBivPoissArray(1, 2, 3, 4, 5)[0].length,0.00001);		
		//System.out.println(TruncBivPoissArray.getTruncBivPoissArray(1, 2, 3, 4, 5)[0].length);
	}
}
