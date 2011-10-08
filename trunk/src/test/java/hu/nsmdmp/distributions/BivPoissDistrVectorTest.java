package hu.nsmdmp.distributions;

import org.junit.Assert;
import org.junit.Test;

public class BivPoissDistrVectorTest {

	@Test
	public void testGetBivPoissDistrVector() {
		double[] expected={0.00247875, 0.0049575, 0.0049575, 0.003305, 0.00261688, 0.00743626, 
				0.0173513, 0.01983, 0.0148725, 0.0137725, 0.0111544, 0.029745, 0.0384207, 
				0.0322238, 0.0349813, 0.0111544, 0.0334632, 0.0483357, 0.0450307, 0.0573829, 
				0.00836579, 0.027886, 0.0446175, 0.0458569, 0.0686406, 0.0091975, 0.0359583, 
				0.0678804, 0.0827529, 0.175374};
		
		double[] actual=BivPoissDistrVector.getBivPoissDistrVector(TruncBivPoissArray.getTruncBivPoissArray(1, 2, 3, 4, 5));
		
		//System.out.println(Arrays.toString(actual));
		Assert.assertArrayEquals(expected, actual, 0.00001);
	}

}
