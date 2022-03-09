package geometries;

import primitives.Point;

import java.util.Vector;

public class Plane implements Geometry{
    Vector normal;
    Point q0;
    public Plane(Vector normal2, Point q1){
        normal = new Vector(normal2);
        q0 = new Point(q1);
    }
    public Plane (Point p1,Point p2,Point p3) {

    }
    @Override
    public Vector getNormal(Point p){
        return null;
    }
}
