package renderer;
import java.util.List;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.scene;

public class RayTracerBasic extends RayTracerBase {
    /**
     * @param sc
     * Ctor using super class constructor
     */
    public RayTracerBasic(scene sc) {
        super(sc);
    }

    /**
     * Implementation for the abstract method traceRay
     */
    @Override
    public Color traceRay(Ray ray) {
        List<Point> intersectionsPoints = scene.geometries.findIntersections(ray);
        if (intersectionsPoints == null)
            return scene.background;
        else
            return calcColor(ray.findClosestPoint(intersectionsPoints));
    }

    /**
     * Calculate the color of a certain point
     * @param point
     * @return The ambient light of the scene
     */
    private Color calcColor(Point point)
    {
        return scene.ambientLight.getIntensity();
    }
}
