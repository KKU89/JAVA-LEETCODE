class Solution {
    public double[] convertTemperature(double celsius) {
        double [] kk =new double[2];
        double f =(celsius *9.0 /5.0)+32;
        double k= celsius + 273.15;
        kk[0]= k;
        kk[1]= f;
        return kk;
        
    }
}