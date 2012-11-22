
public class SinCosLookup
{
    private static double[] sin, cos;
    
    static
    {
        SinCosLookup.sin = new double[360];
        SinCosLookup.cos = new double[360];
        
        for(int i = 0; i < 360; i++)
        {
            double rad = Math.toRadians(i);
            SinCosLookup.sin[i] = Math.sin(rad);
            SinCosLookup.cos[i] = Math.cos(rad);
        }
    }
        
    public static double getSin(int pos)
    {
        return SinCosLookup.sin[pos];
    }
    
    public static double getCos(int pos)
    {
        return SinCosLookup.cos[pos];
    }
}