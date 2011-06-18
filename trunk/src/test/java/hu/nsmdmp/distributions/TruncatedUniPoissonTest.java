package hu.nsmdmp.distributions;

import org.junit.Assert;
import org.junit.Test;


public class TruncatedUniPoissonTest {
	@Test
	public void getTrunkedUniPoissonTest1() {
		Assert.assertEquals(Math.exp(-2), TruncatedUniPoisson.getTruncatedUniPoisson(2,0,3), 0.001 );
		Assert.assertEquals(2*Math.exp(-2), TruncatedUniPoisson.getTruncatedUniPoisson(2,1,3), 0.001 );
		Assert.assertEquals(2*Math.exp(-2), TruncatedUniPoisson.getTruncatedUniPoisson(2,2,3), 0.001 );
		Assert.assertEquals(1.0, TruncatedUniPoisson.getTruncatedUniPoisson(2,3,3)+5*Math.exp(-2), 0.001 );	
		for(int i=90;i<=100; i++){
			System.out.print(TruncatedUniPoisson.getTruncatedUniPoisson(1,i,100));
		}
	}

}
