package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Plane
 *
 * @author sendi pardes Israel Shlomo
 */

public class Plane implements Geometry {
    Vector normal;
    Point q0;

    /**
     * constructor who gets one point and  the normalize vector and creates a plain
     *
     * @param q1
     * @param normal2
     */

    public Plane(Vector normal2, Point q1) {
        this.q0 = q1;
        this.normal = normal2;
    }

    /**
     * constructor who gets 3 points and creates a plain
     *
     * @param p1
     * @param p2
     * @param p3
     */

    public Plane(Point p1, Point p2, Point p3) {
        this.q0 = p1;
        Vector v1 = (p2.subtract(p1));
        Vector v2 = (p3.subtract(p1));
        this.normal = v1.crossProduct(v2).normalize();
    }

    public Vector getNormal(Point p) {
        return normal;
    }

    @Override
    public String toString() {
        return "Plane [q0=" + q0.toString() + ", normal=" + normal.toString() + "]";
    }

    /**
     * @return the normalize vector
     */
    public Vector getNormal() {
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
        return this.normal.equals(other.normal) && this.q0.equals(other.q0);
    }


    @Override
    public List<Point> findIntersections(Ray ray) {
        Point P0= ray.getP0(); // according to the illustration P0 is the same point of the ray's P0 (that's why the definition))
        Vector v = ray.getDir(); // according to the illustration v is the same vector of the ray's vector (that's why the definition))

        if(q0.equals(P0)){ // if the ray starting from the plane it doesn't cut the plane at all
            return null; // so return null
        }

        Vector n = normal; // the normal to the plane

        double nv = n.dotProduct(v); // the formula's denominator of "t" (t =(n*(Q-P0))/nv)

        // ray is lying on the plane axis
        if (isZero(nv)){ // can't divide by zero (nv is the denominator)
            return null;
        }

        Vector Q0_P0 = q0.subtract(P0);
        double nP0Q0= alignZero(n.dotProduct(Q0_P0));

        // t should be bigger than 0
        if(isZero(nP0Q0)){
            return null;
        }

        double t =alignZero(nP0Q0 / nv);

        // t should be bigger than 0
        if(t<=0){
            return null;
        }

        return List.of(ray.getPoint(t));
    }



}
