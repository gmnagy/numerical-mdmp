package hu.nsmdmp;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.commons.math.fraction.BigFraction;
import org.apfloat.Apfloat;
import org.junit.Ignore;
import org.junit.Test;

public class NumberTest {

	private static final long scale = Apfloat.INFINITE;

	@Test
	@Ignore
	public void test1() {

		Apfloat a = new Apfloat(1.0 / 3.0, scale);
		System.out.println(a);

		Apfloat b = new Apfloat(1.0, scale).divide(new Apfloat(3.0, scale));
		System.out.println(b);

		BigDecimal c = new BigDecimal(1.0).divide(new BigDecimal(3.0), (int) scale, RoundingMode.HALF_UP);
		System.out.println(c);

		BigFraction d = new BigFraction(1.0).divide(new BigFraction(3.0));
		System.out.println(d);
		System.out.println(new Apfloat(d.bigDecimalValue(), scale));
	}

}
