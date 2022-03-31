package geometries;

import primitives.*;

import java.util.List;

/**
 * Sphare
 * @author sendi pardes Israel Shlomo
 *
 */

public class Sphare implements Geometry{
    Point center;
    double radius;
    public Sphare(Point center,double radius) {
        super();
        if(Util.isZero(radius) || radius < 0 )
            throw new IllegalArgumentException("Zero or negative radius");
        this.center = center;
        this.radius = radius;
    }
    @Override
    public Vector getNormal(Point p){
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
    public List<Point> findIntsersections(Ray ray) {
        Point p0 = ray.getP0();
        Point O  = center;
        Vector v =ray.getDir();

        Vector U =O.subtract(p0);
        double tm =v.dotProduct(U);
        double d = Math.sqrt(U.lengthSquared()- tm*tm);
        if(d>=radius)
        {
            return null;
        }
        double th = Math.sqrt(radius*radius -d*d);
        double t1 =tm-th;
        double t2 = tm+th;

        if(t1>0 && t2 >0)
        {
            Point p1 =p0.add(v.scale(t1));
            Point p2 =p0.add(v.scale(t2));
            return List.of(p1,p2);
        }
        if(t1>0)
        {
            Point p1 =p0.add(v.scale(t1));
            return (List.of(p1));
        }
        if(t2>0)
        {
            Point p2 =p0.add(v.scale(t2));
            return (List.of(p2));
        }
        return null;
    }
}
