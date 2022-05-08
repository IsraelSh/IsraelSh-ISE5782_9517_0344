package lighting;


import primitives.Color;

/* abstract class Light
 */
abstract class Light {
    private Color intensity;

    /* ctor
     * @param intensity
     */
    protected Light(Color intensity){
        this.intensity = intensity;
    }

    /**
     * get Intensity
     * @return
     */
    public Color getIntensity(){
        return intensity;
    }



}
