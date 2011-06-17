package hu.nsmdmp.distributions;

import org.junit.Assert;
import org.junit.Test;


public class TrunkedUniPoissonTest {
	@Test
	public void getTrunkedUniPoissonTest1() {
		Assert.assertEquals(Math.exp(-2), TrunkedUniPoisson.getTrunkedUniPoisson(2,0,3), 0.001 );
		Assert.assertEquals(2*Math.exp(-2), TrunkedUniPoisson.getTrunkedUniPoisson(2,1,3), 0.001 );
		Assert.assertEquals(2*Math.exp(-2), TrunkedUniPoisson.getTrunkedUniPoisson(2,2,3), 0.001 );
		Assert.assertEquals(1.0, TrunkedUniPoisson.getTrunkedUniPoisson(2,3,3)+5*Math.exp(-2), 0.001 );	
	}

}
