package lighting;
import primitives.Color;


public class AmbientLight {

    private Color intensity;
    public AmbientLight() {
        intensity= Color.BLACK;
    }

    public AmbientLight(Color intensity, double kA) {
        intensity= intensity.scale(kA);
    }

    public Color getIntensity() {
        return intensity;
    }
}
