package geometries;

import primitives.Point;
import primitives.Vector;


public class Plane implements Geometry{
    Vector normal;
    Point q0;
    public Plane(Vector normal2, Point q1){
        super();
        this.q0 = q0;
        this.normal =normal.normalize();
    }
    public Plane (Point p1,Point p2,Point p3) {
        super();
        this.normal=null;
        this.q0=p1;

    }



    @Override
    public String toString() {
        return "Plane [q0=" + q0.toString() + ", normal=" + normal.toString() + "]";
    }
    @Override
    public Vector getNormal(Point p){
        return null;
    }
}
