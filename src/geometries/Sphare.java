package geometries;

import primitives.*;

import java.util.LinkedList;
import java.util.List;
import static primitives.Util.*;

/**
 * Sphare
 * @author sendi pardes Israel Shlomo
 *
 */

public class Sphare extends Geometry {
    Point center;
    double radius;

    public Sphare(Point center, double radius) {
        super();
        if (Util.isZero(radius) || radius < 0)
            throw new IllegalArgumentException("Zero or negative radius");
        this.center = center;
        this.radius = radius;
    }

    @Override
    public Vector getNormal(Point p) {
        //the normal to sphere is the subtraction of the given point from the center. we get the normal vector
        return center.subtract(p).normalize();
    }

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Sphere [center=" + center + ", radius=" + radius + "]";
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray){
        double r = this.radius;

        // Special case: if point p0 == center, that mean that all we need to calculate
        // is the radios mult scalar with the direction, and add p0
        if (center.equals(ray.getP0())) {
            LinkedList<GeoPoint> result = new LinkedList<GeoPoint>();
            result.add(new GeoPoint(this, ray.getPoint(r)));
            return result;
        }

        Vector u = center.subtract(ray.getP0());
        double tm = u.dotProduct(ray.getDir());
        double d = Math.sqrt(alignZero(u.lengthSquared() - tm * tm));

        if (d >= r) //also In case the cut is tangent to the object still return null - d = r
            return null;

        double th = Math.sqrt(r * r - d * d);
        double t1 = tm + th;
        double t2 = tm - th;

        if(alignZero(t1) > 0 || alignZero(t2) > 0){
            LinkedList<GeoPoint> result = new LinkedList<GeoPoint>();
            if(alignZero(t1) > 0){
                Point p1 = ray.getPoint(t1);
                result.add(new GeoPoint(this, p1));
            }
            if(alignZero(t2) > 0){
                Point p2 = ray.getPoint(t2);
                result.add(new GeoPoint(this, p2));
            }
            return result;
        }
        else { //In case there are no intersections points
            return null;
        }
    }

}
