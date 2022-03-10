package geometries;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;



public class Plane implements Geometry{
    Vector normal;
    Point q0;
    public Plane(Vector normal2, Point q1){
        this.q0 = q1;
        this.normal =normal2;
    }
    public Plane (Point p1,Point p2,Point p3) {
        this.q0=p1;
        Vector v1 = (p2.subtract(p3));
        Vector v2 = (p1.subtract(p3));
        this.normal = v1.crossProduct(v2).normalize();
    }

    public Vector getNormal(Point p){
        return null;
    }


    @Override
    public String toString() {
        return "Plane [q0=" + q0.toString() + ", normal=" + normal.toString() + "]";
    }
    @Override
    public Vector getNormal(){
        return null;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Ray))
            return false;
        Plane other = (Plane) obj;
        return this.normal.equals(other.normal)&&this.q0.equals(other.q0);
    }
}
