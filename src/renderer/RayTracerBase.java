package renderer;

import primitives.Color;
import primitives.Ray;
import Scene.Scene;

/**
 * RayTracerBase class
 */
public abstract class RayTracerBase<scene> {
    protected  Scene Scene;

    /**
     *ctor for scene field
     */
    public RayTracerBase(Scene Scene){
        this.Scene = Scene;
    }

    /**
     * abstract method who gets ray and return the color
     */
    public abstract Color traceRay(Ray ray);
}