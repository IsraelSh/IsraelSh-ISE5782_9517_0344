package Scene;

import primitives.*;
import lighting.*;
import geometries.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Scene class
 */
public class Scene {

    public String name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = new AmbientLight();
    public Geometries geometries;
    public List<LightSource> lights = new LinkedList<>();

    /**
     * @param name
     * Ctor sets up the name and creates an empty collection of geometries
     */
    public Scene(String name) {
        this.name = name;
        geometries = new Geometries();
    }

    /**
     * set Background field
     * @param background
     * @return The scene object (this)
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * set AmbientLight field
     * @param ambientLight
     * @return The scene object (this)
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * set geometries field
     * @param geo
     * @return The scene object (this)
     */
    public Scene setGeometries(Geometries geo) {
        this.geometries = geo;
        return this;
    }

    public  Scene setLights (List<LightSource> lights ){
        this.lights = lights;
        return this;
    }
}
