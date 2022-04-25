package lighting;
import primitives.*;

/**
 * AmbientLight class
 */
public class AmbientLight {
    private Color intensity;

    /**
     *
     * @param iA
     * @param kA
     *  constructor build the ambient light intensity
     */
    public AmbientLight(Color iA, Double3 kA) {

        this.intensity = iA.scale(kA);
    }

    /**
     * default ctr
     */
    public AmbientLight() {
        this.intensity = Color.BLACK;
    }

    /**
     *
     * @return The ambient light intensity
     */
    public Color getIntensity() {
        return intensity;
    }
}