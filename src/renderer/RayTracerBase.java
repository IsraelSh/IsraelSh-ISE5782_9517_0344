package renderer;

import primitives.Color;
import primitives.Ray;
import scene.scene;

/**
 * RayTracerBase class
 */
public abstract class RayTracerBase {
    protected scene scene;

    /**
     *ctor for scene field
     */
    public RayTracerBase(scene scene){
        this.scene=scene;
    }

    /**
     * abstract method who gets ray and return the color
     */
    public abstract Color traceRay(Ray ray);
}