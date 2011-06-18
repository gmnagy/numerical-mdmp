package hu.nsmdmp.distributions;

public class BivPoissDistrVector {
	public static double[] getBivPoissDistrVector(double[][]  truncBivPoissArray){
		int u1=truncBivPoissArray.length;
		int u2=truncBivPoissArray[0].length;		
		double[] result=new double[u1*u2];
		int i=0;
		for(int i2=0; i2<u2; i2++){
			for(int i1=0;i1<u1;i1++){
				result[i]=truncBivPoissArray[i1][i2];
				i++;
			}
		}
		return result;
	}

}
