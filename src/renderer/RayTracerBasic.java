package renderer;

import java.util.List;

import lighting.DirectionalLight;
import lighting.LightSource;
import lighting.PointLight;
import lighting.SpotLight;
import primitives.Color;
import primitives.*;
import Scene.Scene;

import geometries.Intersectable.GeoPoint;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {

    private static final double INITIAL_K = 1.0;
    private static final double DELTA = 0.1;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;

    /**
     * @param sc Ctor using super class constructor
     */
    public RayTracerBasic(Scene sc) {
        super(sc);
    }

    /**
     * Implementation for the abstract method traceRay
     */
    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? Scene.background : calcColor(closestPoint, ray);
    }

    /**
     * @param ray
     * @return The closest intersection point
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = Scene.geometries.findGeoIntersections(ray);
        if (intersections == null)
            return null;
        return ray.findClosestGeoPoint(intersections);
    }

    /**
     * Calculate the color of a certain point
     *
     * @param point
     * @return The color of the point (calculated with local effects)
     */
    public Color calcColor(GeoPoint point, Ray ray) {
        return calcColor(point, ray, MAX_CALC_COLOR_LEVEL, new Double3(INITIAL_K)).add(Scene.ambientLight.getIntensity());
    }

    /**
     * Calculate the color of a certain point
     *
     * @param point
     * @return The color of the point (calculated with local effects)
     */
    public Color calcColor(GeoPoint point, Ray ray, int level, Double3 k) {
        Color color = point.geometry.getEmission();
        color = color.add(calcLocalEffects(point, ray, k));
        return 1 == level ? color : color.add(calcGlobalEffects(point, ray, level, k));
    }

    /**
     * calc Global Effects
     *
     * @param gp
     * @param ray
     * @param level
     * @param k
     * @return The color with the global effects
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();

        Double3 kkr = material.kR.product(k);
        if (!kkr.lowerThan(MIN_CALC_COLOR_K))
            color = calcGlobalEffect(constructReflectedRay(gp.geometry.getNormal(gp.point), gp.point, ray), level, material.kR, kkr);

        Double3 kkt = material.kT.product(k);
        ;
        if (!kkt.lowerThan(MIN_CALC_COLOR_K))
            color = color.add(calcGlobalEffect(constructRefractedRay(gp.geometry.getNormal(ray.getP0()), gp.point, ray), level, material.kT, kkt));

        return color;
    }

    /**
     * help func for calcGlobalEffects
     *
     * @param ray
     * @param level
     * @param kx
     * @param kkx
     * @return
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 kx, Double3 kkx) {
        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? Scene.background : calcColor(gp, ray, level - 1, kkx).scale(kx));
    }

    /**
     * Calculate the reflection ray
     *
     * @param n
     * @param point
     * @param inRay
     * @return The new ray after the reflection calculate
     */
    private Ray constructReflectedRay(Vector n, Point point, Ray inRay) {
        Vector v = inRay.getDir();
        Vector r = v.subtract(n.scale(alignZero(2 * (n.dotProduct(v)))));
        return new Ray(r.normalize(), point, n);
    }

    /**
     * Calculate the refracted ray
     *
     * @param n
     * @param point
     * @param inRay
     * @return The new ray refracted ray
     */
    private Ray constructRefractedRay(Vector n, Point point, Ray inRay) {
        return new Ray(inRay.getDir(), point, n);
    }

    /**
     * Calculate the effects of lights
     *
     * @param intersection
     * @param ray
     * @return The color resulted by local effecrs calculation
     */
