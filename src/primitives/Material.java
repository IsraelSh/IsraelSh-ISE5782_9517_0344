package primitives;

/* class Material
 */
public class Material {
    /**
     * fields for class material Attenuation diffuse, specular
     */
    public Double3 kD = new Double3(0), kS = new Double3(0), kT = new Double3(0),kR = new Double3(0);

    public int nShininess = 0;

    /* set KD
     * @param kD
     * @return
    */
    public Material setKD(double kD){
        this.kD = new Double3(kD);
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
    /**
     * @param kT the kT to set
     */
    public Material setkT(double kT) {
        this.kT = new Double3(kT);
        return this;
    }/**
     * @param kR the kR to set
     */
    public Material setkR(double kR) {
        this.kR = new Double3(kR);
        return this;
    }

    public Material setNShininess(int nShininess){
        this.nShininess = nShininess;
        return this;
    }
}