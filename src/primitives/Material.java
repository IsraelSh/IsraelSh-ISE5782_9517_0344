package primitives;

/* class Material
 */
public class Material {
    public Double3 kD = new Double3(0), kS = new Double3(0);
    public int nShininess = 0;

    /* set KD
     * @param kD
     * @return
             */
    public Material setKD(Double3 kD){
        this.kD = kD;
        return this;
    }

    /* set KD
     * @param kD
     * @return
             */
    public Material setKD(double kD){
        this.kD = new Double3(kD);
        return this;
    }

    /* set KS
     * @param kS
     * @return
             */
    public Material setKS(Double3 kS){
        this.kS = kS;
        return this;
    }

    /**
     * set KS
     * @param kS
     * @return
     */
    public Material setKS(double kS){
        this.kS = new Double3(kS);
        return this;
    }

    public Material setNShininess(int nShininess){
        this.nShininess = nShininess;
        return this;
    }
}