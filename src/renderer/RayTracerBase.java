package renderer;

import primitives.Color;
import primitives.Ray;
import Scene.Scene;

/**
 * RayTracerBase class
 */
public abstract class RayTracerBase<scene> {
    protected  Scene scene;

    /**
     *ctor for scene field
     */
    public RayTracerBase(Scene scenet){
        scene = scenet;
    }

    /**
     * abstract method who gets ray and return the color
     */
    public abstract Color traceRay(Ray ray);
}