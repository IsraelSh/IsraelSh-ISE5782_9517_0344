package renderer;
import java.util.List;

import geometries.FlatGeometry;
import lighting.LightSource;
import primitives.Color;
import primitives.*;
import Scene.Scene;

import geometries.Intersectable.GeoPoint;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {
    private static final double EPS =0.1;

    /**
     * @param sc
     * Ctor using super class constructor
     */
    public RayTracerBasic(Scene sc) {
        super(sc);
    }

    /**
     * Implementation for the abstract method traceRay
     */
    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> intersectionsPoints = Scene.geometries.findGeoIntersections(ray);
        if (intersectionsPoints == null)
            return Scene.background;
        else
            return calcColor(ray.findClosestGeoPoint(intersectionsPoints), ray);
    }

    /**
     * Calculate the color of a certain point
     *
     * @param point
     * @return The color of the point (calculated with local effects)
     */
    public Color calcColor(GeoPoint point, Ray ray) {
        return Scene.ambientLight.getIntensity().add(point.geometry.getEmission()).add(calcLocalEffects(point, ray));
    }

    /**
     * Calculate the effects of lights
     *
     * @param intersection
     * @param ray
     * @return The color resulted by local effecrs calculation
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return Color.BLACK;
        int nShininess = intersection.geometry.getMaterial().nShininess;


        Double3 kd = intersection.geometry.getMaterial().kD;
        Double3 ks = intersection.geometry.getMaterial().kS;
        Color color = Color.BLACK;
        for (LightSource lightSource : Scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // checks if nl == nv
                if(unshaded(intersection,lightSource,l,n,nl,nv))
                {
                    Color lightIntensity = lightSource.getIntensity(intersection.point);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }

            }
        }
        return color;
    }

    private boolean unshaded(GeoPoint gp,LightSource lightSource,Vector l, Vector n, double nl,double nv)
    {
        Point point = gp.point;
        Vector lightDirection =l.scale(-1); //from the point to light source

        //double MaxDis = gp.point.distance(lightSource.)
        Vector epsVector = n.scale(nv<0 ? EPS : -EPS);
        Point pointRay = gp.point.add(epsVector);
        Ray lightRay = new Ray(gp.point,n,lightDirection);
        List<GeoPoint>  intersections = Scene.getGeometries().findGeoIntersections(lightRay);

        return intersections == null;
    }


    /**
     * Calculates diffusive light
     * @param kd
     * @param l
     * @param n
     * @param lightIntensity
     * @return The color of diffusive effects
     */
    private Color calcDiffusive(Double3 kd, Vector l, Vector n, Color lightIntensity) {
        double ln = alignZero(l.dotProduct(n));
        if (ln < 0)
            ln = ln * -1;
        return lightIntensity.scale(kd.scale(ln));
    }

    /**
     * Calculate specular light
     * @param ks
     * @param l
     * @param n
     * @param v
     * @param nShininess
     * @param lightIntensity
     * @return The color of specular reflection
     */
    private Color calcSpecular(Double3 ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        Vector r = l.subtract(n.scale(l.dotProduct(n) * 2));
        double vr = alignZero(v.scale(-1).dotProduct(r));
        if (vr < 0)
            vr = 0;
        vr = Math.pow(vr, nShininess);
        return lightIntensity.scale(ks.scale(vr));
    }
}