//    private Color calcLocalEffects(GeoPoint intersection, Ray ray, Double3 k) {
//        Vector v = ray.getDir();
//        Vector n = intersection.geometry.getNormal(intersection.point);
//        double nv = alignZero(n.dotProduct(v));
//        if (nv == 0)
//            return Color.BLACK;
//        int nShininess = intersection.geometry.getMaterial().nShininess;
//
//        Double3 kd = intersection.geometry.getMaterial().kD;
//        Double3 ks = intersection.geometry.getMaterial().kS;
//        Color color = Color.BLACK;
//        for (LightSource lightSource : Scene.lights) {
//            Vector l = lightSource.getL(intersection.point);
//            double nl = alignZero(n.dotProduct(l));
//            if (nl * nv > 0) { // checks if sign(nl) == sing(nv)
//                //if (unshaded(lightSource, l, n, intersection))
//                Double3 ktr = transparency(lightSource, l, n, intersection);
//                if (!k.product(ktr).lowerThan(MIN_CALC_COLOR_K)) {
//                    Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
//                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
//                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
//                }
//            }
//        }
//        return color;
//    }
    /**
     * Calculate the Local effects of the lights in the area
     *
     * @param gp  the point
     * @param ray the ray from the camera
     * @return the added effects of the light on the point
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray,Double3 k) {
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        Color color = gp.geometry.getEmission();
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : Scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sign(nv)
                Double3 hitRate = hitPercentageColor(lightSource, gp, n);//the percentage of rays that intersect with the lightSource.
                Color iL = lightSource.getIntensity(gp.point);
                if (!hitRate.equals(new Double3(-1, -1, -1))) {
                    iL = iL.scale(hitRate);
                } else {
                    iL = iL.scale(transparency(lightSource, l, n, gp));
                }
                color = color.add(iL.scale(calcDiffusive(material, nl)),iL.scale(calcSpecular(material, n, l, nl, v)));
            }

        }
        return color;
    }

    /**
     * Calculates diffusive light
     *
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

    private Double3 calcDiffusive(Material m, double nl) {
        return m.kD.scale(nl > 0 ? nl : -nl);
    }

    /**
     * Calculate the specular factor
     *
     * @param mat the material of the geometry
     * @param n   Vector n
     * @param l   vector l
     * @param nl  dotProduct of n and l
     * @param v   Vector v
     * @return the specular factor
     */
    private Double3 calcSpecular(Material mat, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(nl * 2));
        double vr = alignZero(-v.dotProduct(r));
        if (vr <= 0) return Double3.ZERO;
        return mat.kS.scale(Math.pow(vr, mat.nShininess));
    }
    private Color calcSpecular(Double3 ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        Vector r = l.subtract(n.scale(l.dotProduct(n) * 2));
        double vr = alignZero(v.scale(-1).dotProduct(r));
        if (vr < 0)
            vr = 0;
        vr = Math.pow(vr, nShininess);
        return lightIntensity.scale(ks.scale(vr));
    }
    /**
     * Checks if there is no shade between a point and a light source
     *
     * @param l
     * @param n
     * @param gp
     * @return Boolean value if the unshaded check was successful
     */
    private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint gp) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
        Point point = gp.point.add(delta);
        Ray lightRay = new Ray(lightDirection, point);
        List<GeoPoint> intersections = Scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null)
            return true;

        double lightDistance = light.getDistance(gp.point);
        for (GeoPoint geop : intersections) {
            if (alignZero(geop.point.distance(gp.point) - lightDistance) <= 0)
                if (gp.geometry.getMaterial().kT.equals(Double3.ZERO))
                    return false;
        }
        return true; //in case all intersections are in between lightDistance and gp.
    }

    /**
     * Checks if there is no shade between a point and a light source
     *
     * @param ls
     * @param l
     * @param n
     * @param geoPoint
     * @return Double value if the transparency check was successful
     */
    private Double3 transparency(LightSource ls, Vector l, Vector n, GeoPoint geoPoint) {
        Vector lightDirection = l.scale(-1); // from point to light source

        Ray lightRay = new Ray(lightDirection, geoPoint.point, n);

        List<GeoPoint> intersections = Scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null)
            return new Double3(1.0);

        double lightDistance = ls.getDistance(geoPoint.point);
        Double3 ktr = new Double3(1.0);
        for (GeoPoint geop : intersections) {
            if (geoPoint.point.distance(geop.point)<lightDistance) {
                ktr = ktr.product(geop.geometry.getMaterial().kT);
                if (ktr.lowerThan(MIN_CALC_COLOR_K))
                    return Double3.ZERO;
            }
        }
        return ktr;
    }


    /**
     * Calculates the percentage of rays that hit the object,
     * from all the rays that were created by the points of hte light source.
     *
     * @param ls       the light source.
     * @param geoPoint the intersection point.
     * @return the percentage of rays that are heat by some object.
     */
    private Double3 hitPercentageColor(LightSource ls, GeoPoint geoPoint, Vector n) {
        if (ls instanceof DirectionalLight)
            return Double3.ONE;
        if (!(ls instanceof SpotLight)) {//means that this is the point light.
            ((PointLight) ls).initializePoints(geoPoint.point);//so need to initialize the points vector.
        }
        PointLight pl = (PointLight) ls;
        if (pl.getPoints() == null)//means that the light sours has no size thus we have to return the code that indicates so.
            return new Double3(-1, -1, -1);

        //if this is a relevant light source, and it has size, we are iterating over the points of the light source and averaging the transparency of all of them.
        Double3 average = Double3.ZERO;
        for (Point point : pl.getPoints()) {
            average = average.add(transparency(ls, geoPoint.point.subtract(point), n, geoPoint).reduce(pl.getNUMBER_OF_POINTS()));
        }

        return average;
    }
}


