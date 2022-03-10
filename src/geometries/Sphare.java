package geometries;

import primitives.Point;
import primitives.Util;
import primitives.Vector;


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
        return null;
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
