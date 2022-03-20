package geometries;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;

/**
 * Plane
 * @author sendi pardes Israel Shlomo
 *
 */

public class Plane implements Geometry{
    Vector normal;
    Point q0;
    /**
     * constructor who gets one point and  the normalize vector and creates a plain
     * @param q1
     * @param normal2
     */

    public Plane(Vector normal2, Point q1){
        this.q0 = q1;
        this.normal =normal2;
    }
    /**
     *constructor who gets 3 points and creates a plain
     * @param p1
     * @param p2
     * @param p3
     */

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
    /**
     *
     * @return the normalize vector
     */
    public Vector getNormal(){
        return normal;
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
