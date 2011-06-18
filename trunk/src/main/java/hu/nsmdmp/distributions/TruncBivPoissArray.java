package hu.nsmdmp.distributions;

public class TruncBivPoissArray {
	public static double[][] getTruncBivPoissArray(final double lambda, final double lambda1, final double lambda2,
			final int maxOccur1,final int maxOccur2 ){
		double[][] result=new double[maxOccur1+1][maxOccur2+1];
		int maxOccur=Math.max(maxOccur1, maxOccur2);
		for(int i=0; i<=maxOccur; i++){
			for(int i2=0; i2<=maxOccur2;i2++){
				for(int i1=0; i1<=maxOccur1; i1++){
					result[Math.min(i+i1,maxOccur1)][Math.min(i+i2,maxOccur2)]=
						result[Math.min(i+i1,maxOccur1)][Math.min(i+i2,maxOccur2)]+
						TruncatedUniPoisson.getTruncatedUniPoisson(lambda, i, maxOccur)*
						TruncatedUniPoisson.getTruncatedUniPoisson(lambda1, i1, maxOccur1)*
						TruncatedUniPoisson.getTruncatedUniPoisson(lambda2, i2, maxOccur2);
				}
			}
		}
		return result;
		
	}


}
