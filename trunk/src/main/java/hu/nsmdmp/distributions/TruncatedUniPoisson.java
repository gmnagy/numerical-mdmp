package hu.nsmdmp.distributions;


public class TruncatedUniPoisson {
	public static double getTruncatedUniPoisson(final double lambda, final int k, final int maxOccur){
		double result=0;
		double expPart=Math.exp(-lambda);
		if(k==maxOccur){
			result=1;
			for(int i=0; i<=k-1; i++){
				result=result-Math.pow(lambda,i)/factorial(i)*expPart;
			}
		}
		else{
			result=Math.pow(lambda,k)/factorial(k)*expPart;
		}
		return result;
	}

    public static long factorial( int n )
    {
        if( n <= 1 )     // base case
            return 1;
        else
            return n * factorial( n - 1 );
    }

	
	
}
