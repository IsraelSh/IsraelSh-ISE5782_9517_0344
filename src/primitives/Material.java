package primitives;

/* class Material
 */
public class Material {
    /**
     * fields for class material Attenuation diffuse, specular
     */
    public Double3 kD = new Double3(0), kS = new Double3(0), kT = new Double3(0),kR = new Double3(0);

    public int nShininess = 0;
    public double diffuse =0;
    public double specular =0;

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
    public Material setKT(double kT) {
        this.kT = new Double3(kT);
        return this;
    }/**
     * @param kR the kR to set
     */
    public Material setKR(double kR) {
        this.kR = new Double3(kR);
        return this;
    }

    public Double3 getKT() {
        return kT;
    }
    public Double3 getKR() {
        return kR;
    }

    public Material setNShininess(int nShininess){
        this.nShininess = nShininess;
        return this;
    }
    /**
     * set the diffuse coefficient
     *
     * @param diffuse the diffuse coefficient
     * @return this
     */
    public Material setDiffuse(double diffuse){
        this.diffuse = diffuse;
        return this;
    }
    /**
     * set the specular coefficient
     *
     * @param specular the specular coefficient
     * @return this
     */
    public Material setSpecular(double specular){
        this.specular = specular;
        return this;
    }

}