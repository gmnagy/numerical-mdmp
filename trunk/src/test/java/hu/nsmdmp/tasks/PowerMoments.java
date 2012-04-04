package hu.nsmdmp.tasks;

import hu.nsmdmp.matrix.Matrix;
import hu.nsmdmp.matrixfactory.MatrixFactory;

import org.junit.Test;

public class PowerMoments {

	@Test
	public void discreteMultivariateDistributions1() {
		int moment = 2;

		Matrix monomial = MatrixFactory.getMonomialMatrix(TaskUtils.createVectorSet(2, 3), moment);

		System.out.println(monomial);
	}

}
