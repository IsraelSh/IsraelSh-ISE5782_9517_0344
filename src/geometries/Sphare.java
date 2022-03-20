package geometries;

import primitives.Point;
import primitives.Util;
import primitives.Vector;

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
}
