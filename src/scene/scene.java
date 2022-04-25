package scene;
import geometries.Geometries;
import primitives.Color;
import lighting.AmbientLight;


public class scene {

    public String name;

    public Color background = Color.BLACK;

    public AmbientLight ambientLight = new AmbientLight();

    public Geometries geometries = new Geometries();



    public scene(String name) {
        this.name = name;
    }

    public scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    public scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }
    public scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

}
