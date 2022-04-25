package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * RayTracerBase class
 */
public abstract class RayTracerBase {
    protected Scene scene;

    /**
     *ctor for scene field
     */
    public RayTracerBase(Scene scene){
        this.scene=scene;
    }

    /**
     * abstract method who gets ray and return the color
     */
    public abstract Color traceRay(Ray ray);
}